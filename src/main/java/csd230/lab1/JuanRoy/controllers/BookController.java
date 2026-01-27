package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.entities.BookEntity;
import csd230.lab1.JuanRoy.entities.CartEntity;
import csd230.lab1.JuanRoy.repositories.BookEntityRepository;
import csd230.lab1.JuanRoy.repositories.CartEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookEntityRepository bookRepository;

    @Autowired
    private CartEntityRepository cartRepository;

    @GetMapping
    public String getAllBook(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "/lists/bookList";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "/details/bookDetails";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookEntity());
        return "adds/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute BookEntity book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "edits/editBook";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute BookEntity updatedBook) {
        BookEntity existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setName(updatedBook.getName());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setCopies(updatedBook.getCopies());
            bookRepository.save(existingBook);
        }
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id, RedirectAttributes redirectAttributes) {
        CartEntity cart = cartRepository.findById(1L).orElse(null);
        BookEntity book = bookRepository.findById(id);

        // Check if in cart
        if (cart != null && book != null && cart.getProducts().contains(book)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete: Item is in the cart!");
            return "redirect:/books";
        }

        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            // This catches the Foreign Key error if it's in an Order
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete: Item is linked to a completed Order!");
        }

        return "redirect:/books";
    }

    @PostMapping("/addToCart/{id}")
    public String addBookToCart(@PathVariable Long id) {
        CartEntity cart = cartRepository.findById(1L).orElse(null);
        BookEntity book = bookRepository.findById(id).orElse(null);
        if(cart != null && book != null && book.getCopies() != 0) {
            cart.addProduct(book);
            cartRepository.save(cart);
        }
        return "redirect:/books";
    }
}

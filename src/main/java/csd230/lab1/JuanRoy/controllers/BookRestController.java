package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.controllers.exceptions.BookNotFoundException;
import csd230.lab1.JuanRoy.entities.BookEntity;
import csd230.lab1.JuanRoy.entities.MagazineEntity;
import csd230.lab1.JuanRoy.repositories.BookEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book REST API", description = "JSON API for managing books")
@RestController
@RequestMapping("/api/rest/books")
@CrossOrigin(origins = "*")
public class BookRestController {
    private final BookEntityRepository bookRepository;

    public BookRestController(BookEntityRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Operation(summary = "Get all books as JSON")
    @ApiResponse(responseCode = "200", description = "Book list fetched successfully")
    @ApiResponse(responseCode = "404", description = "Books not found")
    @GetMapping
    public List<BookEntity> all() {
        return bookRepository.findAll();
    }

    @Operation(summary = "Get a single book by ID")
    @ApiResponse(responseCode = "200", description = "Book fetched successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public BookEntity getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Book created")
    @ApiResponse(responseCode = "400", description = "Bad request - invalid payload")
    @PostMapping
    public BookEntity newBook(@RequestBody BookEntity newBook) {
        return bookRepository.save(newBook);
    }

    @Operation(summary = "Update or Replace a book")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @ApiResponse(responseCode = "400", description = "Bad request - invalid payload")
    @PutMapping("/{id}")
    public BookEntity replaceBook(@RequestBody BookEntity newBook, @PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setName(newBook.getName());
                    book.setPrice(newBook.getPrice());
                    book.setCopies(newBook.getCopies());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });
    }

    @Operation(summary = "Delete a book")
    @ApiResponse(responseCode = "200", description = "Book deleted successfully")
    @ApiResponse(responseCode = "204", description = "Book deleted (no content)")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        BookEntity book = bookRepository.findById(id);
        bookRepository.deleteById(id);
        return "Book with the ID: " + book.getId() + " has been deleted";
    }
}

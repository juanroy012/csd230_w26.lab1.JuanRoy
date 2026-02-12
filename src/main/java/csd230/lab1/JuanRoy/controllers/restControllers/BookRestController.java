package csd230.lab1.JuanRoy.controllers.restControllers;

import csd230.lab1.JuanRoy.controllers.exceptions.BookNotFoundException;
import csd230.lab1.JuanRoy.controllers.exceptions.NotBookException;
import csd230.lab1.JuanRoy.entities.BookEntity;
import csd230.lab1.JuanRoy.entities.ProductEntity;
import csd230.lab1.JuanRoy.repositories.BookEntityRepository;
import csd230.lab1.JuanRoy.repositories.ProductEntityRepository;
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
    private final ProductEntityRepository productRepository;

    public BookRestController(BookEntityRepository bookRepository, ProductEntityRepository productRepository) {
        this.bookRepository = bookRepository;
        this.productRepository = productRepository;
    }

    @Operation(summary = "Get all books as JSON")
    @ApiResponse(responseCode = "200", description = "Book list fetched successfully")
    @GetMapping
    public List<BookEntity> all() {
        return bookRepository.findAll();
    }

    @Operation(summary = "Get a single book by ID")
    @ApiResponse(responseCode = "200", description = "Book fetched successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @ApiResponse(responseCode = "422", description = "Item is not a book")
    @GetMapping("/{id}")
    public BookEntity getBook(@PathVariable Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        if (!(product instanceof BookEntity)) {
            throw new NotBookException(id);
        };
        return (BookEntity) product;
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
    @ApiResponse(responseCode = "422", description = "Item is not a Book")
    @PutMapping("/{id}")
    public BookEntity replaceBook(@RequestBody BookEntity newBook, @PathVariable Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        if (!(product instanceof BookEntity)) {
            throw new NotBookException(id);
        };
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
    @ApiResponse(responseCode = "422", description = "Item is not a Book")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        if (!(product instanceof BookEntity)) {
            throw new NotBookException(id);
        };
        bookRepository.deleteById(id);
        return "Book with the ID: " + id + " has been deleted";
    }
}

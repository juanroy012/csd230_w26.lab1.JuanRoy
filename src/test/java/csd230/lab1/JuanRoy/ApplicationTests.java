package csd230.lab1.JuanRoy;

import csd230.lab1.JuanRoy.entities.*;
import csd230.lab1.JuanRoy.nicheEntities.HandheldConsoleEntity;
import csd230.lab1.JuanRoy.nicheEntities.HomeConsoleEntity;
import csd230.lab1.nicheEntities.*;
import csd230.lab1.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Entity Test Suite")
class ProductEntityTestSuite {

    @Nested
    @DisplayName("Ticket Entity Tests")
    class TicketEntityTests {
        private TicketEntity ticket;

        @BeforeEach
        void setUp() {
            ticket = new TicketEntity("Concert Ticket", 99.99);
        }

        @Test
        @DisplayName("Test ticket creation with constructor")
        void testTicketCreation() {
            assertEquals("Concert Ticket", ticket.getDescription());
            assertEquals(99.99, ticket.getPrice());
        }

        @Test
        @DisplayName("Test ticket setters")
        void testSetters() {
            ticket.setDescription("Movie Ticket");
            ticket.setPrice(15.50);

            assertEquals("Movie Ticket", ticket.getDescription());
            assertEquals(15.50, ticket.getPrice());
        }

        @Test
        @DisplayName("Test sellItem method output")
        void testSellItem() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            ticket.sellItem();

            assertTrue(outContent.toString().contains("Selling Ticket: Concert Ticket for $99.99"));
            System.setOut(System.out);
        }

        @Test
        @DisplayName("Test toString method")
        void testToString() {
            String result = ticket.toString();
            assertTrue(result.contains("Concert Ticket"));
            assertTrue(result.contains("99.99"));
        }
    }

    @Nested
    @DisplayName("Book Entity Tests")
    class BookEntityTests {
        private BookEntity book;

        @BeforeEach
        void setUp() {
            book = new BookEntity("Java Programming", 49.99, 10, "John Doe");
        }

        @Test
        @DisplayName("Test book creation")
        void testBookCreation() {
            assertEquals("Java Programming", book.getTitle());
            assertEquals(49.99, book.getPrice());
            assertEquals(10, book.getCopies());
            assertEquals("John Doe", book.getAuthor());
        }

        @Test
        @DisplayName("Test selling book with stock available")
        void testSellItemWithStock() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            book.sellItem();

            assertEquals(9, book.getCopies());
            assertTrue(outContent.toString().contains("Sold 'Java Programming'. Remaining copies: 9"));
            System.setOut(System.out);
        }

        @Test
        @DisplayName("Test selling book without stock")
        void testSellItemOutOfStock() {
            book.setCopies(0);
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            book.sellItem();

            assertEquals(0, book.getCopies());
            assertTrue(outContent.toString().contains("Cannot sell 'Java Programming'. Out of stock."));
            System.setOut(System.out);
        }

        @Test
        @DisplayName("Test book setters")
        void testSetters() {
            book.setAuthor("Jane Smith");
            book.setTitle("Python Programming");
            book.setPrice(39.99);
            book.setCopies(15);

            assertEquals("Jane Smith", book.getAuthor());
            assertEquals("Python Programming", book.getTitle());
            assertEquals(39.99, book.getPrice());
            assertEquals(15, book.getCopies());
        }
    }

    @Nested
    @DisplayName("Magazine Entity Tests")
    class MagazineEntityTests {
        private MagazineEntity magazine;
        private LocalDate issueDate;

        @BeforeEach
        void setUp() {
            issueDate = LocalDate.of(2024, 1, 15);
            magazine = new MagazineEntity("Tech Monthly", 9.99, 50, 100, issueDate);
        }

        @Test
        @DisplayName("Test magazine creation")
        void testMagazineCreation() {
            assertEquals("Tech Monthly", magazine.getTitle());
            assertEquals(9.99, magazine.getPrice());
            assertEquals(50, magazine.getCopies());
            assertEquals(100, magazine.getOrderQty());
            assertEquals(issueDate, magazine.getCurrentIssue());
        }

        @Test
        @DisplayName("Test magazine setters")
        void testSetters() {
            LocalDate newDate = LocalDate.of(2024, 2, 15);
            magazine.setOrderQty(200);
            magazine.setCurrentIssue(newDate);

            assertEquals(200, magazine.getOrderQty());
            assertEquals(newDate, magazine.getCurrentIssue());
        }

        @Test
        @DisplayName("Test selling magazine")
        void testSellItem() {
            int initialCopies = magazine.getCopies();
            magazine.sellItem();
            assertEquals(initialCopies - 1, magazine.getCopies());
        }
    }

    @Nested
    @DisplayName("Disc Magazine Entity Tests")
    class DiscMagEntityTests {
        private DiscMagEntity discMag;

        @BeforeEach
        void setUp() {
            LocalDate issueDate = LocalDate.of(2024, 1, 15);
            discMag = new DiscMagEntity("Gaming Magazine", 14.99, 30, 50, issueDate, true);
        }

        @Test
        @DisplayName("Test disc magazine creation")
        void testDiscMagCreation() {
            assertEquals("Gaming Magazine", discMag.getTitle());
            assertEquals(14.99, discMag.getPrice());
            assertEquals(30, discMag.getCopies());
            assertEquals(50, discMag.getOrderQty());
            assertTrue(discMag.isHasDisc());
        }

        @Test
        @DisplayName("Test hasDisc setter")
        void testHasDiscSetter() {
            discMag.setHasDisc(false);
            assertFalse(discMag.isHasDisc());
        }

        @Test
        @DisplayName("Test toString contains disc info")
        void testToString() {
            String result = discMag.toString();
            assertTrue(result.contains("disc=true"));
        }
    }

    @Nested
    @DisplayName("Cart Entity Tests")
    class CartEntityTests {
        private CartEntity cart;
        private TicketEntity ticket;
        private BookEntity book;

        @BeforeEach
        void setUp() {
            cart = new CartEntity();
            ticket = new TicketEntity("Concert", 50.0);
            book = new BookEntity("Title", 25.0, 5, "Author");
        }

        @Test
        @DisplayName("Test empty cart creation")
        void testEmptyCartCreation() {
            assertNotNull(cart.getProducts());
            assertTrue(cart.getProducts().isEmpty());
        }
    }

    @Nested
    @DisplayName("Home Console Entity Tests")
    class HomeConsoleEntityTests {
        private HomeConsoleEntity console;

        @BeforeEach
        void setUp() {
            console = new HomeConsoleEntity("PlayStation 5", "Sony", 499.99, 10, "4K");
        }

        @Test
        @DisplayName("Test home console creation")
        void testConsoleCreation() {
            assertEquals("PlayStation 5", console.getName());
            assertEquals("Sony", console.getManufacturer());
            assertEquals(499.99, console.getPrice());
            assertEquals(10, console.getQuantity());
            assertEquals("4K", console.getMaxResolution());
        }

        @Test
        @DisplayName("Test selling console with stock")
        void testSellItemWithStock() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            console.sellItem();

            assertEquals(9, console.getQuantity());
            assertTrue(outContent.toString().contains("Sold 'PlayStation 5'. Remaining quantities: 9"));
            System.setOut(System.out);
        }

        @Test
        @DisplayName("Test selling console without stock")
        void testSellItemOutOfStock() {
            console.setQuantity(0);
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            console.sellItem();

            assertEquals(0, console.getQuantity());
            assertTrue(outContent.toString().contains("Cannot sell 'PlayStation 5'. Out of stock."));
            System.setOut(System.out);
        }

        @Test
        @DisplayName("Test console setters")
        void testSetters() {
            console.setMaxResolution("8K");
            console.setQuantity(20);

            assertEquals("8K", console.getMaxResolution());
            assertEquals(20, console.getQuantity());
        }

        @Test
        @DisplayName("Test equals method")
        void testEquals() {
            HomeConsoleEntity console2 = new HomeConsoleEntity("PlayStation 5", "Sony", 499.99, 10, "4K");
            HomeConsoleEntity console3 = new HomeConsoleEntity("Xbox", "Microsoft", 499.99, 10, "4K");

            assertEquals(console, console2);
            assertNotEquals(console, console3);
        }

        @Test
        @DisplayName("Test hashCode method")
        void testHashCode() {
            HomeConsoleEntity console2 = new HomeConsoleEntity("PlayStation 5", "Sony", 499.99, 10, "4K");
            assertEquals(console.hashCode(), console2.hashCode());
        }
    }

    @Nested
    @DisplayName("Handheld Console Entity Tests")
    class HandheldConsoleEntityTests {
        private HandheldConsoleEntity handheld;

        @BeforeEach
        void setUp() {
            handheld = new HandheldConsoleEntity("Nintendo Switch", "Nintendo", 299.99, 15, 6);
        }

        @Test
        @DisplayName("Test handheld console creation")
        void testHandheldCreation() {
            assertEquals("Nintendo Switch", handheld.getName());
            assertEquals("Nintendo", handheld.getManufacturer());
            assertEquals(299.99, handheld.getPrice());
            assertEquals(15, handheld.getQuantity());
            assertEquals(6, handheld.getBatteryLifeHours());
        }

        @Test
        @DisplayName("Test battery life setter")
        void testBatteryLifeSetter() {
            handheld.setBatteryLifeHours(8);
            assertEquals(8, handheld.getBatteryLifeHours());
        }

        @Test
        @DisplayName("Test selling handheld console")
        void testSellItem() {
            int initialQty = handheld.getQuantity();
            handheld.sellItem();
            assertEquals(initialQty - 1, handheld.getQuantity());
        }

        @Test
        @DisplayName("Test equals method")
        void testEquals() {
            HandheldConsoleEntity handheld2 = new HandheldConsoleEntity("Nintendo Switch", "Nintendo", 299.99, 15, 6);
            HandheldConsoleEntity handheld3 = new HandheldConsoleEntity("Steam Deck", "Valve", 399.99, 10, 8);

            assertEquals(handheld, handheld2);
            assertNotEquals(handheld, handheld3);
        }
    }

    @Nested
    @DisplayName("Product Entity Base Tests")
    class ProductEntityTests {

        @Test
        @DisplayName("Test product ID generation")
        void testIdGeneration() {
            TicketEntity product = new TicketEntity("Test", 10.0);
            product.setId(1L);
            assertEquals(1L, product.getId());
        }

        @Test
        @DisplayName("Test product equals method")
        void testProductEquals() {
            TicketEntity product1 = new TicketEntity("Test", 10.0);
            TicketEntity product2 = new TicketEntity("Test", 10.0);

            product1.setId(1L);
            product2.setId(1L);

            assertEquals(product1, product2);
        }
    }
}
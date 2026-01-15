package csd230.lab1;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.nicheEntities.HandheldConsoleEntity;
import csd230.lab1.nicheEntities.HomeConsoleEntity;
import csd230.lab1.pojos.SaleableItem;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.ProductEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Application  implements CommandLineRunner {
    private final ProductEntityRepository productRepository;
    private final CartEntityRepository cartRepository;

    public Application(ProductEntityRepository productRepository,
                       CartEntityRepository cartRepository
    ) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Number number = faker.number();
        com.github.javafaker.Book fakeBook = faker.book();
        String name = cm.productName();
        String description = cm.material();
        String priceString = faker.commerce().price();

        BookEntity book = new BookEntity(
                fakeBook.title(),
                Double.parseDouble(priceString),
                10,
                fakeBook.author());
        ;

        MagazineEntity magazine = new MagazineEntity(
                faker.lorem().word() + " Magazine",
                12.99,
                20,
                50,
                LocalDate.now()
        );

        Random random = new Random();
        TicketEntity ticket = new TicketEntity(
                "This is a ticket for cool event # " + Math.abs(random.nextInt()),
                number.randomDouble(2, 10, 100)
        );

        TicketEntity ticket2 = new TicketEntity(
                "This is a ticket for cool event # " + Math.abs(random.nextInt()),
                number.randomDouble(2, 10, 100)
        );



        HomeConsoleEntity homeConsole = new HomeConsoleEntity("PlayStation 5", "Sony", 799.99, 30, "4K");

        HandheldConsoleEntity handheldConsole = new HandheldConsoleEntity("Switch 2", "Nintendo", 399.99, 50, 3);

        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        CartEntity cart2 = new CartEntity();


        cart.addProduct(book);
        cart.addProduct(magazine);
        cart.addProduct(ticket);
        cart2.addProduct(homeConsole);
        cart2.addProduct(handheldConsole);
        cart2.addProduct(ticket2);

        cartRepository.save(cart);
        cartRepository.save(cart2);

        cart2.getProducts().forEach((SaleableItem::sellItem));

        List<ProductEntity> allProducts = productRepository.findAll();

        for (ProductEntity p : allProducts) {
            System.out.println(p.toString());
        }
        List<CartEntity> allCarts = cartRepository.findAll();
        for (CartEntity c : allCarts) {
            System.out.println(c.toString());
            for (ProductEntity p : c.getProducts()) {
                System.out.println(p.toString());
            }
        }

    }

}
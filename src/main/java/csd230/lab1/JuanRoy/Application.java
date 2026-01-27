package csd230.lab1.JuanRoy;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.JuanRoy.entities.*;
import csd230.lab1.JuanRoy.nicheEntities.HandheldConsoleEntity;
import csd230.lab1.JuanRoy.nicheEntities.HomeConsoleEntity;
import csd230.lab1.JuanRoy.pojos.SaleableItem;
import csd230.lab1.JuanRoy.repositories.CartEntityRepository;
import csd230.lab1.JuanRoy.repositories.ProductEntityRepository;
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

        for(int i = 0; i < 5; i++) {
            BookEntity book = new BookEntity(
                    fakeBook.title(),
                    Double.parseDouble(faker.commerce().price()),
                    10,
                    fakeBook.author());
            ;
            cart.addProduct(book);
        }

        cartRepository.save(cart);

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
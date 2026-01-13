package csd230.lab1;
import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.pojos.Cart;
import csd230.lab1.pojos.HandheldConsole;
import csd230.lab1.pojos.Magazine;
import csd230.lab1.pojos.Product;
import csd230.lab1.repositories.CartEntityRepository;
import csd230.lab1.repositories.ProductEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

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
        String name=cm.productName();
        String description=cm.material();
        String priceString = faker.commerce().price();

        BookEntity  book = new BookEntity(
                fakeBook.title(),
                Double.parseDouble(priceString),
                10,
                fakeBook.author());
        ;

        MagazineEntity magazine = new csd230.lab1.entities.MagazineEntity(
                faker.lorem().word() + " Magazine",
                12.99,
                20,
                50,
                java.time.LocalDateTime.now()
        );

        HomeConsoleEntity homeConsole = new HomeConsoleEntity("Sony", 799.99, 30, "4K");

        HandheldConsoleEntity handheldConsole = new HandheldConsoleEntity("Nintendo", 399.99, 50, 3);

        CartEntity cart=new CartEntity();
        cartRepository.save(cart);

        CartEntity cart2 = new CartEntity();


        cart.addProduct(book);
        cart.addProduct(magazine);
        cart2.addProduct(homeConsole);
        cart2.addProduct(handheldConsole);

        cartRepository.save(cart);
        cartRepository.save(cart2);


        List<ProductEntity> allProducts = productRepository.findAll();

        for(ProductEntity p : allProducts) {
            System.out.println(p.toString());
        }
        List<CartEntity> allCarts = cartRepository.findAll();
        for(CartEntity c : allCarts) {
            System.out.println(c.toString());
            for(ProductEntity p : c.getProducts()) {
                System.out.println(p.toString());
            }
        }

    }

}
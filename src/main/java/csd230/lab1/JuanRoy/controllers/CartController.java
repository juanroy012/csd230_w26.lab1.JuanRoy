package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.entities.*;
import csd230.lab1.JuanRoy.pojos.Product;
import csd230.lab1.JuanRoy.pojos.SaleableItem;
import csd230.lab1.JuanRoy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartEntityRepository cartRepository;
    @Autowired
    private BookEntityRepository bookRepository;
    @Autowired
    private OrderEntityRepository orderRepository;

    // 1. View the contents of the cart
    @GetMapping
    public String viewCart(Model model) {
        // HARDCODED ID: In a real app, this comes from the Session or SecurityContext
        Long defaultCartId = 1L;

        // Find cart with ID 1, or create a temporary empty one if not found
        CartEntity cart = cartRepository.findById(defaultCartId)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setId(defaultCartId);
                    return cartRepository.save(newCart); // Save it so it exists
                });
        model.addAttribute("cart", cart);
        return "details/cartDetails";
    }
    // 2. Add a specific book to the cart
    @PostMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if (cart != null && book != null) {
            cart.addProduct(book); // Uses the helper method in CartEntity
            cartRepository.save(cart); // Updates the Join Table
        }
        return "redirect:/books"; // Send them back to the shopping list
    }

    // 3. Remove item from cart
    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {
        Long defaultCartId = 1L;
        CartEntity cart = cartRepository.findById(defaultCartId).orElse(null);
        BookEntity book = bookRepository.findById(bookId).orElse(null);
        if(cart != null && book != null) {
            cart.getProducts().remove(book);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutMenu(Model model) {
        OrderEntity order = new OrderEntity();

        CartEntity cart = cartRepository.findById(1L).orElse(null);

        Set<ProductEntity> products = new LinkedHashSet<>();

        double totalPrice = 0;

        for(ProductEntity product: cart.getProducts()) {
            if (product != null) {
                totalPrice += product.getPrice();
                products.add(product);
            }
            order.setProducts(products);
            totalPrice = Math.floor(totalPrice*100)/100;
        };

        if (totalPrice != 0) {
            order.setTotalAmount(totalPrice);
        }

        model.addAttribute("order", order);

        return "/details/checkoutDetails";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute OrderEntity order) {
        CartEntity cart = cartRepository.findById(1L).orElse(null);

        cart.getProducts().forEach((SaleableItem::sellItem));
        cart.getProducts().removeAll(cart.getProducts());
        orderRepository.save(order);

        return "redirect:/books";
    }

    @GetMapping("/orders")
    public String orderDetails(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "/lists/orderList";
    }

    @GetMapping("/order/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        Set<ProductEntity> products = order.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("order", order);
        return "/details/orderDetails";
    }
}



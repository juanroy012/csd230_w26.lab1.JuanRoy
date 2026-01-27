package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.repositories.CartEntityRepository;
import csd230.lab1.JuanRoy.repositories.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productEntityRepository.findAll());
        return "/lists/productList";
    }

}

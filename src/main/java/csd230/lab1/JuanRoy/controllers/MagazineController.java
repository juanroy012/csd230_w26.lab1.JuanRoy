package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.entities.MagazineEntity;
import csd230.lab1.JuanRoy.repositories.CartEntityRepository;
import csd230.lab1.JuanRoy.repositories.MagazineEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

    @Autowired
    private MagazineEntityRepository magazineEntityRepository;

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @GetMapping
    public String getAllMagazine(Model model) {
        model.addAttribute("magazines", magazineEntityRepository.findAll());
        return "/lists/magazineList";
    }

    @GetMapping("/{id}")
    public String getMagazineById(@PathVariable Long id, Model model) {
        MagazineEntity magazine = magazineEntityRepository.findById(id).orElse(null);
        model.addAttribute("magazine", magazine);
        return "/details/magazineDetails";
    }
}

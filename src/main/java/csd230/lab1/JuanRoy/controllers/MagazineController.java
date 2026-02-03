package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.entities.CartEntity;
import csd230.lab1.JuanRoy.entities.MagazineEntity;
import csd230.lab1.JuanRoy.repositories.CartEntityRepository;
import csd230.lab1.JuanRoy.repositories.MagazineEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

    @Autowired
    private MagazineEntityRepository magazineRepository;

    @Autowired
    private CartEntityRepository cartRepository;

    @GetMapping
    public String getAllMagazine(Model model, HttpServletRequest request) {
        model.addAttribute("magazines", magazineRepository.findAll());
        model.addAttribute("currentUri", request.getRequestURI());
        return "/lists/magazineList";
    }

    @GetMapping("/{id}")
    public String getMagazineById(@PathVariable Long id, Model model, HttpServletRequest request) {
        MagazineEntity magazine = magazineRepository.findById(id).orElse(null);
        model.addAttribute("magazine", magazine);
        model.addAttribute("currentUri", request.getRequestURI());
        return "/details/magazineDetails";
    }

    @GetMapping("/add")
    public String addMagazineForm(Model model) {
        model.addAttribute("magazine", new MagazineEntity());
        return "adds/addMagazine";
    }

    @PostMapping("/add")
    public String addMagazine(@ModelAttribute MagazineEntity magazine) {
        magazineRepository.save(magazine);
        return "redirect:/magazines";
    }

    @GetMapping("/edit/{id}")
    public String editMagazineForm(@PathVariable Long id, Model model) {
        MagazineEntity magazine = magazineRepository.findById(id).orElse(null);
        model.addAttribute("magazine", magazine);
        return "edits/editMagazine";
    }

    @PostMapping("/edit/{id}")
    public String editMagazine(@PathVariable Long id, @ModelAttribute MagazineEntity updatedMagazine) {
        MagazineEntity existingMagazine = magazineRepository.findById(id).orElse(null);
        if (existingMagazine != null) {
            existingMagazine.setName(updatedMagazine.getName());
            existingMagazine.setCurrentIssue(updatedMagazine.getCurrentIssue());
            existingMagazine.setOrderQty((updatedMagazine.getOrderQty()));
            existingMagazine.setCopies(updatedMagazine.getCopies());
            magazineRepository.save(existingMagazine);
        }
        return "redirect:/magazines";
    }

    @PostMapping("/delete/{id}")
    public String deleteMagazine(@PathVariable long id, RedirectAttributes redirectAttributes) {
        List<CartEntity> carts = cartRepository.findAll();
        MagazineEntity magazine = magazineRepository.findById(id);

        for(CartEntity cart: carts) {
            cart.getProducts().remove(magazine);
        }

        try {
            magazineRepository.deleteById(id);
        } catch (Exception e) {
            // This catches the Foreign Key error if it's in an Order
            redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete: Item is linked to a completed Order!");
        }

        return "redirect:/magazines";
    }
}

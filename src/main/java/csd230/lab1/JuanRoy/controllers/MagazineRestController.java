package csd230.lab1.JuanRoy.controllers;

import csd230.lab1.JuanRoy.controllers.exceptions.MagazineNotFoundException;
import csd230.lab1.JuanRoy.entities.MagazineEntity;
import csd230.lab1.JuanRoy.repositories.MagazineEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="Magazine REST API", description="JSON API for managing magazines")
@RestController
@RequestMapping("/api/rest/magazines")
@CrossOrigin(origins = "*")
public class MagazineRestController {
    private final MagazineEntityRepository magazineRepository;

    public MagazineRestController(MagazineEntityRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @Operation(summary = "Get all magazines")
    @ApiResponse(responseCode = "200", description = "Magazine list fetched successfully")
    @ApiResponse(responseCode = "404", description = "Magazines not found")
    @GetMapping
    public List<MagazineEntity> all() {
        return magazineRepository.findAll();
    }

    @Operation(summary = "Get magazine by Id")
    @ApiResponse(responseCode = "200", description = "Magazine fetched successfully")
    @ApiResponse(responseCode = "404", description = "Magazine not found")
    @GetMapping("/{id}")
    public MagazineEntity getMagazine(@PathVariable Long id) {
        return magazineRepository.findById(id)
                .orElseThrow(() -> new MagazineNotFoundException(id));
    }

    @Operation(summary = "Create a new magazine")
    @ApiResponse(responseCode = "201", description = "Magazine successfully created")
    @ApiResponse(responseCode = "400", description = "Bad request - invalid payload")
    @PostMapping
    public MagazineEntity newMagazine (@RequestBody MagazineEntity newMagazine) {
        return magazineRepository.save(newMagazine);
    }

    @Operation(summary = "Update or replace a magazine")
    @ApiResponse(responseCode = "200", description = "Magazine updated successfully")
    @ApiResponse(responseCode = "404", description = "Magazine not found")
    @ApiResponse(responseCode = "400", description = "Bad request - invalid payload")
    @PutMapping("/{id}")
    public MagazineEntity updateMagazine (@PathVariable Long id, @RequestBody MagazineEntity updatedMagazine) {
        return magazineRepository.findById(id)
                .map(magazine -> {
                    magazine.setName(updatedMagazine.getName());
                    magazine.setOrderQty(updatedMagazine.getOrderQty());
                    magazine.setPrice(updatedMagazine.getPrice());
                    magazine.setCopies(updatedMagazine.getCopies());
                    magazine.setCurrentIssue(updatedMagazine.getCurrentIssue());
                    return magazineRepository.save(magazine);
                })
                .orElseGet(() -> {
                    updatedMagazine.setId(id);
                    return magazineRepository.save(updatedMagazine);
                });
    }

    @Operation(summary = "Delete a magazine")
    @ApiResponse(responseCode = "200", description = "Magazine deleted successfully")
    @ApiResponse(responseCode = "204", description = "Magazine deleted (no content)")
    @ApiResponse(responseCode = "404", description = "Magazine not found")
    @DeleteMapping("/{id}")
    public String deleteMagazine(@PathVariable long id) {
        MagazineEntity magazine = magazineRepository.findById(id);
        magazineRepository.deleteById(id);
        return "Magazine with the ID: " + magazine.getId() + " has been deleted";
    }
}

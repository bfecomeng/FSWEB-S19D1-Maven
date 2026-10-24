package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.PlantResponse;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import com.workintech.s18d2.validations.PlantValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// context-path /workintech olduğu için gerçek adres: /workintech/vegetable
@RestController
@RequestMapping("/vegetable")
public class VegetableController {

    private final VegetableService vegetableService;

    @Autowired
    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    // [GET] price'a göre artan
    @GetMapping
    public List<Vegetable> getByPriceAsc() {
        return vegetableService.getByPriceAsc();
    }

    // [GET] /desc -> price'a göre azalan
    @GetMapping("/desc")
    public List<Vegetable> getByPriceDesc() {
        return vegetableService.getByPriceDesc();
    }

    // [GET] /{id}
    @GetMapping("/{id}")
    public Vegetable getById(@PathVariable Long id) {
        PlantValidation.checkId(id);
        return vegetableService.getById(id);
    }

    // [POST] id'ye göre insert ya da update
    @PostMapping
    public Vegetable save(@Valid @RequestBody Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }

    // [GET] /name/{name} -> name içinde geçen kayıtlar (testlerin beklediği yol)
    @GetMapping("/name/{name}")
    public List<Vegetable> searchByName(@PathVariable String name) {
        PlantValidation.checkName(name);
        return vegetableService.searchByName(name);
    }

    // [POST] /{name} -> README'deki yol, aynı işi yapar
    @PostMapping("/{name}")
    public List<Vegetable> searchByNamePost(@PathVariable String name) {
        return searchByName(name);
    }

    // [DELETE] /{id} -> Görev 4: silinen obje + başarı mesajı
    @DeleteMapping("/{id}")
    public PlantResponse<Vegetable> delete(@PathVariable Long id) {
        PlantValidation.checkId(id);
        Vegetable deleted = vegetableService.delete(id);
        return new PlantResponse<>("Vegetable başarıyla silindi", deleted);
    }
}

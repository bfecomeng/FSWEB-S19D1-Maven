package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.PlantResponse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import com.workintech.s18d2.validations.PlantValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// context-path /workintech olduğu için gerçek adres: /workintech/fruit
@RestController
@RequestMapping("/fruit")
public class FruitController {

    private final FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    // [GET] price'a göre artan
    @GetMapping
    public List<Fruit> getByPriceAsc() {
        return fruitService.getByPriceAsc();
    }

    // [GET] /desc -> price'a göre azalan
    @GetMapping("/desc")
    public List<Fruit> getByPriceDesc() {
        return fruitService.getByPriceDesc();
    }

    // [GET] /{id}
    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Long id) {
        PlantValidation.checkId(id);
        return fruitService.getById(id);
    }

    // [POST] id'ye göre insert ya da update
    @PostMapping
    public Fruit save(@Valid @RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }

    // [GET] /name/{name} -> name içinde geçen kayıtlar (testlerin beklediği yol)
    @GetMapping("/name/{name}")
    public List<Fruit> searchByName(@PathVariable String name) {
        PlantValidation.checkName(name);
        return fruitService.searchByName(name);
    }

    // [POST] /{name} -> README'deki yol, aynı işi yapar
    @PostMapping("/{name}")
    public List<Fruit> searchByNamePost(@PathVariable String name) {
        return searchByName(name);
    }

    // [DELETE] /{id} -> Görev 4: silinen obje + başarı mesajı
    @DeleteMapping("/{id}")
    public PlantResponse<Fruit> delete(@PathVariable Long id) {
        PlantValidation.checkId(id);
        Fruit deleted = fruitService.delete(id);
        return new PlantResponse<>("Fruit başarıyla silindi", deleted);
    }
}

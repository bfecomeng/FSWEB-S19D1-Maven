package com.workintech.s18d2.services;

import com.workintech.s18d2.repository.VegetableRepository;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VegetableServiceImpl implements VegetableService {

    private final VegetableRepository repository;

    // Constructor injection ile DAO bağımlılığı
    @Autowired
    public VegetableServiceImpl(VegetableRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Vegetable> getByPriceAsc() {
        return repository.getByPriceAsc();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        return repository.getByPriceDesc();
    }

    @Override
    public Vegetable getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PlantException(
                        "Vegetable with given id does not exist: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        // id varsa ve kayıt mevcutsa update, yoksa yeni kayıt olarak insert
        if (vegetable.getId() != null && !repository.existsById(vegetable.getId())) {
            vegetable.setId(null);
        }
        return repository.save(vegetable);
    }

    @Override
    public Vegetable delete(Long id) {
        Vegetable existing = getById(id);
        repository.delete(existing);
        return existing;
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        return repository.searchByName(name);
    }
}

package com.workintech.s18d2.services;

import com.workintech.s18d2.repository.FruitRepository;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository repository;

    // Constructor injection ile DAO bağımlılığı
    @Autowired
    public FruitServiceImpl(FruitRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return repository.getByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return repository.getByPriceDesc();
    }

    @Override
    public Fruit getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PlantException(
                        "Fruit with given id does not exist: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Fruit save(Fruit fruit) {
        // id varsa ve kayıt mevcutsa update, yoksa yeni kayıt olarak insert
        if (fruit.getId() != null && !repository.existsById(fruit.getId())) {
            fruit.setId(null);
        }
        return repository.save(fruit);
    }

    @Override
    public Fruit delete(Long id) {
        Fruit existing = getById(id);
        repository.delete(existing);
        return existing;
    }

    @Override
    public List<Fruit> searchByName(String name) {
        return repository.searchByName(name);
    }
}

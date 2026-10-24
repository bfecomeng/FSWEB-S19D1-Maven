package com.workintech.s18d2.validations;

import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.http.HttpStatus;

public class PlantValidation {

    private PlantValidation() {
    }

    public static void checkId(Long id) {
        if (id == null || id < 0) {
            throw new PlantException("Id 0'dan küçük olamaz: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new PlantException("Name boş olamaz", HttpStatus.BAD_REQUEST);
        }
    }
}

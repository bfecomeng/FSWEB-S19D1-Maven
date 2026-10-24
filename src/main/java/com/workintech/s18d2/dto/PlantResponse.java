package com.workintech.s18d2.dto;

/**
 * Görev 4: başarılı işlemde hem objeyi hem de bir mesajı döner.
 */
public record PlantResponse<T>(String message, T data) {
}

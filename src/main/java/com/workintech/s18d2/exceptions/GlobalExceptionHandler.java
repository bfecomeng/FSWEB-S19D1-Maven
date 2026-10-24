package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * Tüm hatalar tek merkezden yönetilir; controller sınıflarında hata yakalama yoktur.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bizim fırlattığımız hatalar (negatif id, bulunamayan id vb.)
    @ExceptionHandler(PlantException.class)
    public ResponseEntity<PlantErrorResponse> handlePlantException(PlantException ex) {
        log.error("PlantException: {}", ex.getMessage());
        return build(ex.getHttpStatus(), ex.getMessage());
    }

    // Insert/Update'te eksik veya hatalı alan (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PlantErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("Validation error: {}", message);
        return build(HttpStatus.BAD_REQUEST, message);
    }

    // Boş / bozuk JSON gövdesi veya geçersiz enum değeri
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<PlantErrorResponse> handleUnreadable(HttpMessageNotReadableException ex) {
        log.error("Unreadable request body: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Request body eksik veya hatalı formatta");
    }

    // /fruits/abc gibi sayı olmayan id
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<PlantErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.error("Type mismatch: {}", ex.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Geçersiz parametre: " + ex.getName());
    }

    // Beklenmeyen tüm diğer hatalar
    @ExceptionHandler(Exception.class)
    public ResponseEntity<PlantErrorResponse> handleException(Exception ex) {
        log.error("Unexpected error", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<PlantErrorResponse> build(HttpStatus status, String message) {
        return new ResponseEntity<>(
                new PlantErrorResponse(status.value(), message, System.currentTimeMillis()), status);
    }
}

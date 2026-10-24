package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository sayesinde tüm CRUD işlemleri (save, findById, findAll, delete...) hazır gelir
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {

    // Ücrete göre azalan sırada
    @Query("SELECT v FROM Vegetable v ORDER BY v.price DESC")
    List<Vegetable> getByPriceDesc();

    // Ücrete göre artan sırada
    @Query("SELECT v FROM Vegetable v ORDER BY v.price ASC")
    List<Vegetable> getByPriceAsc();

    // name içinde verilen ifade geçen tüm kayıtlar (büyük/küçük harf duyarsız)
    @Query("SELECT v FROM Vegetable v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Vegetable> searchByName(@Param("name") String name);
}

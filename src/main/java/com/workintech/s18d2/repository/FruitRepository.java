package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository sayesinde tüm CRUD işlemleri (save, findById, findAll, delete...) hazır gelir
public interface FruitRepository extends JpaRepository<Fruit, Long> {

    // Ücrete göre azalan sırada
    @Query("SELECT f FROM Fruit f ORDER BY f.price DESC")
    List<Fruit> getByPriceDesc();

    // Ücrete göre artan sırada
    @Query("SELECT f FROM Fruit f ORDER BY f.price ASC")
    List<Fruit> getByPriceAsc();

    // name içinde verilen ifade geçen tüm kayıtlar (büyük/küçük harf duyarsız)
    @Query("SELECT f FROM Fruit f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Fruit> searchByName(@Param("name") String name);
}

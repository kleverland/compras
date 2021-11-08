package com.kleverland.compras.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kleverland.compras.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {

}

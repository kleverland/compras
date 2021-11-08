package com.kleverland.compras.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kleverland.compras.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Integer> {

}

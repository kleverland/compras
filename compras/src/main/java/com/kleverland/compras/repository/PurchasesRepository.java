package com.kleverland.compras.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kleverland.compras.model.Purchases;

public interface PurchasesRepository  extends JpaRepository<Purchases, Integer> {

}

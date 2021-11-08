package com.kleverland.compras.dao;

import org.springframework.stereotype.Repository;

import com.kleverland.compras.model.Purchases;

@Repository
public class PurchasesDAO {

	public Purchases listarCompras() {
		
		Purchases purchases = new Purchases();
		
		return purchases;
	}
}

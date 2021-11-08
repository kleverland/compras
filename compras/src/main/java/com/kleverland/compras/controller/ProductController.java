package com.kleverland.compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kleverland.compras.dao.PurchasesDAO;
import com.kleverland.compras.model.Product;
import com.kleverland.compras.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor
@Api("Produto")
public class ProductController {
	
	private final ProductRepository repository;

	@Autowired
	PurchasesDAO purchasesDAO;
	
	@ApiOperation("Lista os produtos disponíveis.")
	@GetMapping("/produtos")
	public List<Product> listProdutos() {
	  return repository.findAll();
	}
	
	@PutMapping("/produto/{id}")
	public ResponseEntity<?> updateProduto(@RequestBody Product newProduto, @PathVariable int id) {

	  repository.findById(id) 
	      .map(Produto -> {
	    	  Produto.setNome(newProduto.getNome());
	    	  Produto.setPreco(newProduto.getPreco());
	        return repository.save(Produto);
	      }) 
	      .orElseGet(() -> {
	    	  newProduto.setId(id);
	        return repository.save(newProduto);
	      });

	  return ResponseEntity 
	      .ok("Produto inserido com sucesso!");
	}
	
	@PostMapping(path = "/produto")
	public ResponseEntity<String> insertProduto(@RequestBody Product compra){
		repository.save(compra);
		return ResponseEntity.ok("Usuário "+compra.getNome()+" inserido com sucesso!");
	}

	
	@DeleteMapping("/produto/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable int id) {

		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body("ID do produto não existe!");
	    }

	  return ResponseEntity
		      .ok("Produto removido com sucesso!");
	}
	
}

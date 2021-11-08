package com.kleverland.compras.controller;

import java.util.List;

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

import com.kleverland.compras.model.Category;
import com.kleverland.compras.repository.CategoryRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor
@Api("Categoria")
public class CategoryController {
	
	private final CategoryRepository repository;
	
	@ApiOperation("Lista categorias disponíveis.")
	@GetMapping("/categorias")
	public List<Category> listProducts() {
	  return repository.findAll();
	}
	
	@PutMapping("/categoria/{id}")
	public ResponseEntity<String> updateCategory(@RequestBody Category newCategoria, @PathVariable int id) {
	  repository.findById(id) 
	      .map(Category -> {
	    	  Category.setName(newCategoria.getName());
	        return repository.save(Category);
	      }) 
	      .orElseGet(() -> {
	    	  newCategoria.setId(id);
	        return repository.save(newCategoria);
	      });

	  return ResponseEntity 
	      .ok("Produto inserido com sucesso!");
	}
	
	@PostMapping(path = "/categoria")
	public ResponseEntity<String> insertCategory(@RequestBody Category category){
		repository.save(category);
		return ResponseEntity.ok("Categoria "+category.getName()+" inserida com sucesso!");
	}

	
	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {

		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body("ID da categoria não existe!");
	    }

	  return ResponseEntity
		      .ok("Categoria removida com sucesso!");
	}
	
}

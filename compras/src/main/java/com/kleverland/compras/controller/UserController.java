package com.kleverland.compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kleverland.compras.model.User;
import com.kleverland.compras.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor
@Api("Usuario")
public class UserController {
	
	private final UserRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@ApiOperation("Lista usuarios do sistema.")
	@GetMapping("/usuarios")
	public List<User> listUsers() {
	  return repository.findAll();
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<String> updateUser(@RequestBody User newUser, @PathVariable int id) {
	  repository.findById(id) 
	      .map(User -> {
	    	  User.setName(newUser.getName());
	        return repository.save(User);
	      }) 
	      .orElseGet(() -> {
	    	  newUser.setId(id);
	        return repository.save(newUser);
	      });

	  return ResponseEntity 
	      .ok("Usuário salvo com sucesso!");
	}
	
	@PostMapping(path = "/usuario")
	public ResponseEntity<String> insertUser(@RequestBody User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		repository.save(user);
		return ResponseEntity.ok("Usuário "+user.getEmail()+" inserido com sucesso!");
	}

	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {

		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body("ID do usuário não existe!");
	    }

	  return ResponseEntity
		      .ok("Usuário removido com sucesso!");
	}
	
}

package br.com.fiap.money_control_api.controller;

import br.com.fiap.money_control_api.model.Category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	private List<Category> repository=new ArrayList<>();
    @GetMapping(path="/categories")
	public List<Category> index(){
		return repository;
	}
	@PostMapping("/categories")
	@ResponseStatus(code=HttpStatus.CREATED)
	public ResponseEntity<Category> create(@RequestBody Category category){
		System.out.println("Cadastrando Categorias "+ category.getName());
		repository.add(category);
		return ResponseEntity.status(201).body(category);
	}
	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> get(@PathVariable Long id){
		System.out.println("buscando categoria "+id);
		var category=repository.stream()
			.filter(c->c.getId().equals(id))
			.findFirst();
		if(category.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body( category.get());
	}
    
}

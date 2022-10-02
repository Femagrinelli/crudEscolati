package com.example.Controller;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Domain.Pessoa;
import com.example.Service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;
	
	@PostMapping
	public Pessoa salvar(@RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = service.save(pessoa);
		return pessoaSalva;
	}
	
	@GetMapping("/{id}")
	public Optional<Pessoa> buscarPorId(@PathVariable("id") Long id) {
		Optional<Pessoa> pessoaBanco = service.findById(id);
		if(pessoaBanco.isPresent()) {
			return pessoaBanco;
		}
		
		return null;
	}
	
	@GetMapping
	public Page<Pessoa> buscarTodos(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
		if(!id.equals(pessoa.getId())){
			return null;
		}
		
		return ResponseEntity.ok(service.update(pessoa));
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	
}

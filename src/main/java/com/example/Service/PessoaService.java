package com.example.Service;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.Domain.Pessoa;
import com.example.Repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	
	public Pessoa save(Pessoa pessoaParametro) {
		return repository.save(pessoaParametro);
	}
	
	public Pessoa update(Pessoa pessoa) {
		Pessoa pessoaProcurada = repository.getReferenceById(pessoa.getId());
		
		if(pessoaProcurada.getMatricula().equals(pessoa.getMatricula())){
			return repository.save(pessoa);
		}
		
		return repository.save(pessoa);
	}
	
	public Optional<Pessoa> findById(Long id){
		return repository.findById(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Page<Pessoa> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
}

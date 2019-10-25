package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

class REC03ExcluirLivro {

	@Autowired
	LivroRepository repository;
	@Test
	public void CT01ExcluirLivroComSucesso() {
		repository.deleteAll();
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		Livro ro = repository.findByIsbn("3333");
		repository.deleteById(ro.getId());
		assertThat(repository.findByIsbn("3333")).isEqualTo(null);
	}
}

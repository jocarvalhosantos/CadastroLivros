package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REC01CadastrarLivro {
	@Autowired
	LivroRepository repository;
	private Validator validator;
	private ValidatorFactory validatorFactory;

	@Test
	public void CT01CadastrarLivroComSucesso() {
		repository.deleteAll();
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		assertEquals(1, repository.count());
	}

	@Test
	public void CT02CadastrarLivroComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
//		Se os dados estiverem passando, não vai ter nenhuma violação de campo
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void CT03DeveDetectarTituloInvalido() {
	validatorFactory = Validation.buildDefaultValidatorFactory();
	validator = validatorFactory.getValidator();
	Livro livro = new Livro("3333", "", "Delamaro");
	Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
	assertEquals(violations.size(), 1);
	assertEquals("O titulo deve ser preenchido", violations.iterator().next().getMessage());
	}
	
}
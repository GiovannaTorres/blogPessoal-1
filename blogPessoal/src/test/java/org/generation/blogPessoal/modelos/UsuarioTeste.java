package org.generation.blogPessoal.modelos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import javax.validation.Validation;
import javax.validation.Validator;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTeste {
	
public Usuario usuario;
	
	@Autowired
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("JoaoSousa1", "123456789"); //model de usuario tem construtor com esses 2 atributos
	}

	@Test
	void testaAtributosUsuario() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}
	
	@Test
	void testaSenhaNula() {
		Usuario usuarioSemSenha = new Usuario();
		usuarioSemSenha.setUsuario("Maria1990");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioSemSenha);
		System.out.println(violacao.toString());
		assertFalse(violacao.isEmpty());
	}

	@Test
	void testaUsuarioNulo() {
		Usuario usuarioSemUsuario = new Usuario();
		usuarioSemUsuario.setSenha("1123456789");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioSemUsuario);
		System.out.println(violacao.toString());
		assertFalse(violacao.isEmpty());
	}

}

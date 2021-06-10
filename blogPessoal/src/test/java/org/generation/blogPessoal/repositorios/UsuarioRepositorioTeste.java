package org.generation.blogPessoal.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositorioTeste {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	@BeforeAll
	void start() {
		Usuario usuario = new Usuario("Pipa92", "2222222");
		if(repositorio.findByUsuario(usuario.getUsuario())!=null)
			repositorio.save(usuario);
		
		usuario = new Usuario("Marie92", "2222222");
		if(repositorio.findByUsuario(usuario.getUsuario())!=null)
			repositorio.save(usuario);
		
		usuario = new Usuario("Jullia.t92", "2222222");
		if(repositorio.findByUsuario(usuario.getUsuario())!=null)
			repositorio.save(usuario);
	}
	
	@Test
	public void findByUsuarioRetornaUsuario() throws Exception {

		Usuario usuario = repositorio.findByUsuario("Pipa92").get();
		assertTrue(usuario.getUsuario().equals("Pipa92"));
	}
	
	@Test
	public void findAllByUsuarioContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = repositorio.findAllByUsuarioContainingIgnoreCase("92");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@Test
	public void findAllByUsuarioContainingIgnoreCaseRetornaUmUsuario() {

		List<Usuario> listaDeUsuarios = repositorio.findAllByUsuarioContainingIgnoreCase("jU");
		assertEquals(1, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		repositorio.deleteAll();
	}

	

}

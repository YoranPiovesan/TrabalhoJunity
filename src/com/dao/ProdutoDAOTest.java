package com.dao;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.List;

import org.junit.Test;
import com.entity.Produto;

public class ProdutoDAOTest {
	
	
	ProdutoDAO produtoDao = new ProdutoDAO();
	@Test
	public void testInserir() {
		boolean b;
		b = produtoDao.inserir(new Produto("Teste", "teste", 10));
		assertTrue(b);
	}
	@Test
	public void testInserirNull() {
		boolean b;
		b = produtoDao.inserir(null);
		assertFalse(b);
	}
	@Test
	public void testInserirNomeNull() {
		boolean b;
		b = produtoDao.inserir(new Produto(null, "Teste", 10));
		assertFalse(b);
	}
	@Test
	public void testInserirTipoNull() {
		boolean b;
		b = produtoDao.inserir(new Produto("Teste", null, 10));
		assertFalse(b);	
	}
	@Test
	public void testSelect() {
		List<Produto> produto ;
		produto = ProdutoDAO.listAll();
		assertNotNull(produto);
	}
	@Test
	public void testAlterar() {
		boolean b;
		Produto produto = new Produto(5, "Teste", "Teste", 200);
		b = produtoDao.alterar(new Produto(5, "Editado", "Teste", 10));
		assertTrue(b);
		assertNotEquals(produto, produtoDao.findByID(5));
	}
	@Test
	public void testeAlterarNull() {
		boolean b;
		b = produtoDao.alterar(null);
		assertFalse(b);
	}
	@Test
	public void testeAlterarNomeNull() {
		boolean b;
		b = produtoDao.alterar(new Produto(null, "Teste", 10));
		assertFalse(b);
	}
	@Test
	public void testeAlterarTipoNull() {
		boolean b;
		b = produtoDao.alterar(new Produto("Teste", null, 10));
		assertFalse(b);
	}
	@Test
	public void testDeletar() {
		boolean b;
		b = produtoDao.deletar(20);
		assertTrue(b);
		Produto produtoDeletado = produtoDao.findByID(20);
		assertNull(produtoDeletado);
	}
}

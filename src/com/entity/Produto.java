package com.entity;

public class Produto {
	private int id;
	private String nome;
	private String tipo;
	private float preco; 

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", preco=" + preco + "]";
	}
	
	public Produto() {
		super();
	}

	public Produto(String nome, String tipo, float preco) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
	}

	public Produto(int id, String nome, String tipo, float preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

}

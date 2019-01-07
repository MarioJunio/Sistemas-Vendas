package com.jdenner.model;

import java.math.BigDecimal;

public class Produto {

	private int codigo;
	private String nome;
	private String marca;
	private float peso;
	private BigDecimal precoUnitario;
	private Unidade unidade;
	private float quantidade;
	private int estoque;
	private UnidadeCoversao unidadeConversao = new UnidadeCoversao();
	
	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public Produto() {
		this.codigo = 0;
	}

	public UnidadeCoversao getUnidadeConversao() {
		return unidadeConversao;
	}

	public void setUnidadeConversao(UnidadeCoversao unidadeConversao) {
		this.unidadeConversao = unidadeConversao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome.trim().length() < 3 || nome.trim().length() > 200) {
			throw new Exception("Nome inválido!");
		}
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", marca=" + marca + ", peso=" + peso
				+ ", precoUnitario=" + precoUnitario + ", unidade=" + unidade + ", quantidade=" + quantidade
				+ ", estoque=" + estoque + ", unidadeConversao=" + unidadeConversao + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Produto) {
			Produto p = (Produto) o;
			if (p.getCodigo() == this.getCodigo()) {
				return true;
			}
		}
		return false;
	}

}

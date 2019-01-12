package com.jdenner.model;

public class Fornecedor extends Entity {

	private int codigo;
	private String nome;
	private String cnpj;

	public Fornecedor() {
		this.codigo = 0;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", nome=" + nome + ", cnpj=" + cnpj + "]";
	}

}

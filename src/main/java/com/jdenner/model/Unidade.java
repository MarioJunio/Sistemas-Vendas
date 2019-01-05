package com.jdenner.model;

public enum Unidade {

	KG("Kilograma"), PC("Peça"), PCT("Pacote"), CX("Caixa");

	private String descricao;

	Unidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}

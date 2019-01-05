package com.jdenner.model;

public class UnidadeCoversao {

	private float quantidadeUnidadeConversao;
	private Unidade unidadeConversao;

	private float equivaleQuantidadeUnidadeConversao;
	private Unidade equivaleUnidadeConversao;

	public float getQuantidadeUnidadeConversao() {
		return quantidadeUnidadeConversao;
	}

	public void setQuantidadeUnidadeConversao(float quantidadeUnidadeConversao) {
		this.quantidadeUnidadeConversao = quantidadeUnidadeConversao;
	}

	public Unidade getUnidadeConversao() {
		return unidadeConversao;
	}

	public void setUnidadeConversao(Unidade unidadeConversao) {
		this.unidadeConversao = unidadeConversao;
	}

	public float getEquivaleQuantidadeUnidadeConversao() {
		return equivaleQuantidadeUnidadeConversao;
	}

	public void setEquivaleQuantidadeUnidadeConversao(float equivaleQuantidadeUnidadeConversao) {
		this.equivaleQuantidadeUnidadeConversao = equivaleQuantidadeUnidadeConversao;
	}

	public Unidade getEquivaleUnidadeConversao() {
		return equivaleUnidadeConversao;
	}

	public void setEquivaleUnidadeConversao(Unidade equivaleUnidadeConversao) {
		this.equivaleUnidadeConversao = equivaleUnidadeConversao;
	}

	@Override
	public String toString() {
		return "UnidadeCoversao [quantidadeUnidadeConversao=" + quantidadeUnidadeConversao + ", unidadeConversao="
				+ unidadeConversao + ", equivaleQuantidadeUnidadeConversao=" + equivaleQuantidadeUnidadeConversao
				+ ", equivaleUnidadeConversao=" + equivaleUnidadeConversao + "]";
	}

}

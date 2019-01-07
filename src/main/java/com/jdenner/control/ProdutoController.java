package com.jdenner.control;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.jdenner.dao.ProdutoDAO;
import com.jdenner.model.Produto;
import com.jdenner.model.Unidade;
import com.jdenner.util.Colors;
import com.jdenner.view.component.BotaoEditar;
import com.jdenner.view.component.BotaoExcluir;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ProdutoController implements Initializable, Controller {

	private final int QUANTIDADE_PAGINA = 9;

	@FXML
	private Parent consulta;

	@FXML
	private TableView<Produto> tabela;

	@FXML
	private TextField filtro;

	@FXML
	private TableColumn colunaNome;

	@FXML
	private TableColumn colunaEditar;

	@FXML
	private TableColumn colunaExcluir;

	@FXML
	private Pagination paginacao;

	@FXML
	private Parent formulario;

	@FXML
	private Label rotuloNome;

	@FXML
	private TextField campoNome;

	@FXML
	private Label rotuloMarca;

	@FXML
	private TextField campoMarca;

	@FXML
	private Label rotuloPeso;

	@FXML
	private TextField campoPeso;

	@FXML
	private Label rotuloUnidade;

	@FXML
	private ComboBox<Unidade> comboUnidade;

	@FXML
	private Label rotuloQuantidade;

	@FXML
	private TextField campoQuantidade;

	@FXML
	private Label rotuloUnidadeQuantidade;

	@FXML
	private Label rotuloPrecoUnitario;

	@FXML
	private TextField campoPrecoUnitario;

	@FXML
	private Label rotuloEstoque;

	@FXML
	private TextField campoEstoque;

	@FXML
	private CheckBox checkUsarUnidadeConversao;

	@FXML
	private HBox boxUnidadeConversao;

	@FXML
	private Label rotuloUnidadeConversao1;

	@FXML
	private ComboBox<Unidade> comboUnidadeConversao1;

	@FXML
	private Label rotuloUnidadeEquivalente1;

	@FXML
	private TextField campoQuantidadeUnidadeEquivalente1;

	@FXML
	private ComboBox<Unidade> comboUnidadeEquivalente1;

	@FXML
	private Button botaoSalvar;

	@FXML
	private Button botaoCancelar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaEditar.setCellFactory(new BotaoEditar(this));
		colunaEditar.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colunaExcluir.setCellFactory(new BotaoExcluir(this));
		colunaExcluir.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		paginacao.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pagina) {
				atualizarGrade(pagina);
				return tabela;
			}
		});

		trocar(false);
		atualizarGrade(0);
	}

	private void trocar(boolean form) {
		formulario.setVisible(form);
		consulta.setVisible(!form);
	}

	@FXML
	private void novo() {

		campoNome.clear();
		campoNome.clear();
		campoMarca.clear();
		campoPeso.clear();
		comboUnidade.getSelectionModel().clearSelection();
		campoQuantidade.clear();
		campoPrecoUnitario.clear();
		campoEstoque.clear();
		checkUsarUnidadeConversao.setSelected(Boolean.FALSE);
		boxUnidadeConversao.setVisible(checkUsarUnidadeConversao.isSelected());

		trocar(true);
	}

	@FXML
	private void filtrar() {
		atualizarGrade(0);
	}

	@Override
	public void editar(int codigo) {

		try {
			this.produto = ProdutoDAO.recuperar(codigo);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}

		campoNome.setText(produto.getNome());

		// TODO: preencher os demais campos com o produto a ser editado

		trocar(true);
	}

	@Override
	public void excluir(int codigo) {
		try {
			this.produto = ProdutoDAO.recuperar(codigo);
			ProdutoDAO.excluir(produto);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		atualizarGrade(0);
	}

	@FXML
	private void salvar() {

		// remove warning do formulario
		clearCadastroProdutoWarnings();

		// valida o formulario do cadastro de produto
		boolean erro = validarCadastroProduto();

		if (!erro) {

			try {
				ProdutoDAO.salvar(getProduto());
			} catch (Exception e) {
				e.printStackTrace();
			}

			trocar(false);
			atualizarGrade(0);
		}
	}

	private Produto getProduto() throws Exception {
		Produto produto = new Produto();

		produto.setNome(campoNome.getText().trim());
		produto.setMarca(campoMarca.getText().trim());
		produto.setPeso(Float.parseFloat(campoPeso.getText().trim()));
		produto.setUnidade(comboUnidade.getValue());
		produto.setQuantidade(Float.parseFloat(campoQuantidade.getText()));
		produto.setPrecoUnitario(new BigDecimal(campoPrecoUnitario.getText().replaceAll("[,\\.]+", "")));
		produto.setEstoque(Integer.parseInt(campoEstoque.getText()));

		if (checkUsarUnidadeConversao.isSelected()) {
			produto.getUnidadeConversao().setUnidadeConversao(comboUnidadeConversao1.getValue());
			produto.getUnidadeConversao()
					.setQuantidadeUnidadeConversao(Float.parseFloat(campoQuantidadeUnidadeEquivalente1.getText()));

		}

		return produto;
	}

	@FXML
	private void cancelar() {
		trocar(false);
		atualizarGrade(0);
	}

	private void atualizarGrade(int pagina) {
		try {
			paginacao.setPageCount(
					(int) Math.ceil(((double) ProdutoDAO.quantidade(filtro.getText())) / QUANTIDADE_PAGINA));
			tabela.setItems(ProdutoDAO.listar(filtro.getText(), QUANTIDADE_PAGINA, pagina));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void clearCadastroProdutoWarnings() {

		rotuloNome.setTextFill(Colors.GRAY_20);
		rotuloMarca.setTextFill(Colors.GRAY_20);
		rotuloPeso.setTextFill(Colors.GRAY_20);
		rotuloUnidade.setTextFill(Colors.GRAY_20);
		rotuloQuantidade.setTextFill(Colors.GRAY_20);
		rotuloPrecoUnitario.setTextFill(Colors.GRAY_20);
		rotuloEstoque.setTextFill(Colors.GRAY_20);
		rotuloUnidadeConversao1.setTextFill(Colors.GRAY_20);
		rotuloUnidadeEquivalente1.setTextFill(Colors.GRAY_20);
	}

	private boolean validarCadastroProduto() {

		boolean erro = false;

		if (StringUtils.isEmpty(campoNome.getText())) {
			rotuloNome.setTextFill(Colors.RED);
			erro = true;
		}

		if (StringUtils.isEmpty(campoMarca.getText())) {
			rotuloMarca.setTextFill(Colors.RED);
			erro = true;
		}

		if (StringUtils.isEmpty(campoPeso.getText())) {
			rotuloPeso.setTextFill(Colors.RED);
			erro = true;
		}

		if (comboUnidade.getSelectionModel().getSelectedItem() == null) {
			rotuloUnidade.setTextFill(Colors.RED);
			erro = true;
		}

		if (StringUtils.isEmpty(campoQuantidade.getText())) {
			rotuloQuantidade.setTextFill(Colors.RED);
			erro = true;
		}

		if (StringUtils.isEmpty(campoPrecoUnitario.getText())) {
			rotuloPrecoUnitario.setTextFill(Colors.RED);
			erro = true;
		}

		if (StringUtils.isEmpty(campoEstoque.getText())) {
			rotuloEstoque.setTextFill(Colors.RED);
			erro = true;
		}

		if (checkUsarUnidadeConversao.isSelected()) {

			if (comboUnidadeConversao1.getSelectionModel().getSelectedItem() == null) {
				rotuloUnidadeConversao1.setTextFill(Colors.RED);
				erro = true;
			}

			if (StringUtils.isEmpty(campoQuantidadeUnidadeEquivalente1.getText())
					|| (comboUnidadeEquivalente1.getSelectionModel().getSelectedItem()) == null) {
				rotuloUnidadeEquivalente1.setTextFill(Colors.RED);
				erro = true;
			}

		}

		return erro;
	}
}

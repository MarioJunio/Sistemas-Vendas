package com.jdenner.control;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdenner.dao.ProdutoDAO;
import com.jdenner.model.Produto;
import com.jdenner.model.Unidade;
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
import javafx.scene.paint.Paint;
import javafx.util.Callback;

public class ProdutoController implements Initializable, Controller {

	private final int QUANTIDADE_PAGINA = 9;

	private Produto produto;

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
	private ComboBox comboUnidadeConversao1;

	@FXML
	private Label rotuloUnidadeEquivalente1;

	@FXML
	private TextField campoQuantidadeUnidadeEquivalente1;

	@FXML
	private ComboBox comboUnidadeEquivalente1;

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
		this.produto = new Produto();

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
		rotuloNome.setTextFill(Paint.valueOf("#333333"));
		rotuloPrecoCompra.setTextFill(Paint.valueOf("#333333"));
		rotuloPrecoVenda.setTextFill(Paint.valueOf("#333333"));

		boolean erro = false;

		try {
			produto.setNome(campoNome.getText().trim());
		} catch (Exception e) {
			rotuloNome.setTextFill(Paint.valueOf("red"));
			erro = true;
		}
		try {
			produto.setPrecoCompra(campoPrecoCompra.getText());
		} catch (Exception e) {
			rotuloPrecoCompra.setTextFill(Paint.valueOf("red"));
			erro = true;
		}
		try {
			produto.setPrecoVenda(campoPrecoVenda.getText());
		} catch (Exception e) {
			rotuloPrecoVenda.setTextFill(Paint.valueOf("red"));
			erro = true;
		}

		if (erro) {
			return;
		}

		try {
			ProdutoDAO.salvar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		trocar(false);
		atualizarGrade(0);
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
}

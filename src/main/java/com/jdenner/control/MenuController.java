package com.jdenner.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Juliano
 */
public class MenuController implements Initializable {

	@FXML
	private TabPane tabPane;

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void abrirProdutos() {
		abrir("/fxml/Produto.fxml", "Produtos");
	}

	@FXML
	private void abrirClientes() {
		abrir("/fxml/Cliente.fxml", "Clientes");
	}

	@FXML
	private void abrirFornecedores() {
		abrir("/fxml/Fornecedor.fxml", "Fornecedores");
	}

	@FXML
	private void abrirCompras() {
		abrir("/fxml/Compra.fxml", "Compras");
	}

	@FXML
	private void abrirVendas() {
		abrir("/fxml/Venda.fxml", "Vendas");
	}

	private void abrir(String url, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
			Parent content = loader.load();
			Tab tab = new Tab(title);
			tab.setContent(content);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().select(tab);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

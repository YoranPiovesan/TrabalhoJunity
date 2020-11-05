package com.view.produto;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.Test;

import com.dao.ProdutoDAO;
import com.entity.Produto;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import junit.framework.Assert;

public class ControllerProduto extends Application implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldTipo;

    @FXML
    private TextField textFieldPreco;

    @FXML
    private Button buttonInserir;

    @FXML
    private TextArea textAreaListProdutos;

    @FXML
    private TextField textFieldFindID;

    @FXML
    private Label labelLabelID;

    @FXML
    private Label labelID;

    @FXML
    private Button buttonAlterar;
    
    @FXML
  public  void DeletarProduto(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Deletar Produto");
    	alert.setHeaderText("Você está prestes a deletar um produto");
    	alert.setContentText("Tem certeza que deseja deletar o produto?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Produto produto= pegaDadosID();
    		boolean qtde = new ProdutoDAO().deletar(produto.getId());
        	limpaCampos();
        	listarProduto();
    	}
    	
    }
    @FXML
   public void AlterarProduto(ActionEvent event) {
    	Produto produto= pegaDadosID();
    	if(String.valueOf(produto.getId()) == null || String.valueOf(produto.getId()) =="") {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Alerrta");
    		alert.setHeaderText("Produto não selecionado");
    		alert.setContentText("Selecione um produto para alterar");
    	}
    	else {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Alterar Produto");
        	alert.setHeaderText("Você está prestes a alterar um produto");
        	alert.setContentText("Tem certeza que deseja altterar o produto?");
        	Optional<ButtonType> result = alert.showAndWait();
        	if (result.get() == ButtonType.OK){
        	
    		limpaCampos();
    		boolean qtde = new ProdutoDAO().alterar(produto);
    		listarProduto();
        	}
    	}
    }
    @FXML
   public void buscarProduto(ActionEvent event) {
		String idString = textFieldFindID.getText();
		Produto produto = null;
		if (!idString.equals("")) {
			try {
				int id = Integer.valueOf(idString);
				produto = new ProdutoDAO().findByID(id);
			} catch (Exception e) {
			}
			if (produto != null) {
				labelLabelID.setVisible(true);
				labelID.setVisible(true);
				labelID.setText(produto.getId() + "");
				textFieldNome.setText(produto.getNome());
				textFieldTipo.setText(produto.getTipo());
				textFieldPreco.setText(produto.getPreco() + "");
			}
		}
		textFieldFindID.clear();
    }
    @FXML
    public void inserirProduto(ActionEvent event) {
    	
    	Produto produto = pegaDados();
		limpaCampos();
		System.out.println(produto);
		boolean qtde = new ProdutoDAO().inserir(produto);
		
		listarProduto();
    }
	public void execute() {
		launch();
	}
	
	public void limpaCampos() {
		textFieldTipo.clear();
		textFieldPreco.clear();
		textFieldNome.clear();
		textFieldNome.requestFocus();
		labelLabelID.setVisible(false);
		labelID.setVisible(false);
		labelID.setText("");
	}
	@Override
	public void start(Stage stage) {
		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Produto.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarProduto();
	}
	public Produto pegaDados() {
		return new Produto(textFieldNome.getText(), textFieldTipo.getText(), Float.valueOf(textFieldPreco.getText()));
	}
	public Produto pegaDadosID() {
		if(labelID.getText() == null || labelID.getText() == ""){
			return null;
		}
		return new Produto(Integer.valueOf(labelID.getText()), textFieldNome.getText(), textFieldTipo.getText(), Float.valueOf(textFieldPreco.getText()));
	}
	
	public boolean Teste() {
		return true;	
	}
    
	public void listarProduto() {
		textAreaListProdutos.clear();
		List<Produto> listaProduto = new ProdutoDAO().listAll();
		listaProduto.forEach(produto -> {
			textAreaListProdutos.appendText(produto.toString() + "\n");
		});
	}

}
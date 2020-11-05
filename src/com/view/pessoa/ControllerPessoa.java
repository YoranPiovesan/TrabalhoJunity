package com.view.pessoa;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dao.PessoaDAO;
import com.entity.Pessoa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerPessoa extends Application implements Initializable {

	@FXML
	private TextField textFieldNome;

	@FXML
	private TextField textFieldEMail;

	@FXML
	private Button buttonInserir;

	@FXML
	private TextArea textAreaListPessoas;

	@FXML
	private TextField textFieldFindID;

	@FXML
	private Label labelID;

	@FXML
	private Label labelLabelID;

	@FXML
	void buscarPessoa(ActionEvent event) {
		String idString = textFieldFindID.getText();
		Pessoa pessoa = null;
		if (!idString.equals("")) {
			try {
				int id = Integer.valueOf(idString);
				pessoa = new PessoaDAO().findByID(id);
			} catch (Exception e) {

			}

			if (pessoa != null) {
				labelLabelID.setVisible(true);
				labelID.setVisible(true);
				labelID.setText(pessoa.getId() + "");
				textFieldNome.setText(pessoa.getNome());
				textFieldEMail.setText(pessoa.getEmail());
			}

		}
		textFieldFindID.clear();
	}

	@FXML
	void inserirPessoa(ActionEvent event) {
		Pessoa pessoa = pegaDados();
		limpaCampos();
		int qtde = new PessoaDAO().inserir(pessoa);
		listarPessoas();
	}



	@FXML
	void AlterarPessoa(ActionEvent event) {
		Pessoa pessoa = pegaDadosID();
		limpaCampos();
		int qtde = new PessoaDAO().alterar(pessoa);
		listarPessoas();
	}
	
	
	private void limpaCampos() {
		textFieldEMail.clear();
		textFieldNome.clear();
		textFieldNome.requestFocus();
		labelLabelID.setVisible(false);
		labelID.setVisible(false);
		labelID.setText("");
	}

	private Pessoa pegaDados() {
		return new Pessoa(textFieldNome.getText(), textFieldEMail.getText());
	}
	
	private Pessoa pegaDadosID() {
		return new Pessoa(Integer.valueOf(labelID.getText()), textFieldNome.getText(), textFieldEMail.getText());
	}

	private void listarPessoas() {
		textAreaListPessoas.clear();
		List<Pessoa> listaPessoa = new PessoaDAO().listAll();
		listaPessoa.forEach(pessoa -> {
		textAreaListPessoas.appendText(pessoa.toString() + "\n");
		});
	}

	public void execute() {
		launch();
	}

	@Override
	public void start(Stage stage) {

		try {
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Pessoa.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarPessoas();

	}
	

}
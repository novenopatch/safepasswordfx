package javafxsolo.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafxsolo.Main;
import javafxsolo.Utils.Connect;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppController extends MainController implements Initializable {
	@FXML
	private TextField txfUsername;
	@FXML
	private PasswordField txfPassword;
	@FXML
	private DatePicker pdate;
	@FXML
	private Label lblStatus;
	@FXML
	private TextArea txtAdescription;
	@FXML
	private Button btnSave;
	@FXML
	private TableView tblData;
	@FXML
	BorderPane borderPaneRoot;
	private ObservableList<ObservableList> data;
	int i = Connect.idconnect();
	private String SQL = "SELECT * from t_save  WHERE saveAuthor=" + i;
	int saveid;



	private void clearFields() {
		txfUsername.clear();
		txfPassword.clear();
		txtAdescription.clear();
	}

	private String saveData() {

		String st = "INSERT INTO T_save (nb,Username, password, Date, Description, saveAuthor) VALUES (?,?,?,?,?,?)";
		try (PreparedStatement stUpdate = conn.prepareStatement(st)) {
			stUpdate.setInt(1, ++saveid);
			stUpdate.setString(2, txfUsername.getText());
			stUpdate.setString(3, txfPassword.getText());
			stUpdate.setString(4, pdate.getValue().toString());
			stUpdate.setString(5, txtAdescription.getText());
			stUpdate.setInt(6, i);
			stUpdate.executeUpdate();
			lblStatus.setTextFill(Color.GREEN);
			lblStatus.setText("Added Successfully");

			fetRowList();
			clearFields();
			return "Success";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText(ex.getMessage());
			return "Exception";
		}
	}

	private void fetColumnList() {

		try (ResultSet rs = conn.createStatement().executeQuery(SQL)) {

			// SQL FOR SELECTING ALL OF CUSTOMER
			for (int i = 1; i < rs.getMetaData().getColumnCount() - 1; i++) {
				/*
				 * i=1 et non 0 car i est l'identifiant entier des save conteneu dans la base de
				 * donn�s doc laisser i commencer a zero causera une icoherance au yeux de
				 * l'user bah pour l'instant je sais pas palier � cela. pour le metadata - 1
				 * c'est just pour ne pas afficher la collone de l'id user correspondant en
				 * d'autre terme pour ne pas afficher encors une autre collone mais cette
				 * fois-ci une collone de la fin
				 */
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});

				tblData.getColumns().removeAll(col);
				tblData.getColumns().addAll(col);

				// System.out.println("Column [" + i + "] ");

			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());

		}
	}

	// for row off tableview
	private void fetRowList() {

		data = FXCollections.observableArrayList();

		try (ResultSet rs = conn.createStatement().executeQuery(SQL)) {

			while (rs.next()) {
				saveid = rs.getInt(2);
				// Iterate Row
				ObservableList row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));

				}
				// System.out.println("Row [1] added " + row);
				data.add(row);

			}

			tblData.setItems(data);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@FXML
	private void saveaction(ActionEvent e) {

		// check if not empty
		if (txfPassword.getText().isEmpty() || txfUsername.getText().isEmpty() || txtAdescription.getText().isEmpty()|| pdate.getValue().equals(null)) {
			lblStatus.setTextFill(Color.TOMATO);
			lblStatus.setText("Erreur: Vous avez laissez des champs vides");
		} else {
			saveData();
		}
		
	}
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tblData.setOnContextMenuRequested(e -> {
			System.out.println(e.getPickResult().toString());

		});
		borderPaneRoot.setStyle("-fx-background-image: url(/ressources/image/2.jpg)");
		tblData.setStyle("-fx-background-image: url(/ressources/image/3.jpg)");
		fetColumnList();
		fetRowList();
		Main.myStage.centerOnScreen();
	}

}

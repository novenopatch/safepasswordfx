package javafxsolo.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafxsolo.Utils.Connect;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FirstLaunchController extends MainController implements Initializable {

	@FXML
	private ChoiceBox<String> choice;
	@FXML
	private TextField answer;
	@FXML
	private TextField textUsername;
	@FXML
	private Label lblQuestion;
	@FXML
	private PasswordField txtfPassword;
	@FXML
	private PasswordField confirmPassword;
	@FXML
	private Button btnConnect;
	@FXML
	private VBox fristLVbox;
	/*
	 * @FXML public void handleButtonAction(MouseEvent event) {
	 * 
	 * if (event.getSource() == btnConnect) { //login here if
	 * (saveData().equals("Success")) { try {
	 * 
	 * //add you loading or delays - ;-) Node node = (Node) event.getSource(); Stage
	 * stage = (Stage) node.getScene().getWindow(); //stage.setMaximized(true);
	 * stage.close(); Parent root =
	 * FXMLLoader.load(getClass().getResource("/ressources/fxml/Main.fxml")); Scene
	 * appScene = new Scene(root); Main.myStage.setScene(appScene);
	 * //stage.setScene(scene); stage.show();
	 * 
	 * } catch (IOException ex) { System.err.println(ex.getMessage()); }
	 * 
	 * } } }
	 */
	private boolean checkifnotempty(){
		if (answer.getText().isEmpty() && textUsername.getText().isEmpty() && confirmPassword.getText().isEmpty()
				&& txtfPassword.getText().isEmpty() ) {
			return true;
		}
		else{
			return false;
		}
	}
	private void clearchamp(){
		textUsername.clear();
		txtfPassword.clear();
		confirmPassword.clear();
		answer.clear();
	}
	@FXML
	private void saveaction(ActionEvent e) {
		boolean problem =false;
		// check if not empty
		if (checkifnotempty() || textUsername.getText().length() < 4 || answer.getText().isEmpty()|| Connect.userExist(textUsername.getText()) || !equalsPassword().equals("cool")) {
			problem =true;
			if (checkifnotempty()) {
				lblQuestion.setTextFill(Color.TOMATO);
				lblQuestion.setText("Vous avez laissez tout les champs vides");
			}
			else if(!textUsername.getText().isEmpty() && textUsername.getText().length() < 4  ) {
				lblQuestion.setTextFill(Color.TOMATO);
				lblQuestion.setText("votre nom d'utilisateur est trop court !");
				textUsername.clear();

			}
			else if (Connect.userExist(textUsername.getText())) {
				lblQuestion.setTextFill(Color.BROWN);
				lblQuestion.setText("Erreur :Cet Utilisateur existe déja!");
				textUsername.clear();
			}


			else if(!equalsPassword().equals("cool")){
				///System.out.println(" not Ok");
				lblQuestion.setTextFill(Color.CHOCOLATE);
				txtfPassword.clear();
				confirmPassword.clear();
				lblQuestion.setText("Les mots de passe ne correspondent pas!");
			}
			else if (answer.getText().isEmpty()){
				lblQuestion.setTextFill(Color.CHOCOLATE);
				lblQuestion.setText("!!!!!!!");
			}
			else{
				lblQuestion.setTextFill(Color.RED);
				lblQuestion.setText("Vous avez laissez  au moins un champ vides!");
			}
		}
		if( problem){
			doTransition(btnConnect,"TranslateTransition");
			//clearchamp();
		}
		else {
			if(equalsPassword().equals("cool") && !problem) {
				if (saveData().equals("Success")) {
					doTransition(fristLVbox,"");
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(500);
								Platform.runLater(() -> {
									myOriginalLaunch();
								});
							} catch (InterruptedException interruptedException) {
								interruptedException.printStackTrace();
							}

						}
					}).start();
				}

			}

		}

	}
	private String saveData() {
		String st = "INSERT INTO T_Users   (Login, Password,userQuestion, userAnswer) VALUES (?,?,?,?)";
		try (Connection conn = Connect.conSqlite(); PreparedStatement stUpdate = conn.prepareStatement(st)) {

			stUpdate.setString(1, textUsername.getText());
			stUpdate.setString(2, txtfPassword.getText());

			stUpdate.setInt(3, Connect.questionId(choice.getValue().toString()));
			stUpdate.setString(4, answer.getText());
			stUpdate.executeUpdate();
			lblQuestion.setTextFill(Color.GREEN);
			lblQuestion.setText("Added Successfully");
			Connect.fristLaunchD();//cette ligne désactive le first launch dans la db
			return "Success";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			lblQuestion.setTextFill(Color.TOMATO);
			lblQuestion.setText(ex.getMessage());
			return "Exception";
		}
	}

	private String equalsPassword() {
		if( txtfPassword.getText().equals(confirmPassword.getText()) ) {
			return "cool";
		}
		else {
			return "not cool";
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//quiterAction();
		//for charge all connection in database
		choice.getItems().addAll(Connect.questionList());
		//for selecte first question
		choice.getSelectionModel().select(Connect.questionList().get(0));
	}

}

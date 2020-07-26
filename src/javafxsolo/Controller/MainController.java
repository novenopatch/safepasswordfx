package javafxsolo.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafxsolo.Main;
import javafxsolo.Modele.Connect;
import javafxsolo.Modele.User;
import javafxsolo.Utils.Serializer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainController implements Initializable {

	@FXML
	private VBox AppMainbox;
	@FXML
	private HBox hbox;
	@FXML
	private Button btnforget ;
	@FXML
	private  Button btnConnection ;
	@FXML
	private PasswordField PasswordField;
	@FXML
	private TextField TextField ;
	@FXML
	private MenuItem about;
	@FXML
	private Label lblErrors;
	private  Parent root;
	private Scene appScene;


	@FXML
	BorderPane bossBorderpane;
	@FXML VBox VboxTo;
	@FXML
	BorderPane pan2;
	@FXML
	MenuBar menuBar;
	protected static String nomFic="user";



	protected Connection conn = Connect.conSqlite();
	@FXML
	protected void quiterAction(ActionEvent e){
		Alert a = new Alert(Alert.AlertType.CONFIRMATION);
		a.initOwner(Main.primaryStage);
		a.setTitle("Confimation");
		a.setHeaderText("Vous etes sur de vouloir quiter le programme ?");
		//a.setContentText("Vous etes sur de quiter le programme ?");
		Optional<ButtonType>r = a.showAndWait();
		r.ifPresent(button ->{
			if(button.getText().equals("OK"))Platform.exit();
			//System.out.println(button.getText());
		} );
	}
	protected void quiterAction(){
		Main.myStage.setOnCloseRequest(event -> {
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.initOwner(Main.primaryStage);
			a.setTitle("Confimation");
			a.setHeaderText("Vous etes sur de vouloir quiter le programme ?");
			//a.setContentText("Vous etes sur de quiter le programme ?");
			Optional<ButtonType>r = a.showAndWait();
			r.ifPresent(button ->{
				if(button.getText().equals("OK")){
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					//Platform.exit();
				}
				//System.out.println(button.getText());
			} );
		});

	}
	private ActionEvent b;
	@FXML
	protected void btnforgetAction(ActionEvent e)  {
		b=e;
		Dialog<Pair<String,String>>a = new Dialog<Pair<String,String>>();
		a.getDialogPane().getButtonTypes().add(new ButtonType("Bye...", ButtonBar.ButtonData.OTHER));
		a.initOwner(Main.primaryStage);
		a.setTitle("Hum c'est quoi le Lol!");
		a.setHeaderText("Salut ");
		a.setContentText(
				" comment vous avez pu oublier un mot de passe qui vas vous permettre de recupérer tout vos identifiant et mot de passe\n " +
				"A cause des gens comme vous je dois aussi codé cette scenne hum oorh Lundi 6 juillet 2020  15h10 et j'ai toujours pas cod� la recup�ration en cas d'oublie de l'user. chui trop fatigu� le firstlogin fonctionne, pas de message d''erreur moi je vais dodo. \n" +
						"A la prochaine cher Utilisateur"
		);


		//a.show();
		//Node node = (Node) e.getSource();
		//Stage stage = (Stage) node.getScene().getWindow();
		//stage.setMaximized(true);
		Stage stage = ((Stage) ( ( (Node) b.getSource() ).getScene().getWindow() )) ;
		stage.close();

		
	}


	@FXML
	protected void firstLoginScreen(MouseEvent e) {
		try {

			Parent foot = FXMLLoader.load(getClass().getResource("/ressources/fxml/Firstlogin.fxml"));
			Scene  otherScene= new Scene(foot);
			Main.myStage.setScene(otherScene);
		} catch (IOException p) {
			p.printStackTrace();
		}
	
		
	}
	@FXML
	protected void aboutAction(ActionEvent actionEvent) {
		//Alert a = new Alert(Alert.AlertType.INFORMATION);
		Dialog<Pair<String,String>>a = new Dialog<Pair<String,String>>();
		a.getDialogPane().getButtonTypes().add(new ButtonType("Bye...", ButtonBar.ButtonData.OTHER));
		a.initOwner(Main.primaryStage);
		a.setTitle("A propos de JinCoffer");
		a.setHeaderText("Salut ");
		a.setContentText(
				" il est 3h42min du 23 juin 2020. Lol,tout le monde dors,oups il fait tard \n " +
				"Je m'appelle Joseph, � l'heure au j'�cris ces lignes le programme est a sa version 1.0\n" +
				"Je suis pas douer pour les discours mais je suis certain que mon programme vous a �t� utile.\n" +
				"Pour tous vos sugestions d'am�lioration ou signaler un dysfonctionement du rogramme. \n" +
				"Veillez m'envoyer un email Sur cette addresse: homawoojoseph@gmail.com \n" +
						"A la prochaine cher Utilisateur"
		);


		a.show();
	}
	@FXML
	protected void ClavierListener(KeyEvent event) {//to do login whith the press enter key
		if(event.getCode() == KeyCode.ENTER) {
			loginT();
		}
		
	}

	  @FXML
	    public void handleButtonAction(MouseEvent event) {

	        if (event.getSource() == btnConnection) {
	            // loginTrue
	        	loginT();
	        }
	    }
	    //after i will change this make to make transition with another node
	    public void doTransition(String node){
			switch (node){
				case "VboxTo": {
					FadeTransition ft = new FadeTransition();
					ft.setDuration(Duration.millis(50));
					ft.setNode(VboxTo);
					ft.setFromValue(0);
					ft.setToValue(1);
					ft.setCycleCount(1);
					ft.setAutoReverse(true);
					ft.play();
					break;
				}
				case "btnConnection":{
					TranslateTransition tT = new TranslateTransition(Duration.millis(50),btnConnection);
					tT.setFromX(0);
					tT.setToX(15);
					tT.setCycleCount(6  );
					tT.setAutoReverse(true);
					tT.play();
					break;
				}
				default:{

				}

			}
		}
	public void doTransition(Node node,String transtionName){
		switch (transtionName){
			case "TranslateTransition":{
				TranslateTransition tT = new TranslateTransition(Duration.millis(50),node);
				tT.setFromX(0);
				tT.setToX(15);
				tT.setCycleCount(6  );
				tT.setAutoReverse(true);
				tT.play();
				break;
			}
			default:{
				FadeTransition ft = new FadeTransition();
				ft.setDuration(Duration.millis(50));
				ft.setNode(node);
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(true);
				ft.play();
				break;
			}

		}
	}


	  //connectTest
	protected String connectTest() {
		 String status = "Success";
		try {
			if(TextField.getText().isEmpty() || PasswordField.getText().isEmpty()) {

				doTransition("btnConnection");

				lblErrors.setTextFill(Color.TOMATO);
				lblErrors.setText("Veillez saisir vos identifiants");
	           status = "Error";
	        }
			else {
				if( Connect.dataTest(conn,TextField.getText(), PasswordField.getText()) ) {
					lblErrors.setTextFill(Color.LIGHTGREEN);
		            lblErrors.setText("You are connected!!!");
					}
				else {
					status = "Error";
					doTransition("btnConnection");
					lblErrors.setTextFill(Color.INDIANRED);
		            lblErrors.setText("password or username are false.");
		            
				}
			}
			
		}catch (Exception p) {
			System.err.println("ConnectionUtil : "+p.getMessage());
			 status = "Exception";
		}
		return status;
	}

	/**
	 * action after result connectTest
	 */
	private void loginT() {
		if (connectTest() .equals("Success")) {
			doTransition("boxTo");
			new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						root = FXMLLoader.load(getClass().getResource("/ressources/fxml/App.fxml"));
						appScene = new Scene(root);
						Thread.sleep(50);
						Platform.runLater(() -> {
							Main.myStage.setScene(appScene);
						});
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	/**
	 * 	myorginal launch is an methode who test if is the first launch or no,and make return the windows contain   at the main scene
	 */
	protected void myOriginalLaunch() {
		try {
			if (Connect.fristLaunchTest()) {
				Parent foot = FXMLLoader.load(getClass().getResource("/ressources/fxml/Firstlogin.fxml"));
				Scene otherScene = new Scene(foot);
				Main.myStage.setScene(otherScene);

			} else {
				Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Main.fxml"));
				Scene otherScene = new Scene(root);
				Main.myStage.setScene(otherScene);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		@FXML//for fxml action version
		protected void myOriginalLaunch(ActionEvent e) {
			try {
				if(Connect.fristLaunchTest()) {
					//Connect.fristLaunchD();
					Parent foot = FXMLLoader.load(getClass().getResource("/ressources/fxml/Firstlogin.fxml"));
					Scene  otherScene= new Scene(foot);
					Main.myStage.setScene(otherScene);

				}
				else {
					Parent root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Main.fxml"));
					Scene  otherScene= new Scene(root);
					Main.myStage.setScene(otherScene);

				}
			} catch (IOException p) {
				p.printStackTrace();
			}
	
	}
	/*
	*recupère le dernier user
	 */
	protected void recupSerialize(){
		User user = (User) Serializer.deSerialize(nomFic);
		TextField.setText(user.getUserAnswer());
	}

	/**
	 * test of if database is right
	 */
	private void databaseright(){
		try(Connection conI = Connect.conSqlite()) {
			if(  conI ==null) {
				lblErrors.setTextFill(Color.TOMATO);
				lblErrors.setText("Server Error : Check");
				btnConnection.setDisable(true);
				btnforget.setDisable(true);
			}
			else {
				lblErrors.setTextFill(Color.GREEN);
				lblErrors.setText("Hi,let's go!!");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		recupSerialize();
		VboxTo.setOnKeyReleased(e->{
			if(e.getCode() == KeyCode.ENTER) {
				loginT();
			}
		});

		pan2.setStyle("-fx-background-image: url(/ressources/image/test.jpg)");
		about.setGraphic(new ImageView(getClass().getResource("/ressources/icone/10.png").toString()));
		databaseright();

	}
}

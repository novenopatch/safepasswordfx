package javafxsolo.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.Pair;
import javafxsolo.Main;
import javafxsolo.Utils.Connect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.scene.layout.BackgroundSize.AUTO;


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
		Stage mito = new Stage();
		VBox moto = new VBox();

		ObservableList<PieChart.Data>pCD = FXCollections.observableArrayList(
				new PieChart.Data("Valeur",42),
				new PieChart.Data("Valeur1",30),
				new PieChart.Data("Valeur2",300)//les portion


		);
		pCD.add(new PieChart.Data("valeur3", 91));

		//Diagramme frommage
		PieChart c = new PieChart(pCD);//on met en parèmete la collectionliste
		c.setVisible(true);
		c.setLabelLineLength(10);
		c.setTitle("Diagramme frommage");//le titre du diagramme
		c.setLegendSide(Side.RIGHT);
		c.setStartAngle(90);//le debut des calcul se fait à 90
		//moto.getChildren().add(c);

		//diagramme en forme de courbe
		//on doit créer les axe
		//on peut mette aussi des catégories abxices avec : CategoryAxis();
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();

		xAxis.setSide(Side.TOP);
		xAxis.setLabel("axe x");
		LineChart<Number,Number> l= new LineChart<Number,Number>(xAxis,yAxis);
		XYChart.Series<Number,Number> s1 = new XYChart.Series<Number,Number>();
		s1.setName("sèrie1");
		XYChart.Series<Number,Number> s2 = new XYChart.Series<Number,Number>();
		s2.setName("sèrie2");
		s1.getData().addAll(
				new XYChart.Data<Number, Number>(10,10),
				new XYChart.Data<Number, Number>(20,100),
				new XYChart.Data<Number, Number>(30,20),
				new XYChart.Data<Number, Number>(40,42),
				new XYChart.Data<Number, Number>(50,66)
		);
		s2.getData().addAll(
				new XYChart.Data<Number, Number>(10,30),
				new XYChart.Data<Number, Number>(20,0),
				new XYChart.Data<Number, Number>(30,40),
				new XYChart.Data<Number, Number>(40,62),
				new XYChart.Data<Number, Number>(50,23)
		);
		l.setCreateSymbols(true);//rajoute des point à la fin des intersections
		l.getData().addAll(s1,s2);
		//moto.getChildren().add(l);





		//pour les aire presque identique au précédent
		AreaChart<Number,Number> lo= new AreaChart<Number,Number>(xAxis,yAxis);
		s1.getData().addAll(
				new XYChart.Data<Number, Number>(10,10),
				new XYChart.Data<Number, Number>(20,100),
				new XYChart.Data<Number, Number>(30,20),
				new XYChart.Data<Number, Number>(40,42),
				new XYChart.Data<Number, Number>(50,66)
		);
		s2.getData().addAll(
				new XYChart.Data<Number, Number>(10,30),
				new XYChart.Data<Number, Number>(20,0),
				new XYChart.Data<Number, Number>(30,40),
				new XYChart.Data<Number, Number>(40,62),
				new XYChart.Data<Number, Number>(50,23)
		);
		lo.setCreateSymbols(true);//rajoute des point à la fin des intersections
		lo.getData().addAll(s1,s2);
		//moto.getChildren().add(lo);

		//pour les surface
		StackedAreaChart<Number,Number> po= new StackedAreaChart<Number,Number>(xAxis,yAxis);
		s1.getData().addAll(
				new XYChart.Data<Number, Number>(10,10),
				new XYChart.Data<Number, Number>(20,100),
				new XYChart.Data<Number, Number>(30,20),
				new XYChart.Data<Number, Number>(40,42),
				new XYChart.Data<Number, Number>(50,66)
		);
		s2.getData().addAll(
				new XYChart.Data<Number, Number>(10,30),
				new XYChart.Data<Number, Number>(20,0),
				new XYChart.Data<Number, Number>(30,40),
				new XYChart.Data<Number, Number>(40,62),
				new XYChart.Data<Number, Number>(50,23)
		);
		po.setCreateSymbols(true);//rajoute des point à la fin des intersections
		po.getData().addAll(s1,s2);
		//moto.getChildren().add(po);

		//pour les graphique en bulle
		BubbleChart<Number,Number> jo= new BubbleChart<Number,Number>(xAxis,yAxis);
		s1.getData().addAll(
				new XYChart.Data<Number, Number>(10,10,10),
				new XYChart.Data<Number, Number>(20,100),
				new XYChart.Data<Number, Number>(30,20),
				new XYChart.Data<Number, Number>(40,42),
				new XYChart.Data<Number, Number>(50,66)
		);
		s2.getData().addAll(
				new XYChart.Data<Number, Number>(10,30,40),
				new XYChart.Data<Number, Number>(20,0),
				new XYChart.Data<Number, Number>(30,40),
				new XYChart.Data<Number, Number>(40,62),
				new XYChart.Data<Number, Number>(50,23)
		);
		jo.getData().addAll(s1,s2);
		//moto.getChildren().add(jo);


		//ce sont des graphiques en forme de forme (rectangle rond) des nuages de point
		ScatterChart<Number,Number> pi= new ScatterChart<Number,Number>(xAxis,yAxis);
		s1.getData().addAll(
				new XYChart.Data<Number, Number>(10,10),
				new XYChart.Data<Number, Number>(20,100),
				new XYChart.Data<Number, Number>(30,20),
				new XYChart.Data<Number, Number>(40,42),
				new XYChart.Data<Number, Number>(50,66)
		);
		s2.getData().addAll(
				new XYChart.Data<Number, Number>(10,30),
				new XYChart.Data<Number, Number>(20,0),
				new XYChart.Data<Number, Number>(30,40),
				new XYChart.Data<Number, Number>(40,62),
				new XYChart.Data<Number, Number>(50,23)
		);
		pi.getData().addAll(s1,s2);
		//moto.getChildren().add(pi);


		CategoryAxis xs = new CategoryAxis();
		NumberAxis xy = new NumberAxis();

		//ce sont des graphiques en forme de forme (rectangle rond) des nuages de point
		BarChart<String,Number> ii= new BarChart<String,Number>(xs,xy );
		XYChart.Series<String,Number> x1 = new XYChart.Series<String,Number>();
		x1.setName("Pluie");
		XYChart.Series<String,Number> x2 = new XYChart.Series<String,Number>();
		x2.setName("Température");

		x1.getData().addAll(
				new XYChart.Data<String, Number>("Janvier",10),
				new XYChart.Data<String, Number>("Fevier",15),
				new XYChart.Data<String, Number>("Mars",20),
				new XYChart.Data<String, Number>("Avril",32),
				new XYChart.Data<String, Number>("Mai",16),
				new XYChart.Data<String, Number>("Juin",30),
				new XYChart.Data<String, Number>("juillet",100),
				new XYChart.Data<String, Number>("Aout",9),
				new XYChart.Data<String, Number>("Septembre",12),
				new XYChart.Data<String, Number>("Octobre",3),
				new XYChart.Data<String, Number>("Novembre",0),
				new XYChart.Data<String, Number>("Décembre",5)
		);
		x2.getData().addAll(
				new XYChart.Data<String, Number>("Janvier",33),
				new XYChart.Data<String, Number>("Fevier",26),
				new XYChart.Data<String, Number>("Mars",20),
				new XYChart.Data<String, Number>("Avril",31),
				new XYChart.Data<String, Number>("Mai",36),
				new XYChart.Data<String, Number>("Juin",25),
				new XYChart.Data<String, Number>("juillet",24.5d),
				new XYChart.Data<String, Number>("Aout",20),
				new XYChart.Data<String, Number>("Septembre",25),
				new XYChart.Data<String, Number>("Octobre",25.5d),
				new XYChart.Data<String, Number>("Novembre",26),
				new XYChart.Data<String, Number>("Décembre",24)
		);
		ii.getData().addAll(x1,x2);
		//moto.getChildren().add(ii);


		StackedBarChart<String,Number> io= new StackedBarChart<String,Number>(xs,xy );
		io.getData().addAll(x1,x2);
		//moto.getChildren().add(io);


		mito.setScene(new Scene(moto));
		mito.show();
		
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
				/*
				AnimationTimer aT =new AnimationTimer() {
					@Override
					public void handle(long l) {
						bossBorderpane.heightProperty().addListener(e->{
							//pan2.setPrefHeight(bossBorderpane.getHeight()-menuBar.getHeight());
							//VboxTo.setPrefHeight(pan2.getHeight()*0.75);
						} );
						bossBorderpane.widthProperty().addListener(e->{
							//pan2.setPrefWidth(bossBorderpane.getWidth()*0.85);
							//VboxTo.setPrefWidth(pan2.getWidth());
						});

					}
				};aT.start();

				 */
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
	//action after result connectTest
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
	//myorginal launch is an methode who test if is the first launch or no,and make return the windows contain   at the main scene
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		VboxTo.setOnKeyReleased(e->{
			if(e.getCode() == KeyCode.ENTER) {
				loginT();
			}
		});

		/*
		 * btnConnection.setOnKeyReleased(new EventHandler<KeyEvent>() {
		 *
		 * @Override public void handle(KeyEvent arg0) {
		 * System.out.println(arg0.getCode());
		 *
		 * } });
		 */

		pan2.setStyle("-fx-background-image: url(/ressources/image/test.jpg)");
		about.setGraphic(new ImageView(getClass().getResource("/ressources/icone/10.png").toString()));
		//test of if database is right
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
}

package javafxsolo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafxsolo.Modele.Connect;

/*
 * @je suis josephHo,novenosexta77@gmail.com
 * j'ai fais l'effort d'ecrire les bloc important en anglais
 * l'interphace du programe n'est pas tr�s originale desol�.
 * bon maintenenant allons-y:
 * a main class extends Application and contain the main methode,this this the enter point of the programm
 * 
 */
public class Main extends Application {

	@SuppressWarnings("exports")
	public static Stage primaryStage ;
	
	@SuppressWarnings("exports")
	public static Stage myStage ;
	Parent foot;
	Parent root;
	@Override
	public  void start(@SuppressWarnings("exports") Stage primaryStage) {
			myStage = new Stage();
			myStage = primaryStage;

			//ImageView likeImageView = new ImageView(getClass().getResource("../ressources/icone/heart.png").toString());
			

			//myStage.setFullScreen(false);
			
			//myStage.show();
			Image rView = new Image(getClass().getResource("/ressources/icone/h.png").toString());
			

			myStage.setTitle("JinCoffre " + Connect.progV());
			myStage.getIcons().add(rView);
			myOriginalLaunch();
		   myStage.setMinHeight(603.0);
		   myStage.setMaxHeight(954.0);
			//myStage.setMaxWidth(1000);
			//myStage.setResizable(false);
			myStage.show();
			myStage.centerOnScreen();
			/*
			 pour créer le scrennshot
			WritableImage  screen=  root.snapshot(null, null);
			File f = new File("image.png");
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(screen, null), "png", f);
			} catch (IOException e) {

				e.printStackTrace();
			}
			*/
			/*
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
						Platform.runLater(() -> {
							primaryStage.setScene(otherScene);
							primaryStage.show();
							otherStage.show();
						});
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}).start();

			 */
		
	}
private void myOriginalLaunch() {
	try {

		
		if(Connect.fristLaunchTest()) {

			foot = FXMLLoader.load(getClass().getResource("/ressources/fxml/Firstlogin.fxml"));
			Scene  otherScene= new Scene(foot);
			myStage.setScene(otherScene);
		}
		else {
			 root = FXMLLoader.load(getClass().getResource("/ressources/fxml/Main.fxml"));
			Scene  otherScene= new Scene(root);
			myStage.setScene(otherScene);
			
		}
		
	}
	catch(Exception e) {	
		e.printStackTrace();
	}
}
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}

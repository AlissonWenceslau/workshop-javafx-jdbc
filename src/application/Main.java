package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Carrega o caminho completo da tela que será aberta
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			//Carrega a hierarquia de objetos do FXML
			ScrollPane scrollPane = loader.load();
			
			//Ajusta ScrollPanel conforme janela
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			//Instancia da scena
			mainScene = new Scene(scrollPane);
			//Especifica a cena a ser usada neste stage.
			primaryStage.setScene(mainScene);
			//Define o valor da propriedade redimensionável.
			primaryStage.setResizable(false);
			//Especifica o título do stage
			primaryStage.setTitle("Sample JavaFX application");
			//Mostra o stage (Janela)
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		//Inicia o aplicativo
		launch(args);
	}
}

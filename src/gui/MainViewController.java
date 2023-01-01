package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller)->{
			controller.setService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializing) {
		try {
			//Carrega o caminho completo da tela que será aberta
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			//Carrega a hierarquia de objetos do FXML
			VBox newVbox = loader.load();
			
			//Carrega uma referência da scena principal
			Scene mainScene = Main.getMainScene();
			//Carrega uma referência ao VBox
			VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			//Carrega o MenuBar antes de limpar os filhos do VBox
			Node mainMenu = mainVBox.getChildren().get(0);
			//Limpa os filhos do VBox
			mainVBox.getChildren().clear();
			
			//Configura novamente o MenuBar
			mainVBox.getChildren().add(mainMenu);
			//Acrescenta os objetos da view chamada
			mainVBox.getChildren().addAll(newVbox);
			
			//Carrega um objeto do tipo generico
			T controller = loader.getController();
			initializing.accept(controller);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}

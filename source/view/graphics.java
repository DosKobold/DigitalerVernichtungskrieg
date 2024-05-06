//package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class graphics extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		Button btn1 = new Button("Hello World");
		StackPane root = new StackPane();
		root.getChildren().add(btn1);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("DigitalerVernichtungskrieg");
		primaryStage.show();
	}

	public static void main (String[] args) {
		launch(args);
	}

}

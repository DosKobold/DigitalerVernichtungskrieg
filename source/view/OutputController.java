
package view;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.text.Font;
import javafx.fxml.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

public class OutputController implements Initializable {

	@FXML
	private Canvas canvas;
	private GraphicsContext gc;


	@FXML 
	public void OutputController() {
		gc = canvas.getGraphicsContext2D();
	}

	@FXML
	public void drawField(int xPos, int yPos, Image image) {
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(image, 32*xPos, 32*yPos);
	}
	
	public void markAsChoosen(int xPos, int yPos) {
		// TODO
	}

	public void markAsMarked(int xPos, int yPos) {
		// TODO
	}

	@FXML
	public void drawText(String[] texts) {
		// TODO
		/*text0.setText(texts[0]);
		text1.setText(texts[1]);
		text2.setText(texts[2]);*/
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*canvas = Canvas.getInstance();
		canvas = this.canvas;
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(1.0, 1.0, 80.0, 80.0);*/
	}
}

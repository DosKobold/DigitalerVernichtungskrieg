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
import javafx.scene.text.Text;
import javafx.fxml.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;

public class OutputController implements Initializable {

	@FXML
	private StackPane stackPane1;
	@FXML
	private Canvas canvas;
	private GraphicsContext gc;
	@FXML
	private Text text0;
	@FXML
	private Text text1;
	@FXML
	private Text text2;


	@FXML
	public void initialize() {
		gc = canvas.getGraphicsContext2D();
		ResizableCanvas resCanvas = new ResizableCanvas();
		stackPane1.getChildren().add(resCanvas);
		GraphicsContext gc2 = resCanvas.getGraphicsContext2D();
		resCanvas.widthProperty().bind(stackPane1.widthProperty());
		resCanvas.heightProperty().bind(stackPane1.heightProperty());
		stackPane1.widthProperty().addListener((obs, oldVal,newVal) -> {
			double width = stackPane1.getWidth();
			double height = stackPane1.getHeight();
			//resCanvas.setWidth(width);
			//resCanvas.setHeight(height);
			gc2.clearRect(0, 0, width, height);
			gc2.setStroke(Color.RED);
			gc2.strokeLine(0, 0, width, height);
			gc2.strokeLine(0, height, width, 0);
		});
	}

	@FXML
	public void drawField(int xPos, int yPos, int xMax, int yMax, Image image) {
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(image, (canvas.getWidth()/xMax)*xPos, (canvas.getHeight()/yMax)*yPos, canvas.getWidth()/xMax, canvas.getHeight()/yMax);
	}
	
	public void markAsChoosen(int xPos, int yPos) {
		// TODO
	}

	public void markAsMarked(int xPos, int yPos) {
		// TODO
	}

	@FXML
	public void drawText(String[] texts) {
		text0.setText(texts[0]);
		text1.setText(texts[1]);
		text2.setText(texts[2]);
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

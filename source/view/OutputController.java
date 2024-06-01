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

public class OutputController {

	@FXML
	private StackPane stackPane1;
	@FXML
	private Text text0;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	
	private ResizableCanvas canvas;
	private GraphicsContext gc;
	private ArrayList<ArrayList<ArrayList<Image>>> frameBuffer;

	public void initialize() {
		canvas = new ResizableCanvas();
		stackPane1.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		canvas.widthProperty().bind(stackPane1.widthProperty());
		canvas.heightProperty().bind(stackPane1.heightProperty());
		stackPane1.widthProperty().addListener((obs, oldVal,newVal) -> draw(frameBuffer));
		stackPane1.heightProperty().addListener((obs, oldVal,newVal) -> draw(frameBuffer));
	}

	public void draw(ArrayList<ArrayList<ArrayList<Image>>> frame) {
		System.out.println("Platzhalter für Fenstergröße");
		frameBuffer = frame;

		final int xMax = frame.get(0).get(0).size();
		final int yMax = frame.get(0).size();
		final double width = canvas.getWidth();
		final double height = canvas.getHeight();

		final double size;
		double xMargin = 0;
		double yMargin = 0;

		if ((width/xMax) < (height/yMax)) {
			size = width/xMax;
			yMargin = (height-(yMax*size))/2;
		} else {
			size = height/yMax;
			xMargin = (width-(xMax*size))/2;
		}

		gc.clearRect(0, 0, width, height);
		for (int layerNo=0; layerNo<frame.size(); layerNo++) {
			for (int lineNo=0;lineNo<frame.get(layerNo).size(); lineNo++) {
				for (int fieldNo=0; fieldNo<frame.get(layerNo).get(lineNo).size(); fieldNo++) {
					final int xPos = fieldNo;
					final int yPos = lineNo;
					Image image = frame.get(layerNo).get(lineNo).get(fieldNo);
					gc.drawImage(image, xPos*size+xMargin, yPos*size+yMargin, size, size);
				}
			}
		}
	}
	
	public void drawText(String[] texts) {
		text0.setText(texts[0]);
		text1.setText(texts[1]);
		text2.setText(texts[2]);
	}

}

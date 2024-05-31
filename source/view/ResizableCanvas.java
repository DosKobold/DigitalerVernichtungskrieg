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

public class ResizableCanvas extends Canvas {

	public ResizableCanvas() {
		/*widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());*/
	}

	private void draw() {
		/*double width = getWidth();
		System.out.println(width);
		double height = getHeight();
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);
		gc.setStroke(Color.RED);
		gc.strokeLine(0, 0, width, height);
		gc.strokeLine(0, height, width, 0);*/
	}

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double prefWidth(double height) {
		return 0;
	}

	@Override
	public double prefHeight(double width) {
		return 0;
	}
}

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

	/* A own class for a resizable canvas is needed.
	 * The prefered width and height of the canvas 
	 * has to be 0, so it doesn't block the parent
	 * pane in resizing it self.
	 */

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

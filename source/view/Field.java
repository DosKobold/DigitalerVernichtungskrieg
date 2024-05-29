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

public class Field {
	
	private Image background;
	private Image foreground;
	private boolean choosen = false;
	private boolean marked  = false;

	public void setBackground(Image background) {
		if (this.background != null)
			System.out.print("WARNING: Background is already set!");
		this.background = background;
	}

	public Image getBackground() {
		return background;
	}

	public void setForeground(Image foreground) {
		this.foreground = foreground;
	}

	public Image getForeground() {
		return foreground;
	}

	public void choosenStatus(boolean status) {
		choosen = status;
	}

	public boolean isChoosen() {
		return choosen;
	}

	public void markedStatus(boolean status) {
		marked = status;
	}

	public boolean isMarked() {
		return marked;
	}
}

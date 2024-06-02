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

public class Texture {

	private String imagePath = "file:../../textures/";
	private final Image plain    		= new Image(imagePath + "map/plain.png");
	private final Image wood     		= new Image(imagePath + "map/wood.png");
	private final Image mountain 		= new Image(imagePath + "map/mountain.png");
	private final Image sea      		= new Image(imagePath + "map/sea.png");

	private final Image infantry 		= new Image(imagePath + "troops/infantry.png");
	private final Image mechanizedInfantry  = new Image(imagePath + "troops/mechanizedInfantry.png");
	private final Image tank 		= new Image(imagePath + "troops/tank.png");
	private final Image mobileArtillery 	= new Image(imagePath + "troops/mobileArtillery.png");
	private final Image antiAir		= new Image(imagePath + "troops/antiAir.png");
	private final Image fighter 		= new Image(imagePath + "troops/fighter.png");
	private final Image bomber 		= new Image(imagePath + "troops/bomber.png");
	private final Image battleCopter 	= new Image(imagePath + "troops/battleCopter.png");

	private final Image choosen		= new Image(imagePath + "choosen.png");

	private final Image marked		= new Image(imagePath + "marked.png");

	public Image getMapImage(char field) {
		switch (field) {
			case 'P':
				return plain;
			case 'W':
				return wood;
			case 'M':
				return mountain;
			case 'S':
				return sea;
			default:
				System.out.print("ERROR: Cannot load texture for following map-field: " + field);
		return null;
		}
	}

	public Image getTroopImage(char field) {
		switch (field) {
			case '_':
				return null;
			case 'I':
				return infantry;
			case 'i':
				return infantry;
			case 'M':
				return mechanizedInfantry;
			case 'm':
				return mechanizedInfantry;
			case 'T':
				return tank;
			case 't':
				return tank;
			case 'D':
				return mobileArtillery;
			case 'd':
				return mobileArtillery;
			case 'A':
				return antiAir;
			case 'a':
				return antiAir;
			case 'F':
				return fighter;
			case 'f':
				return fighter;
			case 'B':
				return bomber;
			case 'b':
				return bomber;
			case 'C':
				return battleCopter;
			case 'c':
				return battleCopter;
			default:
				System.out.print("ERROR: Cannot load texture for following troop-field: " + field);
		return null;
		}
	}

	public Image getChoosenImage(char field) {
		switch (field) {
			case '_':
				return null;
			case 'X':
				return choosen;
			default:
				System.out.print("ERROR: Cannot load texture for following choosen field: " + field);
		}
		return null;
	}

	public Image getMarkedImage(char field) {
		switch (field) {
			case '_':
				return null;
			case 'X':
				return marked;
			default:
				System.out.println("ERROR: Cannot load texture for following marked field: " + field);
		}
		return null;
	}
}

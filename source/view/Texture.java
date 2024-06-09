package view;

import javafx.scene.image.Image;

public class Texture {

	private final String imagePath = "file:../../textures/";
	private final String errorPath = imagePath + "error.png";

	private final Image plain    			= loadTexture(imagePath + "map/plain.png");
	private final Image wood     			= loadTexture(imagePath + "map/wood.png");
	private final Image mountain 			= loadTexture(imagePath + "map/mountain.png");
	private final Image sea      			= loadTexture(imagePath + "map/sea.png");

	private final Image redInfantry 		= loadTexture(imagePath + "troops/red/infantry.png");
	private final Image redMechanizedInfantry  	= loadTexture(imagePath + "troops/red/mechanizedInfantry.png");
	private final Image redTank 			= loadTexture(imagePath + "troops/red/tank.png");
	private final Image redMobileArtillery	 	= loadTexture(imagePath + "troops/red/mobileArtillery.png");
	private final Image redAntiAir			= loadTexture(imagePath + "troops/red/antiAir.png");
	private final Image redFighter 			= loadTexture(imagePath + "troops/red/fighter.png");
	private final Image redBomber 			= loadTexture(imagePath + "troops/red/bomber.png");
	private final Image redBattleCopter 		= loadTexture(imagePath + "troops/red/battleCopter.png");

	private final Image blueInfantry 		= loadTexture(imagePath + "troops/blue/infantry.png");
	private final Image blueMechanizedInfantry  	= loadTexture(imagePath + "troops/blue/mechanizedInfantry.png");
	private final Image blueTank 			= loadTexture(imagePath + "troops/blue/tank.png");
	private final Image blueMobileArtillery 	= loadTexture(imagePath + "troops/blue/mobileArtillery.png");
	private final Image blueAntiAir			= loadTexture(imagePath + "troops/blue/antiAir.png");
	private final Image blueFighter 		= loadTexture(imagePath + "troops/blue/fighter.png");
	private final Image blueBomber 			= loadTexture(imagePath + "troops/blue/bomber.png");
	private final Image blueBattleCopter 		= loadTexture(imagePath + "troops/blue/battleCopter.png");

	private final Image choosen			= loadTexture(imagePath + "choosen.png");

	private final Image marked			= loadTexture(imagePath + "marked.png");


	private Image loadTexture(String path) {
		Image texture = new Image(path);
		if (texture.isError()) {
			System.err.println("[view] WARNING: Cannot find the following image: " + path);
			texture = new Image("file:../../textures/error.png");
			if (texture.isError() && !(path == errorPath)) {
				System.err.println("[view] WARNING: Cannot find the following image: " + errorPath);
			}
		}
		return texture;
	}

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
				System.out.println("[view] WARNING: Cannot find texture for following map-field: " + field);
		return loadTexture(errorPath);
		}
	}

	public Image getTroopImage(char field) {
		switch (field) {
			case '_':
				return null;
			case 'I':
				return redInfantry;
			case 'i':
				return blueInfantry;
			case 'M':
				return redMechanizedInfantry;
			case 'm':
				return blueMechanizedInfantry;
			case 'T':
				return redTank;
			case 't':
				return blueTank;
			case 'D':
				return redMobileArtillery;
			case 'd':
				return blueMobileArtillery;
			case 'A':
				return redAntiAir;
			case 'a':
				return blueAntiAir;
			case 'F':
				return redFighter;
			case 'f':
				return blueFighter;
			case 'B':
				return redBomber;
			case 'b':
				return blueBomber;
			case 'C':
				return redBattleCopter;
			case 'c':
				return blueBattleCopter;
			default:
				System.out.println("[view] WARNING: Cannot find texture for following troop-field: " + field);
		return loadTexture(errorPath);
		}
	}

	public Image getChoosenImage(char field) {
		switch (field) {
			case '_':
				return null;
			case 'X':
				return choosen;
			default:
				System.out.println("[view] WARNING: Cannot find texture for following choosen field: " + field);
		}
		return loadTexture(errorPath);
	}

	public Image getMarkedImage(char field) {
		switch (field) {
			case '_':
				return null;
			case 'X':
				return marked;
			default:
				System.out.println("ERROR: Cannot find texture for following marked field: " + field);
		}
		return loadTexture(errorPath);
	}
}

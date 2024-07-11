package logic;

import view.*;
import javafx.application.Application;
import java.lang.Thread;

public class Main {

	public static void main (String[] args) {
		new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(view.Graphics.class);
			}
		}.start();
		view.Graphics graphics = view.Graphics.waitForStartUp();
		graphics.setText(1, "IT'S RUNNING!");
	}
}

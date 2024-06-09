package view;

import javafx.scene.canvas.Canvas;

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

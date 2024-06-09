package view;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class InputController {

	private KeyCode keyCode;

	public synchronized void setKey(KeyEvent keyEvent) {
		keyCode = keyEvent.getCode();
		System.out.println("[view] Key \"" + keyCode.getName() + "\" was pressed");
		notifyAll();
	}

	public synchronized KeyCode getKey() throws Exception {
		wait();
		KeyCode keyCode = this.keyCode;
		this.keyCode = null;
		return keyCode;
	}
}


#!/bin/ksh

javac -Xlint --module-path ../lib/javafx-sdk-22.0.1 --add-modules javafx.controls,javafx.fxml view/Graphics.java view/OutputController.java view/Data.java view/Texture.java view/ResizableCanvas.java view/InputController.java logic/Main.java

if [ $? -eq 0 ]; then
	echo "[$0] INFO: Succesfully compiled!"
	java -classpath . --module-path ../lib/javafx-sdk-22.0.1 --add-modules javafx.controls,javafx.fxml "logic.Main"
else
	echo "[$0] ERROR: Return value of javac not 0!"
fi


if [ $? -eq 0 ]; then
	echo "[$0] INFO: Successfully run!"
else
	echo "[$0] ERROR: Return value of java not 0!"
fi

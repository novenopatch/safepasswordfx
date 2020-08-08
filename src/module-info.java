module javafxsolo {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	requires java.desktop;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.swing;
	requires javafx.media;
	
	opens javafxsolo to javafx.fxml;
	exports javafxsolo to javafx.graphics;
	opens javafxsolo.Controller to javafx.fxml;

	exports javafxsolo.Controller to javafx.graphics;
}
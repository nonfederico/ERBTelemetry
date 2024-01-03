package it.erb.telemetry.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class View extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		Button btn = new Button();
		btn.setText("Connetti");
		btn.setOnAction(null);
		
		StackPane root = new StackPane();
		//root.getChildren().add(btn);
		Group group = new Group();
		
		LinearGradient paint = new LinearGradient(
				0.0, 0.0038, 0.0, 1.0, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.2316, 0.2316, 0.2316, 1.0)),
				new Stop(1.0, new Color(0.1053, 0.1053, 0.1053, 1.0)));
		
		
		root.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// Crea la scena
		Scene scene = new Scene(root, 1500, 850);
		scene.setFill(Color.BLACK);
		
		
		primaryStage.setTitle("ERB telemetry");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("file:Logo.png"));
		primaryStage.show();
			
		
	}
	
	public void run()
	{
		launch();		
	}
	
	
}

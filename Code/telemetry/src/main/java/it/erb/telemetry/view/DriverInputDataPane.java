package it.erb.telemetry.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

class DriverInputDataPane 
{
	VBox vb = new VBox();
	ProgressBar pb_throttle = new ProgressBar();
	ProgressBar pb_brake = new ProgressBar();
	Label lbl_throttleData;
	Label lbl_brakeData;
	UpwardProgress upb_throttle;
	UpwardProgress upb_brake;

	public DriverInputDataPane(String style_dataPane, String style_gridPane, String style_LblTitle, String style_lbl, String style_lblData)
	{
		// TITLE + GRID PANE
		vb.setStyle(style_dataPane);
		
		Label lbl_title = new Label("Driver input");
		Label lbl_throttle = new Label("T");
		Label lbl_brake = new Label("B");
		lbl_throttleData = new Label("50");
		lbl_brakeData = new Label("100");
		
		lbl_title.setStyle(style_LblTitle); 
		lbl_throttle.setStyle(style_lbl); 
		lbl_brake.setStyle(style_lbl); 
		lbl_throttleData.setStyle(style_lblData); 
		lbl_brakeData.setStyle(style_lblData); 
		
		upb_throttle = new UpwardProgress(20, 60);
		upb_brake = new UpwardProgress(20, 60);
		
		upb_brake.getProgressBar().setStyle("-fx-accent: red;");
		
		upb_throttle.getProgressBar().setProgress(0.5);
		upb_brake.getProgressBar().setProgress(0.9);
						
		GridPane gridPane = new GridPane();
		gridPane.setStyle(style_gridPane);
		gridPane.add(lbl_throttle, 0, 0);
		gridPane.add(lbl_brake, 1, 0);
		gridPane.add(upb_throttle.getProgressHolder(), 0, 1);
		gridPane.add(upb_brake.getProgressHolder(), 1, 1);
		gridPane.add(lbl_throttleData, 0, 2);
		gridPane.add(lbl_brakeData, 1, 2);
		gridPane.setMaxWidth(400);
		gridPane.setMaxHeight(200);
		
		vb.getChildren().add(lbl_title);
		vb.getChildren().add(gridPane);		
	}
		
	VBox getPane(){ return vb; }
	
	

}

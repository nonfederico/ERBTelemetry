package it.erb.telemetry.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DriverInputDataPane 
{
	VBox vb = new VBox();
	ProgressBar pb_throttle = new ProgressBar();
	ProgressBar pb_brake = new ProgressBar();
	DataLabel lbl_throttle;
	DataLabel lbl_brake;
	UpwardProgress upb_throttle;
	UpwardProgress upb_brake;

	public DriverInputDataPane(String style_gridPane)
	{
		// TITLE + GRID PANE
		vb.getStyleClass().add("dataPane");
		
		Label lbl_title = new Label("Driver input");
		lbl_throttle = new DataLabel("50",0,false);
		lbl_brake = new DataLabel("100",0,false);
		
		lbl_title.getStyleClass().add("titleLabel"); 
		
		upb_throttle = new UpwardProgress(25, 80);
		upb_brake = new UpwardProgress(25, 80);
		
		upb_brake.getProgressBar().setStyle("-fx-accent: red;");
		
		GridPane gridPane = new GridPane();
		gridPane.setStyle(style_gridPane);
		gridPane.add(new Label("T"), 0, 0);
		gridPane.add(new Label("B"), 1, 0);
		gridPane.add(upb_throttle.getProgressHolder(), 0, 1);
		gridPane.add(upb_brake.getProgressHolder(), 1, 1);
		gridPane.add(lbl_throttle, 0, 2);
		gridPane.add(lbl_brake, 1, 2);
		gridPane.setMaxWidth(400);
		gridPane.setMaxHeight(200);
		
		vb.getChildren().add(lbl_title);
		vb.getChildren().add(gridPane);		
	}
		
	VBox getPane(){ return vb; }
	
	public UpwardProgress getUpb_throttle() {
		return upb_throttle;
	}

	public void set_throttleProgressBar(double value) {
		upb_throttle.getProgressBar().setProgress(value);
	}

	public void set_brakeProgressBar(double value) {
		upb_brake.getProgressBar().setProgress(value);
	}

}

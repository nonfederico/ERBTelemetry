package it.erb.telemetry.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View 
{
	public Scene scene;
	
	public ChoiceBox chBox_comPort;
	
	public Button btn_comScan;
	
	public Label lbl_accTitle;
	public Label lbl_accVoltage;
	public Label lbl_accVoltageData;
	public Label lbl_accCurrent;
	public Label lbl_accCurrentData;
	public Label lbl_accPower;
	public Label lbl_accPowerData;
	public Label lbl_accTemp;
	public Label lbl_accTempData;
	
	String style_LblTitle = ""
			+ "-fx-text-fill: #eeeeee; "
			+ "-fx-font-weight: bold; "
			+ "-fx-font-size: 14; "
			+ "-fx-background-color: #ff0000;"
			+ "-fx-alignment: center; "
			+ "-fx-text-alignment: center; "; 
	String style_lbl = ""
			+ "-fx-text-fill: #eeeeee; ";
	String style_lblData = ""
			+ "-fx-text-fill: #eeeeee; "
			+ "-fx-font-weight: bold;"
			+ "-fx-font-size: 13; ";
	String style_circle = ""
			+ "-fx-fill: rgba(20, 150, 20, 0.8);"
			+ "-fx-stroke: rgba(150, 150, 150, 1); "
			+ "-fx-stroke-width: 0.5; ";
	String style_dataPane = ""
			+ "-fx-background-color: #333333";
	String style_gridPane = ""
			+ "-fx-background-color: rgba(150, 150, 150, 0.3)";
	
	
	public View()
	{
		
		btn_comScan = new Button();
		btn_comScan.setText("Connetti");
		btn_comScan.setOnAction(null);
		
		chBox_comPort = new ChoiceBox();
		chBox_comPort.setPrefWidth(100);
		
		
		// TOOL BAR COM port
		ToolBar tlb_com = new ToolBar();
		tlb_com.getItems().add(chBox_comPort);
		tlb_com.getItems().add(new Separator());
		tlb_com.getItems().add(btn_comScan);
		
		// HV accumulator data
		// TITLE + GRID PANE
		VBox vb_acc = new VBox();
		vb_acc.setStyle(style_dataPane);
		
		lbl_accTitle = new Label("HV Accumulator");
		lbl_accVoltage = new Label("Voltage");
		lbl_accVoltageData = new Label("750 V");
		lbl_accCurrent = new Label("Current");
		lbl_accCurrentData = new Label("100 A");
		lbl_accPower = new Label("Power");
		lbl_accPowerData = new Label("7000 kW");
		lbl_accTemp = new Label("Temperature");
		lbl_accTempData = new Label("56°C");
		
		lbl_accTitle.setStyle(style_LblTitle);
		lbl_accVoltage.setStyle(style_lbl); 
		lbl_accVoltageData.setStyle(style_lblData); 
		lbl_accCurrent.setStyle(style_lbl); 
		lbl_accCurrentData.setStyle(style_lblData); 
		lbl_accPower.setStyle(style_lbl); 
		lbl_accPowerData.setStyle(style_lblData); 
		lbl_accTemp.setStyle(style_lbl); 
		lbl_accTempData.setStyle(style_lblData); 
				
		// GRIDPANE
		GridPane gridPane_Acc = new GridPane();
		gridPane_Acc.setStyle(style_gridPane);
		gridPane_Acc.setAlignment(Pos.CENTER);
		gridPane_Acc.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane_Acc.setVgap(0); 
	    gridPane_Acc.setHgap(5);       
		gridPane_Acc.add(lbl_accVoltage, 0, 0);
		gridPane_Acc.add(lbl_accVoltageData, 1, 0);
		gridPane_Acc.add(lbl_accCurrent, 0, 1);
		gridPane_Acc.add(lbl_accCurrentData, 1, 1);
		gridPane_Acc.add(lbl_accPower, 0, 2);
		gridPane_Acc.add(lbl_accPowerData, 1, 2);
		gridPane_Acc.add(lbl_accTemp, 0, 3);
		gridPane_Acc.add(lbl_accTempData, 1, 3);
		gridPane_Acc.setMaxWidth(400);
		gridPane_Acc.setMaxHeight(200);
		
		vb_acc.getChildren().add(lbl_accTitle);
		vb_acc.getChildren().add(gridPane_Acc);
		
		// INVERTER data
		// TITLE + GRID PANE
		VBox vb_inv = new VBox();
		vb_inv.setStyle(style_dataPane);
		
		Label lbl_invTitle = new Label("Inverter");
		Label lbl_invTemp = new Label("Temperature");
		Label lbl_invTempData = new Label("101°C");
		Label lbl_hvVoltage = new Label("HV Voltage");
		Label lbl_hvVoltageData = new Label("567 V");
		Label lbl_lvVoltage = new Label("LV Voltage");
		Label lbl_lvVoltageData = new Label("23.65 V");
		
		lbl_invTitle.setStyle(style_LblTitle); 
		lbl_invTemp.setStyle(style_lbl);
		lbl_invTempData.setStyle(style_lblData);
		lbl_hvVoltage.setStyle(style_lbl); 
		lbl_hvVoltageData.setStyle(style_lblData); 
		lbl_lvVoltage.setStyle(style_lbl); 
		lbl_lvVoltageData.setStyle(style_lblData); 
				
		GridPane gridPane_inv = new GridPane();
		gridPane_inv.setStyle(style_gridPane);
		gridPane_inv.setAlignment(Pos.CENTER);
		gridPane_inv.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane_inv.setVgap(0); 
		gridPane_inv.setHgap(5);       
		gridPane_inv.add(lbl_invTemp, 0, 0);
		gridPane_inv.add(lbl_invTempData, 1, 0);
		gridPane_inv.add(lbl_hvVoltage, 0, 1);
		gridPane_inv.add(lbl_hvVoltageData, 1, 1);
		gridPane_inv.add(lbl_lvVoltage, 0, 2);
		gridPane_inv.add(lbl_lvVoltageData, 1, 2);
		gridPane_inv.setMaxWidth(400);
		gridPane_inv.setMaxHeight(200);
		
		vb_inv.getChildren().add(lbl_invTitle);
		vb_inv.getChildren().add(gridPane_inv);

		
		// MOTORS data
		// TITLE + GRID PANE
		VBox vb_mot = new VBox();
		vb_mot.setStyle(style_dataPane);
		
		Label lbl_motTitle = new Label("Motors");
		Label lbl_motActSpeed = new Label("Act. speed");
		Label lbl_motRLActSpeedData = new Label("4456.6 rpm");
		Label lbl_motRRActSpeedData = new Label("4560.7 rpm");
		Label lbl_motCmdSpeed = new Label("Cmd speed");
		Label lbl_motRLCmdSpeedData = new Label("4706.2 rpm");
		Label lbl_motRRCmdSpeedData = new Label("4706.2 rpm");
		Label lbl_motActTorque = new Label("Act. torque");
		Label lbl_motRLActTorqueData = new Label("5634 Nm");
		Label lbl_motRRActTorqueData = new Label("6374 Nm");
		Label lbl_motCmdTorque = new Label("Cmd torque");
		Label lbl_motRLCmdTorqueData = new Label("20000 Nm");
		Label lbl_motRRCmdTorqueData = new Label("20000 Nm");
		Label lbl_motActCurrent = new Label("Current");
		Label lbl_motRLActCurrentData = new Label("102 A");
		Label lbl_motRRActCurrentData = new Label("97 A");
		Label lbl_motActFrequency = new Label("Frequency");
		Label lbl_motRLActFrequencyData = new Label("48 Hz");
		Label lbl_motRRActFrequencyData = new Label("49 Hz");
		Label lbl_motTemperature = new Label("Temperature");
		Label lbl_motRLTemperatureData = new Label("86°C");
		Label lbl_motRRTemperatureData = new Label("92°C");
		
		lbl_motTitle.setStyle(style_LblTitle); 
		lbl_motActSpeed.setStyle(style_lbl); 
		lbl_motRLActSpeedData.setStyle(style_lblData); 
		lbl_motRRActSpeedData.setStyle(style_lblData); 
		lbl_motCmdSpeed.setStyle(style_lbl); 
		lbl_motRLCmdSpeedData.setStyle(style_lblData); 
		lbl_motRRCmdSpeedData.setStyle(style_lblData); 
		lbl_motActTorque.setStyle(style_lbl); 
		lbl_motRLActTorqueData.setStyle(style_lblData); 
		lbl_motRRActTorqueData.setStyle(style_lblData); 
		lbl_motCmdTorque.setStyle(style_lbl); 
		lbl_motRLCmdTorqueData.setStyle(style_lblData); 
		lbl_motRRCmdTorqueData.setStyle(style_lblData); 
		lbl_motActCurrent.setStyle(style_lbl); 
		lbl_motRLActCurrentData.setStyle(style_lblData); 
		lbl_motRRActCurrentData.setStyle(style_lblData); 
		lbl_motActFrequency.setStyle(style_lbl); 
		lbl_motRLActFrequencyData.setStyle(style_lblData); 
		lbl_motRRActFrequencyData.setStyle(style_lblData); 
		lbl_motTemperature.setStyle(style_lbl); 
		lbl_motRLTemperatureData.setStyle(style_lblData); 
		lbl_motRRTemperatureData.setStyle(style_lblData); 
		
		GridPane gridPane_mot = new GridPane();
		gridPane_mot.setStyle(style_gridPane);
		gridPane_mot.setAlignment(Pos.CENTER);
		gridPane_mot.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane_mot.setVgap(0); 
		gridPane_mot.setHgap(5);       
		gridPane_mot.add(lbl_motActSpeed, 0, 0);
		gridPane_mot.add(lbl_motRLActSpeedData, 1, 0);
		gridPane_mot.add(lbl_motRRActSpeedData, 2, 0);
		gridPane_mot.add(lbl_motCmdSpeed, 0, 1);
		gridPane_mot.add(lbl_motRLCmdSpeedData, 1, 1);
		gridPane_mot.add(lbl_motRRCmdSpeedData, 2, 1);
		gridPane_mot.add(lbl_motCmdTorque, 0, 2);
		gridPane_mot.add(lbl_motRLCmdTorqueData, 1, 2);
		gridPane_mot.add(lbl_motRRCmdTorqueData, 2, 2);
		gridPane_mot.add(lbl_motActCurrent, 0, 3);
		gridPane_mot.add(lbl_motRLActCurrentData, 1, 3);
		gridPane_mot.add(lbl_motRRActCurrentData, 2, 3);
		gridPane_mot.add(lbl_motActFrequency, 0, 4);
		gridPane_mot.add(lbl_motRLActFrequencyData, 1, 4);
		gridPane_mot.add(lbl_motRRActFrequencyData, 2, 4);
		gridPane_mot.add(lbl_motTemperature, 0, 5);
		gridPane_mot.add(lbl_motRLTemperatureData, 1, 5);
		gridPane_mot.add(lbl_motRRTemperatureData, 2, 5);
		gridPane_mot.setMaxWidth(400);
		gridPane_mot.setMaxHeight(200);
		
		vb_mot.getChildren().add(lbl_motTitle);
		vb_mot.getChildren().add(gridPane_mot);		

		// SAFETY CIRCUIT data
		// TITLE + GRID PANE
		VBox vb_saf = new VBox();
		vb_saf.setStyle(style_dataPane);
		
		Label lbl_safTitle = new Label("Safety circuit");
		Label lbl_safBSPD = new Label("BSPD");
		Label lbl_safIMD = new Label("IMD");
		Label lbl_safLVMS = new Label("LVMS");
		Label lbl_safAMS = new Label("AMS");
		Label lbl_safIS = new Label("IS");
		Label lbl_safBOTS = new Label("BOTS");
		Label lbl_safSDBCockpit = new Label("SDB Cockpit");
		Label lbl_safSDBLeft = new Label("SDB Left");
		Label lbl_safSDBRight = new Label("SDB Right");
		
		lbl_safTitle.setStyle(style_LblTitle); 
		lbl_safBSPD.setStyle(style_lbl);
		lbl_safIMD.setStyle(style_lbl);
		lbl_safLVMS.setStyle(style_lbl);
		lbl_safAMS.setStyle(style_lbl);
		lbl_safIS.setStyle(style_lbl);
		lbl_safBOTS.setStyle(style_lbl);
		lbl_safSDBCockpit.setStyle(style_lbl);
		lbl_safSDBLeft.setStyle(style_lbl);
		lbl_safSDBRight.setStyle(style_lbl);
		
		Circle circle_safBSPD = new Circle(5);
		Circle circle_safIMD = new Circle(5);
		Circle circle_safLVMS = new Circle(5);
		Circle circle_safAMS = new Circle(5);
		Circle circle_safIS = new Circle(5);
		Circle circle_safBOTS = new Circle(5);
		Circle circle_safSDBCockpit = new Circle(5);
		Circle circle_safSDBLeft = new Circle(5);
		Circle circle_safSDBRight = new Circle(5);
		       
		circle_safBSPD.setStyle(style_circle);
		circle_safIMD.setStyle(style_circle);
		circle_safLVMS.setStyle(style_circle);
		circle_safAMS.setStyle(style_circle);
		circle_safIS.setStyle(style_circle);
		circle_safBOTS.setStyle(style_circle);
		circle_safSDBCockpit.setStyle(style_circle);
		circle_safSDBLeft.setStyle(style_circle);
		circle_safSDBRight.setStyle(style_circle);
		
		GridPane gridPane_saf = new GridPane();
		gridPane_saf.setStyle(style_gridPane);
		gridPane_saf.setAlignment(Pos.CENTER);
		gridPane_saf.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane_saf.setVgap(0); 
		gridPane_saf.setHgap(5);       
		gridPane_saf.add(circle_safBSPD, 0, 0);
		gridPane_saf.add(lbl_safBSPD, 1, 0);
		gridPane_saf.add(circle_safIMD, 0, 1);
		gridPane_saf.add(lbl_safIMD, 1, 1);
		gridPane_saf.add(circle_safLVMS, 0, 2);
		gridPane_saf.add(lbl_safLVMS, 1, 2);
		gridPane_saf.add(circle_safAMS, 0, 3);		
		gridPane_saf.add(lbl_safAMS, 1, 3);
		gridPane_saf.add(circle_safIS, 0, 4);
		gridPane_saf.add(lbl_safIS, 1, 4);
		gridPane_saf.add(circle_safBOTS, 0, 5);
		gridPane_saf.add(lbl_safBOTS, 1, 5);
		gridPane_saf.add(circle_safSDBCockpit, 0, 6);
		gridPane_saf.add(lbl_safSDBCockpit, 1, 6);
		gridPane_saf.add(circle_safSDBLeft, 0, 7);
		gridPane_saf.add(lbl_safSDBLeft, 1, 7);
		gridPane_saf.add(circle_safSDBRight, 0, 8);
		gridPane_saf.add(lbl_safSDBRight, 1, 8);
		
		
		gridPane_saf.setMaxWidth(400);
		gridPane_saf.setMaxHeight(200);
		
		vb_saf.getChildren().add(lbl_safTitle);
		vb_saf.getChildren().add(gridPane_saf);

		// BOTTOM PANE
		// HV ACCUMULATOR + INVERTER + MOTORS + TYRES + SAFETY CIRCUIT + DRIVING
		HBox bottomPane = new HBox();
		bottomPane.getChildren().add(vb_acc);
		bottomPane.getChildren().add(vb_inv);
		bottomPane.getChildren().add(vb_mot);
		bottomPane.getChildren().add(vb_saf);
		//bottomPane.getChildren().add(vb_drv);
		//bottomPane.getChildren().add(vb_tst);
		
		// MAIN PANE
		BorderPane bPane = new BorderPane();
		bPane.setTop(tlb_com);
		bPane.setBottom(bottomPane);
		
		LinearGradient paint = new LinearGradient(
				0.0, 0.0038, 0.0, 1.0, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.2316, 0.2316, 0.2316, 1.0)),
				new Stop(1.0, new Color(0.1053, 0.1053, 0.1053, 1.0)));
		bPane.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// SCENE
		scene = new Scene(bPane, 1500, 850);
		scene.setFill(Color.BLACK);
		
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	
	
	
	
	
	
	
}

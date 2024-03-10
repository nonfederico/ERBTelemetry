package it.erb.telemetry.view;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
import eu.hansolo.medusa.Gauge.SkinType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/*
 * classe utilizzata per determinare le informazioni
 * che vengono visualizzate nel bottomPane
 */

public class DataSensorReal {
	
private HBox bottomPane = new HBox();
private VBox vb_speed = new VBox();

private GridPane gridPane_saf = new GridPane();
private VBox vb_saf = new VBox();
private GridPane gridPane_mot = new GridPane();
private VBox vb_mot = new VBox();
private GridPane gridPane_inv = new GridPane();
private VBox vb_inv = new VBox();
private VBox vb_lvAcc = new VBox();
private GridPane gridPane_HVAcc = new GridPane();
private VBox vb_HVAcc = new VBox();

public DataLabel lbl_HVAccVoltage;
public DataLabel lbl_HVAccCurrent;
public DataLabel lbl_HVAccPower;
public DataLabel lbl_HVAccTemp;

public Gauge gg_HVAcc;

public Gauge gg_LVAcc;
public Gauge gg_linearSpeed;


public DataLabel lbl_inv_Temperature;
public DataLabel lbl_inv_HVVoltage;
public DataLabel lbl_inv_LVVoltage;

public DataLabel lbl_motRLActSpeed;
public DataLabel lbl_motRRActSpeed;
public DataLabel lbl_motRLCmdSpeed;
public DataLabel lbl_motRRCmdSpeed;
public DataLabel lbl_motRLActTorque;
public DataLabel lbl_motRRActTorque;
public DataLabel lbl_motRLCmdTorque;
public DataLabel lbl_motRRCmdTorque;
public DataLabel lbl_motRLActCurrent;
public DataLabel lbl_motRRActCurrent;
public DataLabel lbl_motRLActFrequency;
public DataLabel lbl_motRRActFrequency;
public DataLabel lbl_motRLTemperature;
public DataLabel lbl_motRRTemperature;

public DataLabel lbl_LVAcc_Voltage;
public DataLabel lbl_LVAcc_Current;

public Led led_safBSPD;
public Led led_safIMD;
public Led led_safLVMS;
public Led led_safAMS;
public Led led_safIS;
public Led led_safBOTS;
public Led led_safSDBCockpit;
public Led led_safSDBLeft;
public Led led_safSDBRight;

public DriverInputDataPane driverInputDataPane = new DriverInputDataPane();
//è un Hbox
	public DataSensorReal() {
		
		
		lbl_HVAccVoltage = new DataLabel();
		lbl_HVAccCurrent= new DataLabel();
		lbl_HVAccPower= new DataLabel();
		lbl_HVAccTemp= new DataLabel();

		  gg_HVAcc= new Gauge();

		  gg_LVAcc= new Gauge();
		  gg_linearSpeed= new Gauge();


		  lbl_inv_Temperature= new DataLabel();
		  lbl_inv_HVVoltage= new DataLabel();
		  lbl_inv_LVVoltage= new DataLabel();

		  lbl_motRLActSpeed= new DataLabel();
		  lbl_motRRActSpeed= new DataLabel();
		  lbl_motRLCmdSpeed= new DataLabel();
		  lbl_motRRCmdSpeed= new DataLabel();
		  lbl_motRLActTorque= new DataLabel();
		  lbl_motRRActTorque= new DataLabel();
		  lbl_motRLCmdTorque= new DataLabel();
		  lbl_motRRCmdTorque= new DataLabel();
		  lbl_motRLActCurrent= new DataLabel();
		  lbl_motRRActCurrent= new DataLabel();
		  lbl_motRLActFrequency= new DataLabel();
		  lbl_motRRActFrequency= new DataLabel();
		  lbl_motRLTemperature= new DataLabel();
		  lbl_motRRTemperature= new DataLabel();

		  lbl_LVAcc_Voltage= new DataLabel();
		  lbl_LVAcc_Current= new DataLabel();

		  led_safBSPD = new Led();
		  led_safIMD= new Led();
		  led_safLVMS= new Led();
		  led_safAMS= new Led();
		  led_safIS= new Led();
		  led_safBOTS= new Led();
		  led_safSDBCockpit= new Led();
		  led_safSDBLeft= new Led();
		  led_safSDBRight= new Led();
	}
	
	
	
	public HBox getBottomPane() {
		driverInputDataPane = new DriverInputDataPane();
		bottomPane.getStyleClass().add("bottomPane");
		bottomPane.getChildren().add(getVb_HVAcc());
		bottomPane.getChildren().add(getVb_LVAcc());
		bottomPane.getChildren().add(getVb_inv());
		bottomPane.getChildren().add(getVb_mot());
		bottomPane.getChildren().add(getVb_saf());
		bottomPane.getChildren().add(driverInputDataPane.getPane());
		bottomPane.getChildren().add(getVb_speed());
		return bottomPane;
	}
	
//HV ccumulator data
private VBox getVb_HVAcc() {
		//titolo
		vb_HVAcc.getStyleClass().add("dataPane");
		lbl_HVAccVoltage = new DataLabel("-",1,true);
		lbl_HVAccCurrent = new DataLabel("-",1,true);
		lbl_HVAccPower = new DataLabel("-",0,true);
		lbl_HVAccTemp = new DataLabel("-",1,true);
		Label lbl_accTitle = new Label("HV Accumulator");
		lbl_accTitle.getStyleClass().add("titleLabel");
		
		//gridpane
		gridPane_HVAcc.getStyleClass().add("gridPane");
		gridPane_HVAcc.add(new Label("Voltage"), 0, 0);
		gridPane_HVAcc.add(lbl_HVAccVoltage, 1, 0);
		gridPane_HVAcc.add(new Label("Current"), 0, 1);
		gridPane_HVAcc.add(lbl_HVAccCurrent, 1, 1);
		gridPane_HVAcc.add(new Label("Power"), 0, 2);
		gridPane_HVAcc.add(lbl_HVAccPower, 1, 2);
		gridPane_HVAcc.add(new Label("Temperature"), 0, 3);
		gridPane_HVAcc.add(lbl_HVAccTemp, 1, 3);
		gridPane_HVAcc.setMaxWidth(400);
		gridPane_HVAcc.setMaxHeight(200);
		
		
		gg_HVAcc = GaugeBuilder.create()
				.skinType(SkinType.SIMPLE_SECTION)
				.prefSize(90,90)  
				.minValue(0)                                                                     
                .maxValue(100) 
                .title("")
                .barColor(Color.GREEN)
                .valueColor(Color.WHITE)
                .decimals(1)  
                .sectionsAlwaysVisible(true)
                .sectionsVisible(true)
                .highlightSections(true)
                .sections(new Section(0, 30, Color.RED), new Section(30, 50, Color.ORANGE), new Section(50, 100, Color.GREEN))
				.build();
		gg_HVAcc.setStyle("-fx-padding: 10;");
		gg_HVAcc.setValue(90);
		
		vb_HVAcc.getChildren().add(lbl_accTitle);
		vb_HVAcc.getChildren().add(gg_HVAcc);
		vb_HVAcc.getChildren().add(gridPane_HVAcc);
		return vb_HVAcc;
	}

//lv accumulator data
private VBox getVb_LVAcc() {
	vb_lvAcc.getStyleClass().add("dataPane");
	
	Label lbl_LVAccTitle = new Label("LV Accumulator");
	lbl_LVAccTitle.getStyleClass().add("titleLabel");
	lbl_LVAcc_Voltage = new DataLabel("-",1,true);
	lbl_LVAcc_Current = new DataLabel("-",1,true);
			
	gg_LVAcc = GaugeBuilder.create()
			.skinType(SkinType.SIMPLE_SECTION)
			.prefSize(90,90)  
			.minValue(0)                                                                     
            .maxValue(100) 
            .title("")
            .barColor(Color.GREEN)
            .valueColor(Color.WHITE)
            .decimals(1)   
            .build();
	gg_LVAcc.setStyle("-fx-padding: 10; ");
	gg_LVAcc.setValue(90);
	
	GridPane gridPane_lvAcc = new GridPane();
	gridPane_lvAcc.getStyleClass().add("gridPane");
	gridPane_lvAcc.add(new Label("Voltage"), 0, 0);
	gridPane_lvAcc.add(lbl_LVAcc_Voltage, 1, 0);
	gridPane_lvAcc.add(new Label("Current"), 0, 1);
	gridPane_lvAcc.add(lbl_LVAcc_Current, 1, 1);
	gridPane_lvAcc.setMaxWidth(400);
	gridPane_lvAcc.setMaxHeight(200);
	
	vb_lvAcc.getChildren().add(lbl_LVAccTitle);
	vb_lvAcc.getChildren().add(gg_LVAcc);
	vb_lvAcc.getChildren().add(gridPane_lvAcc);
	return vb_lvAcc;
}

//inverter data
public VBox getVb_inv() {
		//titolo
		vb_inv.getStyleClass().add("dataPane");
		
		Label lbl_invTitle = new Label("Inverter");
		lbl_invTitle.getStyleClass().add("titleLabel");
		lbl_inv_Temperature = new DataLabel("-",1,true);
		lbl_inv_HVVoltage = new DataLabel("-",1,true);
		lbl_inv_LVVoltage = new DataLabel("-",1,true);
	
		vb_inv.getChildren().add(lbl_invTitle);
		vb_inv.getChildren().add(gridPane_inv);
		//gridpane
		gridPane_inv.getStyleClass().add("gridPane");
		gridPane_inv.add(new Label("Temperature"), 0, 0);
		gridPane_inv.add(lbl_inv_Temperature, 1, 0);
		gridPane_inv.add(new Label("HV Voltage"), 0, 1);
		gridPane_inv.add(lbl_inv_HVVoltage, 1, 1);
		gridPane_inv.add(new Label("LV Voltage"), 0, 2);
		gridPane_inv.add(lbl_inv_LVVoltage, 1, 2);
		gridPane_inv.setMaxWidth(400);
		gridPane_inv.setMaxHeight(200);
		
		return vb_inv;
	}
	
//motor data
public VBox getVb_mot() {
		vb_mot.getStyleClass().add("dataPane");
		
		
		lbl_motRLActSpeed = new DataLabel("-",1,true);
		lbl_motRRActSpeed = new DataLabel("-",1,true);
		lbl_motRLCmdSpeed = new DataLabel("-",1,true);
		lbl_motRRCmdSpeed = new DataLabel("-",1,true);
		lbl_motRLActTorque = new DataLabel("-",0,true);
		lbl_motRRActTorque = new DataLabel("-",0,true);
		lbl_motRLCmdTorque = new DataLabel("-",0,true);
		lbl_motRRCmdTorque = new DataLabel("-",0,true);
		lbl_motRLActCurrent = new DataLabel("-",1,true);
		lbl_motRRActCurrent = new DataLabel("-",1,true);
		lbl_motRLActFrequency = new DataLabel("-",1,true);
		lbl_motRRActFrequency = new DataLabel("-",1,true);
		lbl_motRLTemperature = new DataLabel("-",1,true);
		lbl_motRRTemperature = new DataLabel("-",1,true);
			
		Label lbl_motTitle = new Label("Motors");
		lbl_motTitle.getStyleClass().add("titleLabel");
		
		gridPane_mot.getStyleClass().add("gridPane");
		gridPane_mot.add(new Label("Act. speed"), 0, 0);
		gridPane_mot.add(lbl_motRLActSpeed, 1, 0);
		gridPane_mot.add(lbl_motRRActSpeed, 2, 0);
		gridPane_mot.add(new Label("Cmd speed"), 0, 1);
		gridPane_mot.add(lbl_motRLCmdSpeed, 1, 1);
		gridPane_mot.add(lbl_motRRCmdSpeed, 2, 1);
		gridPane_mot.add(new Label("Act. torque"), 0, 2);
		gridPane_mot.add(lbl_motRLActTorque, 1, 2);
		gridPane_mot.add(lbl_motRRActTorque, 2, 2);
		gridPane_mot.add(new Label("Cmd torque"), 0, 3);
		gridPane_mot.add(lbl_motRLCmdTorque, 1, 3);
		gridPane_mot.add(lbl_motRRCmdTorque, 2, 3);
		gridPane_mot.add(new Label("Current"), 0, 4);
		gridPane_mot.add(lbl_motRLActCurrent, 1, 4);
		gridPane_mot.add(lbl_motRRActCurrent, 2, 4);
		gridPane_mot.add(new Label("Frequency"), 0, 5);
		gridPane_mot.add(lbl_motRLActFrequency, 1, 5);
		gridPane_mot.add(lbl_motRRActFrequency, 2, 5);
		gridPane_mot.add(new Label("Temperature"), 0, 6);
		gridPane_mot.add(lbl_motRLTemperature, 1, 6);
		gridPane_mot.add(lbl_motRRTemperature, 2, 6);
		gridPane_mot.setMaxWidth(400);
		gridPane_mot.setMaxHeight(200);
		
		vb_mot.getChildren().add(lbl_motTitle);
		vb_mot.getChildren().add(gridPane_mot);	
		
		return vb_mot;
		}

	
//safety circuit data
public VBox getVb_saf() {
vb_saf.getStyleClass().add("dataPane");
Label lbl_safTitle = new Label("Safety circuit");
lbl_safTitle.getStyleClass().add("titleLabel");

		led_safBSPD = new Led(5);
		led_safIMD = new Led(5);
		led_safLVMS = new Led(5);
		led_safAMS = new Led(5);
		led_safIS = new Led(5);
		led_safBOTS = new Led(5);
		led_safSDBCockpit = new Led(5);
		led_safSDBLeft = new Led(5);
		led_safSDBRight = new Led(5);
		
		gridPane_saf.getStyleClass().add("gridPane");
		gridPane_saf.add(led_safBSPD, 0, 0);
		gridPane_saf.add(new Label("BSPD"), 1, 0);
		gridPane_saf.add(led_safIMD, 0, 1);
		gridPane_saf.add(new Label("IMD"), 1, 1);
		gridPane_saf.add(led_safLVMS, 0, 2);
		gridPane_saf.add(new Label("LVMS"), 1, 2);
		gridPane_saf.add(led_safAMS, 0, 3);		
		gridPane_saf.add(new Label("AMS"), 1, 3);
		gridPane_saf.add(led_safIS, 0, 4);
		gridPane_saf.add(new Label("IS"), 1, 4);
		gridPane_saf.add(led_safBOTS, 0, 5);
		gridPane_saf.add(new Label("BOTS"), 1, 5);
		gridPane_saf.add(led_safSDBCockpit, 0, 6);
		gridPane_saf.add(new Label("SDB Cockpit"), 1, 6);
		gridPane_saf.add(led_safSDBLeft, 0, 7);
		gridPane_saf.add(new Label("SDB Left"), 1, 7);
		gridPane_saf.add(led_safSDBRight, 0, 8);
		gridPane_saf.add(new Label("SDB Right"), 1, 8);
		gridPane_saf.setMaxWidth(400);
		gridPane_saf.setMaxHeight(200);
		
		vb_saf.getChildren().add(gridPane_saf);
		vb_saf.getChildren().add(lbl_safTitle);
		
		return vb_saf;
	}
	
//speed data
public VBox getVb_speed() {
vb_speed.getStyleClass().add("dataPane");
		
		Label lbl_vehicleTitle = new Label("Speed");
		lbl_vehicleTitle.getStyleClass().add("titleLabel");
			
		gg_linearSpeed = GaugeBuilder.create()
				.skinType(SkinType.SPACE_X)
				.prefSize(150,150)  
				.unit("km/h")
				.maxValue(200)
				.threshold(150)
				.build();
		vb_speed.getChildren().addAll(lbl_vehicleTitle, gg_linearSpeed);
		return vb_speed;
	}
	
	}

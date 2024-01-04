package it.erb.telemetry.view;

import eu.hansolo.medusa.*;
import eu.hansolo.medusa.Gauge.KnobType;
import eu.hansolo.medusa.Gauge.LedType;
import eu.hansolo.medusa.Gauge.NeedleShape;
import eu.hansolo.medusa.Gauge.NeedleSize;
import eu.hansolo.medusa.Gauge.ScaleDirection;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.skins.ModernSkin;
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

public class View extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		// STYLE
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
		
		Button btn = new Button();
		btn.setText("Connetti");
		btn.setOnAction(null);
		//btn.setStyle("-fx-background-color: #ff0000; ");
		Button button1 = new Button("Button 1");
        Button button2 = new Button("Button Number 2");
        Button button3 = new Button("Button No 3");
        Button button4 = new Button("Button No 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button Number 6");
		
		ChoiceBox choiceBox = new ChoiceBox();
		choiceBox.setPrefWidth(100);
		choiceBox.getItems().add("Choice 1");
		choiceBox.getItems().add("Choice 2");
		choiceBox.getItems().add("Choice 3");
		
		// TOOL BAR COM port
		ToolBar tlb_com = new ToolBar();
		tlb_com.getItems().add(choiceBox);
		tlb_com.getItems().add(new Separator());
		tlb_com.getItems().add(btn);
		
		
		// HV accumulator data
		// TITLE + GRID PANE
		VBox vb_acc = new VBox();
		vb_acc.setStyle(style_dataPane);
		
		Label acc_title = new Label("HV Accumulator");
		acc_title.setAlignment(Pos.CENTER);
		acc_title.setStyle(style_LblTitle); 
		
		Label label1 = new Label("Voltage");
		label1.setAlignment(Pos.BOTTOM_LEFT);
		label1.setStyle(style_lbl); 
		
		Label label2 = new Label("750 V");
		label2.setStyle(style_lblData); 
		
		Label label3 = new Label("Current");
		label3.setStyle(style_lbl); 
		
		Label label4 = new Label("100 A");
		label4.setStyle(style_lblData); 
		
		Label label5 = new Label("Power");
		label5.setStyle(style_lbl); 
				
		Label label6 = new Label("7000 kW");
		label6.setStyle(style_lblData); 
		
		Label label7 = new Label("Temperature");
		label7.setStyle(style_lbl); 
		
		Label label8 = new Label("56°C");
		label8.setStyle(style_lblData); 
		
		Gauge gauge = GaugeBuilder.create()
                .skinType(SkinType.GAUGE)                                                        // Skin for your Gauge
                .prefSize(500,500)                                                               // Preferred size of control
                // Related to Foreground Elements
                .foregroundBaseColor(Color.BLACK)                                                // Color for title, subtitle, unit, value, tick label, zeroColor, tick mark, major tick mark, medium tick mark and minor tick mark
                // Related to Title Text
                .title("Title")                                                                  // Text for title
                .titleColor(Color.BLACK)                                                         // Color for title text
                // Related to Sub Title Text
                .subTitle("SubTitle")                                                            // Text for subtitle
                .subTitleColor(Color.BLACK)                                                      // Color for subtitle text
                // Related to Unit Text
                .unit("Unit")                                                                    // Text for unit
                .unitColor(Color.BLACK)                                                          // Color for unit text
                // Related to Value Text
                .valueColor(Color.BLACK)                                                         // Color for value text
                .decimals(0)                                                                     // Number of decimals for the value/lcd text
                // Related to LCD
                .lcdVisible(false)                                                               // LCD instead of the plain value text
                .lcdDesign(LcdDesign.STANDARD)                                                   // Design for LCD
                .lcdFont(LcdFont.DIGITAL_BOLD)                                                   // Font for LCD (STANDARD, LCD, DIGITAL, DIGITAL_BOLD, ELEKTRA)
                // Related to scale
                .scaleDirection(ScaleDirection.CLOCKWISE)                                        // Direction of Scale (CLOCKWISE, COUNTER_CLOCKWISE)
                .minValue(0)                                                                     // Start value of Scale
                .maxValue(100)                                                                   // End value of Scale
                .startAngle(320)                                                                 // Start angle of Scale (bottom -> 0, direction -> CCW)
                .angleRange(280)                                                                 // Angle range of Scale starting from the start angle
                // Related to Tick Labels
                .tickLabelDecimals(0)                                                            // Number of decimals for tick labels
                .tickLabelLocation(TickLabelLocation.INSIDE)                                     // Should tick labels be inside or outside Scale (INSIDE, OUTSIDE)
                .tickLabelOrientation(TickLabelOrientation.HORIZONTAL)                           // Orientation of tick labels (ORTHOGONAL,  HORIZONTAL, TANGENT)
                .onlyFirstAndLastTickLabelVisible(false)                                         // Should only the first and last tick label be visible
                .tickLabelSectionsVisible(false)                                                 // Should sections for tick labels be visible
                .tickLabelColor(Color.BLACK)                                                     // Color for tick labels (overriden by tick label sections)
                // Related to Tick Marks
                .tickMarkSectionsVisible(false)                                                  // Should sections for tick marks be visible
                // Related to Major Tick Marks
                .majorTickMarksVisible(true)                                                     // Should major tick marks be visible
                .majorTickMarkType(TickMarkType.LINE)                                            // Tick mark type for major tick marks (LINE, DOT, TRIANGLE, TICK_LABEL)
                .majorTickMarkColor(Color.BLACK)                                                 // Color for major tick marks (overriden by tick mark sections)
                // Related to Medium Tick Marks
                .mediumTickMarksVisible(true)                                                    // Should medium tick marks be visible
                .mediumTickMarkType(TickMarkType.LINE)                                           // Tick mark type for medium tick marks (LINE, DOT, TRIANGLE)
                .mediumTickMarkColor(Color.BLACK)                                                // Color for medium tick marks (overriden by tick mark sections)
                // Related to Minor Tick Marks
                .minorTickMarksVisible(true)                                                     // Should minor tick marks be visible
                .minorTickMarkType(TickMarkType.LINE)                                            // Tick mark type for minor tick marks (LINE, DOT, TRIANGLE)
                .minorTickMarkColor(Color.BLACK)                                                 // Color for minor tick marks (override by tick mark sections)
                // Related to LED
                .ledVisible(false)                                                               // Should LED be visible
                .ledType(LedType.STANDARD)                                                       // Type of the LED (STANDARD, FLAT)
                .ledColor(Color.rgb(255, 200, 0))                                                // Color of LED
                .ledBlinking(false)                                                              // Should LED blink
                .ledOn(false)                                                                    // LED on or off
                // Related to Needle
                .needleShape(NeedleShape.ANGLED)                                                 // Shape of needle (ANGLED, ROUND, FLAT)
                .needleSize(NeedleSize.STANDARD)                                                 // Size of needle (THIN, STANDARD, THICK)
                .needleColor(Color.CRIMSON)                                                      // Color of needle
                // Related to Needle behavior
                .startFromZero(false)                                                            // Should needle start from the 0 value
                .returnToZero(false)                                                             // Should needle return to the 0 value (only makes sense when animated==true)
                // Related to Knob
                .knobType(KnobType.STANDARD)                                                     // Type for center knob (STANDARD, PLAIN, METAL, FLAT)
                .knobColor(Color.LIGHTGRAY)                                                      // Color of center knob
                .interactive(false)                                                              // Should center knob be act as button
                // Related to Threshold
                .thresholdVisible(false)                                                         // Should threshold indicator be visible
                .threshold(50)                                                                   // Value of threshold
                .thresholdColor(Color.RED)                                                       // Color of threshold indicator
                .checkThreshold(false)                                                           // Should each value be checked against threshold
                // Related to Gradient Bar
                .gradientBarEnabled(false)                                                       // Should gradient filled bar be visible to visualize a range
                .gradientBarStops(new Stop(0.0, Color.BLUE),                                     // Color gradient that will be use to color fill bar
                                  new Stop(0.25, Color.CYAN),
                                  new Stop(0.5, Color.LIME),
                                  new Stop(0.75, Color.YELLOW),
                                  new Stop(1.0, Color.RED))
                // Related to Sections
                .sectionsVisible(false)                                                          // Should sections be visible
                .checkSectionsForValue(false)                                                    // Should each section be checked against current value (if true section events will be fired)
                // Related to Areas
                .areasVisible(false)                                                             // Should areas be visible
                // Related to Markers
                .markersVisible(false)                                                           // Should markers be visible
                // Related to Value
                .animated(false)                                                                 // Should needle be animated
                .animationDuration(500)                                                          // Speed of needle in milliseconds (10 - 10000 ms)
                .build();
		
		
		
		// GRIDPANE
		GridPane gridPane_Acc = new GridPane();
		gridPane_Acc.setStyle(style_gridPane);
		gridPane_Acc.setAlignment(Pos.CENTER);
		gridPane_Acc.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane_Acc.setVgap(0); 
	    gridPane_Acc.setHgap(5);       
		gridPane_Acc.add(label1, 0, 0);
		gridPane_Acc.add(label2, 1, 0);
		gridPane_Acc.add(label3, 0, 1);
		gridPane_Acc.add(label4, 1, 1);
		gridPane_Acc.add(label5, 0, 2);
		gridPane_Acc.add(label6, 1, 2);
		gridPane_Acc.add(label7, 0, 3);
		gridPane_Acc.add(label8, 1, 3);
		//gridPane.add(gauge, 0, 4);
		gridPane_Acc.setMaxWidth(400);
		gridPane_Acc.setMaxHeight(200);
		
		vb_acc.getChildren().add(acc_title);
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

		// DRIVING data
		// TITLE + GRID PANE
		VBox vb_drv = new VBox();
		vb_drv.setStyle(style_dataPane);
		
		Label lbl_drvTitle = new Label("Driving");
		
		lbl_drvTitle.setStyle(style_LblTitle); 
		
		GridPane gridPane_drv = new GridPane();
		gridPane_drv.setStyle(style_gridPane);
		gridPane_drv.setAlignment(Pos.CENTER);
		gridPane_drv.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane_drv.setVgap(0); 
		gridPane_drv.setHgap(5);       
		gridPane_drv.setMaxWidth(400);
		gridPane_drv.setMaxHeight(200);
		
		vb_drv.getChildren().add(lbl_drvTitle);
		vb_drv.getChildren().add(gridPane_drv);

		// TEST data
		// TITLE + GRID PANE
		VBox vb_tst = new VBox();
		vb_tst.setStyle(style_dataPane);
		
		Label lbl_tstTitle = new Label("Driving");
		
		lbl_tstTitle.setStyle(style_LblTitle); 
		
		Gauge gauge_DrvThrottle = GaugeBuilder.create()
                .skinType(SkinType.MODERN)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle2 = GaugeBuilder.create()
                .skinType(SkinType.LEVEL)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle3 = GaugeBuilder.create()
                .skinType(SkinType.BATTERY)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle4 = GaugeBuilder.create()
                .skinType(SkinType.DASHBOARD)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle5 = GaugeBuilder.create()
                .skinType(SkinType.DIGITAL)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle6 = GaugeBuilder.create()
                .skinType(SkinType.FLAT)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle7 = GaugeBuilder.create()
                .skinType(SkinType.GAUGE)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle8 = GaugeBuilder.create()
                .skinType(SkinType.INDICATOR)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle9 = GaugeBuilder.create()
                .skinType(SkinType.KPI)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle10 = GaugeBuilder.create()
                .skinType(SkinType.NASA)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle11 = GaugeBuilder.create()
                .skinType(SkinType.SLIM)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle12 = GaugeBuilder.create()
                .skinType(SkinType.SPACE_X)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle13 = GaugeBuilder.create()
                .skinType(SkinType.WHITE)
                .title("TITLE")
                .unit("km/h")
                .build();
		Gauge gauge_DrvThrottle14 = GaugeBuilder.create()
                .skinType(SkinType.BAR)
                .title("TITLE")
                .unit("km/h")
                .build();
		gauge_DrvThrottle10.setValue(50);
		gauge_DrvThrottle11.setValue(50);
		gauge_DrvThrottle14.setValue(50);
		
		vb_tst.getChildren().add(lbl_tstTitle);
		vb_tst.getChildren().add(gauge_DrvThrottle10);
		
		// BOTTOM PANE
		// HV ACCUMULATOR + INVERTER + MOTORS + TYRES + SAFETY CIRCUIT + DRIVING
		HBox bottomPane = new HBox();
		
		bottomPane.getChildren().add(vb_acc);
		bottomPane.getChildren().add(vb_inv);
		bottomPane.getChildren().add(vb_mot);
		bottomPane.getChildren().add(vb_saf);
		bottomPane.getChildren().add(vb_drv);
		bottomPane.getChildren().add(vb_tst);
		
		
		
		// TILEPANE (not used)
		/*
		TilePane tilePane = new TilePane(); 
	    tilePane.getChildren().add(label1);
        tilePane.getChildren().add(label2);
        tilePane.getChildren().add(label3);
        tilePane.getChildren().add(label4);
        tilePane.getChildren().add(label5);
        tilePane.getChildren().add(label6);
        tilePane.getChildren().add(label7);
        tilePane.getChildren().add(label8);
        //tilePane.setTileAlignment(Pos.TOP_LEFT);
        //tilePane.setAlignment();
        tilePane.setPrefColumns(2);
        */
		
		
		
		
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
		Scene scene = new Scene(bPane, 1500, 850);
		scene.setFill(Color.BLACK);
		
		// STAGE
		primaryStage.setTitle("ERB Telemetry");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("file:Logo.png"));
		primaryStage.show();
			
		
	}
	
	public void run()
	{
		launch();		
	}
	
	
}

package it.erb.telemetry.view;

import eu.hansolo.medusa.*;
import eu.hansolo.medusa.Gauge.KnobType;
import eu.hansolo.medusa.Gauge.LedType;
import eu.hansolo.medusa.Gauge.NeedleShape;
import eu.hansolo.medusa.Gauge.NeedleSize;
import eu.hansolo.medusa.Gauge.ScaleDirection;
import eu.hansolo.medusa.Gauge.SkinType;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
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
		Label label1 = new Label("Voltage");
		label1.setTextFill(Color.color(0.9,0.9,0.9));
		label1.setStyle("-fx-background-color: #ff0000; ");
		label1.setAlignment(Pos.BOTTOM_LEFT);
		
		Label label2 = new Label("750 V");
		label2.setTextFill(Color.color(0.9,0.9,0.9));
		label2.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-background-color: #ff0000;"); 
		
		Label label3 = new Label("Current");
		label3.setTextFill(Color.color(0.9,0.9,0.9));
		
		Label label4 = new Label("100 A");
		label4.setTextFill(Color.color(0.9,0.9,0.9));
		label4.setStyle("-fx-font-weight: bold; -fx-font-size: 16; "); 
		
		Label label5 = new Label("Power");
		label5.setTextFill(Color.color(0.9,0.9,0.9));
		
		Label label6 = new Label("7000 kW");
		label6.setTextFill(Color.color(0.9,0.9,0.9));
		label6.setStyle("-fx-font-weight: bold; -fx-font-size: 16; "); 
		
		Label label7 = new Label("Temperature");
		label7.setTextFill(Color.color(0.9,0.9,0.9));
		
		Label label8 = new Label("56°C");
		label8.setTextFill(Color.color(0.9,0.9,0.9));
		label8.setStyle("-fx-font-weight: bold; -fx-font-size: 16; "); 
		
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
		
		
		RadialGradient gridBg = new RadialGradient(
				0.0, 0.0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
				new Stop(0.0, new Color(0.7789, 0.0058, 0.0058, 1.0)),
				new Stop(1.0, new Color(0.3053, 0.3053, 0.3053, 1.0)));
		// GRIDPANE
		GridPane gridPane = new GridPane();
		gridPane.setBackground(new Background(new BackgroundFill(gridBg, CornerRadii.EMPTY, Insets.EMPTY)));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(10, 10, 10, 10)); 
		gridPane.setVgap(0); 
	    gridPane.setHgap(5);       
		gridPane.add(label1, 0, 0);
		gridPane.add(label2, 1, 0);
		gridPane.add(label3, 0, 1);
		gridPane.add(label4, 1, 1);
		gridPane.add(label5, 0, 2);
		gridPane.add(label6, 1, 2);
		gridPane.add(label7, 0, 3);
		gridPane.add(label8, 1, 3);
		//gridPane.add(gauge, 0, 4);
		gridPane.setMaxWidth(400);
		gridPane.setMaxHeight(200);
		
		
		
		
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
		bPane.setCenter(gridPane);
		
		
		
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

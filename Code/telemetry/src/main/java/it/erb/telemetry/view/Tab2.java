package it.erb.telemetry.view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.TilePane;

public class Tab2 {

	//aggiungo un grafico vuoti (contenitore) che sarà modificato da un'altra classe
	
	private LineChart lineChart = new LineChart(new NumberAxis(), new NumberAxis()); 
	private TilePane tilePane = new TilePane();	
	
	public Tab2() {
		tilePane.getChildren().add(lineChart);
	}
	
	public TilePane getTilePane() {
		return tilePane;
	}
	
	public LineChart getLineChart() {
		return lineChart;
	}
	
}

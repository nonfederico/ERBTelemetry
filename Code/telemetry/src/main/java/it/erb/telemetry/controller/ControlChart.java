package it.erb.telemetry.controller;

import javafx.scene.chart.LineChart;		
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class ControlChart {
    //si occupa di renderizzare il grafico, aggiungendo i dati presi dal database o dai messaggi
	//inserendoli nel grafico creato nella classe view
  
	public ControlChart() {};
	
	private XYChart.Series<Number, Number> serie= new XYChart.Series<Number, Number>();
	private LineChart lineChart = new LineChart(new NumberAxis(), new NumberAxis()); 	


	public void AddSeries(LineChart A) {
		serie.getData().add(new XYChart.Data<>(1, 5));
	    serie.getData().add(new XYChart.Data<>(2, 10));
	    serie.getData().add(new XYChart.Data<>(3, 15));
	    A.getData().add(serie);
	}

	
}

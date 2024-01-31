package it.erb.telemetry.controller;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

//classe per aggiungere i dati al grafico
public class ModChart {
private XYChart.Series<Number, Number> serie= new XYChart.Series<Number, Number>();
private LineChart lineChart = new LineChart(new NumberAxis(), new NumberAxis()); 	
	
public ModChart() {
}


public void AddSeries(LineChart A) {
	serie.getData().add(new XYChart.Data<>(1, 5));
    serie.getData().add(new XYChart.Data<>(2, 10));
    serie.getData().add(new XYChart.Data<>(3, 15));
    A.getData().add(serie);
}



}

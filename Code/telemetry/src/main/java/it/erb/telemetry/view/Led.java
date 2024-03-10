package it.erb.telemetry.view;

import javafx.scene.shape.Circle;

//classe utilizzata per determinare le caratteristiche dell'elemento Led, utilizzato nei Pane
public class Led extends Circle
{
	Led()
	{
		super();
		this.getStyleClass().add("led");
	}
	
	Led(double radius)
	{
		super(radius);
		this.getStyleClass().add("led");
	}
	
	public void update(boolean value)
	{
		if(value)
		{
			this.setStyle("-fx-fill: rgba(20, 150, 20, 1)");
		}
		else
		{
			this.setStyle("-fx-fill: rgba(220, 20, 20, 1)");
			
		}
	}
	
	
}

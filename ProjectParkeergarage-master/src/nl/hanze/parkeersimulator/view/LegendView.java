package nl.hanze.parkeersimulator.view;

import java.awt.Color;
import java.awt.Graphics;

import nl.hanze.parkeersimulator.model.CarParkModel;

public class LegendView extends AbstractView {

	private static final long serialVersionUID = 883482756602880606L;

	public LegendView(CarParkModel simulatorModel) {
		super(simulatorModel);
		setSize(150, 54);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Ad-Hoc auto's", 40, 22);
		g.setColor(Color.red);
		g.fillRect(10, 10, 24, 16);
		g.setColor(Color.black);
		g.drawString("Pass auto's", 40, 42);
		g.setColor(Color.blue);
		g.fillRect(10, 30, 24, 16);
	}
	
	public void updateView() {
		
	}
}

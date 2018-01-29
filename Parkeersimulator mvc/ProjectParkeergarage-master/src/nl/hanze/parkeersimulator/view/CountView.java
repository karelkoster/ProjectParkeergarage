package nl.hanze.parkeersimulator.view;

import java.awt.Color;
import java.awt.Graphics;

import nl.hanze.parkeersimulator.model.Model;

public class CountView extends AbstractView {
	private static final long serialVersionUID = -7503377039578042533L;

	public CountView(Model model) {
		super(model);
		setSize(200, 200);
	}
	
	public void paintComponent(Graphics g) {
		int aantal=getModel().getAantal();
		boolean ready=false;
		int counter=1;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.RED);
		
		for(int y=70;y<=180 && !ready; y+=5) {
			for(int x=20;x<=180 && !ready; x+=5) {
				ready=counter>aantal;
				if (!ready) g.fillRect(x, y, 3, 3);
				counter++;
			}
		}
	}
}
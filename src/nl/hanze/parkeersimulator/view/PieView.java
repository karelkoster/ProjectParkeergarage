package nl.hanze.parkeersimulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;

import nl.hanze.parkeersimulator.model.CarParkModel;

@SuppressWarnings("serial")
public class PieView extends AbstractView {
	private int red;
	private int blue;

	public PieView(CarParkModel carParkModel) {
		super(carParkModel);
		setSize(200, 200);
	}

	public void paintComponent(Graphics g) {
		int normaal = berekenPie(getModel().getNormaal());
		int reservering = berekenPie(getModel().getReservering());

		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLUE);
		g.fillArc(10, 10, 180, 180, normaal, reservering);
		g.setColor(Color.RED);
		g.fillArc(10, 10, 180, 180, 0, normaal);
	}

	public int getAantalRed() {
		return red;
	}

	public int getAantalBlue() {
		return blue;
	}

	public int getAantalBluePie() {
		return red + blue;
	}

	private int berekenPie(int aantal) {
		return (int) (aantal / 1.5);
	}
}

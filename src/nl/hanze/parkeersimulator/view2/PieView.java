package nl.hanze.parkeersimulator.view2;

import java.awt.*;

import nl.hanze.parkeersimulator.model.Model;

public class PieView extends AbstractView {
	private static final long serialVersionUID = 5455934187803194147L;

	public PieView(Model model) {
		super(model);
		setSize(200, 200);
	}

	public void paintComponent(Graphics g) {
		int aantalRed = getModel().getAantalRed();
		int aantalBlue = getModel().getAantalBluePie();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 540, 540);
		g.setColor(Color.BLUE);
		g.fillArc(10, 10, 180, 180, 0, berekenPie(aantalBlue));
		g.setColor(Color.RED);
		g.fillArc(10, 10, 180, 180, 0, berekenPie(aantalRed));
	}

	private int berekenPie(int aantal) {
		return (int) (aantal / 1.5);
	}
}

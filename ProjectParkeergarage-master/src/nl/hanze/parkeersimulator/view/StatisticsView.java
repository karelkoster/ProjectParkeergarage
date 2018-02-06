package nl.hanze.parkeersimulator.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.hanze.parkeersimulator.model.CarParkModel;

public class StatisticsView extends AbstractView {

	private static final long serialVersionUID = -2997552213169062263L;

	private JLabel openSpots;
	private JLabel normaal;
	private JLabel reservering;
	private JLabel totalCars;
	private JLabel totalSpots;
	private JLabel carsVisited;

	public StatisticsView(CarParkModel carParkModel) {
		super(carParkModel);
		setSize(250, 250);

		this.setLayout(new GridLayout(6, 2));

		add(new JLabel("Aantal open spots", SwingConstants.RIGHT));
		openSpots = new JLabel("", SwingConstants.RIGHT);
		add(openSpots);

		add(new JLabel("Aantal AdHoc auto's:", SwingConstants.RIGHT));
		normaal = new JLabel("", SwingConstants.RIGHT);
		add(normaal);

		add(new JLabel("Aantal Pass auto's:", SwingConstants.RIGHT));
		reservering = new JLabel("", SwingConstants.RIGHT);
		add(reservering);

		add(new JLabel("Aantal auto's:", SwingConstants.RIGHT));
		totalCars = new JLabel("", SwingConstants.RIGHT);
		add(totalCars);

		add(new JLabel("Aantal totale spots:", SwingConstants.RIGHT));
		totalSpots = new JLabel("", SwingConstants.RIGHT);
		add(totalSpots);

		add(new JLabel("Weggereden auto's:", SwingConstants.RIGHT));
		carsVisited = new JLabel("", SwingConstants.RIGHT);
		add(carsVisited);

	}

	@Override
	public void updateView() {
		openSpots.setText(Integer.toString(getModel().getNumberOfOpenSpots()) + "  ");
		normaal.setText(Integer.toString(getModel().getNormaal()) + "  ");
		reservering.setText(Integer.toString(getModel().getReservering()) + "  ");
		totalCars.setText(Integer.toString(getModel().getTotalCars()) + "  ");
		totalSpots.setText(Integer.toString(getModel().getTotalNumberOfSpots()) + "  ");
		carsVisited.setText(Integer.toString(getModel().getCarsVisited()) + "  ");
		repaint();
	}

}

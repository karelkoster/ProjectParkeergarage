package nl.hanze.parkeersimulator.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.hanze.parkeersimulator.model.CarParkModel;

// TODO: Auto-generated Javadoc
/**
 * The Class StatisticsView.
 */
public class StatisticsView extends AbstractView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2997552213169062263L;

	/** The open spots. */
	private JLabel openSpots;
	
	/** The normaal. */
	private JLabel normaal;
	
	/** The reservering. */
	private JLabel reservering;
	
	/** The total cars. */
	private JLabel totalCars;
	
	/** The snelheid. */
	private JLabel snelheid;
	
	/** The cars visited. */
	private JLabel carsVisited;

	/**
	 * Instantiates a new statistics view.
	 *
	 * @param carParkModel the car park model
	 */
	public StatisticsView(CarParkModel carParkModel) {
		super(carParkModel);
		setSize(250, 250);

		this.setLayout(new GridLayout(6, 2));

		add(new JLabel("Aantal open spots", SwingConstants.RIGHT));
		openSpots = new JLabel("", SwingConstants.RIGHT);
		add(openSpots);

		add(new JLabel("Geen abbonement:", SwingConstants.RIGHT));
		normaal = new JLabel("", SwingConstants.RIGHT);
		add(normaal);

		add(new JLabel("Met abbonement:", SwingConstants.RIGHT));
		reservering = new JLabel("", SwingConstants.RIGHT);
		add(reservering);

		add(new JLabel("Aantal auto's:", SwingConstants.RIGHT));
		totalCars = new JLabel("", SwingConstants.RIGHT);
		add(totalCars);

		add(new JLabel("Snelheid simulator", SwingConstants.RIGHT));
		snelheid = new JLabel("", SwingConstants.RIGHT);
		add(snelheid);

		add(new JLabel("Weggereden auto's:", SwingConstants.RIGHT));
		carsVisited = new JLabel("", SwingConstants.RIGHT);
		add(carsVisited);

	}

	/* (non-Javadoc)
	 * @see nl.hanze.parkeersimulator.view.AbstractView#updateView()
	 */
	@Override
	public void updateView() {
		openSpots.setText(Integer.toString(getModel().getNumberOfOpenSpots()) + "  ");
		normaal.setText(Integer.toString(getModel().getNormaal()) + "  ");
		reservering.setText(Integer.toString(getModel().getReservering()) + "  ");
		totalCars.setText(Integer.toString(getModel().getTotalCars()) + "  ");
		snelheid.setText(Integer.toString(getModel().getTickSpeed()) + "  ");
		carsVisited.setText(Integer.toString(getModel().getCarsVisited()) + "  ");
		repaint();
	}

}

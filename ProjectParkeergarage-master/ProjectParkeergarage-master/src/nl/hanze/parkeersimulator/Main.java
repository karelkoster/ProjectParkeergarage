package nl.hanze.parkeersimulator;

import javax.swing.JFrame;


import javax.swing.border.LineBorder;

import java.awt.Color;

import nl.hanze.parkeersimulator.controller.Controller;
import nl.hanze.parkeersimulator.model.CarParkModel;
import nl.hanze.parkeersimulator.view.AbstractView;
import nl.hanze.parkeersimulator.view.CarParkView;
import nl.hanze.parkeersimulator.view.ClockView;
import nl.hanze.parkeersimulator.view.PieView;
import nl.hanze.parkeersimulator.view.StatisticsView;
import nl.hanze.parkeersimulator.view.LegendView;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
	
	/** The car park view. */
	private AbstractView carParkView;
	
	/** The pie view. */
	private AbstractView pieView;
	
	/** The clock view. */
	private AbstractView clockView;
	
	/** The statistics view. */
	private AbstractView statisticsView;
	
	/** The Legend view. */
	private AbstractView LegendView;
	
	/** The car park model. */
	private CarParkModel carParkModel;
	
	/** The screen. */
	private JFrame screen;
	
	/** The controller. */
	private Controller controller;

	/**
	 * Instantiates a new main.
	 */
	public Main() {
		screen = new JFrame("City Parking Groningen");

		carParkModel = new CarParkModel(3, 6, 30);

		carParkView = new CarParkView(carParkModel);
		carParkView.setBorder(new LineBorder(new Color(0, 0, 0)));

		controller = new Controller(carParkModel);
		controller.setLocation(280, 553);
		controller.setSize(850, 40);

		clockView = new ClockView(carParkModel);
		clockView.setBorder(new LineBorder(new Color(0, 0, 0)));
		clockView.setLocation(575, 0);
		
		LegendView = new LegendView(carParkModel);
		LegendView.setBorder(new LineBorder(new Color(0, 0, 0)));
		LegendView.setLocation(800, 0);

		pieView = new PieView(carParkModel);
		pieView.setLocation(35, 80);

		statisticsView = new StatisticsView(carParkModel);
		statisticsView.setBorder(new LineBorder(new Color(0, 0, 0)));
		statisticsView.setLocation(10, 300);
		
		LegendView = new LegendView(carParkModel);
		LegendView.setBorder(new LineBorder(new Color(0, 0, 0)));
		LegendView.setLocation(800, 0);
		
		screen.setSize(1200, 630);
		screen.setResizable(false);
		screen.getContentPane().setLayout(null);

		carParkView.setLayout(null);

		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(pieView);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(clockView);
		screen.getContentPane().add(statisticsView);
		screen.getContentPane().add(LegendView);

		carParkView.setBounds(280, 53, 850, 500);

		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new Main();
	}

}

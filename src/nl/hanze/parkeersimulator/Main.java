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

public class Main {
	private AbstractView carParkView;
	private AbstractView pieView;
	private AbstractView clockView;
	private AbstractView statisticsView;
	private AbstractView LegendView;
	private CarParkModel carParkModel;
	private JFrame screen;
	private Controller controller;

	public Main() {
		screen = new JFrame("City Parking Groningen");

		carParkModel = new CarParkModel(3, 6, 30);

		carParkView = new CarParkView(carParkModel);
		carParkView.setBorder(new LineBorder(new Color(0, 0, 0)));

		controller = new Controller(carParkModel);
		controller.setLocation(8, 0);
		controller.setSize(350, 40);
		controller.setBorder(new LineBorder(new Color(0, 0, 0)));

		clockView = new ClockView(carParkModel);
		clockView.setBorder(new LineBorder(new Color(0, 0, 0)));
		clockView.setLocation(575, 0);
		clockView.setSize(200, 54);
		
		LegendView = new LegendView(carParkModel);
		LegendView.setBorder(new LineBorder(new Color(0, 0, 0)));
		LegendView.setLocation(800, 0);

		pieView = new PieView(carParkModel);
		pieView.setBorder(new LineBorder(new Color(0, 0, 0)));
		pieView.setLocation(10, 80);

		statisticsView = new StatisticsView(carParkModel);
		statisticsView.setBorder(new LineBorder(new Color(0, 0, 0)));
		statisticsView.setLocation(10, 300);
		
		LegendView = new LegendView(carParkModel);
		LegendView.setBorder(new LineBorder(new Color(0, 0, 0)));
		LegendView.setLocation(800, 0);
		

		screen.setSize(1200, 600);
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

	public static void main(String[] args) {
		new Main();
	}

}

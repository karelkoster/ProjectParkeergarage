package nl.hanze.parkeersimulator;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import nl.hanze.parkeersimulator.model.CarParkModel;
import nl.hanze.parkeersimulator.view.AbstractView;
import nl.hanze.parkeersimulator.view.CarParkView;
import nl.hanze.parkeersimulator.view.ClockView;
import nl.hanze.parkeersimulator.view.PieView;

public class Main {
	private AbstractView carParkView;
	private AbstractView pieView;
	private AbstractView clockView;
	private CarParkModel model;
	private JFrame screen;

	public Main() {
		screen = new JFrame("City Parking Groningen");

		model = new CarParkModel(3, 6, 30);

		carParkView = new CarParkView(model);
		carParkView.setBorder(new LineBorder(new Color(0, 0, 0)));

		pieView = new PieView(model);
		pieView.setBorder(new LineBorder(new Color(0, 0, 0)));
		pieView.setLocation(10, 80);

		clockView = new ClockView(model); 
		clockView.setBorder(new LineBorder(new Color(0, 0, 0)));
		clockView.setLocation(575, 0);
		clockView.setSize(200,100);
		
		screen.setSize(1200, 600);
		screen.setResizable(true);
		screen.getContentPane().setLayout(null);

		carParkView.setLayout(null);

		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(pieView);
		screen.getContentPane().add(clockView);
		carParkView.setBounds(239, 53, 850, 500);

		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		model.run();
	}

	public static void main(String[] args) {
		new Main();
	}

}

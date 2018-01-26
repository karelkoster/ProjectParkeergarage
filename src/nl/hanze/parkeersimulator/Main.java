package nl.hanze.parkeersimulator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import nl.hanze.parkeersimulator.controller.CarparkController;
import nl.hanze.parkeersimulator.controller.Controller;
import nl.hanze.parkeersimulator.view.CarparkView;
import nl.hanze.parkeersimulator.view.View;

public class Main extends JFrame {

	private static final long serialVersionUID = 9123275098959761736L;

	public Main() {
		setTitle("Parkeergaragesimulator");
		setSize(640,400);
		JPanel rootPanel = new JPanel();
		
		JLabel header = new JLabel("Parkeergaragesimulator", SwingConstants.CENTER);
		rootPanel.add(header);
		
//		root.setLayout(new GridBagLayout());

		// Model/Controller/View

		Controller carparkController = new CarparkController();
		View carparkView = new CarparkView(carparkController);

		rootPanel.add(carparkView);
		
		//		StoplichtModel model=new StoplichtModel();
//		Controller controller=new StoplichtController(model);
//		View view=new StoplichtView(controller);
//		View view2=new StoplichtViewTekst();
//
//		GridBagConstraints c = new GridBagConstraints();

//		root.add(view,c);
//		c.gridx=0;
//		c.gridy=1;
//		root.add(view2,c);
		add(rootPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

//		model.registerView(view);
//		model.registerView(view2);
//		model.updateViews();

	}
	
	public static void main(String[] args) {
		new Main();
		
	}

}

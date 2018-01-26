package nl.hanze.parkeersimulator.view;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.hanze.parkeersimulator.controller.Controller;
import nl.hanze.parkeersimulator.model.Model;

public class CarparkView extends View {

	private static final long serialVersionUID = -7335963429148869447L;

	@Override
	public void update(Model model) {
		// TODO Auto-generated method stub
		
	}

	public CarparkView(Controller controller) {
		super(controller);
		
		JLabel label = new JLabel("CarparkView", SwingConstants.CENTER);
		add(label);
	}

}

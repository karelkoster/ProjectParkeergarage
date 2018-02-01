//package nl.hanze.parkeersimulator.model;
//
//import javax.swing.JFrame;
//
//import javax.swing.JPanel;
//
//import nl.hanze.parkeersimulator.view2.AbstractView;
//import nl.hanze.parkeersimulator.view2.PieView;
//import nl.hanze.parkeersimulator.view2.SimulatorView;
//
//public class MVCDDynamicModelThreadGeneralized extends JFrame {
//	private Model model;
//	private JFrame screen;
//	private AbstractView pieview;
//	private static final long serialVersionUID = 9123275098959761736L;
//
//	public MVCDDynamicModelThreadGeneralized() {
//		setTitle("Parkeergaragesimulator");
//		// setSize(640,400);
//		JPanel rootPanel = new JPanel();
//
//		// JLabel header = new JLabel("Parkeerplekken", SwingConstants.CENTER);
//		// rootPanel.add(header);
//
//		// Model/Controller/View
//		CarParkModel simulatorModel = new CarParkModel(3, 6, 30);
//
//		SimulatorView simulatorView = new SimulatorView(simulatorModel);
//		SimulatorModel simulatorController = new SimulatorModel(simulatorView);
//
//		model = new Model();
//		pieview = new PieView(model);
//		// door ries toegevoegd
//		simulatorModel.SetModel(model);
//		simulatorController.SetModel(model);
//		// ---
//		screen = new JFrame("Model View Controller/Dynamic Model with thread");
//		screen.setSize(450, 285);
//		screen.setResizable(false);
//		screen.setLayout(null);
//		screen.getContentPane().add(pieview);
//		pieview.setBounds(230, 10, 200, 200);
//		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		add(rootPanel);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		screen.setVisible(true);
//		simulatorController.run();
//	}
//
//	public Model GetModel() {
//		return model;
//	}
//}

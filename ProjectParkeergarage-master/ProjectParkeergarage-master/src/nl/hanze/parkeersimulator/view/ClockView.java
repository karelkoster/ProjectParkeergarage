package nl.hanze.parkeersimulator.view;


import nl.hanze.parkeersimulator.model.*;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ClockView.
 */
public class ClockView extends AbstractView {

   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2399529792226996637L;
	
	/** The clock. */
	private JLabel clock;

    /**
     * Instantiates a new clock view.
     *
     * @param model the model
     */
    public ClockView(CarParkModel model) {
        super(model);
        setSize(200, 54);
        clock = new JLabel("hier komt de tijd te staan");
        add(clock);
        updateView();
       
    }

    /**
     * Sets the clock.
     *
     * @param day the day
     * @param hour the hour
     * @param minute the minute
     */
    private void setClock(int day, int hour, int minute) {
        String dayString;
        switch(day) {
            case 0:     dayString = "Maandag";
                    break;
            case 1:     dayString = "Dinsdag";
                    break;
            case 2:     dayString = "Woensdag";
                    break;
            case 3:     dayString = "Donderdag";
                    break;
            case 4:     dayString = "Vrijdag";
                    break;
            case 5:     dayString = "Zaterdag";
                    break;
            case 6:     dayString = "Zondag";
                    break;
            default:    dayString = "Invalid day";
                    break;
        }
        String hourString = String.format("%02d", hour);
        String minuteString = String.format("%02d", minute);
        clock.setText("<html><h2>"+dayString + " " + hourString + ":" + minuteString+"</h2></html>");
    }

    /* (non-Javadoc)
     * @see nl.hanze.parkeersimulator.view.AbstractView#updateView()
     */
    public void updateView() {
        int day = model.getDay();
        int hour = model.getHour();
        int minute = model.getMinute();
        setClock(day, hour, minute);
        
    }


}
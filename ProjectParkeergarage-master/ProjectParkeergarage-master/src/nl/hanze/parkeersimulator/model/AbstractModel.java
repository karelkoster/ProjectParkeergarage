package nl.hanze.parkeersimulator.model;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.parkeersimulator.view.AbstractView;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractModel.
 */
public abstract class AbstractModel {
	
	/** The views. */
	private List<AbstractView> views;

	/**
	 * Instantiates a new abstract model.
	 */
	public AbstractModel() {
		views = new ArrayList<AbstractView>();
	}

	/**
	 * Adds the view.
	 *
	 * @param view the view
	 */
	public void addView(AbstractView view) {
		views.add(view);
	}

	/**
	 * Notify views.
	 */
	public void notifyViews() {
		for (AbstractView v : views)
			v.updateView();
	}
}

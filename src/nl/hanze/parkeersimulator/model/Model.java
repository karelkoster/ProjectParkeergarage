package nl.hanze.parkeersimulator.model;

public class Model extends AbstractModel implements Runnable {
	private int aantal;
	private int red; 
	private int blue; 
	private boolean run;
	
	public Model() {
	}
	
	public void setAantalRed(int aantal) {
		red=aantal;
		notifyViews();
	} 
	
	public void setAantalBlue(int aantal) {
		blue=aantal;
		notifyViews();
	} 
			
	public int getAantal() {
		return aantal;
	}
	
	public int getAantalRed() {
		return red;
	}
	
	public int getAantalBlue() {
		return blue;
	}
	
	public int getAantalBluePie() {
		return getAantalRed()+getAantalBlue();
	}
	
	public void setAantal(int aantal) {
		if (aantal>=0 && aantal <=540) {
			this.aantal=aantal;
			notifyViews();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		run=false;
	}
	
	@Override
	public void run() {
		run=true;
		while(run) {
			setAantal(getAantal()+1);
			try {
				Thread.sleep(100);
			} catch (Exception e) {} 
		}
	}
}
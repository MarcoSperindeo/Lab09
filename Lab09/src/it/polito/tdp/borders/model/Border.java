package it.polito.tdp.borders.model;

public class Border {
	
	private int state1No;
	private int state2No;
	private int year;
	
	public Border(int state1No, int state2No, int year) {
		super();
		this.state1No = state1No;
		this.state2No = state2No;
		this.year = year;
	}

	public int getState1No() {
		return state1No;
	}

	public void setState1No(int state1No) {
		this.state1No = state1No;
	}

	public int getState2No() {
		return state2No;
	}

	public void setState2No(int state2No) {
		this.state2No = state2No;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

	
}

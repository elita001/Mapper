package classes;

public class SubSubNewClass {
	private double newD;
	private int newInt;
	private String newString;

	public SubSubNewClass() {
		this.setNewD(10.0);
		this.setNewString("newString");
		this.setNewInt(15);
	}

	public void setNewD(double newD) {
		this.newD = newD;
	}

	public double getNewD() {
		return newD;
	}

	public String getNewString() {
		return newString;
	}

	public void setNewString(String newString) {
		this.newString = newString;
	}

	public int getNewInt() {
		return newInt;
	}

	public void setNewInt(int newInt) {
		this.newInt = newInt;
	}
}

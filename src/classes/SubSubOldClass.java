package classes;

import annotations.ToField;
import annotations.ToType;

@ToType("classes.SubSubNewClass")
public class SubSubOldClass {
	@ToField("newD")
	private int oldInt;
	@ToField("newString")
	private String oldString;

	public SubSubOldClass() {
		this.setOldInt(5);
		this.setOldString("old_string");
	}

	public int getOldInt() {
		return oldInt;
	}

	public void setOldInt(int oldInt) {
		this.oldInt = oldInt;
	}

	public String getOldString() {
		return oldString;
	}

	public void setOldString(String oldString) {
		this.oldString = oldString;
	}
}

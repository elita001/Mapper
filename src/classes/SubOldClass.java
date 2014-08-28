package classes;

import annotations.ToField;
import annotations.ToType;

@ToType("classes.SubNewClass")
public class SubOldClass {
	@ToField("newSSC")
	private SubSubOldClass oldSSC;

	public SubOldClass() {
		this.setOldSSC(new SubSubOldClass());
	}

	public SubSubOldClass getOldSSC() {
		return oldSSC;
	}

	public void setOldSSC(SubSubOldClass oldSSC) {
		this.oldSSC = oldSSC;
	}
}

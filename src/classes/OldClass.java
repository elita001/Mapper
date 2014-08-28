package classes;

import interfaces.FieldsOutput;
import annotations.ToField;
import annotations.ToType;

@ToType("classes.NewClass")
public class OldClass implements FieldsOutput {
	@ToField("newSC")
	private SubOldClass oldSC;
	
	public OldClass() {
		this.setOldSC(new SubOldClass());
	}

	public SubOldClass getOldSC() {
		return oldSC;
	}

	public void setOldSC(SubOldClass oldSC) {
		this.oldSC = oldSC;
	}

	@Override
	public void printSimpleFields() {
		System.out.println("String field of OldClass: "
				+ this.getOldSC().getOldSSC().getOldString()
				+ "\nint field of OldClass: "
				+ this.getOldSC().getOldSSC().getOldInt() + "\n");
	}
}

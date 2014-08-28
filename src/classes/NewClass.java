package classes;

import interfaces.FieldsOutput;

public class NewClass implements FieldsOutput {
	private SubNewClass newSC;

	public NewClass() {
		this.setNewSC(new SubNewClass());
	}

	public SubNewClass getNewSC() {
		return newSC;
	}

	public void setNewSC(SubNewClass newSC) {
		this.newSC = newSC;
	}

	@Override
	public void printSimpleFields() {
		System.out.println("String field of NewClass: "
				+ this.getNewSC().getNewSSC().getNewString()
				+ "\nDouble field of NewClass: "
				+ this.getNewSC().getNewSSC().getNewD() + "\n");
	}
}

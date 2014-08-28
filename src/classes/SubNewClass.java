package classes;

public class SubNewClass {
	private SubSubNewClass newSSC;

	public SubNewClass() {
		this.setNewSSC(new SubSubNewClass());
	}

	public SubSubNewClass getNewSSC() {
		return newSSC;
	}

	public void setNewSSC(SubSubNewClass newSSC) {
		this.newSSC = newSSC;
	}
}

package orika;

import classes.NewClass;
import classes.OldClass;

public class OricaTest {
	public static void main(String[] args) {
		OldClass oc = new OldClass();
		oc.printSimpleFields();
		NewClass nc = new NewClass();
		nc.printSimpleFields();
		nc = (NewClass) OricaMapper.convertFromOldClassToNewClass(oc);
		System.out.println("After converting:");
		nc.printSimpleFields();
	}
}

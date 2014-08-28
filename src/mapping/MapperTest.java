package mapping;

import classes.NewClass;
import classes.OldClass;

public class MapperTest {
	public static void main(String[] args) throws Exception {
		OldClass oc = new OldClass();
		oc.printSimpleFields();
		NewClass nc = new NewClass();
		nc.printSimpleFields();
		nc = (NewClass) Mapper.map(oc);
		if (nc != null) {
			System.out.println("After converting:");
			nc.printSimpleFields();
		} else {
			Mapper.file_logger.error("Mapping failed");
			Mapper.console_logger.error("Mapping failed");
		}
	}
}

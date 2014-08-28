package orika;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import classes.NewClass;
import classes.OldClass;

public class OricaMapper {
	/**
	 *@return Converted Object
	 *@param source Object to convert
	 */
	public static Object convertFromOldClassToNewClass(OldClass source) {		
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		mapperFactory.classMap(OldClass.class, NewClass.class)
				.field("oldSC", "newSC").field("oldSC.oldSSC", "newSC.newSSC")
				.field("oldSC.oldSSC.oldInt", "newSC.newSSC.newD")
				.field("oldSC.oldSSC.oldString", "newSC.newSSC.newString")
				.byDefault().register();
		BoundMapperFacade<OldClass, NewClass> boundMapper = mapperFactory
				.getMapperFacade(OldClass.class, NewClass.class);
		return boundMapper.map(source);
	}


}

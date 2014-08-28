package mapping;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotations.ToField;
import annotations.ToType;

public class Mapper {
	/**
	 * Logger for console
	 */
	final static Logger console_logger = LoggerFactory
			.getLogger("classes.Mapper.console_logger");
	/**
	 * Logger for log file
	 */
	final static Logger file_logger = LoggerFactory
			.getLogger("classes.Mapper.file_logger");

	/**
	 * @return Converted Object
	 * @param oldObject
	 *            Object to convert
	 */
	public static Object map(Object oldObject) throws Exception {
		if (oldObject == null) {
			file_logger.info("Object to convert is null");
			console_logger.info("Object to convert is null");
			return null;
		}
		file_logger.info("Start mapper");
		console_logger.info("Start mapper");
		Object instanceOfNewClass = null;
		String newClassName = "";
		try {
			newClassName = oldObject.getClass().getAnnotation(ToType.class)
					.value();

			Class<?> newClass = Class.forName(newClassName);
			file_logger.info("New class " + newClass.getName()
					+ " from annotation created");
			console_logger.info("New class from annotation created");
			try {
				instanceOfNewClass = newClass.newInstance();
				file_logger.info("Instance of new class " + newClass.getName()
						+ " created");
				console_logger.info("Instance of new class created");
				try {
					file_logger.info("Start checking fields");
					console_logger.info("Start checking fields");
					checkFields(oldObject, instanceOfNewClass);
					file_logger.info("Checking fields ended");
					console_logger.info("Checking fields ended");
				} catch (NoSuchFieldException e) {
					file_logger.warn(e.toString()
							+ "\nWrong field name in annotation");
					console_logger.warn(e.toString()
							+ "\nWrong field name in annotation");
					return null;
				} catch (SecurityException e) {
					file_logger.warn(e.toString()
							+ "\nAccess to field(s) denied");
					console_logger.warn(e.toString()
							+ "\nAccess to field(s) denied");
					return null;
				}
			} catch (InstantiationException e) {
				file_logger.warn(e.toString() + "\nCannot create instance of "
						+ newClass.getName());
				console_logger.warn(e.toString()
						+ "\nCannot create new class instance");
				return null;
			} catch (IllegalAccessException e) {
				file_logger.warn(e.toString() + "\nAccess to field(s) denied");
				console_logger.warn(e.toString()
						+ "\nAccess to field(s) denied");
				return null;
			}
		} catch (ClassNotFoundException e) {
			file_logger.warn(e.toString() + "\nWrong class name "
					+ newClassName + "in annotation ");
			console_logger.warn(e.toString()
					+ "\nWrong class name in annotation");
			return null;
		}
		file_logger.info("End mapper");
		console_logger.info("End mapper");
		return instanceOfNewClass;
	}

	/**
	 * Maps annotated fields
	 * 
	 * @param oldObject
	 *            Object to convert
	 * @param newClass
	 *            Type of converted object
	 * @param instanceOfNewClass
	 *            Instance of converted Object
	 * @throws Exception
	 */
	public static void checkFields(Object oldObject, Object instanceOfNewClass)
			throws Exception {
		for (Field f : oldObject.getClass().getDeclaredFields()) {
			if (f.getType().equals(oldObject.getClass().getName())) {
				throw new Exception(
						"Type of field matches with class type. Infinite recursion.");
			}
			if (!f.isAnnotationPresent(ToField.class)) {
				file_logger.info("Field " + f.getName()
						+ " without annotation. Continue");
				console_logger.info("Field without annotation. Continue");
				continue;
			}
			String newFieldName = f.getAnnotation(ToField.class).value();
			file_logger.info("Name of new field " + newFieldName
					+ " taken from annotation");
			console_logger.info("Name of new field taken from annotation");
			Field newField = instanceOfNewClass.getClass().getDeclaredField(
					newFieldName);
			file_logger.info("New field " + newField.getName() + " taken");
			console_logger.info("New field taken");
			file_logger.info("Set fields accessible");
			console_logger.info("Set fields accessible");
			f.setAccessible(true);
			newField.setAccessible(true);
			file_logger.info("Check field's class " + f.getType().getName()
					+ " for annotation");
			console_logger.info("Check field's class for annotation");
			if (f.getType().isAnnotationPresent(ToType.class)) {
				file_logger.info("Annotation exist. Start mapper recursion");
				console_logger.info("Annotation exist. Start mapper recursion");
				Object newFieldInstance = map(f.get(oldObject));
				file_logger.info("Set old field " + f.getName()
						+ " to new field " + newFieldName + " after mapping");
				console_logger.info("Set old field to new field after mapping");
				newField.set(instanceOfNewClass, newFieldInstance);
			} else {
				file_logger.info("No annotation. Set old field " + f.getName()
						+ " to new field " + newFieldName);
				console_logger
						.info("No annotation. Setting old field to new field");
				newField.set(instanceOfNewClass, f.get(oldObject));
			}
		}
	}
}

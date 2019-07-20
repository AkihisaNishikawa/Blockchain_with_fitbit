package additional_functions;

import java.lang.instrument.Instrumentation;
/*
 * It is not used in this project
 */
public class ObjectSizeCalculator {
	private static Instrumentation instrumentation;

	public static void premain(String args, Instrumentation inst) {
		instrumentation = inst;
	}

	public static long calculateObjectSize(Object o) {
		return instrumentation.getObjectSize(o);
	}
}

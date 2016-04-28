package kr.co.hucloud.utilities.file;


public class FileUtilAdapter {

	public static void doFunc(FileFunctionable functionable) {
		try {
			functionable.doFunc();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
}

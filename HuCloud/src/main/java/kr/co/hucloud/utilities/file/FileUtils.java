package kr.co.hucloud.utilities.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class FileUtils {

	public static File makeDirectory(String path) {
		File directory = new File(path);
		
		if( !directory.exists() ) {
			directory.mkdirs();
		}
		
		return directory;
	}
	
	public static File makeFile(final String path) {
		
		final File file = new File(path);
		
		FileUtilAdapter.doFunc(new FileFunctionable(){
			@Override
			public void doFunc() throws IOException {
				file.createNewFile();
			}
		});
		
		return file;
	}
	
	public static void copy(final String originFilePath, final String destFilePath) {
		FileUtilAdapter.doFunc(new FileFunctionable(){
			@Override
			public void doFunc() throws IOException {
				Files.copy(new File(originFilePath).toPath(), new File(destFilePath).toPath());
			}
		});
	}
	
	public static void move(final String originFilePath, final String destFilePath) {
		FileUtilAdapter.doFunc(new FileFunctionable(){
			@Override
			public void doFunc() throws IOException {
				CopyOption[] copyOptions = new CopyOption[] {
						StandardCopyOption.REPLACE_EXISTING, // 원본파일이 존재한다면 덮어쓰
						StandardCopyOption.COPY_ATTRIBUTES // 원본파일의 속성까지 모두 복
				};
				
				Files.move(new File(originFilePath).toPath(), new File(destFilePath).toPath(), copyOptions);
			}
		});
	}
	
	public static boolean deleteFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			if(file.isFile()) {
				return file.delete();
				
			}
			else {
				throw new RuntimeException(path + "는 파일이 아닙니다.");
			}
		}
		else {
			throw new RuntimeException(path + "는 존재하지 않습니다.");
		}
	}
	
	public static boolean deleteDirectory(String path) {
		
		File file = new File(path);
		if(file.exists()) {
			if(file.isDirectory()) {
				return file.delete();
			}
			else {
				throw new RuntimeException(path + "는 디렉토리가 아닙니다.");
			}
		}
		else {
			throw new RuntimeException(path + "는 존재하지 않습니다.");
		}
	}
	
	public static String read(String filePath) {
		
		byte[] bytes = null;
		
		try {
			bytes = Files.readAllBytes(new File(filePath).toPath());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static void append(final String path, final String contents) {
		FileUtilAdapter.doFunc(new FileFunctionable(){
			@Override
			public void doFunc() throws IOException {
				OpenOption[] openOptions = new OpenOption[]{
						StandardOpenOption.APPEND
				};
				
				Files.write(new File(path).toPath(), contents.getBytes(), openOptions);
			}
		});
	}
	
}
 
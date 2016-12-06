package br.com.idwall.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtil {

	public static String readFile(String file){
		return readFile(file, "UTF-8");
	}
	
	public static String readFile(String file, String charset) {
		InputStream stream = null;
		try {
			URL url = FileUtil.class.getClassLoader().getResource(file);
			if(url == null){
				throw new RuntimeException("File not found");
			}
			
			stream = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
			
			StringBuilder strBuilder = new StringBuilder("");
			String line = reader.readLine();
			
			 while(line != null) {
				 strBuilder.append(line);
				 line = reader.readLine();
				 if(line != null){
					 strBuilder.append("\n");
				 }
	        }
			 
			return strBuilder.toString();
		} catch (IOException e) {
			throw new RuntimeException("File not found");
		} finally {
			close(stream);
		}
	}

	private static void close(InputStream stream) {
		if(stream != null){
			try {
				stream.close();
			} catch (IOException e) {
				throw new RuntimeException("Cant close stream");
			}
		}
	}
	
}

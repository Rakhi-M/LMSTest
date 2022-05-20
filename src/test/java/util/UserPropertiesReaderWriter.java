package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class UserPropertiesReaderWriter {
	
	Properties userProp = new Properties();
	
	public void writeUserProperties(String key, String value) throws IOException{
		
		OutputStream outputstream = new FileOutputStream(System.getProperty("user.dir")+"\\src\\test\\java\\config\\users.properties");
		
				userProp.setProperty(key, value);
				userProp.store(outputstream, "Updated");				
		
		}
	public String readUserProperties(String key) throws IOException {
		
		FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\config\\users.properties");
		try {
			userProp.load(ip);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return userProp.getProperty(key);
	}

}

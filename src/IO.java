import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class IO implements Serializable {

	/**
	 * Method to read a file using FileinputStream
	 * @return
	 * 
	 * 
	 */
	public static String fname = "database.txt";
	
		public String readInputStream(){
			
			StringBuilder contents = new StringBuilder();
			
			File file = new File(fname);
			
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				
				int i;
				/*
				 *  Read in an Int and convert it to a char
				 */
				while((i = fis.read()) != -1){
					char c = (char) i; contents.append(c);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(fis != null){
					try {
						fis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
			
			return contents.toString();
		}
		
		public void writeOutputStream
		(String contents){
			
			File file = new File(fname);
			
			FileOutputStream os = null;
			
			try{
				os = new FileOutputStream(file, true);
				
				os.write(contents.getBytes());
			}catch (FileNotFoundException e){
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(os!=null){
					try{
						os.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
		
		/**
		 * Read a file using BufferedInputStream
		 * in conjuction with try-with-response
		 * @return
		 */
		
	public String readBufferInputStream(){
			
			StringBuilder contents = new StringBuilder();
			
			File file = new File(fname);
			
			try(FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);){
				
				int i;
				
				while((i = bis.read())!=-1){
					char c = (char) i;
					contents.append(c);
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return contents.toString();
		}
		
		public String readBufferReader(){
			
			StringBuilder contents = new StringBuilder();
			
			try(BufferedReader reader = new BufferedReader(new FileReader(new File(fname)));){
				
				String s;
				
				while((s = reader.readLine())!= null){
					contents.append(s + "\n");
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return contents.toString();
		}
		
		/**
		 * Write to a file using BufferedWriter wrapping
		 * a FileWriter inside a try-with-resources.
		 * @param contents
		 */
		
		public void writeBufferedWriter(String contents){
			
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fname), true))){
				
				writer.write(contents);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Method to create a properties object
		 * from a properties file and read the values based on a key
		 * @param key
		 * @return
		 */
		
		public String readProperty(String key){
			
			Properties props = new Properties();
			String value = null;
			
			try(FileInputStream fis = new FileInputStream("test.properties");){
				
				props.load(fis);
				
				value = props.getProperty(key);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return value;
		}
		
		public void writeProperty(String key, String value){
			
			Properties props = new Properties();
			
			try(FileOutputStream fis = new FileOutputStream("test.properties");){
				props.setProperty(key, value);
				
				props.store(fis, "My key+value");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
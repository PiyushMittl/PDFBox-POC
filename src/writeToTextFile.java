import java.io.*;



public class writeToTextFile {
	
	

	    public static void main(String[] args) {

	        try {
	            String str = "SomeMoreTextIsHere";
	            //File newTextFile = new File("C:/thetextfile.txt");
	            File newTextFile=new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\abc1.txt");

	            //FileWriter fw = new FileWriter(newTextFile);
	            FileWriter fw = new FileWriter(newTextFile);
		            fw.write(str);
	            fw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
	    }
}

import java.io.File;
import java.io.FilenameFilter;

public class FindPdfFileNames {
public static void main(String a[]){
      /*  File dir = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\pagal.pdf");
*/
File dir=new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\ss");

        dir.mkdir();
        
        File[] ff=finder("C:\\Users\\PiyushMittal\\Desktop\\garbage\\");
        
        
        
        for(File f:ff){
        	String s=f.getAbsolutePath();
        	 File dir_img = new File(s+"_img");
        	 File dir_txt = new File(s+"_txt");
         	
        	 //dir=f;
        	 dir_img.mkdir();
        	 dir_txt.mkdir();
         	
        	
        }
        
        
        System.out.println("hi");

}



public static File[] finder( String dirName){
	File dir = new File(dirName);

	return dir.listFiles(new FilenameFilter() { 
		public boolean accept(File dir, String filename)
		{ 
			return filename.endsWith(".pdf"); 
		}
	} );
}

    
    
    
    
}
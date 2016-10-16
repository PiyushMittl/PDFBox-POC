import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import org.apache.commons.io.FileUtils;
public class CompareTxt {

	String comingPdfFile;
	
	
	public String getComingPdfFile() {
		return comingPdfFile;
	}


	public void setComingPdfFile(String comingPdfFile) {
		this.comingPdfFile = comingPdfFile;
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 /* Get the files to be compared first */
     /*  
       */ 
        
		
      
	}

	
	public static void abc(String ss) throws IOException{
		
		
		List<String> txtLocations=new ArrayList();
		List<String> imgLocations=new ArrayList();
		
		
		// String comingPdfFile="C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7_0.txt";
		 String comingPdfFile=ss;
	        
        
        
        
        
        
        
        File file = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\");
        String[] directories = file.list(new FilenameFilter() {
          public boolean accept(File current, String name) {
            return new File(current, name).isDirectory();
          }
        });
        List<String> strings = new ArrayList<String>(Arrays.asList(directories));
        
     // take C:\\Users\\PiyushMittal\\Desktop\\garbage\\
        //String ss=Strings.get(0);
     // take C:\\Users\\PiyushMittal\\Desktop\\garbage\\+""+ss
        
     // strings.get(0).upend(strings.get(0)+"_img")  a_img
     // strings.get(0).upend(strings.get(0)+"_txt")  a_txt		 	
        
        for(String s1:strings)
        {
        	String txtPath="C:\\Users\\PiyushMittal\\Desktop\\garbage\\"+s1+"\\"+s1+"_txt\\";
        	String imgPath="C:\\Users\\PiyushMittal\\Desktop\\garbage\\"+s1+"\\"+s1+"_img\\";
        	txtLocations.add(txtPath);
        	imgLocations.add(imgPath);
        /*	System.out.println(txtPath);
        	System.out.println(imgPath);
        	System.out.println(new File(txtPath).exists());
        	System.out.println(new File(imgPath).exists());
        */    	
        }
        
        
        for(String s1:txtLocations)
        {
        	System.out.println(s1+" "+ new File(s1).exists());
        	//s1=s1+""
        	
        	String[] dir=s1.split("\\\\");
        	
        	s1=s1+dir[dir.length-2]+".txt";
        	System.out.println(comingPdfFile);
        	System.out.println(s1);
        			boolean result=compareTxt(comingPdfFile,s1);
        
        	
        }
       /* for(String s1:imgLocations)
        {
        	System.out.println(s1+" "+ new File(s1).exists());
        	
        }*/
        
        
		
		
	}
	
	
	
	
	private static boolean compareTxt(String source,String Dest) throws IOException{
		
		/* File file1 = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7_0.txt");
	        File file2 = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7.txt");
	    */  File file1 = new File(source);
	        File file2 = new File(Dest);

	        boolean compareResult = FileUtils.contentEquals(file1, file2);
	        System.out.println("Are the files are same? " + compareResult);
		return compareResult;
	}
	
	
	
}

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindTxtImgLocation {
	
	static String txtLoc;
	
	
	public static void main(String a[]) throws IOException{
	
				
		List<String> alLoc=location("C:\\Users\\PiyushMittal\\Desktop\\garbage\\as.pdf");
		List<String> alLoc1=location("C:\\Users\\PiyushMittal\\Desktop\\new_pdf\\xx.pdf");
		
		
		
		String srcTxtLoc=alLoc.get(0);
		
		String s = srcTxtLoc.replace(".pdf","");
		StringBuffer buf = new StringBuffer(s);
		buf.setLength(buf.length() - 1);
		
		
		
		String aa=buf.toString();
		//aa=aa.concat("\\");
		String fileName1=aa.substring(aa.lastIndexOf("\\") + 1);
		System.out.println(srcTxtLoc+fileName1+".txt");
		
		txtLoc=srcTxtLoc+fileName1+".txt";
		
		CompareTxt cmp=new CompareTxt();
		//cmp.comingPdfFile=txtLoc;
		cmp.abc(txtLoc);
		
		
		
		
		String dstTxtLoc=alLoc1.get(0);
						
		String srcImgLoc=alLoc.get(1);
		String dstImgLoc=alLoc1.get(1);
						
		
		
		
		
		System.out.println();
	}
	public static List<String> location(String s){
		List<String> al=new ArrayList<String>();
		
		//String s=f.getAbsolutePath();
		s = s.replace(".pdf","");
		
		String aa=s;
		aa=aa.concat("\\");
		new File(aa).mkdir();
		String fileName1=s.substring(s.lastIndexOf("\\") + 1);
		
		String imgLocation=aa.concat(fileName1+"_img\\");
	//	new File(imgLocation).mkdir();
		String txtLocation=aa.concat(fileName1+"_txt\\");
		//new File(txtLocation).mkdir();
		
		System.out.println();
		
		al.add(imgLocation);
		al.add(txtLocation);
		
		return al;
		
	}
	
	
	
	
}

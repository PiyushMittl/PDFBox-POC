import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.text.PDFTextStripper;

public class PutImgTxtInFolder {


	public static void main(String a[]) throws Exception{
		/*  File dir = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\pagal.pdf");
		 */
		/*File dir=new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\ss");

		dir.mkdir();*/

		File[] ff=finder("C:\\Users\\PiyushMittal\\Desktop\\garbage\\");
		//File[] ff=finder("C:\\Users\\PiyushMittal\\Desktop\\new_pdf\\");
		

		for(File f:ff){
			String pdfsrc=f.getAbsolutePath();
			
			//String fileName=f.getAbsolutePath();
			
	
			 
			String s=f.getAbsolutePath();
			s = s.replace(".pdf","");
			
			String aa=s;
			aa=aa.concat("\\");
			new File(aa).mkdir();
			String fileName1=s.substring(s.lastIndexOf("\\") + 1);
			
			String imgLocation=aa.concat(fileName1+"_img\\");
			new File(imgLocation).mkdir();
			String txtLocation=aa.concat(fileName1+"_txt\\");
			new File(txtLocation).mkdir();
			
			
			//get file name to save images and text by that name 
			//aa.pdf get aa as file name and save aa1.png and aa1.txt
			System.out.println("file name= "+s.substring(s.lastIndexOf("\\") + 1));
			String fileName=s.substring(s.lastIndexOf("\\") + 1);
			
			//make folder with file name which will contain img and txt folder
			new File(s).mkdir();
			
			
			//s=s.concat("_img"+"\\");
			
			s=s.concat("_img"+"\\");
			
			File dir_img = new File(s);
			//dir_img.mkdir();
			//call getImage pass dir_img(destination path) path
			String source=s;
			String dest=dir_img.toString();
			//getImage(dir_img);
		//	getImage(pdfsrc,source,fileName);
			
	/////////image extract coding 
			
			
			getImage(pdfsrc,imgLocation,fileName);
			
			
			
			
			
			/////////text extract coding 
			
			
			String srcTxt=f.getAbsolutePath();
			/*String fileName1=srcTxt;
			fileName1 = fileName1.replace(".pdf","");
			System.out.println("file name= "+s.substring(fileName1.lastIndexOf("\\") + 1));
			 fileName1=fileName1.substring(fileName1.lastIndexOf("\\") + 1);
			*/
			//remove .pdf ext
			//pick file name
			
			
			String s1=f.getAbsolutePath();
			
			s1 = s1.replace(".pdf",""); 
			s1=s1.concat("_txt"+"\\");
			File destTxt = new File(s1);
			//destTxt.mkdir();
			//getText(srcTxt,s1,fileName);
			getText(srcTxt,txtLocation,fileName);
			
			//call getText pass dir_txt(destination path)
			//getText(dir_txt);
			
			//dir=f;
			
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

	
	//provide pdf file location(source) param1
	//provide destination param2
	/*static String getText(File pdfFile) throws IOException {
		PDDocument doc = PDDocument.load(pdfFile);
		
		// code to write to a file
		  
		String s=new PDFTextStripper().getText(doc);
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\PiyushMittal\\Desktop\\garbage\\aanw.txt"));
		writer.write(s);
		writer.flush();
		writer.close();
		
		return new PDFTextStripper().getText(doc);
	}*/
	
	//src is the pdf file location of pdf file from which img and txt being extracted 
	//dest is the destination, where to save text file
	static String getText(String src,String dest,String fileName) throws IOException {
		/**********************************************************hard coding to be remove*****************/		
		//PDDocument doc = PDDocument.load(new File("c:/my.pdf"));
		PDDocument doc = PDDocument.load(new File(src));
		
		// code to write to a file
		  
		String s=new PDFTextStripper().getText(doc);
		BufferedWriter writer = new BufferedWriter(new FileWriter(dest+""+fileName+".txt"));
		//BufferedWriter writer = new BufferedWriter(new FileWriter(dest+""+System.nanoTime()+".txt"));
		//BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\PiyushMittal\\Desktop\\garbage\\aanw.txt"));
		writer.write(s);
		writer.flush();
		writer.close();
		
		return new PDFTextStripper().getText(doc);
	}
	//provide pdf file location(source) param1
	//provide destination param2
	/*static void getImage(File pdfFile) throws IOException {
		
		System.out.println(new File("c:/my.pdf").exists());
		PDDocument document = PDDocument.load(new File("c:/my.pdf"));
		//PDDocument document = PDDocument.load(new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\my.pdf"));
		
		
		//PDDocument document = PDDocument.load(pdfFile);
	    PDPageTree list = document.getPages();
	    for (PDPage page : list) {
	        PDResources pdResources = page.getResources();
	        for (COSName c : pdResources.getXObjectNames()) {
	            PDXObject o = pdResources.getXObject(c);
	            if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
	                File file = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\" + System.nanoTime() + ".jpeg");//file name with address to be created
	                
	                
	                ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject)o).getImage(), "png", file);
	            System.out.println("asas");
	            }
	        }
	    }
		}*/
	static void getImage(String pdfFile,String dest,String fileName) throws IOException {
		
		System.out.println(pdfFile);
		/**********************************************************hard coding to be remove*****************/
		PDDocument document = PDDocument.load(new File(pdfFile));
		//PDDocument document = PDDocument.load(new File("c:/my.pdf"));
		
		//PDDocument document = PDDocument.load(new File(pdfFile));//source
		
		
		//PDDocument document = PDDocument.load(pdfFile);
	    PDPageTree list = document.getPages();
	    int i=0;
	    for (PDPage page : list) {
	        PDResources pdResources = page.getResources();
	        for (COSName c : pdResources.getXObjectNames()) {
	            PDXObject o = pdResources.getXObject(c);
	            if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
	            	//File file = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\" + System.nanoTime() + ".jpeg");//dest
	            	//File file = new File(dest+ System.nanoTime() + ".jpeg");//dest
	            	File file = new File(dest+ fileName+ i++  + ".jpeg");//dest
	            	
	                //file=new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\my.pdf_img\\"+ System.nanoTime() + ".jpeg");
	                
	                ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject)o).getImage(), "png", file);
	            System.out.println("asas");
	            }
	        }
	    }
		}
		
	static void makeDir(String mkdir){
		new File(mkdir).mkdir();
	}
	
	
	
	
	
	
	
}

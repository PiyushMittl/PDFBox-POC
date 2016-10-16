package com.iTuple.selfSamePDF;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import com.iTuple.logger.*;
public class UtilImpl implements Util{
SelfSamePdfLogger lgr=new SelfSamePdfLogger();

static List<String> alPdfLoc=new ArrayList<String>();
//static List<String> alfindTotalFilesInDirectory=new ArrayList<String>();
int countFileInDirectory=0;


public List<String> allFileLoc(String dirLoc) throws IOException {
		File file = new File(dirLoc);
		File[] files = file.listFiles();
		System.out.println();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				String s=file1.getCanonicalPath();
				allFileLoc(s);
			} else {
				System.out.println("file Not Acess===>" + file1.getCanonicalPath());
				lgr.AllPdfLoclogger.info(file1.getCanonicalPath());
				alPdfLoc.add(file1.getCanonicalPath());
			}
		}
		return alPdfLoc;
	}

	
/*public List<String> findPathOfTotalFilesInDirectory(String dirLoc) throws IOException {
	File file = new File(dirLoc);
	File[] files = file.listFiles();
	System.out.println();
	for (File file1 : files) {
		if (file1.isDirectory()) {
			String s=file1.getCanonicalPath();
			allFileLoc(s);
		} else {
			System.out.println("file Not Acess===>" + file1.getCanonicalPath());
			lgr.AllPdfLoclogger.info(file1.getCanonicalPath());
			alfindTotalFilesInDirectory.add(file1.getCanonicalPath());
		}
	}
	return alfindTotalFilesInDirectory;
}*/

	
	
	
	
	public boolean extractImgAndSave(String pdfSrc, String imgDest, String fileName) throws IOException {
		PDDocument document = PDDocument.load(new File(pdfSrc));
		PDPageTree list = document.getPages();
		int i=0;
		for (PDPage page : list) {
			PDResources pdResources = page.getResources();
			for (COSName c : pdResources.getXObjectNames()) {
				PDXObject o = pdResources.getXObject(c);
				if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
					File file = new File(imgDest+ fileName+ i++  + ".jpeg");//dest
					ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject)o).getImage(), "png", file);
					System.out.println("image file created no: "+i);
				}
			}
		}
	
		return false;
	}

	public String extractTxtAndSave(String pdfSrc, String txtDest, String fileName) throws IOException {
		// TODO Auto-generated method stub
		//PDDocument doc = PDDocument.load(new File("c:/my.pdf"));
		PDDocument doc = PDDocument.load(new File(pdfSrc));

		// code to write to a file
		String s=new PDFTextStripper().getText(doc);
		BufferedWriter writer = new BufferedWriter(new FileWriter(txtDest+""+fileName+".txt"));
		writer.write(s);
		writer.flush();
		writer.close();
		return new PDFTextStripper().getText(doc);
	}

	public boolean createDir(String path) {
		File f = new File(path);
		f.mkdirs();
		return false;
	}

	public Map<String, String> imgTextAndFileName(String remove, String add, String pdfSource) {
		// TODO Auto-generated method stub
		Map<String,String> hm=new HashMap<String,String>();
		String newPath = pdfSource.replace(remove,"");
		String noExtensn = newPath.replace(".pdf", "");
		String path2=add+noExtensn;
		String fileName=noExtensn.substring(noExtensn.lastIndexOf("\\") + 1);
		String pathForImg=path2+"\\"+fileName+"_img\\";
		String pathForTxt=path2+"\\"+fileName+"_txt\\";
		hm.put("fileName", fileName);
		hm.put("pathForImg", pathForImg);
		hm.put("pathForTxt", pathForTxt);
		return hm;
	}


	public boolean compareImgFiles(String source, String Dest) {
		File fileA=new File(source);
		File fileB=new File(Dest);
		try
		{
			// take buffer data from botm image files //
			BufferedImage biA = ImageIO.read(fileA);
			DataBuffer dbA = biA.getData().getDataBuffer();
			int sizeA = dbA.getSize();
			BufferedImage biB = ImageIO.read(fileB);
			DataBuffer dbB = biB.getData().getDataBuffer();
			int sizeB = dbB.getSize();
			// compare data-buffer objects //
			if (sizeA == sizeB)
			{
				for (int i = 0; i < sizeA; i++)
				{
					if (dbA.getElem(i) != dbB.getElem(i))
					{
						return false;
					}
				}
				return true;
			}
			else
			{return false;}
		}
		catch (Exception e)
		{
			System.out.println("Failed to compare image files ...");
			return false;
		}

	}


	public void logOfPdfForSameImagesFound(String src, String target) {
		// TODO Auto-generated method stub
		/*lgr.MathFoundPdfLoclogger.info("<===================================>");
		lgr.MathFoundPdfLoclogger.info(src);
		lgr.MathFoundPdfLoclogger.info(target);*/
		
	}

	public void logOfPdfForSameImagesFound2(Map<String,ArrayList<String>> hm, int selfSamePDFCounter) {
		
		int MtchedPdfCounter=Constatnt.MTCHED_PDF_COUNTER;
		
		// TODO Auto-generated method stub
		lgr.MathFoundPdfLoclogger2.info("<=============== total match found = " + selfSamePDFCounter +"====================>");
		for (Entry<String, ArrayList<String>> entry : hm.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    lgr.MathFoundPdfLoclogger2.info("Source PDF = " + entry.getKey());// + " total match found = " + selfSamePDFCounter);
		    
		    for(String s:entry.getValue()){
		    	 lgr.MathFoundPdfLoclogger2.info("Match "+MtchedPdfCounter++ +" found with    "+s);
		    	
		    }
		    
		    
		}
		
		
		
		/*lgr.MathFoundPdfLoclogger2.info("<===================================>");
		lgr.MathFoundPdfLoclogger2.info(src);
		lgr.MathFoundPdfLoclogger2.info(target);*/
		
	}

	

	public int countFileInDirectory(String path) throws IOException
	{
		int count=0;
		File file = new File(path);
		File[] files = file.listFiles();
		System.out.println();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				// System.out.println("Found directory address==>:" + file1.getCanonicalPath());
				String s=file1.getCanonicalPath();
				countFileInDirectory(s);
				//    findSubdirectory(file.getCanonicalPath());
				//displayDirectoryContents(file);
			} else {
				System.out.println("file Not Acess===>" + file1.getCanonicalPath());
				count++;
			}
		}
		return count;
	}
	
	
	public long calculateToatalSizeOfFileInDirectory(String src) throws IOException {

		long totalSize=0;
		File file = new File(src);
		File[] files = file.listFiles();
		System.out.println();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				// System.out.println("Found directory address==>:" + file1.getCanonicalPath());
				String s=file1.getCanonicalPath();
				countFileInDirectory(s);
				//    findSubdirectory(file.getCanonicalPath());
				//displayDirectoryContents(file);
			} else {
				totalSize=totalSize+file1.length();

			}
		}
		return totalSize;

	}


	public boolean compareTxtFiles(String source, String Dest) throws IOException {
		 /* File file1 = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7_0.txt");
        File file2 = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7.txt");
    */  File file1 = new File(source);
        File file2 = new File(Dest);

        boolean compareResult = FileUtils.contentEquals(file1, file2);
        System.out.println("Are the files are same? " + compareResult);
	return compareResult;
	
	}
	
	public ArrayList<String> rmv(List<String> al2,List<String> remove){
		
		List<String> al=new ArrayList(al2);
al.removeAll(remove);
		
		return (ArrayList<String>) al;
	}
	
	
	
}

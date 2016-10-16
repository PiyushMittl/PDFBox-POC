package com.poc.pdf;
import com.poc.pdf.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.text.PDFTextStripper;

public class Main {

	//private static final String SALIX = "C://Users//PiyushMittal//Desktop//Data";
	private static final String SALIX = "C:\\Users\\PiyushMittal\\Desktop\\Data";
	private static final String RFL = "C:\\Users\\PiyushMittal\\Desktop\\garbage\\";
	//C:\Users\PiyushMittal\Desktop\garbage

	public static void main(String[] args) throws IOException {

		findSubdirectory(SALIX);



		/*	for(String ss:alPdfLoc){
			pdfsImgTxtLocation(ss,ss);
		//putImgTxtInFolder(ss);
		}
		 */

		//count files in directory




		for(String ss:alPdfLoc){
			putImgTxtInFolder(RFL,ss);
			//putImgTxtInFolder(ss);
		}

		// ITERATE ON ALL FILES IN SALIX AND CREATE PDF DUMP IN RFL FOLDER
		// OP : SCAN ALL FILES IN SALIX
		// O : FOLDERS IN RFL

		// PROCESS ON ALL RFL FOLDERS AND COMPARE ONE AT TIME WITH ALL OTHER AND
		// AVOID MATCH IF A COMPARE B HAS OCCURRED.
		// O : IF A FOLDER MATCHED OTHER FOLDER.

		// OUTPUT : LIST OF DUPLICATE FILES
	}

	static List<String> alPdfLoc=new ArrayList<String>();
	public static void findSubdirectory(String ss) throws IOException{

		File file = new File(ss);
		File[] files = file.listFiles();
		System.out.println();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				// System.out.println("Found directory address==>:" + file1.getCanonicalPath());
				String s=file1.getCanonicalPath();
				findSubdirectory(s);
				//    findSubdirectory(file.getCanonicalPath());
				//displayDirectoryContents(file);
			} else {
				System.out.println("file Not Acess===>" + file1.getCanonicalPath());
				alPdfLoc.add(file1.getCanonicalPath());
			}
		}
	}

	public static void putImgTxtInFolder(String pdfPath,String pdfFileLoc) throws IOException{

		String pdfsrc=pdfFileLoc;

		Map<String,String> hm=new HashMap();

		Test t=new Test();
		String remove = "C:\\Users\\PiyushMittal\\Desktop\\Data\\Data\\";
		hm=Test.prepareImgAndTxtPath(remove,pdfFileLoc);

		String hmImgLoc=hm.get("pathForImg");
		String hmTxtLoc=hm.get("pathForTxt");
		String hmFileName=hm.get("fileName");

		getImage(pdfsrc,hmImgLoc,hmFileName);
		getText(pdfsrc,hmTxtLoc,hmFileName);

	}

	static void getImage(String pdfFile,String dest,String fileName) throws IOException {

		System.out.println(pdfFile);
		/**********************************************************hard coding to be remove*****************/
		PDDocument document = PDDocument.load(new File(pdfFile));
		PDPageTree list = document.getPages();
		int i=0;
		for (PDPage page : list) {
			PDResources pdResources = page.getResources();
			for (COSName c : pdResources.getXObjectNames()) {
				PDXObject o = pdResources.getXObject(c);
				if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
					File file = new File(dest+ fileName+ i++  + ".jpeg");//dest
					ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject)o).getImage(), "png", file);
					System.out.println("asas");
				}
			}
		}
	}

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


	public static void createDir(String path)
	{
		//File f = new File("C:/Users/PiyushMittal/Desktop/ashu/ashu2/ashu3/ashu4");
		File f = new File(path);
		f.mkdirs();
	}




	public static Map<String,String> pdfsImgTxtLocation(String pdf1Loc,String pdf2Loc){
		String remove = "C:\\Users\\PiyushMittal\\Desktop\\Data\\Data\\";

		Map<String,String> hm=new HashMap();


		Map<String,String> hmPdf1Loc=new HashMap<String,String>();

		hmPdf1Loc=Test.createPath(remove, pdf1Loc);
		String hmPdf1LocImgLoc=hmPdf1Loc.get("pathForImg");
		String hmPdf1LocTxtLoc=hmPdf1Loc.get("pathForTxt");
		String hmPdf1LocFileName=hmPdf1Loc.get("fileName");

		Map<String,String> hmPdf2Loc=new HashMap<String,String>();

		hmPdf2Loc=Test.createPath(remove, pdf1Loc);
		String hmPdf2LocImgLoc=hmPdf2Loc.get("pathForImg");
		String hmPdf2LocTxtLoc=hmPdf2Loc.get("pathForTxt");
		String hmPdf2LocFileName=hmPdf2Loc.get("fileName");


		hm.put("s1PdfImgFolderLoc",hmPdf1LocImgLoc);
		hm.put("s1PdfTxtFolderLoc",hmPdf1LocTxtLoc);
		hm.put("s1PdfFileName",hmPdf1LocFileName);
		hm.put("s2PdfImgFolderLoc",hmPdf2LocImgLoc);
		hm.put("s2PdfTxtFolderLoc",hmPdf2LocTxtLoc);
		hm.put("s2PdfFileName",hmPdf2LocFileName);

		return hm;
	}


}

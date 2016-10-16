package com.poc.pdf;
import org.apache.commons.io.FileUtils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class Comparison {
	private static final String SALIX = "C:\\Users\\PiyushMittal\\Desktop\\Data";
	private static final String RFL = "C:\\Users\\PiyushMittal\\Desktop\\garbage\\";
	static String remove = "C:\\Users\\PiyushMittal\\Desktop\\Data\\Data\\";

	static Logger logger = Logger.getLogger("MyLog");  
	static Logger loggerDuplicateFile = Logger.getLogger("DuplicateFile");  

	static FileHandler  fh;
	static FileHandler  fhDuplicateFile;


	public static void main(String[] args) throws IOException {

		fh = new FileHandler("C:/log/MyLogFile.log"); 
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
		fh.setFormatter(formatter); 

		

		fhDuplicateFile = new FileHandler("C:/log/DuplicateFile.log"); 
		loggerDuplicateFile.addHandler(fhDuplicateFile);
		SimpleFormatter formattervDuplicateFile = new SimpleFormatter();  
		fhDuplicateFile.setFormatter(formattervDuplicateFile); 
		// the following statement is used to log any messages  

		Main.findSubdirectory(SALIX);
		compare(Main.alPdfLoc);


		/*for(String ss:Main.alPdfLoc){

			//calculate size
			Map<String,String> hm=Test.createPath(remove,ss);
			int noOfImgFiles=Test.countFileInDirectory(hm.get("pathForImg"));
			int noOfTxtFiles=Test.countFileInDirectory(hm.get("pathForTxt"));

			System.out.println(noOfImgFiles);
			System.out.println(noOfTxtFiles);


			//calculate size
			long totalSizeOfImgFiles=Test.calculateToatalSizeOfFileInDirectory(hm.get("pathForImg"));
			long totalSizeOfTxtFiles=Test.calculateToatalSizeOfFileInDirectory(hm.get("pathForTxt"));

			double totalSizeOfImgFilesInMB = (double) totalSizeOfImgFiles / 1024 / 1024;
			double totalSizeOfTxtFilesInMB = (double) totalSizeOfTxtFiles / 1024 / 1024;

			String s1 = " MB";
			if (totalSizeOfImgFilesInMB < 1) {
				totalSizeOfImgFilesInMB = (double) totalSizeOfImgFiles / 1024;
				s1 = " KB";
			}
			String s2="MB";
			if (totalSizeOfImgFilesInMB < 1) {
				totalSizeOfTxtFilesInMB = (double) totalSizeOfTxtFiles / 1024;
				s2 = " KB";
			}
			System.out.println(totalSizeOfImgFilesInMB  +"   "+s1);
			System.out.println(totalSizeOfTxtFilesInMB  +"   "+s2);
			System.out.println("");			

		}*/

		// TODO Auto-generated method stub
	}


	public static void compare(List<String> alPdfFiles) throws IOException{

		List<String> al2=new ArrayList();
		Map<String,String> hmLoc1=new HashMap();
		Map<String,String> hmLoc2=new HashMap();
		String txtLocOfs1;
		String imgLocOfs1;
		String txtLocOfs2;
		String imgLocOfs2;
		int imageSize=0;
		int imagecount=0;
		int textSize=0;
		int textCount=0;

		List<String> alImgFilesS1=new ArrayList();
		List<String> alTxtFilesS1=new ArrayList();
		List<String> alImgFilesS2=new ArrayList();
		List<String> alTxtFilesS2=new ArrayList();

		long totalSizeOfImgFilesInS1=0;
		long totalSizeOfImgFilesIns2=0;

		int test=0;

		Map<String,ImageFileStatus> hmImgStatus=new HashMap();

		for(String s1:alPdfFiles)
		{

			logger.info("For iteration:  "+test++);
			logger.info("size of alPdfFiles1:  "+alPdfFiles.size());
			int indEndOfs1=alPdfFiles.indexOf(s1);
			al2=alPdfFiles.subList(indEndOfs1+1, alPdfFiles.size());
			for(String s2:al2)
			{
				logger.info("s2="+s2);				
				logger.info("size of alPdfFiles1:  "+al2.size());
				System.err.println("s1="+s1+"    compared    "+s2);
				System.err.println("counter="+test+++"    size of s2="+s2.length());
				hmLoc1=Test.createPath(remove,s1);
				hmLoc2=Test.createPath(remove,s2);
				txtLocOfs1=hmLoc1.get("pathForTxt");
				imgLocOfs1=hmLoc1.get("pathForImg");
				txtLocOfs2=hmLoc2.get("pathForTxt");
				imgLocOfs2=hmLoc2.get("pathForImg");
				int noOfImgFilesInS1=Test.countFileInDirectory(imgLocOfs1);
				int noOfImgFilesInS2=Test.countFileInDirectory(imgLocOfs2);
				int noOfTxtFilesInS1=Test.countFileInDirectory(txtLocOfs1);
				int noOfTxtFilesInS2=Test.countFileInDirectory(txtLocOfs2);
				if(noOfImgFilesInS1==noOfImgFilesInS2)
				{
					imagecount=1;
					//calculate size
					totalSizeOfImgFilesInS1=Test.calculateToatalSizeOfFileInDirectory(imgLocOfs1);
					totalSizeOfImgFilesIns2=Test.calculateToatalSizeOfFileInDirectory(imgLocOfs2);
					if(totalSizeOfImgFilesInS1==totalSizeOfImgFilesIns2){
						imageSize=1;
					}

					/*double totalSizeOfImgFilesInMB = (double) totalSizeOfImgFiles / 1024 / 1024;
				double totalSizeOfTxtFilesInMB = (double) totalSizeOfTxtFiles / 1024 / 1024;

				String s11 = " MB";
				if (totalSizeOfImgFilesInMB < 1) {
					totalSizeOfImgFilesInMB = (double) totalSizeOfImgFiles / 1024;
					s11 = " KB";
				}
				String s22="MB";
				if (totalSizeOfImgFilesInMB < 1) {
					totalSizeOfTxtFilesInMB = (double) totalSizeOfTxtFiles / 1024;
					s22 = " KB";
				}

				System.out.println(totalSizeOfImgFilesInMB  +"   "+s11);
				System.out.println(totalSizeOfTxtFilesInMB  +"   "+s22);*/

				}
				/*imagecount=1;
				imageSize=1;*/
				if(imagecount==1 && imageSize==1)
				{
					System.err.println("found same image count and same size");
					logger.info("=========>found same image count and same size  "+imgLocOfs1+"  with   "+imgLocOfs2);
					logger.info("=========>										 						"+imgLocOfs2);
					alfindTotalFilesInDirectory=new ArrayList();	
					alImgFilesS1=Comparison.findPathOfTotalFilesInDirectory(imgLocOfs1);
					alfindTotalFilesInDirectory=new ArrayList();
					alTxtFilesS1=Comparison.findPathOfTotalFilesInDirectory(txtLocOfs1);
					alfindTotalFilesInDirectory=new ArrayList();
					alImgFilesS2=Comparison.findPathOfTotalFilesInDirectory(imgLocOfs2);
					alfindTotalFilesInDirectory=new ArrayList();
					alTxtFilesS2=Comparison.findPathOfTotalFilesInDirectory(txtLocOfs2);
					Map<String,ImageFileStatus> hmImgStatusTemp=new HashMap();
					int i=0;
					for(i=0;i<noOfImgFilesInS1 && noOfImgFilesInS1==noOfImgFilesInS2;i++){
						logger.info("alImgFilesS1= "+alImgFilesS1.size());
						logger.info("alImgFilesS2= "+alImgFilesS2.size());
						boolean status=compareImgFiles(alImgFilesS1.get(i),alImgFilesS2.get(i));
						System.err.println("image match found  "+alImgFilesS1.get(i)+"  with   "+alImgFilesS2.get(i));
						System.out.println("i= "+i+" totalSizeOfImgFilesInS1= "+noOfImgFilesInS1);
						logger.info("image match found  "+alImgFilesS1.get(i)+"  with   "+alImgFilesS2.get(i));
						logger.info("i= "+i+" totalSizeOfImgFilesInS1= "+noOfImgFilesInS1);

						if(status==true){
							ImageFileStatus ims=new ImageFileStatus();
							ims.setFileS1(alImgFilesS1.get(i));
							ims.setFileS2(alImgFilesS2.get(i));
							hmImgStatusTemp.put("true",ims );
						}
					}


					//////////////////////////text compare code to be written///////////////////////////////////////////////////////////////////////
					if(i==noOfImgFilesInS1)
					{
						logger.info("---------");
						logger.info("---------");
						logger.info("match Found: "+s1+"  with"+s2);
						loggerDuplicateFile.info("match Found: "+s1+"  with"+s2);
						loggerDuplicateFile.info(s1);
						loggerDuplicateFile.info(s2);
						logger.info("---------");
						logger.info("---------");
						
				/*	boolean status=compareTxtFiles(alImgFilesS1.get(i),alImgFilesS2.get(i));
					if(status==true)
					{					}	 */

					}
					System.out.println();
				}
				System.out.println("");		
			}

		}


	}



	public static boolean compareTxtFiles(String source, String dest ) throws IOException{

		/* File file1 = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7_0.txt");
        File file2 = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\saurabh7.txt");
		 */  File file1 = new File(source);
		 File file2 = new File(dest);

		 boolean compareResult = FileUtils.contentEquals(file1, file2);
		 System.out.println("Are the files are same? " + compareResult);
		 return compareResult;
	}



	public static boolean compareImgFiles(String source,String Dest ){
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

	static List alfindTotalFilesInDirectory=new ArrayList();
	public static List<String> findPathOfTotalFilesInDirectory(String ss) throws IOException{
		File file = new File(ss);
		File[] files = file.listFiles();
		System.out.println();
		for (File file1 : files) {
			if (file1.isDirectory()) {
				String s=file1.getCanonicalPath();
				findPathOfTotalFilesInDirectory(s);
			} else {
				System.out.println("file Not Acess===>" + file1.getCanonicalPath());
				alfindTotalFilesInDirectory.add(file1.getCanonicalPath());
			}
		}
		return alfindTotalFilesInDirectory;
	}
}

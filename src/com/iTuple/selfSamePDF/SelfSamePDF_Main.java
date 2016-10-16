package com.iTuple.selfSamePDF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.iTuple.logger.SelfSamePdfLogger;
import com.poc.pdf.ImageFileStatus;

public class SelfSamePDF_Main {

	public static void main(String[] args) throws IOException {

		//extraction();
		duplicate();
	}

	private static void duplicate() throws IOException {
		List<String> al2 = new ArrayList();
		Map<String, String> hmLoc1 = new HashMap();
		Map<String, String> hmLoc2 = new HashMap();
		String txtLocOfs1;
		String imgLocOfs1;
		String txtLocOfs2;
		String imgLocOfs2;
		int imageSize = 0;
		int imagecount = 0;
		int textSize = 0;
		int textCount = 0;
		List<String> alImgFilesS1 = new ArrayList();
		List<String> alTxtFilesS1 = new ArrayList();
		List<String> alImgFilesS2 = new ArrayList();
		List<String> alTxtFilesS2 = new ArrayList();
		long totalSizeOfImgFilesInS1 = 0;
		long totalSizeOfImgFilesIns2 = 0;
		List<String> alPdfList2 = new ArrayList();
		int selfSamePDFCounter = 0;
		int i2 = 0;
		int test = 0;

		ArrayList<String> x=new ArrayList();

		Map<String, ImageFileStatus> hmImgStatus = new HashMap();

		SelfSamePdfLogger lgrMain=new SelfSamePdfLogger();


		List<String> alPdfLocation = new ArrayList<String>();
		// get all pdf locations save to collection
		UtilImpl ui = new UtilImpl();
		alPdfLocation = ui.allFileLoc(Constatnt.SALIX);

		for (String s1 : alPdfLocation) {
			System.out.println(s1);
		}

		// compare source ie:s1 to target ie:s2
		// for(String s1:alPdfLocation)

		for (Iterator<String> iterator = alPdfLocation.iterator(); iterator.hasNext();)// asas
		{
			String currentPdf = iterator.next();// asas
			//	alPdfLocation.removeAll(alPdfList2);// asas
			i2 = 0;
			// logger.info("For iteration: "+test++);
			// logger.info("size of alPdfFiles1: "+alPdfFiles.size());
			selfSamePDFCounter = 0;
			Map<String, ArrayList<String>> hmPdfList = new HashMap();
			String key = currentPdf;
			int indEndOfs1 = alPdfLocation.indexOf(currentPdf);
			al2 = alPdfLocation.subList(indEndOfs1 + 1, alPdfLocation.size());
			ArrayList<String>  removeRepeat_al=ui.rmv(al2, x);
			//al2.removeAll(alPdfList2);
			alPdfList2 = new ArrayList();

			lgrMain.MathFoundPdfLoclogger3.info("total pdf to be compared: "+removeRepeat_al.size());
			for(String k:removeRepeat_al){
				lgrMain.MathFoundPdfLoclogger3.info(currentPdf+"  to be compared with  "+k);
			}

			hmLoc1 = ui.imgTextAndFileName(Constatnt.remove, Constatnt.add, currentPdf);
			txtLocOfs1 = hmLoc1.get("pathForTxt");
			imgLocOfs1 = hmLoc1.get("pathForImg");
			int noOfImgFilesInS1 = ui.countFileInDirectory(imgLocOfs1);
			int noOfTxtFilesInS1 = ui.countFileInDirectory(txtLocOfs1);
			totalSizeOfImgFilesInS1 = 0;
			totalSizeOfImgFilesInS1 = ui.calculateToatalSizeOfFileInDirectory(imgLocOfs1);
			//for (String s2 : al2)
			for (String s2 : removeRepeat_al)

			{
				i2++;
				List<String> alPdfList = new ArrayList(); // logger.info("s2="+s2);
				// logger.info("size of alPdfFiles1: "+al2.size());
				totalSizeOfImgFilesIns2 = 0;

				System.err.println("s1=" + currentPdf + "    compared    " + s2);
				System.err.println("counter=" + test++ + "    size of s2=" + s2.length());
				lgrMain.MathFoundPdfLoclogger3.info("s1=" + currentPdf + "    compared    " + s2);
				lgrMain.MathFoundPdfLoclogger3.info("counter=" + test + "    size of s2=" + s2.length());

				hmLoc2 = ui.imgTextAndFileName(Constatnt.remove, Constatnt.add, s2);
				txtLocOfs2 = hmLoc2.get("pathForTxt");
				imgLocOfs2 = hmLoc2.get("pathForImg");
				int noOfImgFilesInS2 = ui.countFileInDirectory(imgLocOfs2);
				int noOfTxtFilesInS2 = ui.countFileInDirectory(txtLocOfs2);

				if (noOfImgFilesInS1 == noOfImgFilesInS2) {
					// imagecount=1;
					// calculate size
					totalSizeOfImgFilesIns2 = ui.calculateToatalSizeOfFileInDirectory(imgLocOfs2);
				}
				/*
				 * if(totalSizeOfImgFilesInS1==totalSizeOfImgFilesIns2){
				 * imageSize=1; } if(imagecount==1 && imageSize==1) {
				 */
				// if((noOfImgFilesInS1==noOfImgFilesInS2) &&
				// (totalSizeOfImgFilesInS1==totalSizeOfImgFilesIns2))

				lgrMain.MathFoundPdfLoclogger3.info("noOfImgFilesInS1 in s1= " + noOfImgFilesInS1 + "    noOfImgFilesInS2 in s2= " + noOfImgFilesInS2);
				lgrMain.MathFoundPdfLoclogger3.info("totalSizeOfImgFilesInS1 in s1= " + ui.calculateToatalSizeOfFileInDirectory(imgLocOfs1) + "    totalSizeOfImgFilesInS2 in s2= " + ui.calculateToatalSizeOfFileInDirectory(imgLocOfs2));

				if ((noOfImgFilesInS1 == noOfImgFilesInS2) && (ui.calculateToatalSizeOfFileInDirectory(imgLocOfs1) == ui
						.calculateToatalSizeOfFileInDirectory(imgLocOfs2))) {

					lgrMain.MathFoundPdfLoclogger3.info("comparing images and files");

					System.err.println("found same image count and same size");
					System.out.println("");
					// logger.info("=========>found same image count and same
					// size "+imgLocOfs1+" with "+imgLocOfs2);
					// logger.info("=========> "+imgLocOfs2);
					/*
					 */
					ui.alPdfLoc = new ArrayList();
					alImgFilesS1 = ui.allFileLoc(imgLocOfs1);
					ui.alPdfLoc = new ArrayList();
					alTxtFilesS1 = ui.allFileLoc(txtLocOfs1);
					ui.alPdfLoc = new ArrayList();
					alImgFilesS2 = ui.allFileLoc(imgLocOfs2);
					ui.alPdfLoc = new ArrayList();
					alTxtFilesS2 = ui.allFileLoc(txtLocOfs2);

					Map<String, ImageFileStatus> hmImgStatusTemp = new HashMap();
					int i = 0;

					for (i = 0; i < noOfImgFilesInS1 && noOfImgFilesInS1 == noOfImgFilesInS2; i++) {
						// logger.info("alImgFilesS1= "+alImgFilesS1.size());
						// logger.info("alImgFilesS2= "+alImgFilesS2.size());
						boolean status = ui.compareImgFiles(alImgFilesS1.get(i), alImgFilesS2.get(i));
						System.err.println(
								"image match found  " + alImgFilesS1.get(i) + "  with   " + alImgFilesS2.get(i));
						System.out.println("i= " + i + " totalSizeOfImgFilesInS1= " + noOfImgFilesInS1);
						// logger.info("image match found
						// "+alImgFilesS1.get(i)+" with "+alImgFilesS2.get(i));
						// logger.info("i= "+i+" totalSizeOfImgFilesInS1=
						// "+noOfImgFilesInS1);

						if (status == true) {
							ImageFileStatus ims = new ImageFileStatus();
							ims.setFileS1(alImgFilesS1.get(i));
							ims.setFileS2(alImgFilesS2.get(i));
							hmImgStatusTemp.put("true", ims);
						}
					}
					////////////////////////// text compare code to be
					////////////////////////// written///////////////////////////////////////////////////////////////////////
					if (i == noOfImgFilesInS1 && ui.compareTxtFiles(alTxtFilesS1.get(0), alTxtFilesS2.get(0)))
						// if(i==noOfImgFilesInS1)
					{
						lgrMain.MathFoundPdfLoclogger3.info("images and text are same");

						ui.logOfPdfForSameImagesFound(currentPdf, s2);
						alPdfList.add(s2);
						selfSamePDFCounter++;
					}
					System.out.println("");
				}
				System.out.println("");

				alPdfList2.addAll(alPdfList);
				x.addAll(alPdfList);
			}
			if (selfSamePDFCounter != 0 && i2 == removeRepeat_al.size()) {
				hmPdfList.put(key, (ArrayList<String>) alPdfList2);

				// ui.logOfPdfForSameImagesFound2((Map<String, List<String>>)
				// hmPdfList.put(key,(ArrayList<String>) alPdfList));
				ui.logOfPdfForSameImagesFound2((Map<String, ArrayList<String>>) hmPdfList, selfSamePDFCounter);
				System.out.println();
			}
			System.out.println("---------------------Program ends---------------------");
			for(String z:x)
				System.out.println(z);
		}
	}
	// }

	private static void extraction() throws IOException {
		List<String> alPdfLocation = new ArrayList();
		// get all pdf locations save to collection
		UtilImpl ui = new UtilImpl();
		alPdfLocation = ui.allFileLoc(Constatnt.SALIX);
		Map<String, String> hmImgTxtFile = new HashMap();
		String txtDirLoc;
		String imgDirLoc;
		String fileName;

		SelfSamePdfLogger lgrImgTxtExtraction=new SelfSamePdfLogger();
		
		// Step1: for each pdf location
		for (String eachLoc : alPdfLocation) {
			// Step2: get file name,directory path for image and text files to
			// be stored
			hmImgTxtFile = ui.imgTextAndFileName(Constatnt.remove, Constatnt.add, eachLoc);
			txtDirLoc = hmImgTxtFile.get("pathForTxt");
			imgDirLoc = hmImgTxtFile.get("pathForImg");
			fileName = hmImgTxtFile.get("fileName");
			System.out.println("file name, Text path and Image path fetched");
			lgrImgTxtExtraction.ImgTxtExtractionPdfLoclogger.info("file name, Text path and Image path fetched for file: "+eachLoc);
			// create directory
			ui.createDir(txtDirLoc);
			ui.createDir(imgDirLoc);
			System.out.println("directory for image and text created");
			lgrImgTxtExtraction.ImgTxtExtractionPdfLoclogger.info("directory for image and text created");
				
			// Step3: copy text and image to the directory path accessed from
			// step2
			ui.extractImgAndSave(eachLoc, imgDirLoc, fileName);
			System.out.println("image exctrated abd saved");
			lgrImgTxtExtraction.ImgTxtExtractionPdfLoclogger.info("image exctrated saved");
			ui.extractTxtAndSave(eachLoc, txtDirLoc, fileName);
			System.out.println("text exctrated abd saved");
			lgrImgTxtExtraction.ImgTxtExtractionPdfLoclogger.info("text exctrated  saved");

			System.out.println();
		}
		System.out.println("---------------------Program ends---------------------");
	}
}

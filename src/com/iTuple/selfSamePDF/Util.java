package com.iTuple.selfSamePDF;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Util {

	//find all files in directory and sub directory
	public List<String> allFileLoc(String dirLoc) throws IOException;
		
	/*//find all files in directory and sub directory
	public List<String> findPathOfTotalFilesInDirectory(String dirLoc) throws IOException;
	*/	
	//save image from source PDF to destination folder
	public boolean extractImgAndSave(String pdfSrc,String imgDest,String fileName) throws IOException;
		
	//save image from source PDF to destination folder
	public String extractTxtAndSave(String pdfSrc,String imgDest,String fileName) throws IOException;
	
	//create directory to given path
	public boolean createDir(String path);
	
	//return map of location for images and text to get extracted and file name
	public Map<String,String> imgTextAndFileName(String remove,String add,String pdfSource);
	
	//compare two image files and send the result true if same false else
	public boolean compareImgFiles(String source,String Dest );
	
	//compare two image files and send the result true if same false else
	public boolean compareTxtFiles(String source,String Dest ) throws IOException;
		
	
	//generate log of two same pdf file whose images are same 
	public void logOfPdfForSameImagesFound(String src,String target);
	
	//calculate total size of files in directory
	public long calculateToatalSizeOfFileInDirectory(String src) throws IOException;
	
	//calculate total number of files in directory
	public int countFileInDirectory(String path) throws IOException;
	
}

package com.poc.pdf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static Map<String,String> prepareImgAndTxtPath(String remove1,String path1){
		Map<String,String> hm=new HashMap<String,String>();

		String remove = remove1;
		String path = path1;

		Map<String,String> pth=new HashMap();
		pth=createPath(remove,path);

		String fileName=pth.get("fileName");
		String pathForImg=pth.get("pathForImg");
		String pathForTxt=pth.get("pathForTxt");

		hm.put("fileName", fileName);
		hm.put("pathForImg", pathForImg);
		hm.put("pathForTxt", pathForTxt);

		createDir(pathForImg);
		createDir(pathForTxt);

		return hm;
	}

	public static Map<String,String> createPath(String remove1,String path1){
		Map<String,String> hm=new HashMap<String,String>();

		String remove = remove1;
		String path = path1;

		String newPath = path.replace(remove,"");
		String noExtensn = newPath.replace(".pdf", "");

		String add="C:\\Users\\PiyushMittal\\Desktop\\processed\\";
		String path2=add+noExtensn;
		//String fileName1=path2.substring(path2.lastIndexOf("//") + 1);
		String fileName=noExtensn.substring(noExtensn.lastIndexOf("\\") + 1);
		String pathForImg=path2+"\\"+fileName+"_img\\";
		String pathForTxt=path2+"\\"+fileName+"_txt\\";

		hm.put("fileName", fileName);
		hm.put("pathForImg", pathForImg);
		hm.put("pathForTxt", pathForTxt);
		return hm;
	}

	public static void createDir(String path)
	{
		File f = new File(path);
		f.mkdirs();
	}


	public static int countFileInDirectory(String path) throws IOException
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


	public static long calculateToatalSizeOfFileInDirectory(String path) throws IOException
	{

		long totalSize=0;
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
				totalSize=totalSize+file1.length();

			}
		}
		return totalSize;

	}






}

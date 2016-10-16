package com.kodehelp.sftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPinJava {

	static String SFTPHOST = "182.71.228.81";
	static int    SFTPPORT = 8899;
	static String SFTPUSER = "root";
	static String SFTPPASS = "redhat";
	static String SFTPWORKINGDIR = "/usr/local/mypack/reports/Daily/SEP15"; 
	 
	static Session     session     = null;
	static Channel     channel     = null;
	static ChannelSftp channelSftp = null;
	
	
	/*JSch jsch = new JSch();
	session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
	session.setPassword(SFTPPASS);
	java.util.Properties config = new java.util.Properties();
	config.put("StrictHostKeyChecking", "no");
	session.setConfig(config);
	session.connect();
	channel = session.openChannel("sftp");
	channel.connect();
	channelSftp = (ChannelSftp)channel;
	channelSftp.cd(SFTPWORKINGDIR);*/
	
	public static void main(String[] args) {
		
		/*String SFTPHOST = "10.20.30.40";
		int    SFTPPORT = 22;
		String SFTPUSER = "username";
		String SFTPPASS = "password";*/
		//String SFTPWORKINGDIR = "/export/home/kodehelp/";
		String SFTPHOST = "182.71.228.81";
		int    SFTPPORT = 8899;
		String SFTPUSER = "root";
		String SFTPPASS = "redhat";
		//String SFTPWORKINGDIR = "/usr/local/mypack/reports/Daily/SEP15"; 
		String SFTPWORKINGDIR = "/usr/local/mypack/reports/"; 
		 
		
		
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		 
		try{
		JSch jsch = new JSch();
		session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
		session.setPassword(SFTPPASS);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channel = session.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp)channel;
		channelSftp.cd(SFTPWORKINGDIR);
		byte[] buffer = new byte[1024];
		//BufferedInputStream bis = new BufferedInputStream(channelSftp.get("ACTIVATION_SERVICE_TRANS_2015-09-09 01-00-05.pdf"));
		
		
		//File newFile = new File("C:/Test.java");
		File newFile = new File("C:\\Users\\PiyushMittal\\Desktop\\test.pdf");
		
		
		String dest="C:\\Users\\PiyushMittal\\Desktop\\MM";
		downloadDir(SFTPWORKINGDIR,dest); 
		
		
		OutputStream os = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		int readCount;
		//System.out.println("Getting: " + theLine);
		/*while( (readCount = bis.read(buffer)) > 0) {
		System.out.println("Writing: " );
		bos.write(buffer, 0, readCount);
		}
		bis.close();*/
		bos.close();
		}catch(Exception ex){
		ex.printStackTrace();
		}
		 
		}
	
	
	
	 static void downloadDir(String sourcePath, String destPath) throws SftpException, JSchException { // With subfolders and all files.
		
		
		 String SFTPHOST = "182.71.228.81";
		 int    SFTPPORT = 8899;
		 String SFTPUSER = "root";
		 String SFTPPASS = "redhat";
		 //String SFTPWORKINGDIR = "/usr/local/mypack/reports/Daily/SEP15"; 
		 
		 String SFTPWORKINGDIR = "/usr/local/mypack/reports/";
		 
		 Session     session     = null;
		 Channel     channel     = null;
		 ChannelSftp channelSftp = null;
		JSch jsch = new JSch();
		session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
		session.setPassword(SFTPPASS);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channel = session.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp)channel;
		channelSftp.cd(SFTPWORKINGDIR);
		
	    // Create local folders if absent.
	    try {
	        new File(destPath).mkdirs();
	    } catch (Exception e) {
	        System.out.println("Error at : " + destPath);
	    }
	    channelSftp.lcd(destPath);

	    // Copy remote folders one by one.
	    lsFolderCopy(sourcePath, destPath); // Separated because loops itself inside for subfolders.
	}

	static void lsFolderCopy(String sourcePath, String destPath) throws SftpException, JSchException { // List source (remote, sftp) directory and create a local copy of it - method for every single directory.
	   
		JSch jsch = new JSch();
		session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
		session.setPassword(SFTPPASS);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		channel = session.openChannel("sftp");
		channel.connect();
		channelSftp = (ChannelSftp)channel;
		channelSftp.cd(SFTPWORKINGDIR);
		
		
		
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(sourcePath); // List source directory structure.
	    for (ChannelSftp.LsEntry oListItem : list) { // Iterate objects in the list to get file/folder names.
	        if (!oListItem.getAttrs().isDir()) { // If it is a file (not a directory).
	            if (!(new File(destPath + "/" + oListItem.getFilename())).exists() || (oListItem.getAttrs().getMTime() > Long.valueOf(new File(destPath + "/" + oListItem.getFilename()).lastModified() / (long) 1000).intValue())) { // Download only if changed later.
	                new File(destPath + "/" + oListItem.getFilename());
	                channelSftp.get(sourcePath + "/" + oListItem.getFilename(), destPath + "/" + oListItem.getFilename()); // Grab file from source ([source filename], [destination filename]).
	            }
	        } else if (!(".".equals(oListItem.getFilename()) || "..".equals(oListItem.getFilename()))) {
	            new File(destPath + "/" + oListItem.getFilename()).mkdirs(); // Empty folder copy.
	            lsFolderCopy(sourcePath + "/" + oListItem.getFilename(), destPath + "/" + oListItem.getFilename()); // Enter found folder on server to read its contents and create locally.
	        }
	    }
	}
	
	
	
	}


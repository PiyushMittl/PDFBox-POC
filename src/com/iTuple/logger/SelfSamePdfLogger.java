package com.iTuple.logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class SelfSamePdfLogger extends Formatter{
	
	//private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	 static String timeStmp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	public static Logger AllPdfLoclogger = Logger.getLogger("AllPdfLocLog");  
	static FileHandler  fhAllPdfLocLog;
	
	public static Logger ImgTxtExtractionPdfLoclogger = Logger.getLogger("ImgTxtExtractionPdfLoclogger");  
	static FileHandler  fhImgTxtExtractionPdfLoclogger;
	
	
	public static Logger MathFoundPdfLoclogger2 = Logger.getLogger("MathFoundPdfLoclogger2");  
	static FileHandler  fhMathFoundPdfLoclogger2;
	
	public static Logger MathFoundPdfLoclogger3 = Logger.getLogger("MathFoundPdfLoclogger3");  
	static FileHandler  fhMathFoundPdfLoclogger3;
	
	
	//public static void main(String[] args) throws SecurityException, IOException {
		static{try {
			
			System.setProperty("java.util.logging.SimpleFormatter.format","%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
			
			
			/*Calendar c = new GregorianCalendar(1995, 10, 23);
			   String s = String.format("%1$s %2$-7s %3$s\n", c);
			   System.setProperty("java.util.logging.SimpleFormatter.format",s);*/
			   
			
			
			fhAllPdfLocLog = new FileHandler("C:/log/AllPdfLocLog.log");
			AllPdfLoclogger.addHandler(fhAllPdfLocLog);
			SimpleFormatter formatter1 = new SimpleFormatter();  
			fhAllPdfLocLog.setFormatter(formatter1); 
			
			
			//fhMathFoundPdfLoclogger = new FileHandler("C:/log/MatchFoundPdfLoclog"+System.nanoTime()+".log");
			fhImgTxtExtractionPdfLoclogger = new FileHandler("C:/log/ImgTxtExtractionPdfLoclog"+System.nanoTime()+".log");
			ImgTxtExtractionPdfLoclogger.addHandler(fhImgTxtExtractionPdfLoclogger);
			SimpleFormatter formatter2 = new SimpleFormatter();  
			fhImgTxtExtractionPdfLoclogger.setFormatter(formatter2); 
			
			
			fhMathFoundPdfLoclogger2 = new FileHandler("C:/log/MatchFoundPdfLoclog"+timeStmp+"00000.log");
			MathFoundPdfLoclogger2.addHandler(fhMathFoundPdfLoclogger2);
			SimpleFormatter formatter3 = new SimpleFormatter();  
			fhMathFoundPdfLoclogger2.setFormatter(formatter3); 
			
			
			
			fhMathFoundPdfLoclogger3 = new FileHandler("C:/log/Mainlog"+System.nanoTime()+"00000.log");
			MathFoundPdfLoclogger3.addHandler(fhMathFoundPdfLoclogger3);
			SimpleFormatter formatter4 = new SimpleFormatter();  
			fhMathFoundPdfLoclogger3.setFormatter(formatter4); 
			
			
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		}


	@Override
	public String format(LogRecord record) {/*
		return String.format(
				"%1$tF %1$tT %4$s %2$s %5$s%6$s%n",
                new SimpleDateFormat(PATTERN).format(
                        new Date(record.getMillis())),
                record.getLevel().getName(), formatMessage(record));
	*/
		//return new java.util.Date() + " " + record.getLevel() + " " + record.getMessage() + "\r\n";	
	return null;
	}
		
	//}
	
	
	
}


import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.util.PDFImageWriter;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class asas {
    public static void main(String[] args) {
        try {
        	String pdfFilename = "C:/my.pdf";
        	
            File oldFile = new File(pdfFilename);
          //  System.out.println(oldFile.exists());
        	PDDocument document = PDDocument.load(new File(pdfFilename));
        	
        	
        	
        	PDFRenderer pdfRenderer = new PDFRenderer(document);
        	int pageCounter = 0;
        	for (PDPage page : document.getPages())
        	{
        	    // note that the page number parameter is zero based
        	    BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);

        	    // suffix in filename will be used as the file format
        	    
        	  //  System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        	    
        	    //ImageIOUtil.writeImage(bim, pdfFilename + "-" + (pageCounter++) + ".png", 300);
        	    ImageIOUtil.writeImage(bim, "C:\\Users\\PiyushMittal\\Downloads" + "-" + (pageCounter++) + ".png", 300);
            	
        	
        	}
        	document.close();
        	
        	
        } catch (Exception e) {
  
        	
        	
        	e.printStackTrace();
    }
}}
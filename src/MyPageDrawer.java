import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;

public class MyPageDrawer 
{
public static void main(String a[]) throws IOException{
	
	System.out.println(new File("c:/my.pdf").exists());
	
	PDDocument document = PDDocument.load(new File("c:/my.pdf"));
    PDPageTree list = document.getPages();
    for (PDPage page : list) {
        PDResources pdResources = page.getResources();
        for (COSName c : pdResources.getXObjectNames()) {
            PDXObject o = pdResources.getXObject(c);
            if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
                File file = new File("C:\\Users\\PiyushMittal\\Desktop\\garbage\\" + System.nanoTime() + ".jpeg");
                ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject)o).getImage(), "png", file);
            System.out.println("asas");
            }
        }
    }
}
}
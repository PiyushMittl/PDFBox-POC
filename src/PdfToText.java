import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfToText {
	public static void main(String args[]) {
		try {

			String text = getText(new File("C:/my.pdf"));

			System.out.println("Text in PDF: " + text);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static String getText(File pdfFile) throws IOException {
		PDDocument doc = PDDocument.load(pdfFile);

		String s = new PDFTextStripper().getText(doc);

		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\PiyushMittal\\Desktop\\garbage\\aa.txt"));
		writer.write(s);
		writer.flush();
		writer.close();
		// writer.writeLine();


		return new PDFTextStripper().getText(doc);
	}

}

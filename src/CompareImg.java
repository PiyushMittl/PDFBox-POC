import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class CompareImg
{
	public static void main(String[] args) throws IOException
	{
		/*File file1 = new File("Aman Paliwal-1.jpg");
		File file2 = new File("Aman Paliwal-1.jpg");
		*/
		
		File file1 = new File("C:\\Capture.png");
		File file2 = new File("C:\\Capture1.png");
		//File file3 = new File("C:\\Capture.png");
		
		System.out.println(file1.exists());
		
		//boolean isTwoEqual = FileUtils.contentEquals(file1, file2);
		
		boolean isTwoEqual = compareImage(file1, file2);
		System.out.println(isTwoEqual);
	}

	public static boolean compareImage(File fileA, File fileB)
	{
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
			{
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Failed to compare image files ...");
			return false;
		}
	}
}

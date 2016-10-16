
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		System.out.println(timeStamp);
		
		
	}

}

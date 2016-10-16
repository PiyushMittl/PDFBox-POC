
public class findSpecificString {

	public static void main(String a[]){
		 //StringBuffer example = new StringBuffer("/abc/def/ghfj.doc/");
		 String example="/abc/def/ghfj.doc";
		 /*s.
		 System.out.println("asas");
		 example.setCharAt((example.rlength()-1),'');
		 */
		 //example=example.setCharAt(4, 'x');
		
		 System.out.println(example.substring(example.lastIndexOf("/") + 1));
		 //System.out.println("aasddsas");
		 
		 // System.out.println(example.substring(example.lastIndexOf("/") + 1));
		    
		
	}
	
	
}

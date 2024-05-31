package practice.datadriventesting;

public class GenerateAlphaNumericRandomData 
{
	public static void main(String[] args) {
		int n=20;
		//Choose a character random from this string
		String AlphaNumericString= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		
		//Create StringBuffer size of AlphaNumericString
		StringBuilder sb= new StringBuilder(n);
		for(int i=0; i<n;i++)
		{
			//Generate a random number between 0 to AlphaNumericString variable length
			int index= (int) (AlphaNumericString.length()*Math.random());
			
			//Add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));	
		}
		System.out.println(sb);
	}

}

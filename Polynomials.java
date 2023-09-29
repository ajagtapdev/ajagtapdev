
import java.util.Scanner;
public class Polynomials {
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("What degree polynomial do you want to create?");
		int j = scan.nextInt();
		int[] coeff = new int[j+1];
		for(int i = 0; i < j; i++)
		{
			System.out.println("Enter coefficent " + (i+1));
			coeff[i] = scan.nextInt();
			while(coeff[i]  > 9)
			{
				System.out.println("Please enter a single digit.");
				coeff[i] = scan.nextInt();
			}
		}
		System.out.println("Enter the linear term:");
		coeff[j] = scan.nextInt();
		String poly = createPolynomial(j, coeff);
		System.out.println("f(x): " + poly + "\n");
		System.out.println("d/dx: " + deriPoly(poly));
		
		
		
		
		
	}
	public static String createPolynomial(int degree, int[] c)
	{
		
		String poly = "";
		if(c.length != degree + 1) return "Coefficent Array must supplment all terms";
		for(int i = 0; i < degree; i++)
		{
			if(c[i] != 0 )
			{
				if(degree == 1)
				{
					poly += String.valueOf(c[i]) + "x" + " + ";
				}
				else
				poly += String.valueOf(c[i]) + "x^" + String.valueOf(degree-i) + " + ";
			}
			
			
			
		}
		if(c[c.length-1] != 0)
		{
			return poly + String.valueOf(c[c.length-1]);
		}
		else
			return poly.substring(0, poly.length()-3) ; 
		
		
	}
	public static String deriPoly(String poly)
	{
		if(poly.length() == 1) return "0";
		if(poly.substring(1,3).equals("x ")) return poly.substring(0, 1);
		String str = "";
		String[] z = poly.split(" +");
		for(int i = 0; i <= z.length; i+=2)
		{
			int fC = Integer.valueOf(z[i].substring(0,1));
			int LC = Integer.valueOf(z[i].substring(z[i].length()-1, z[i].length()));
			int nC = fC*LC;
			z[i]= z[i].replaceFirst(z[i].substring(0,1), String.valueOf(nC));
			z[i] = z[i].substring(0, z[i].length()-1);
			z[i] += String.valueOf(LC-1) + " ";
			str += String.join("+", z[i]); 
		}
		str = str.replaceAll(" ", " + ");
		str = str.substring(0, str.indexOf("x^0"));
		str = str.replace("x^0", "");
		return str;
	}

}


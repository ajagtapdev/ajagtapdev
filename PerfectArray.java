import java.util.*;
import java.util.Scanner;
public class PerfectArray
{
    public static void main(String[] args)
    {
        ArrayList<Integer> points = new ArrayList<Integer>(); // points for PerfectArray Game
        ArrayList<Integer> pointsMode = new ArrayList<Integer>(); // points for Mode game
    
        int[][] temp = new int[0][0]; 
        Scanner scan = new Scanner(System.in);
        System.out.println("Generation of a 2d Array with random numbers.");
        
        
        
        // perfect Array game
        System.out.println("A Perfect Array is an Array that has an equal of amount of odds and evens.");
        System.out.println("Do you want to play the Perfect Array Game? Enter true or false.");
        boolean check = scan.nextBoolean(); // boolean to ask if the user wants to run PerfectArray game
        if(check){
        	// directions
        	System.out.println(" Generate a 2d NxN Array with an even dimension.");
        	System.out.println("Guess the amount of tries Java will take to find a Perfect Array!");
        	System.out.println("If you guess correctly, you get points equal to the amount of times it took to create!");
        	System.out.println("If your guess is too far off, you lose points!");
        int round = 1;
        while(check)
        {
        		System.out.println("-------------Round " + round + "---------------");
                System.out.println("Enter the dimension of the Array(must be even)."); 
                // If the total amount of numbers in the Array is odd,
                // it is impossible to get a Perfect Array.
                int rows = 1; 
                while(rows % 2 != 0) // user validation of dimension
                {
                	System.out.println("Please enter an even number.");
                	rows = scan.nextInt();
                	
                }
                System.out.println("Enter the minimum value of the random numbers.");
                int min = scan.nextInt();
                System.out.println("Enter the maximum value of the random numbers.");
                int max = scan.nextInt();
                int[][] arr = makeRandomArray(rows, rows, min, max); // the following guesses in each round refer to temp
           
            // guessing phase
            System.out.println("How many times do you want to try?");
            int cancel = scan.nextInt();
            int count = 0;
               while(findOdds(arr) != findEvens(arr))// prints until perfect
               {
                   System.out.println(" ");
                   arr = makeRandomArray(rows, rows, min, max);
                   count++;
                   if(count == cancel)
                   {
                       break;
                   }
                   
               } 
               if(findOdds(arr) == findEvens(arr)) // end message, adds to points
               {
                   
                   System.out.println("Congratulations! You have a perfect array in " + count + " tries!" );
                   
                   if(count == cancel)
                   {
                       System.out.println("You scored " + (cancel) + " points.");
                       points.add(cancel);
                   }
                   else
                   {
                       points.add((count - cancel));
                       System.out.println("You scored " + (count - cancel) + " points.");
                   }
                   
                   
                   System.out.println("Odds: " + findOdds(arr));
                   System.out.println("Evens: " + findEvens(arr));
                   
               }
               else
               {
                 System.out.println("Java could not find a perfect Array within " + cancel + " tries.");
                 
               }// fail case
               
               
            System.out.println("Want to try again? Enter true/false.");
            check = scan.nextBoolean();
            round++;
              
        }
        
        
        } // runs the Perfect Array Game
        
        System.out.println("Do you want to explore other Array methods?");
        System.out.println("(This will enter create an Array mode)");
        System.out.println("True/False:");
        boolean cont = scan.nextBoolean();
        if(cont) // creates an Array temp 
        {
            System.out.println("Enter the amount of rows.");
            int rows = scan.nextInt();
            System.out.println("Enter the amount of columns.");
            int columns = scan.nextInt();
            System.out.println("Enter the minimum value of the random numbers.");
            int min = scan.nextInt();
            System.out.println("Enter the maximum value of the random numbers.");
            int max = scan.nextInt();
            temp = makeRandomArray(rows, columns, min, max);
        }
        else
        {
        	  System.out.println("You scored a total of " + findArrayListSum(points) + " points from the Perfect Array game.");
            System.out.println("Thanks for playing!");
            System.exit(0);
        } // terminates Program
       
        
        // The following games all refer to Array temp created in if(cont) !!!
        // sorting Array
        System.out.println("----Sorting array----");
            sortRowWise(temp);
            
            
            
            
        // Mode Guessing game    
        System.out.println("Do you want to guess the mode of the Array? Enter True/False!");
        boolean want2Find = scan.nextBoolean();
        int cGuess = 0;
        while(want2Find)
        {
        	
            System.out.println("Enter your guess(make sure its between the min and max of your numbers!)");
            int fav = scan.nextInt();
            int tempmode = findMode(temp);
            if(fav == tempmode)
            {
            	System.out.println("Congratulations! You got the mode of " + tempmode + " correct!");
            	System.out.println("The mode " + fav + " appears " + countNs(tempmode, temp) + " times! ");
            	System.out.println("You got a score of " + cGuess );
            	pointsMode.add(cGuess);
            	if(cGuess == 0)
            	{
            		System.out.println("Congratulations! You got a perfect score!");
            	}
            	
            	want2Find = false;
            	break;
            }
            if(fav > tempmode )
            {
            	System.out.println("The mode is lower! You suck!");
            	cGuess--;
            }
            if(fav < tempmode)
            {
            	System.out.println("The mode is higher! You suck!");
            	cGuess--;
            }
           
        	} // runs mode guessing game
        
        // Prime Number Array Game
        System.out.println(" "); 
        System.out.println("Do you want to guess how many Primes are in the Array?");
        System.out.println("Enter true or false!");
        boolean want2Prime = scan.nextBoolean();
            if(want2Prime)
            {
                
                System.out.println("------------Prime Number Game!---------");
                printArray(temp);
                System.out.println("Enter your guess(make sure its between the min and max of your numbers!)");
            System.out.println("You only get one shot!");
            int pGuess = scan.nextInt();
            int tempPrime = findNumPrimes(temp);
            if(pGuess == tempPrime)
            {
            	System.out.println("Congratulations! You got the amount of primes " + tempPrime + " correct!");
            	System.out.println("You won the Prime Game!");
            	
          
            }
            else
            {
            	System.out.println("Incorrect! The amount of primes was " + tempPrime + "!");
            	System.out.println("You lost the Prime Game!");
            }
            
            }
            
        // end message 
        System.out.println("Your points from the mode game was " + findArrayListSum(pointsMode) +" (the closer to zero the better)");
        System.out.println("You scored a total of " + findArrayListSum(points) + " points from the Perfect Array game.");
        System.out.println("Thanks for playing!");
        scan.close();
    
    
    }
    public static int findNumPrimes(int arr[][])
    {
    	 int primes = 0;
         for(int i = 0; i < arr.length; i++)
         {
             for(int j =0; j <arr[0].length; j++)
             {
                if(checkPrime(arr[i][j]))
                {
                	primes++;
                }
             }
         }
         return primes;
    }
    public static boolean checkPrime(int prime)
	{
		int two = 0; 
		for(int j = 1; j <= prime; j++)
		{
			
			if(prime % j == 0)
			{
				two++;
			}
		}
		if(two == 2)
		{
			return true;
		}
		return false;
	}
    public static int countNs(int n, int arr[][])
    {
        int count = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[0].length; j++)
            {
                if(arr[i][j] == n)
                {
                    count++;
                }
            }
        }
        return count;
    }
    public static int findMode(int score[][])
    {
    	int value = 0;
        int[] freq = new int[100];

        for (int row = 0; row < score.length; row++) {
            for (int col = 0; col < score[row].length; col++) {
            	 value = score[row][col];
                 freq[value]++;
            }
        }
        int largest = 0;
        int mode = -1;

        for (int i = 0; i < 100; i++) {
            if (freq[i] > largest)
            {
                largest = freq[i];
                mode = i;
            }
        }
        return mode;
    }
    public static int sortRowWise(int m[][])
    {
        // loop for rows of matrix
        for (int i = 0; i < m.length; i++) {
 
            // loop for column of matrix
            for (int j = 0; j < m[i].length; j++) {
 
                // loop for comparison and swapping
                for (int k = 0; k < m[i].length - j - 1; k++) {
                    if (m[i][k] > m[i][k + 1]) {
 
                        // swapping of elements
                        int t = m[i][k];
                        m[i][k] = m[i][k + 1];
                        m[i][k + 1] = t;
                    }
                }
            }
        }
 
        // printing the sorted matrix
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }
 
        return 0;
    }
    public static int findArrayListSum(ArrayList<Integer> arr)
    {
        int sum = 0;
        for(int i =0; i < arr.size(); i++)
        {
            sum += arr.get(i);
        }
        return sum;
    }
    public static void printArray(int arr[][])
    {
        
      for(int i = 0; i < arr.length; i++)
      {
          for(int j = 0; j < arr[0].length; j++)
          {
              System.out.print(arr[i][j] + " ");
          }
          System.out.println();
      }
    }
    public static int[][] makeRandomArray(int row, int col, int min, int max)
    {
        int[][] arr = new int[row][col];
      for(int i = 0; i < arr.length; i++)
      {
          for(int j = 0; j < arr[0].length; j++)
          {
              arr[i][j] = (int) (Math.random() * (max-min) + min);
              System.out.print(arr[i][j] + " ");
          }
          System.out.println();
      }
      return arr;
      
    }
    public static int findSum(int arr[][])
    {
        int sum = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j =0; j <arr[0].length; j++)
            {
                sum += arr[i][j];
            }
        }
        return sum;
        
    }
    public static int findOdds(int arr[][])
    {
        int odds = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j =0; j <arr[0].length; j++)
            {
                if(arr[i][j] % 2 == 1)
                {
                    odds++;
                }
            }
        }
        return odds;
        
    }
    public static int findEvens(int arr[][])
    {
         int evens = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j =0; j <arr[0].length; j++)
            {
                if(arr[i][j] % 2 == 0)
                {
                    evens++;
                }
            }
        }
        return evens;
    }
    public static int findMean(int arr[][]) {

    	return findSum(arr)/(arr.length*arr.length);
    	
    
    }

}
import java.util.Scanner;


public class CS445_Rohweller 
{
	//This program takes in input as a number of bits to enter.
	//Then it takes in that number of bits and puts them into an array with parity bits.
	//After, it asks the user for how many errors they would like to have, and changes those bits to be the opposite of what they were before.
	//Finally, it displays all that was entered and changed, and shows what errors (if any) occurred.
	static int dataLength=0;
	static int numParityBits=2;
	static int minParity=0;
	static int totLength=0;
	static int multipleOfTwo=0;
	static int [] bothArray;
	static int [] dataArray;
	static int [] compareArray;
	static int secondCount=0;
	static int correctnessNum=0;
	public static void main(String[] args) 
	{
		dataLength=0;
		
		//User enters integer, along with bits
		
		System.out.println("Enter an integer to represent how many bits you want.");
		Scanner scan = new Scanner(System.in);
		dataLength=scan.nextInt();
		System.out.print(dataLength);
		
		
		
		//program finds number of bits required for job and prints it.
		calculateParity();
		System.out.println();
		System.out.println();
		System.out.println(numParityBits+" is the number of parity bits.");
		System.out.println(minParity+" is the max that our parity bits could handle.");
		totLength= dataLength+numParityBits;
		System.out.println(totLength+" is total length of bits.");
		
		//Create array for bit positions, reserve parity bit locations as -1s
		bothArray = new int[totLength];
		dataArray=new int[totLength-numParityBits];
		for (int i=0; multipleOfTwo<totLength;i++)
		{
			multipleOfTwo=(int) Math.pow(2,i);
			if (multipleOfTwo>totLength)
				break;
			bothArray[multipleOfTwo-1]=-1;
		}
		
		int j = 0;
		int bit=0;
		int k=2;
		//place the bits into the array
		for (int i=2; i<bothArray.length-1;i++)
		{
			if (bothArray[k]==-1&&k+1==bothArray.length)
				break;
			if (k>=bothArray.length)
				break;
			//prompting user
			if (i==2)
				System.out.println("Enter each bit in order. Enter the first bit.");
			
			if (i>2)
				System.out.println("Enter the next bit.");
			//initializing bit.
			bit=0;
			Scanner scan2 = new Scanner(System.in);
			bit = scan2.nextInt();
			while (bit!=0 && bit!=1)
			{
				System.out.println("Please enter a 0 or a 1");
				bit = scan2.nextInt();
			}
				//array of just the data entered.
				dataArray[i-2]=bit;
				
				if (bothArray[k]==-1)
				{
					k++;
					
					if (k>=bothArray.length)
						break;
					bothArray[k]=bit;
				}
				else if (bothArray[k]!=-1)
				{
					bothArray[k]=bit;
				}
				k++;
				
				if (k>=bothArray.length)
					break;
		
		}
		//place entered integer into remaining positions of array (not parity slots)^^
		int oneCount=0;
		int twoMult=2;
		//turn all -1s into 0s
		for (int i=0; i<bothArray.length; i++)
		{
			
			if (bothArray[i]==-1)
				bothArray[i]=0;
			if (bothArray[i]==1)
				oneCount++;
			if ((twoMult*2)<bothArray.length)
				twoMult=twoMult*2;
		}
		
//this is computing the parity bits
		int thirdCount=0;
		boolean isCorrect=false;
		int h=0;
		int j1=0;
		boolean isNotComplete=false;
		int repVariable=0;
		multipleOfTwo=1;
		while (h<=numParityBits)
		{
			secondCount=0;
			multipleOfTwo=(int) Math.pow(2,h);
			repVariable=multipleOfTwo;
			while (isNotComplete==false)
			{
				for (j1=repVariable;j1<repVariable+multipleOfTwo ;j1++)
				{										
					if (j1>bothArray.length)
						continue;
					if (bothArray[j1-1]==1)
						secondCount++;
				}
				repVariable=j1+multipleOfTwo;
				if ((j1>=bothArray.length)||repVariable>=bothArray.length)
					isNotComplete=true;
				else 
					isNotComplete=false;
				if (repVariable>=bothArray.length)
					continue;
			}
			
			if (secondCount%2==0)
				isCorrect=true;	
			else
				isCorrect=false;
			if (isCorrect==false)
			{
				if (bothArray[multipleOfTwo-1]==0)
				{
					bothArray[multipleOfTwo-1]=1;
				}
					
			}
			h++;
			isNotComplete=false;		
		}
			//print the array, as well as only what was entered.
		System.out.println("This is the array of bits, excluding parity bits:");
		
		System.out.println();
		
		System.out.println("This is the array of bits, including parity bits:");
		for (int i=0; i<bothArray.length; i++)
		{
		System.out.print(bothArray[i]);
		}
			System.out.println("");
		
		//Ask user input to specify which bits should be in error (array locations, not parity spots), and change them at those locations

		System.out.println("How many errors would you like to have? (1-3)");
		Scanner scan3= new Scanner(System.in);
		int option=scan3.nextInt();
		while(option!=1&&option!=2&&option!=3)
		{
			System.out.println("Try again. (1-3)");
			option=scan3.nextInt();
		}
		if (option==1)
		{
			int bit1=-1;
			System.out.println("Which array location (bit) should be in error?");
			bit1=scan3.nextInt();
			while((bit1>=bothArray.length)||bit1<0)
			{
				System.out.println("Your bit location is not in the array, Try again.");
				bit1=scan3.nextInt();
			}
			
			if (bothArray[bit1]==1)
				bothArray[bit1]=0;
			else if (bothArray[bit1]==0)
				bothArray[bit1]=1;
			
			System.out.println("With error/before error correction, this is the array:");
			for (int i=0; i<bothArray.length; i++)
			{
				System.out.print(bothArray[i]);
			}
			//this method uses the parity bits to check and correct errors
			fixSingleError();
			
			System.out.println("");
			System.out.println("Without error/after error correction, this is the array:");
			for (int i=0; i<bothArray.length; i++)
			{
				System.out.print(bothArray[i]);
			}
			decodedPrint();
			System.out.println("");
			System.out.println("You originally entered: ");
			for (int i=0; i<dataArray.length; i++)
			{
				System.out.print(dataArray[i]);
			}
		}
		else if (option==2)
		{
			int bit1=-1;
			int bit2=-1;
			//error bit 1
			System.out.println("Which array location (bit) should be in error?");
			bit1=scan3.nextInt();
			while((bit1>=bothArray.length)||bit1<0)
			{
				System.out.println("Your bit location is not in the array, Try again.");
				bit1=scan3.nextInt();
			}
			if (bothArray[bit1]==1)
				bothArray[bit1]=0;
			else if (bothArray[bit1]==0)
				bothArray[bit1]=1;
			//error bit 2
			System.out.println("Which second array location (bit) should be in error?");
			bit2=scan3.nextInt();
			while(bit1==bit2||(bit2>=bothArray.length)|| bit2<0)
			{
				System.out.println("Either your bit location is not in the array, or it is the same bit that you previously entered. Try again.");
				bit2=scan3.nextInt();
			}
			if (bothArray[bit2]==1)
				bothArray[bit2]=0;
			else if (bothArray[bit2]==0)
				bothArray[bit2]=1;
			
		
			//this method uses the parity bits to check and correct errors
			fixSingleError();
			
			System.out.println("");
			System.out.println("Without error/after error correction, this is the array:");
			for (int i=0; i<bothArray.length; i++)
			{
				System.out.print(bothArray[i]);
			}
			decodedPrint();
			System.out.println("");
			System.out.println("You originally entered: ");
			for (int i=0; i<dataArray.length; i++)
			{
				System.out.print(dataArray[i]);
			}
		}
		else if (option==3)
		{
			int bit1=-1;
			int bit2=-1;
			int bit3=-1;
		//error bit 1
			System.out.println("Which array location (bit) should be in error?");
			bit1=scan3.nextInt();
			while((bit1>=bothArray.length)||bit1<0)
			{
				System.out.println("Your bit location is not in the array, Try again.");
				bit1=scan3.nextInt();
			}
			if (bothArray[bit1]==1)
				bothArray[bit1]=0;
			else if (bothArray[bit1]==0)
				bothArray[bit1]=1;
		//error bit 2
			System.out.println("Which second array location (bit) should be in error?");
			bit2=scan3.nextInt();
			while(bit1==bit2||(bit2>=bothArray.length)|| bit2<0)
			{
				System.out.println("Either your bit location is not in the array, or it is the same bit that you previously entered. Try again.");
				bit2=scan3.nextInt();
			}
			if (bothArray[bit2]==1)
				bothArray[bit2]=0;
			else if (bothArray[bit2]==0)
				bothArray[bit2]=1;
			
		//error bit 3
			System.out.println("Which third array location (bit) should be in error?");
			bit3=scan3.nextInt();
			while(bit1==bit3||bit2==bit3||(bit3>=bothArray.length)|| bit3<0)
			{
				System.out.println("Either your bit location is not in the array, or it is the same bit that you previously entered. Try again.");
				bit3=scan3.nextInt();
			}
			if (bothArray[bit3]==1)
				bothArray[bit3]=0;
			else if (bothArray[bit3]==0)
				bothArray[bit3]=1;
			
		
			//this method uses the parity bits to check and correct errors
			fixSingleError();
			
			System.out.println("");
			System.out.println("Without error/after error correction, this is the array:");
			for (int i=0; i<bothArray.length; i++)
			{
				System.out.print(bothArray[i]);
			}
			decodedPrint();
			System.out.println("");
			System.out.println("You originally entered: ");
			for (int i=0; i<dataArray.length; i++)
			{
				System.out.print(dataArray[i]);
			}
		}
		//this method compares what was originally entered to what our decoder came up with.
		compareResult();
	}
	
	//method finds number of bits required for job.
	public static int calculateParity()
	{
		for (int i=0;i<(dataLength);i++)
		{
			minParity = ((int) Math.pow(2, numParityBits ))-1-numParityBits;
			if (minParity>dataLength)
				return minParity;
			numParityBits++;
		}
		return -1;
	}
	
	//this method checks and corrects errors using parity bit locations.
	public static void fixSingleError()
	{
		boolean isCorrect=false;
		
		
		int h=0;
		int j=0;
		boolean isNotComplete=false;
		int repVariable=0;
		multipleOfTwo=1;
		while (h<=numParityBits)
		{
			secondCount=0;
			multipleOfTwo=(int) Math.pow(2,h);
			repVariable=multipleOfTwo; //1
			while (isNotComplete==false)
			{
				for (j=repVariable;j<repVariable+multipleOfTwo ;j++)
				{									
					if (j>bothArray.length)
						continue;
					if (bothArray[j-1]==1)
						secondCount++;
				}
				repVariable=j+multipleOfTwo;
				if ((j>=bothArray.length)||repVariable>=bothArray.length)
					isNotComplete=true;
				else 
					isNotComplete=false;
				if (repVariable>=bothArray.length)
					continue;
			}
			
			if (secondCount%2==0)
				isCorrect=true;	
			else
				isCorrect=false;
			if (isCorrect==false)
				correctnessNum+=multipleOfTwo;
			h++;
			isNotComplete=false;
			
		}
		
		if (correctnessNum!=0)
		{
			if (bothArray[correctnessNum-1]==1)
				bothArray[correctnessNum-1]=0;
			else if (bothArray[correctnessNum-1]==0)
				bothArray[correctnessNum-1]=1;
		}
		
		return;
	}
	
	//this method prints decoded version of input.
	public static void decodedPrint()
	{
		multipleOfTwo=0;
		
		System.out.println();
		System.out.println("This is the decoded word.");
		for (int i=0; multipleOfTwo<totLength;i++)
		{
			multipleOfTwo=(int) Math.pow(2,i);
			if (multipleOfTwo>totLength)
				break;
			bothArray[multipleOfTwo-1]=-1;
		}
		for (int i=0; i<bothArray.length; i++)
		{
			if (bothArray[i]!=-1)
				System.out.print(bothArray[i]);
		}
	}
	
	//this method compares what was originally entered to what our decoder came up with.
	public static void compareResult()
	{
		int k=0;
		int errorCount=0;
		compareArray=new int[dataArray.length];
		//decoded version
		System.out.println();
		for (int i=0; multipleOfTwo<totLength;i++)
		{
			multipleOfTwo=(int) Math.pow(2,i);
			if (multipleOfTwo>totLength)
				break;
			bothArray[multipleOfTwo-1]=-1;
		}
		k=0;
		//Make new array from bothArray
		for (int i=0; i<bothArray.length;i++)
		{
			if (bothArray[i]!=-1)
			{
				compareArray[k]=bothArray[i];
				k++;
			}
		}
		for (int i=0; i<dataArray.length; i++)
		{
			if (compareArray[i]!=dataArray[i])
			{
				System.out.println("Error at bit "+ i+" in the array.");
				System.out.println();
				System.out.println("Our decoder produced a "+compareArray[i]+" at this bit.");
				System.out.println("Whereas you originally entered a "+dataArray[i] +" at this bit.");
				errorCount++;
			}
				
		}
		if (errorCount==0)
			System.out.print("There was no error.");
		
	}
	
	
}

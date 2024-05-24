// Joshua Sanchez , Alejandro Ortega, Christopher Deluigi
// Unit6_HW4
//10/19/2023



import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) {
		
		
		
		int numRows, numColumns;
		int index,start,end;
		char charAtIndex;
		int length;
		
		String subSequence;
		
		
		
		Scanner myScan = new Scanner(System.in);
		
		System.out.print("Enter how many rows and how many columns to load: ");
		numRows = myScan.nextInt();
		numColumns = myScan.nextInt();
		
		Code codeObject = new Code(numRows, numColumns);
		
		codeObject.loadCodeArray(numRows, numColumns);
		codeObject.printCodeArray(numRows, numColumns);
		
		// __________________________________________
		
		System.out.print("\n\nTesting charAt: Enter your index [a number greater or equal to 0 and less or equal to ");
		
		System.out.print((numRows * numColumns - 1) + "]:");
		
		index = myScan.nextInt();
		charAtIndex = codeObject.charAt(index);
		
		System.out.println("The character located at index " + index + " is: " + charAtIndex);
		
		// __________________________________________
		
		length = numRows * numColumns;
		
		System.out.println("\n\nTesting length: there are " + length + " characters.");
		
		// __________________________________________
		
		
		System.out.print("\n\nTesting subSequence: Enter start and end indexes: ");
		
		start = myScan.nextInt();
		end = myScan.nextInt();
		
		subSequence = codeObject.subSequence(start, end);
		System.out.println("The subsequuence is: " + subSequence);
		
		
		// __________________________________________
		
		
		System.out.println("\nGoodbye!");
		

	}

}



class Code implements CharSequence{
	
	private int[][] codeArray;
	private int numRows,numColumns;
	
	public Code(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		codeArray = new int[numRows][numColumns];
	}
	
	public void loadCodeArray(int numRows, int numColumns) {
		Scanner myScan = new Scanner(System.in);
		
		int i,j;
		
		for (i = 0; i < numRows; i++) {
			System.out.print("Enter Row " + (i + 1) + ": ");
			for (j = 0; j < numColumns; j++) {
				codeArray[i][j] = myScan.nextInt();
			}
		}
	}
	
	public void printCodeArray(int numRows, int numColumns) {
		
		int i, j;
		
		System.out.println("\n_____________\n");
		
		for (i = 0; i < numRows; i++) {
			
			for (j = 0; j < numColumns; j++) {
				System.out.print(codeArray[i][j] + "\t");
			}
			System.out.println("");
			
		}
		
	}
	
	
	
	// ______________THE CODE ABOVE IS TO REMAIN UNCHANGED______________________
	
	
	@Override
	public char charAt(int index) {
		 
		int lastnum = 0;
		int currentIndex = 0;
		
		
		//Will go through the double array list, to search for the input index requested by the user 
		for(int i = 0; i < codeArray.length; i++) {
			int[] row = codeArray[i];
			int rowLength = row.length;
			if (index >= currentIndex && index < currentIndex + rowLength) {
				int charIndex = index - currentIndex;
				int asciiValue = row[charIndex];
				lastnum = asciiValue;
				return(char) asciiValue;
			}
			currentIndex += rowLength;

		}
		
		return (char) lastnum;
	}
	
	
	@Override
	public int length() {
		return codeArray.length;
	}
	
	@Override
	public String subSequence(int start, int end) {
		
		StringBuilder subSequenceBuilder = new StringBuilder();
		int currentIndex = 0;
		
		for (int i = 0; i <codeArray.length; i++) {
			int[] row = codeArray[i];
			int rowLength = row.length;
		
			
			//Will check if the row applies to the subsequence 
			if(end <= currentIndex || start >= currentIndex + rowLength) {
				//current row is before or after the requested subsequence 
				currentIndex += rowLength;
				
				continue;
			}
			
			// Calculate the effective start and end indices within this row.
			int rowStart = Math.max(start - currentIndex,0);
			int rowEnd = Math.min(end - currentIndex,rowLength - 1);
			
			
			//Will add the characters from the row to the subsequence.
			for(int index = rowStart; index <= rowEnd; index++) {
				int asciiValue = row[index];
				subSequenceBuilder.append((char)asciiValue);
			}
			currentIndex += rowLength;
			
			//Will break out if we have reach the "end" value, so it won't keep going 
			if (currentIndex > end) {
				break;
			}
			
		}
		
		//Will return the final string put together.
		return subSequenceBuilder.toString();
	}
	
}








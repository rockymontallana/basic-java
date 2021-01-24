package exerciseone;
import java.util.Scanner;
import java.util.InputMismatchException;

class Menu {
	
	Scanner input = new Scanner(System.in);
	
	final int MAXROW = 100;
	final int MAXCOLUMN = 100;
	
	Menu() {
		
		reset();
		display();
	}
	
	void display() {

		int action = 0;

		System.out.print("\n");
		System.out.println("----------OPTIONS----------");
		System.out.println("\n");
		System.out.println("   (1)     Search");
		System.out.println("   (2)     Edit");
		System.out.println("   (3)     Print");
		System.out.println("   (4)     Reset");
		System.out.println("   (5)     Exit");
		System.out.println("\n");
		System.out.print("Select from option 1-5: ");
		
		action = checkIntegerInput(5);
		System.out.println("\n");

		switch (action) {

		case 1:
			search();
			display();
			break;
		case 2:
			edit();
			display();
			break;
		case 3:
			print();
			display();
			break;
		case 4:
			reset();
			display();
			break;
		case 5:
			System.out.println("THE SYSTEM HAS EXITED! Thank You");
			System.exit(1);
			break;
		default:
			System.out.println("Invalid Input!");
			display();
		}
	}
	
	void search() {

		String strToSearch = ""; 
        String strPerIndex = "";
		boolean isValid = true;
        
        System.out.println("Enter character/s to search.");
		System.out.print("1-3 character/s only: ");
		input.nextLine();
		strToSearch = input.nextLine();
	
		if(strToSearch.length() > 3 || strToSearch.length() < 1) {
			
			while(isValid) {
				
				strToSearch = input.nextLine();
				
				System.out.print("Invalid input! Please try another: ");
				
				if(strToSearch.length() <= 3 && strToSearch.length() >= 1)
					isValid = false;
			}
		}
		
        int count = 0;
		int userRow = 0;
		int userColumn = 0;
		
        System.out.println("ROW     COLUMN     Number of Occurence");
        
        for(int i = 0; i < row; i++){
            
            for(int j = 0; j < column; j++){
                
                strPerIndex = tableArray[i][j];
				userRow = i+1;
				userColumn = j+1;
				
				if(strToSearch.length() == 1) {
					
					for (int k = 0; k < strPerIndex.length(); k++){
						
						if(strToSearch.charAt(0) == strPerIndex.charAt(k))
							count++;
					}
				}else if(strToSearch.length() == 2) {
					
					char charStrToSearch1 = strToSearch.charAt(0);
					char charStrToSearch2 = strToSearch.charAt(1);
					
					for(int k = 0; k < 3; k++) {
						
						if(strPerIndex.charAt(k) == charStrToSearch1 && strPerIndex.charAt(k+1) == charStrToSearch2)
						count++;
					}
				}else if(strToSearch.length() == 3) {
					
					if(strToSearch.equals(strPerIndex))
						count++;
				}
				               
				if(count > 0)
					System.out.println(userRow + "	   " + userColumn + "      		" + count);
				
                count = 0;
            }
        }
	}
	
	void edit() {

        int selectedRow = 0;
		int selectedColumn = 0;
        String newIndexValue = "";
		boolean isNotValid = true;
        
        print();
		
		System.out.print("\n");
		System.out.println("Please select the row and column to edit.");
        System.out.print("Which row?: ");
		selectedRow = checkIntegerInput(row);
		System.out.print("Which column?: ");
		selectedColumn = checkIntegerInput(column);
        System.out.print("Enter up to 3 character/s only): ");
		newIndexValue = input.nextLine();
		
		if(newIndexValue.length() > 3 || newIndexValue.length() < 1) {
			
			while(isNotValid) {
				
				newIndexValue = input.nextLine();
				
				System.out.print("Invalid input! Please try another: ");
				
				if(newIndexValue.length() <= 3 && newIndexValue.length() >= 1)
					isNotValid = false;
			}
		}
		
        tableArray[selectedRow-1][selectedColumn-1] = newIndexValue;
	}
	
	void print() {
		
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < column; j++) {
				
				System.out.print("	"+tableArray[i][j]);
			}
			
			System.out.print("\n");
		}
	}
	
	String threeChar = "";
	String tableArray [][];
	
	int row;
	int column;
	
	void reset() {
		
		System.out.print("Enter number of Rows: ");
		row = checkIntegerInput(MAXROW);
		System.out.print("Enter number of Columns: ");
		column = checkIntegerInput(MAXCOLUMN);
		
		String arrTable[][] = new String[row][column];

		for (int i = 0; i < row; i++) {

			for (int j = 0; j < column; j++) {

				generateThreeAscii();
				arrTable[i][j] = threeChar;
			}

		}
		this.tableArray = arrTable;
	}
	
	void generateThreeAscii() {

		int x = 0;
		char tempChar;
		threeChar = "";

		while (x < 3) {

			int randomAsciiNumber = (int) ((Math.random() * 96)+32);
			tempChar = (char)randomAsciiNumber;
			threeChar += tempChar;
			x++;
		}
	}
	
	int checkIntegerInput(int limit) {
		
		int numInput = 0;
		boolean isNotValid = true;
		
		while (isNotValid) {
			
			try{
				
				numInput = input.nextInt();
				
				if(limit == 5 || limit == 10) {
					
					if(numInput <= limit && numInput >= 1)
						break;
					else
						System.out.print("Input out of range! Please enter 1-" + limit + " only: ");
				}
				
				else {
					
					if((numInput <= limit && numInput >= 1) || (numInput <= limit && numInput >= 1))
						break;
					else
						System.out.print("Input out of range! Please enter 1-" + limit + " only: ");
				}
				
            }
			
			catch(InputMismatchException e){
				
				input.next();
				System.out.print("invalid input! Please try another: ");
			}
		}
		
		return numInput;
	}
}
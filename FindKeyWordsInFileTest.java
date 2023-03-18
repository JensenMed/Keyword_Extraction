import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;



/**
 * 
 * @author Jensen Medeiros
 * Student # 251234023
 * This class will implement the 6 testing checks on the FineKeyWordsInFile class
 * 
 *
 */
public class FindKeyWordsInFileTest {
	
	private static HashMap<String, Boolean> words = new HashMap<String, Boolean>();
	private static HashMap<Integer, String> FrequentWordsData = new HashMap<Integer, String>();
	private static HashMap<Integer, String> FrequentWordsDataThree = new HashMap<Integer, String>();
	private static HashMap<Integer, String> FrequentWordsDataEmpty = new HashMap<Integer, String>();
	
	public static void main(String args[]) throws IOException {
		String fileToCheckThree = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\file3.txt";
		String fileToCheck = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\file1.txt";
		String commonEnglish = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\MostFrequentEnglishWords";
		String outputFile = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\test2.txt";
		Integer kValue = 5;
		FindKeyWordsInFile testObj = new FindKeyWordsInFile(fileToCheck, kValue,commonEnglish);
		
		
		// sorts words in test file
		try {
			int id = 1;
			File myObj = new File(outputFile);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				String data = myReader.next();
				// Remove commas from word
				int testData = data.indexOf(",");
				// if comma is found then remove it and add to dictionary
				if(testData == -1) {
					FrequentWordsData.put(id, data.toLowerCase());
					id ++;	
				}
				else {
					String newData = data.replace(",", "");
					FrequentWordsData.put(id, newData.toLowerCase());
					id ++;		
				}	
				
			}
			myReader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		
		//Test 1 check if the program reads the input file and creates the word frequency correctly.
		
		for(int i = 0; i < FrequentWordsData.size(); i++) {
			if(FrequentWordsData.get(i)!= null) {
				if(FrequentWordsData.get(i).equals("accomplishment.")) {
					if(FrequentWordsData.get(i+1).equals("1")) {
						System.out.println("Test 1 passed.");
					}
				}
			}
		}
		
		//Test 2 check if the program finds the k most frequent words correctly
		
		if((FrequentWordsData.size() / 2) <= kValue) {
			System.out.println("Test 2 passed. ");
		}
		
		//Test 3 to check if the program filters common English words correctly
		for (Entry<Integer, String> entry : FrequentWordsData.entrySet()) {
		    Integer key = entry.getKey();
		    String value = entry.getValue();
		    if(testObj.FrequentWords.get(value) == null) {
		    	System.out.println("Test 3 passed.");
		    	break;
		    }
		}
		
		
		
		// Test 4 check if the program generates the correct output for file3.txt.
		FindKeyWordsInFile testObjTwo = new FindKeyWordsInFile(fileToCheckThree, kValue,commonEnglish);
		
		// sorts words in test file
		try {
			int id = 1;
			File myObj = new File(outputFile);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				String data = myReader.next();
				// Remove commas from word
				int testData = data.indexOf(",");
				// if comma is found then remove it and add to dictionary
				if(testData == -1) {
					FrequentWordsDataThree.put(id, data.toLowerCase());
					id ++;	
				}
				else {
					String newData = data.replace(",", "");
					FrequentWordsDataThree.put(id, newData.toLowerCase());
					id ++;		
				}	
				
			}
			myReader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		for (Entry<Integer, String> entry : FrequentWordsDataThree.entrySet()) {
		    Integer key = entry.getKey();
		    String value = entry.getValue();
		    if(testObj.FrequentWords.get(value) == null) {
		    	if((FrequentWordsDataThree.size() / 2) <= kValue) {
		    		System.out.println("Test 4 passed.");
		    		break;
		    	}
		    }
		}
		
		
		
		
		//Test 5 check if the program handles empty input files
		String emptyFile = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\emptyFile.txt";
		String emptyFileOutput = "C:\\Users\\Jensen Medeiros\\OneDrive\\Desktop\\CS2210\\Assignment_3\\src\\emptyFileOutput.txt";
		
		FindKeyWordsInFile emptyFilesObj = new FindKeyWordsInFile(emptyFile, kValue,commonEnglish);
		
		try {
			int id = 1;
			File myObj = new File(emptyFileOutput);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNext()) {
				String data = myReader.next();
				// Remove commas from word
				int testData = data.indexOf(",");
				// if comma is found then remove it and add to dictionary
				if(testData == -1) {
					FrequentWordsDataEmpty.put(id, data.toLowerCase());
					id ++;	
				}
				else {
					String newData = data.replace(",", "");
					FrequentWordsDataEmpty.put(id, newData.toLowerCase());
					id ++;		
				}	
				
			}
			myReader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		if(FrequentWordsDataEmpty.size() == 0) {
			System.out.println("Test 5 passed. ");
		}else {
			
		}
		
		// Test 6 to check if the program handles non-existing input files
		FindKeyWordsInFile emptyInputsObj = new FindKeyWordsInFile("C:\\\\Users\\\\Jensen Medeiros\\\\OneDrive\\\\Desktop\\\\CS2210\\\\Assignment_3\\\\src\\\\emptyFileOutput2.txt", kValue,"C:\\\\Users\\\\Jensen Medeiros\\\\OneDrive\\\\Desktop\\\\CS2210\\\\Assignment_3\\\\src\\\\emptyFileOutput2.txt");
		if(emptyInputsObj.errorMap.get("Error. File not found") != null) {
			System.out.println("Test 6 passed.");
		}
		

		
	}

}

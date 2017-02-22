import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws IOException{
		Map<Character, String> output;
		CodingTree myTree;
		StringBuilder myText = new StringBuilder();
		
		FileReader inputStream = null;
		
		try {
				inputStream = new FileReader("sameLetterTest.txt");
				BufferedReader bufferedStream = new BufferedReader(inputStream);
				String line;
				while ((line = bufferedStream.readLine()) != null) {
					System.out.print("Line read in: ");
					System.out.println(line + "\n"); // useful for debugging
					myText.append(line);
				}
					bufferedStream.close();
		} finally {}
		
		myTree = new CodingTree(myText.toString());
		output = myTree.codes;
		
		System.out.println("Is the map empty?  " + output.isEmpty());

		try{
		    File outputFile = new File("output.txt");
	        PrintWriter pw = new PrintWriter(outputFile);

	        pw.println(output.toString());
	        pw.flush();
	        pw.close();

		    }catch(Exception e){}
		
		//TODO: Output the compressed message to a binary file.  
		//TODO: Display the size of the compressed text, compression and run time statistics
		
		//TODO: Problem with using a file with only one character, refer to sameLetterTest for example
		//TODO: Problem does not occur when more than one character is present in file
		
	}

}

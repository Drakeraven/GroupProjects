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
		String fileName = "annabanana.txt";
		double inSize, outSize; 
		
		long startTime = System.nanoTime();
		
		FileReader inputStream = null;
		
		try {
				inputStream = new FileReader(fileName);
				BufferedReader bufferedStream = new BufferedReader(inputStream);
				String line;
				while ((line = bufferedStream.readLine()) != null) {
					//System.out.print("Line read in: ");
					//System.out.println(line + "\n"); // useful for debugging
					myText.append(line);
				}
					bufferedStream.close();
		} finally {}
		
		myTree = new CodingTree(myText.toString());
		output = myTree.codes;
		
		System.out.println("Is the map empty?  " + output.isEmpty());

		try{
		    File outputFile = new File("codes.txt");
	        PrintWriter pw = new PrintWriter(outputFile);

	        pw.println(output.toString());
	        pw.flush();
	        pw.close();
		    }catch(Exception e){}
		
		try{
		    File compFile = new File("compressed.txt");
	        PrintWriter pw = new PrintWriter(compFile);

	        pw.println(myTree.getEncoding());
	        pw.flush();
	        pw.close();

		    }catch(Exception e){}
		  
		//TODO: Display the size of the compressed text, compression and run time statistics
		
		File myIn = new File(fileName);
		File myOut =new File("compressed.txt");

		if(myIn.exists() && myOut.exists()){
			inSize = myIn.length();
			outSize = myOut.length();
			//divide the compressed by 8 since it's outputting in binary
			System.out.println("In size: " + inSize + " | Out size: " + (outSize / 8));
		}
		
		long endTime = System.nanoTime(); 
		double elapsedTime = endTime - (startTime / 1000000000.0);
		System.out.println("Time to compress: " + elapsedTime + " seconds");

		//TODO: Problem with using a file with only one character, refer to sameLetterTest for example
		//TODO: Problem does not occur when more than one character is present in file
		
	}

}

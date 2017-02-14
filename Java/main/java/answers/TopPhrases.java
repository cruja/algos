package answers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *  TopPhrases
 *  I'm using Scanner to avoid problems of loading large file into memory.
 *  Also we may use a ChronicleMap to work with Veeeery Large Maps if necessary: http://chronicle.software/products/chronicle-map/ 
 *  
 * time complexity: O(N)+ O(N*lg(N)) +O(N) -> O(N*lg(N))
 * space complexity: O(N)
 *
 */
public class TopPhrases {
	

	/**
	 * Retrieve the topNr phrases  using the provided path and separator
	 * @param topNr
	 * @param path
	 * @param separator
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<Integer, List<String>> getTopPhrases(int topNr, String path, String separator) throws FileNotFoundException, IOException {
		
		try (FileInputStream inputStream = new FileInputStream(path)) {
			Map<String, Integer> countParserMap =  countPhrases(inputStream, separator);
			return getTopPhrases(topNr, countParserMap);
		}
	}
	
	
	/**
	 * Count the phrases which occur in the provided input stream, using provided delimiter
	 * 
	 * @param inputStream
	 * @param separator
	 * @return a map containing the number of occurrences for each phrase
	 * 
	 * @throws IOException
	 */
	Map<String, Integer> countPhrases(InputStream inputStream, String separator) throws IOException  {
		
		Map<String, Integer> countersMap = new HashMap<>();
		
		// using java.util.Scanner which loads File sequentially into memory!, 
		try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
		   
		    while (scanner.hasNextLine()) {		    
		        String[] phrases = scanner.nextLine().split(separator);
		        Arrays.stream(phrases).forEach(phrase -> countersMap.merge(phrase.trim(), 1, (oldValue, newValue) -> oldValue+1));
		    }

		    // note that Scanner suppresses exceptions
		    if (scanner.ioException() != null) {
		        throw scanner.ioException();
		    }
		}
		return countersMap;
	}
	
	
	
	/**
	 * Get the top topNr phrases from the provided counters map
	 * 
	 * @param topNr
	 * @param countersMap the number of occurrences of each phrase
	 * @return a map containing for a number the corresponding phrases
	 */
	Map<Integer, List<String>> getTopPhrases(int topNr, Map<String, Integer> countersMap) {
		Map<Integer, List<String>> topPhrases = new TreeMap<>(Collections.reverseOrder());
		for (Entry<String, Integer> entry : countersMap.entrySet()) {
			
			topPhrases.compute(entry.getValue(), (k, v) -> {	List<String> phrases = v == null ? new ArrayList<>() : v;   
																phrases.add(entry.getKey());
																return phrases;
															});
		}
		
		//remove unrelevant content (<topNr)		
		for (Entry<Integer, List<String>> entry : topPhrases.entrySet()) {
			topNr-= entry.getValue().size();
			if(topNr < 0) {
				topPhrases.remove(entry.getKey());
			}
		}
		return topPhrases;	
	}
	
}

package answers;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;



public class TopPhraseTest {
	
	String data = null;
	
	@Before
	public void setUp() {
		String lineSeparator =  System.getProperty("line.separator");
		data = "Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing" + lineSeparator
				+ "Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing" + lineSeparator
				+ "Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing" + lineSeparator
				+ "Foobar Candy | Olympics 2012 | PGA | CNET | SUN" + lineSeparator
				+ "aaa 			| 	bbb 		| ccc |	  ddd|eee"; 
	}
	
	@Test
	public void givenInputFileThenGetTopPhrases() throws FileNotFoundException, IOException {
		TopPhrases topPhrases = new TopPhrases();
		Map<Integer, List<String>> result = topPhrases.getTopPhrases(5, "src/test/java/answers/TopPhrases.txt", "\\|");		
		assertEquals(2, result.size());
		assertEquals(5, result.get(4).size() + result.get(3).size());
		assertTrue(result.get(4).contains("Foobar Candy"));
		assertTrue(result.get(4).contains("Olympics 2012"));
		assertTrue(result.get(4).contains("CNET"));
		assertTrue(result.get(4).contains("PGA"));
		assertTrue(result.get(3).contains("Microsoft Bing"));
	}

	@Test
	public void givenInputThenCountPhrases() throws FileNotFoundException, IOException {
		
		TopPhrases topPhrases = new TopPhrases();
		Map<String, Integer> result = topPhrases.countPhrases(new ByteArrayInputStream(data.getBytes()), "\\|");		
		assertEquals((Integer)4, result.get("Foobar Candy"));
		assertEquals((Integer)4, result.get("Olympics 2012"));
		assertEquals((Integer)4, result.get("PGA"));
		assertEquals((Integer)4, result.get("CNET"));
		assertEquals((Integer)3, result.get("Microsoft Bing"));
		assertEquals((Integer)1, result.get("SUN"));
		assertEquals((Integer)1, result.get("aaa"));
		assertEquals((Integer)1, result.get("bbb"));
		assertEquals((Integer)1, result.get("ccc"));
		assertEquals((Integer)1, result.get("ddd"));
		assertEquals((Integer)1, result.get("eee"));
		assertEquals(11, result.size());
	}

	@Test
	public void givenInputThenGetTopPhrases() throws FileNotFoundException, IOException {
		
		TopPhrases topPhrases = new TopPhrases();
		Map<String, Integer> countersMap = topPhrases.countPhrases(new ByteArrayInputStream(data.getBytes()), "\\|");
		Map<Integer, List<String>> result = topPhrases.getTopPhrases(5, countersMap);
		assertEquals(2, result.size());
		assertEquals(5, result.get(4).size() + result.get(3).size());
		
		assertTrue(result.get(4).contains("Foobar Candy"));
		assertTrue(result.get(4).contains("Olympics 2012"));
		assertTrue(result.get(4).contains("CNET"));
		assertTrue(result.get(4).contains("PGA"));
		assertTrue(result.get(3).contains("Microsoft Bing"));
	}

	
	

}

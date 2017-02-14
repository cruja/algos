package answers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import answers.ComplementaryPairs;
import answers.ComplementaryPairs.Pair;

public class ComplementaryPairsTest {
	
	@Test
	public void givenInputWhenUniqueValuesThenReturnPairs() {
		ComplementaryPairs complPairs = new ComplementaryPairs();
		List<Pair<Integer>> pairs = complPairs.findPairs(3, new Integer[] {1,2,3,5});
		List<Pair<Integer>> expected = new ArrayList<>();
		expected.add(new Pair<Integer>(0,1));
		assertArrayEquals(expected.toArray(), pairs.toArray());
	}
	@Test
	public void givenInputWhenNotUniqueValuesThenReturnPairs() {
		ComplementaryPairs complPairs = new ComplementaryPairs();
		List<Pair<Integer>> pairs = complPairs.findPairs(3, new Integer[] {1,2,3,2,1,0,5});
		
		assertEquals(5, pairs.size());
		assertTrue(pairs.contains(new Pair<Integer>(0,1)));
		assertTrue(pairs.contains(new Pair<Integer>(0,3)));
		assertTrue(pairs.contains(new Pair<Integer>(1,4)));
		assertTrue(pairs.contains(new Pair<Integer>(2,5)));
		assertTrue(pairs.contains(new Pair<Integer>(3,4)));		
		
		
	}
	@Test
	public void givenInputWhenNoComplValuesThenReturnEmpty() {
		ComplementaryPairs complPairs = new ComplementaryPairs();
		List<Pair<Integer>> pairs = complPairs.findPairs(3, new Integer[] {7,2,3,5});		
		assertEquals(0, pairs.size());
	}

	
	@Test
	public void givenListsWhenDataThenCreatePairs() {
		ComplementaryPairs complPairs = new ComplementaryPairs();
		List<Pair<Integer>> pairs = complPairs.createPairs(Arrays.asList(new Integer[] {1,3,2}), Arrays.asList(new Integer[] {2,4}));
				
		assertEquals(6, pairs.size());
		assertTrue(pairs.contains(new Pair<Integer>(1,2)));
		assertTrue(pairs.contains(new Pair<Integer>(1,4)));
		assertTrue(pairs.contains(new Pair<Integer>(3,2)));
		assertTrue(pairs.contains(new Pair<Integer>(3,4)));
		assertTrue(pairs.contains(new Pair<Integer>(2,2)));		
		assertTrue(pairs.contains(new Pair<Integer>(2,4)));		
	}

	@Test
	public void givenListsWhenNoDataThenReturnEmptyPairs() {
		ComplementaryPairs complPairs = new ComplementaryPairs();
		List<Pair<Integer>> pairs = complPairs.createPairs(Arrays.asList(new Integer[] {1,3,2}), Arrays.asList(new Integer[0]));
		assertEquals(0, pairs.size());				
	}
	
	@Test
	public void givenDataThenCreatePositionMap() {
		ComplementaryPairs complPairs = new ComplementaryPairs();
		Map<Integer, List<Integer>> positionMap = complPairs.createPositionsMap(new Integer[] {1,2,3,2,1,0,5});
				
		assertEquals(5, positionMap.size());
		assertArrayEquals(new Integer[] {0,4}, positionMap.get(1).toArray());
		assertArrayEquals(new Integer[] {1,3}, positionMap.get(2).toArray());
		assertArrayEquals(new Integer[] {2}, positionMap.get(3).toArray());
		assertArrayEquals(new Integer[] {5}, positionMap.get(0).toArray());
		assertArrayEquals(new Integer[] {6}, positionMap.get(5).toArray());			
	}

}

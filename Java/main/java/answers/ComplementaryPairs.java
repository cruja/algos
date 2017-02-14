package answers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  ComplementaryPairs
 *  I created an extra map containing indexes of each value which occurs in the input array to have a liear time complexity.
 * time complexity: O(N)
 * space complexity: O(N)
 *
 */
public class ComplementaryPairs {

	/**
	 * Find the complementary pairs using provided input.
	 * @param k the complementary value
	 * @param data the list of integers
	 * @return the pair list
	 */
	public List<Pair<Integer>> findPairs(Integer k, Integer[] data) {
		return findPairs(k, createPositionsMap(data));
	}
	
	
	/**
	 * Find complementary pairs using a position map, e.g. extracting from the Map the complementary positions lists 
	 * and creating positions out of them.
	 *  
	 * @param k the complementary value
	 * @param positionsMap
	 * @return the pair list
	 */
	private List<Pair<Integer>> findPairs(Integer k, Map<Integer, List<Integer>> positionsMap) {
		
		List<Pair<Integer>> result = new ArrayList<>();
		Iterator<Integer> keysIterator = positionsMap.keySet().iterator();
		while (keysIterator.hasNext()) {
			Integer firstValue = keysIterator.next();
			List<Integer> positions = positionsMap.get(firstValue);
			int pairedValue = k - firstValue;
			List<Integer> pairedPositions = positionsMap.get(pairedValue);
			if (pairedPositions != null)
				result.addAll(createPairs(positions, pairedPositions));
			
			keysIterator.remove();			
		}
		return result;
	}


	/**
	 * Create the index positions map for the corresponding input, e.g for each value in the input array an entry 
	 * is added in the returned data map
	 * 
	 * @param data the list of integers
	 * @return position map
	 */
	Map<Integer, List<Integer>> createPositionsMap(Integer[] data) {		 
		
		Map<Integer, List<Integer>> positionsMap = new HashMap<>();
		
		for (int i = 0; i < data.length; i++) {		
			final int position = i;
			positionsMap.compute(data[i], (aVal, positions) -> { 	List<Integer> positionsList = positions == null ? new ArrayList<>() : positions;   
																	positionsList.add(position);
																	return positionsList;
																});
		}
		
		return positionsMap;
	}

	
	/**
	 * Create a list of pairs by combining the two provided lists.
	 * @param positions
	 * @param pairedPositions
	 * @return the list of pairs
	 */
	List<Pair<Integer>>  createPairs( List<Integer> positions, List<Integer> pairedPositions) {
		List<Pair<Integer>> result = new ArrayList<>();
		for (Integer val : positions) {
			for (Integer pairedVal : pairedPositions) {
				result.add(new Pair<Integer>(val, pairedVal));
			}
		}
		return result;
	}
	
	
	/**
	 * Pair tuple class. 
	 * (We may use also a org.apache.commons.lang3.tuple.Pair instead)
	 *
	 * @param <K>
	 */
	static class Pair<K> {
		K val1;
		K val2;
		
		public Pair(K val, K pairedVal) {
			val1 = val;
			val2 = pairedVal;
		}

		@Override
		public String toString() {
			return "Pair [" + val1 + "," + val2 + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((val1 == null) ? 0 : val1.hashCode());
			result = prime * result + ((val2 == null) ? 0 : val2.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair<K> other = (Pair<K>) obj;
			if (((val1 == null) && (other.val1 == null)) || ((val2 == null) && (other.val2 == null)))
				return true;
			
			if (((val1 == null) && (other.val1 != null)) || ((val2 == null) && (other.val2 != null)))
					return false;
			
			return ((val1.equals(other.val1) && val2.equals(other.val2)) 
					|| (val1.equals(other.val2) && val2.equals(other.val1)));
				
		}
	}
}

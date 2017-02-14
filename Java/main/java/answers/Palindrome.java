package answers;

/**
 *  Palindrome
 * time complexity: O(N)
 * space complexity: O(N)
 *
 */
public class Palindrome {

	public static boolean isPalindrome(String source) {		 
		boolean isPalindrome = true;
		int length = source.length();
		for (int i = 0; i < length/2; i++) {
			if(source.charAt(i) != source.charAt(length - i - 1)) {
				isPalindrome = false;
				break;
			}
		}
		return isPalindrome;
	}
}

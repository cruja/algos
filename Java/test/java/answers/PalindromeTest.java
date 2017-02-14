package answers;

import static org.junit.Assert.*;

import org.junit.Test;

import answers.Palindrome;

public class PalindromeTest {
	
	@Test
	public void givenPalindromeWhenOddSizeThenTrue() {
		assertTrue(Palindrome.isPalindrome("12321"));
		assertTrue(Palindrome.isPalindrome("1"));		
	}

	@Test
	public void givenPalindromeWhenEvenSizeThenTrue() {
		assertTrue(Palindrome.isPalindrome("1221"));
		assertTrue(Palindrome.isPalindrome(""));		
	}

	@Test
	public void givenNotPalindromeThenFalse() {
		assertFalse(Palindrome.isPalindrome("1121"));
		assertFalse(Palindrome.isPalindrome("12"));
		assertFalse(Palindrome.isPalindrome("123"));		
	}
}

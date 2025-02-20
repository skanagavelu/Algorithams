package string;

import java.lang.reflect.Array;

/*
 * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * LISTEN --> SILENT
 *
 * All anagrams are permutations of each other, but not all permutations are anagrams.
 * By definition, an anagram must have meaning (be a real word or phrase),
 * but a permutation can be any ordering of the characters without needing to have meaning.
 */
public class Anagrams {
	public static void main(String[] args) {
		int k = 3;
		printAnagrams("ABCD".toCharArray(), k);
	}

	public static void printAnagrams(char[] set, int anagramLength) {
		int inputLength = set.length;
//		printAnagrams(set, "", inputLength, anagramLength);
		printAnagrams(set, "");

	}

	// Anagram of varying length
	public static void printAnagrams(char[] chars, String prefix, int inputLength, int anagramLength) {

		if (anagramLength == 0) {
			System.out.println(prefix);
			return;
		}

		for (int i = 0; i < inputLength; ++i) {
			String newPrefix = prefix + chars[i];
			//char[] remainingChars = new StringBuilder(new String(chars)).deleteCharAt(i).toString().toCharArray();
			char[] remainingChars = removeElement(chars, i);
            printAnagrams(remainingChars, newPrefix, remainingChars.length, anagramLength - 1);
		}
	}

	public static void printAnagrams(char[] chars, String prefix) {

		if (chars.length == 0) {
			System.out.println(prefix);
			return;
		}

		for (int i = 0; i < chars.length; ++i) {
			String newPrefix = prefix + chars[i];
			//char[] remainingChars = new StringBuilder(new String(chars)).deleteCharAt(i).toString().toCharArray();
			char[] remainingChars = removeElement(chars, i);
			printAnagrams(remainingChars, newPrefix);
		}
	}

//  Arrays.copyOfRange(source, 0, removedIdx) wont work as there is specific index to be removed
	public static char[] removeElement(char[] source, int removedIdx) {

		char[] target = new char[source.length - 1];
		System.arraycopy(source, 0, target, 0, removedIdx); //copy up to removedIdx
		System.arraycopy(source, removedIdx + 1, target, removedIdx, target.length - removedIdx);// copy after removedIdx
		return target;
	}

	/*
	    Look at the below StringBuilder implementation, where just shifting is enough to remove a char.
	    We don't need src and destination:

	    private void shift(int offset, int n) {
			System.arraycopy(value, offset << coder,
							 value, (offset + n) << coder, (count - offset) << coder);
    	}
	 */

// For generics
	public static <T> T[] removeElement(T[] source, Class<T> clazz, int removedIdx) {

		@SuppressWarnings("unchecked")
		T[] target = (T[]) Array.newInstance(clazz, source.length - 1);
		System.arraycopy(source, 0, target, 0, removedIdx); //copy up to removedIdx
		// -1 as there is one element short in the target
		System.arraycopy(source, removedIdx + 1, target, removedIdx, source.length - removedIdx - 1);// copy after removedIdx but one less
		return target;
	}
}

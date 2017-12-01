/*
 * Author: @jayrungta
 */
package edu.stevens.cs600;

public class ProcessWords {
	public static Trie trie = new Trie('#');
	public static String[] stopWords = { "the", "a", "an", "i", "you", "he", "she", "it", "they", "we", "me", "him",
			"her", "them", "us", "aboard", "about", "above", "across", "after", "against", "along", "among", "around",
			"as", "at", "before", "behind", "below", "beside", "besides", "between", "beyond", "but", "by", "despite",
			"down", "during", "except", "following", "for", "from", "in", "inside", "into", "near", "of", "on", "onto",
			"over", "past", "since", "than", "to", "through", "toward", "towards", "under", "until", "up", "upon",
			"via", "with", "within", "without" };

	public static String[] html = { "a", "href", "p", "div", "img", "src", "html", "http", "body", "span", "class",
			"id", "name", "target", "style", "figure", "border", "width", "height" };

	public static String[] punctuation = { ".", ",", "/", "==", "//", "<", ">", "?", ";", "'", ":",
			"\"", "[", "]", "\\", "/{", "}", "|", "+", "-", "=", "!", "@", "#", "$", "%", "^", "&", "*", "(",
			")", "_"};
	
	public boolean isStopWord(String w) {
		String word = w.toLowerCase();
		for (String str : punctuation) {
			if (word.equals(str))
				return true;
		}
		for (String str : stopWords) {
			if (word.equals(str))
				return true;
		}
		for (String str : html) {
			if (word.equals(str))
				return true;
		}
		return false;
	}
	
	public void addPageWithUrl(String page, String url) {
		String[] words = page.split(" ");
		for (String word : words) {
			if (word.trim().length() > 0 && !isStopWord(word)) {
				processWord(word.trim(), url);
			}
		}
	}

	private void processWord(String w, String url) {
		String word = w.toLowerCase();
		int i = 0;
		Trie root = trie;
		while (i < word.length()) {
			root.addChild(word.charAt(i));
			root = root.getChild(word.charAt(i));
			i++;
		}
		root.addUrls(url);
	}
}

package edu.stevens.cs600;

import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String defaultURL = "http://www.imdb.com";
		int noOfPages;
		String url, pages;

		while (true) {
			System.out.println("Enter url to start crawling [" + defaultURL + "]:\n");
			url = scanner.nextLine();
			if (url.trim().length() > 0) {
				// validate the entered URL
				try {
					URL u = new URL(url); // check for the protocol
					u.toURI(); // does the extra checking required for validation of URL
				} catch (Exception e) {
					System.out.println("Invalid URL, try again");
					continue;
				}
			} else {
				url = defaultURL;
			}
			System.out.println("Enter number of pages to crawl:");
			pages = scanner.nextLine();
			try {
				// validate the entered number
				noOfPages = Integer.parseInt(pages);
				if (noOfPages < 1) {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid number, try again");
				continue;
			}
			// all good, start crawling
			WebCrawler crawler = new WebCrawler(noOfPages);
			crawler.startCrawling(url);
			break;
		}

		String search_string = "";
		while (true) {
			System.out.println("\nEnter a search query:\n");
			search_string = scanner.nextLine();
			if (search_string.equals("exit") || search_string.equals("quit")) {
				break;
			}
			Set<String> urls = CrawlerHelper.searchForWord(search_string);
			if (urls.size() == 0) {
				System.out.println("No search results found.");
			} else {
				Iterator<String> iterator = urls.iterator();
				System.out.println(urls.size()+" Results Found: ");
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				System.out.print("\n");
			}
		}
		scanner.close();
	}
}

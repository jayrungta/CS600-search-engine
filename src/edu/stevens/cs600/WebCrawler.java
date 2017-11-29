package edu.stevens.cs600;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WebCrawler {
	private int pagesToCrawl;
	private Set<String> visitedList = new HashSet<String>();
	private List<String> pageList = new LinkedList<>();
	
	public WebCrawler(int pagesToCrawl) {
		this.pagesToCrawl = pagesToCrawl; 
	}
	
	public void startCrawling(String url) {
		while(this.visitedList.size()< pagesToCrawl){
			String currentUrl;
			CrawlerHelper helper = new CrawlerHelper();
			
			if(this.pageList.isEmpty()){
				currentUrl = url;
				this.visitedList.add(url);
			}
			else{
				currentUrl = this.nextUrl();
			}
			System.out.println("Visiting: "+currentUrl);
			helper.crawl(currentUrl); 
			
			this.pageList.addAll(helper.getLinks());
		}	
	}

	private String nextUrl(){
		String nextURL;
		do{
			nextURL = this.pageList.remove(0);
		}while(this.visitedList.contains(nextURL));
		this.visitedList.add(nextURL);
		
		return nextURL;		
	}
}
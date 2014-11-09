import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MyCrawler extends WebCrawler {
	
	public static List<Review> reviewList= new ArrayList<Review>();
	public static Set reviews= new HashSet<String>();

        private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
                                                          + "|png|tiff?|mid|mp2|mp3|mp4"
                                                          + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
                                                          + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

        /**
         * You should implement this function to specify whether
         * the given url should be crawled or not (based on your
         * crawling logic).
         */
        @Override
        public boolean shouldVisit(WebURL url) {
                String href = url.getURL().toLowerCase();
                return !FILTERS.matcher(href).matches() && href.startsWith("http://www.tasty.lk/"+City.city)&& !href.startsWith("http://www.tasty.lk/"+City.city+"/restaurants")&&href.indexOf('?')==-1&&!href.contains("www.facebook.com")&&!href.contains("review");
        }

        /**
         * This function is called when a page is fetched and ready 
         * to be processed by your program.
         */
        @Override
        public void visit(Page page) {          
                String url = page.getWebURL().getURL();
                /*String hrf=url.toLowerCase();
                if(!hrf.startsWith("http://www.tasty.lk/colombo/restaurants"))
                {
                System.out.println("URL: " + url);
                }*/
               // System.out.println(url);
                if (page.getParseData() instanceof HtmlParseData) {
                        HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                        String text = htmlParseData.getText();
                        String html = htmlParseData.getHtml();
                        Document doc = Jsoup.parseBodyFragment(html);
                        
                        /*Elements eles = doc.select("div.reviewItem");
                        for (Element element : eles) {
                            System.out.println(element.ownText());
                        }*/
                        //System.out.println(s);
                       
                        String[] split1=url.split("\\/");
                        String rev= split1[4].split("\\?")[0].trim();
                        System.out.println("****" + rev);
                        Elements eles2=doc.select("div.reviewItem").select("p");
                        for (Element element : eles2) {
                        	String revi=element.ownText();
                        	Elements metaOgTitle = doc.select("meta[property=og:title]");
                        	text= metaOgTitle.attr("content").replace("| Restaurant & Reviews", "").trim();
                        	 System.out.println(text);
                            reviewList.add(new Review(revi,rev,text));
                            
                        }
                        
                        
                      /*  String[] split1=url.split("\\/");
                        String rev= split1[4].split("\\?")[0].trim();
                        //reviews.add(rev);
                        //Review rev1=new Review(s,rev);
                        //reviewList.add(rev1);
                        //System.out.println("URL: " + rev);
                        Elements metalinks = doc.select("meta[property=og:title]");
                        String sp = metalinks.first().attr("content");
                        String[]  split =sp.split("\\|");
                        
                        //System.out.println(s);
                        //System.out.println("***"+reviews.size()+"****");
                      //  List<WebURL> links = htmlParseData.getOutgoingUrls();*/

                       //System.out.println("Text length: " + text.length());
                       // System.out.println("Html length: " + html.length());
                        //System.out.println("Number of outgoing links: " + links.size());
                       // System.out.println(eles);
                }
        }
}

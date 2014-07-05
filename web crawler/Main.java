import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Main {
	
 
	public static void main(String[] args) throws SQLException, IOException {
		
		processPage("http://www.mit.edu");
	}
 
	public static void processPage(String URL) throws SQLException, IOException{
		//to crawl different emotions
		String [] emotions={"stupid","scary","amusing","sad","strange","interesting","hero","weird","dumbass"};
		String [] taglist=new String[600];
		String [] url=new String[20];
		for (int i=0;i<7;i++)
		{
			
		url[i]="http://www.fark.com/topic/"+emotions[i]+"?hrs=168";	
		}
		
		url[7]="http://www.fark.com/archives/2014-03-30";
		int date1=2;
		for(int i=8;i<13;i++)
		{
			if(date1<10)
			url[i]="http://www.fark.com/archives/2014-03-0"+date1;
			else
				url[i]="http://www.fark.com/archives/2014-03-"+date1;	
			date1=date1+7;
		}
		PrintWriter writer = new PrintWriter("G://nlp//allemotions.txt", "UTF-8");
		int cnt=0;
		Document doc=null;
			//get useful information
		int i;
		for(i=0;i<13;i++)
		{
			
			
		try {
		//	 doc = Jsoup.connect("http://www.fark.com/topic/"+emotions[i]+"?hrs=168").get();
			 doc = Jsoup.connect(url[i]).get();
				
		} catch (IOException e1) {
			e1.printStackTrace();
		
		}
			
			if(doc.text().contains("")){
				//System.out.println(URL);
			//	System.out.println("hi");
 
			//get all links and recursively call the processPage method
	//Elements questions = doc.select("a[href]");
				Elements tags=doc.select("td[class=headlineTopic]");
			Elements questions=doc.select("	span[class=headline]");
		int tagcount=0;	
			//	Elements questions=doc.select("	a[class=outboundlink]");
			for(Element tag1: tags){
				
				Elements t = tag1.getElementsByTag("a");
				String t1=t.attr("title");
				System.out.println("tag "+t1);
				taglist[tagcount]=t1;
				tagcount++;
			}
			tagcount--;
			int curtag=0;
			for(Element link: questions){
			//	System.out.println("h");
			//	System.out.println(link);
				Elements questions1 = link.getElementsByTag("a");
				//Element p= doc.select("p").first();
				String t = questions1.attr("href");
				String tag = questions1.attr("title");
				System.out.println("link "+t);
				//System.out.println("tag "+tag);
			//	String t=linkInnerH.attr("href");
				String text = link.text();
				
				System.out.println("headline " + text);
				//return;
				
				int true1=0;
				for(int i1=0;i1<emotions.length;i1++)
				{
					if(taglist[curtag].equalsIgnoreCase(emotions[i1]))
					{
						true1=1;
						System.out.println("true "+true1);
					}
					
				}
				
				System.out.println("cur tag "+curtag);
				if(true1==1)
				{
				//writing the category to which news article belongs to dataset
					writer.print(taglist[curtag]+" ");
					//writing headline of news article belongs to dataset
					writer.print(text+" ");	
					
				
					cnt++;
					
					Document newsdoc = null;
					try {
					//removing redirection frm the link extracted as it results in page not found error
						Pattern pat = Pattern.compile("http://www.fark.com/goto/[0-9]*/http://");
						Matcher mat = pat.matcher(t);
						String crop=t;
						if(mat.find())
						{    
						crop=t.substring( mat.end()-7,t.length());
						System.out.println("connected "+crop);}
						crop=crop.replaceAll("%26","&");
						crop=crop.replaceAll("%3D","=");
						crop=crop.replaceAll("%3F","?");
						crop=crop.replaceAll("%2","+");
						newsdoc = Jsoup.connect(crop).timeout(15000).get();	
						 if(newsdoc.text().contains(""))
						 {
							 System.out.println("inside");
							 //filtering news article content based on tag
							 Elements news=newsdoc.select("p");
							
								 System.out.println("content "+newsdoc.text());
								 //writing news article content to dataset
								 writer.print(newsdoc.text());
							// }
							 writer.println();
						 }
				
						
					} catch (IOException e1) {
						e1.printStackTrace();
						writer.println();
					//return;
					}
					
				}	
				curtag++;
					
			}
			
			System.out.println(cnt);
		}
			
		}
		writer.close();
	}
	
}
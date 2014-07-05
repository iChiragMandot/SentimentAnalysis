
import java.io.*;
import java.util.*;

public class filereader {
	public static int articlecount=0;
	public static String [] emotions={"stupid","scary","amusing","sad","strange","interesting","hero","weird","dumbass"};
	public static HashMap<String, Integer> map =new HashMap<String, Integer>();
	public static HashMap<String,Float> idf1=new HashMap<String,Float>();
public static void addtodictionary(HashMap<String, Integer> m1)
{
	 for (String k : m1.keySet())
	    {
		 if(map.containsKey(k))
		 {
			 int count11=map.get(k);
		    	map.put(k, count11+1);
			 
		 }
			    }
	
}
public static void idf()
{
	System.out.println("map size"+map.size());
	
	 for (String k : map.keySet())
	    {
		 
		 idf1.put(k,new Float(Math.log((float)(articlecount/map.get(k)))));
		 
	    }
	System.out.println("idf map size"+idf1.size());
	
}
public static void writeidftfproduct(HashMap<String,Integer> m1 ,String tag, PrintWriter writer1)
{
	 
		int it;				           
	    for( it=0;it<emotions.length;it++)  {
		    if(tag.equalsIgnoreCase(emotions[it]))
				
		    {	writer1.print((it+1)+" ");	
		    System.out.println(tag+" belongs to category "+emotions[it]);
		    break;
		    }
	    }
	  if(it!=emotions.length)
	  {
	    int cnt=0;
	    for (String k : idf1.keySet())
	    {
	    if(m1.containsKey(k))
	    {
	    	cnt++;
	    	writer1.print(m1.get(k)*idf1.get(k)+" ");
	    }
	    else
	    {
	    	cnt++;
	    	writer1.print("0 ");
	    }
	    }
	    if(it!= emotions.length)
			writer1.println("");
	    System.out.println("cnt :"+cnt);
	  }
}


public static void  readarticle()
{
	 HashMap<String,Integer> m1=new HashMap<String,Integer>();
	 PrintWriter writer1=null;
		
	 try {
		 
			 writer1 = new PrintWriter("C:\\Users\\sai preethy\\Dropbox\\nlp\\headlinedata\\tfidf.txt", "UTF-8");
		//	 writer2 = new PrintWriter("C:\\Users\\Hi\\Dropbox\\nlp\\headlinedata\\highfrequentdictlables.txt", "UTF-8");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 int cc=1;
 
        	cc=1;
	            FileReader fr1 = null;
				try {
					fr1 = new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\allemotionsheadlinecontent.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader bf1=new BufferedReader(fr1);
				String aLine1;
				int count1=1;

String tag=null;
			
					try {
						while((aLine1=bf1.readLine())!=null)
						{   cc=1;
							articlecount++;
							//System.out.println(aLine);
						int categorytag=0;
							String[] arr = aLine1.split(" ");    
							//for (int ij1=0;ij1<arr.length-1;ij1++)
							//{		 
							 	// String ss=arr[ij1]+" "+arr[ij1+1]; 
							
						 for ( String ss : arr) 
							 {
							 categorytag++;
							 
							 if(categorytag==1)
							 {
								 tag=ss;
							 }
									 if(!m1.containsKey(ss) )
									 {
									 m1.put(ss, new Integer(1));
								     //  System.out.println(ss);
									 }
									 else
									 {
										count1=m1.get(ss);
										m1.put(ss,count1+1);
										 
									 }
							  }
							


   int c1= m1.size();
   writeidftfproduct( m1 ,tag,writer1);
//System.out.println("count "+c1+" "+cc);
m1.clear();
						
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 writer1.close();
}
public static void createmaparticle()
{
	 HashMap<String,Integer> m1=new HashMap<String,Integer>();

	 int cc=1;
 
        	cc=1;
	            FileReader fr1 = null;
				try {
					fr1 = new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\allemotionsheadlinecontent.txt");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader bf1=new BufferedReader(fr1);
				String aLine1;
				int count1=1;

String tag=null;
			try {
					while((aLine1=bf1.readLine())!=null)
					{   cc=1;
						articlecount++;
						//System.out.println(aLine);
					int categorytag=0;
						String[] arr = aLine1.split(" ");    
						//for (int ij1=0;ij1<arr.length-1;ij1++)
						//{		 
						 	// String ss=arr[ij1]+" "+arr[ij1+1]; 
						
					 for ( String ss : arr) 
						 {
						 categorytag++;
						 
						 if(categorytag==1)
						 {
							 tag=ss;
						 }
								 if(!m1.containsKey(ss) )
								 {
								 m1.put(ss, new Integer(1));
							     //  System.out.println(ss);
								 }
								 else
								 {
									count1=m1.get(ss);
									m1.put(ss,count1+1);
									 
								 }
						  }
						
	

    int c1= m1.size();
 addtodictionary(m1);
	//System.out.println("count "+c1+" "+cc);
	m1.clear();
  }

			
			bf1.close();
	} catch (IOException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



}
	public static void main(String[] args) throws FileNotFoundException 
	{
			int [] emotionscount=new int[emotions.length];
		
		 PrintWriter writer = null;
		 
	//for(int it=0;it<=6;it++)
	//	{
				            FileReader fr=new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\allemotionsheadlinecontent.txt");
							BufferedReader bf=new BufferedReader(fr);
							String aLine;
							int count=1;
	
						try {
								while((aLine=bf.readLine())!=null)
								{
									int first=0;
									//System.out.println(aLine);
									String[] arr = aLine.split(" ");    
								//	for (int ij1=0;ij1<arr.length-1;ij1++)
									// {		 
									 //	 String ss=arr[ij1]+" "+arr[ij1+1]; 
									
								 for ( String ss : arr) 
								{
										 String category;
										 first++;
										 if(first==1)
											 {category=ss;
											 
											 for(int it=0;it<emotions.length;it++)
											 {
												 if(emotions[it].equalsIgnoreCase(category))
												 {
												 emotionscount[it]++;
												 System.out.println("category "+category+"count "+emotionscount[it]);
												 }
											 }
											
											 }
										 else
										 {
											 if(!map.containsKey(ss) )
											 {
											 map.put(ss, new Integer(1));
										      // System.out.println(ss);
											 }
											 else
											 {
												count=map.get(ss);
												map.put(ss,count+1);
												 
											 }
									  }
									}
								}
								
								bf.close();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								           
			  // }
			    
			
			 
			
		System.out.println("map before "+map.size());
			 
			    for(Iterator<Map.Entry<String,Integer>>it1=map.entrySet().iterator();it1.hasNext();){
			    	
			    	Map.Entry<String, Integer> entry = it1.next();
			    	
			    	if (entry.getValue() < 15) {//removing elements with total frequency count in training corpus less than 5 from dictionary
			    	
			    	it1.remove();
			    	
			    	
			    	}
			    	
			    	
			    	}
			    
				System.out.println("map after "+map.size());
			    for (String k : map.keySet())
			        map.put(k, 0);
			    System.out.println("keys "+map);
			   
	
			    int c= map.size();
				System.out.println("count of dic "+c);
		
createmaparticle();
System.out.println("called function createmaparticle "+map.size());
System.out.println("article count "+articlecount);

idf();
System.out.println("idf "+idf1);
			
readarticle();
	
				//}	 

		

	}
	
}
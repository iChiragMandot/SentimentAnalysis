
import java.io.*;
import java.util.*;

public class filereader {

		
	public static void main(String[] args) throws FileNotFoundException 
	{
		String [] emotions={"stupid","scary","amusing","sad","strange","interesting","hero","weird","dumbass"};
	int [] emotionscount=new int[emotions.length];
		HashMap<String, Integer> map =new HashMap<String, Integer>();
		 PrintWriter writer = null;
		 
	
				            FileReader fr=new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\withsimilaremotions.txt");
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
			    
			
			 
			
			  // Display elements
			  		     // Get a set of the entries
			    Set set = map.entrySet();
			    // Get an iterator
			    Iterator i1 = set.iterator();
			 
			    for(Iterator<Map.Entry<String,Integer>>it1=map.entrySet().iterator();it1.hasNext();){
			    	
			    	Map.Entry<String, Integer> entry = it1.next();
			    	
			    	if (entry.getValue() < 5) {//removing elements with total frequency count in training corpus less than 5 from dictionary
			    	
			    	it1.remove();
			    	
			    	}
			    	
			    	}
			   
			     set = map.entrySet();
			    // Get an iterator
			     i1 = set.iterator();
			 
			    while(i1.hasNext()) 
			    {
			       Map.Entry me = (Map.Entry)i1.next();
			  			
			    }
	
			    int c= map.size();
				System.out.println("count of dic "+c);
		
		
	//	 writer.close();
			 PrintWriter writer1=null;
			 //PrintWriter writer2=null;
			 try {
				 
					 writer1 = new PrintWriter("C:\\Users\\sai preethy\\Dropbox\\nlp\\headlinedata\\similaremotionsfrequency.txt", "UTF-8");
				//	 writer2 = new PrintWriter("C:\\Users\\Hi\\Dropbox\\nlp\\headlinedata\\highfrequentdictlables.txt", "UTF-8");
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 //for writing feature vector and expected output class into file
			 //for(int it=0;it<=6;it++)
			//	{
				 HashMap<String,Integer> m1=new HashMap<String,Integer>();

				 int cc=1;
			 
			        	cc=1;
				            FileReader fr1=new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\withsimilaremotions.txt");
							BufferedReader bf1=new BufferedReader(fr1);
							String aLine1;
							int count1=1;
	
	String tag=null;
						try {
								while((aLine1=bf1.readLine())!=null)
								{   cc=1;
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
									
									           
			    for(int it=0;it<emotions.length;it++)  {
				    if(tag.equalsIgnoreCase(emotions[it]))
						
				    {	writer1.print((it+1)+" ");	
				    System.out.println(tag+" belongs to category "+emotions[it]);}
			    }
		
			  // Display elements
			  		     // Get a set of the entries
			    //Set set = m1.entrySet();
			    Set set1= map.entrySet();
			    // Get an iterator
			   // Iterator i1 = set.iterator();
			 Iterator i2=set1.iterator();
			 System.out.println("m1 count"+m1.size());
			    while(i2.hasNext()) 
			    {
			       Map.Entry me = (Map.Entry)i2.next();
			       if(m1.containsKey(me.getKey()))
			       {
			    	  writer1.print(m1.get(me.getKey())+" ");
			    	  // writer1.print("1 ");
			    	   cc++;
			       }
			       else
			       {
			    	 
				       writer1.print("0 ");  

			    	   cc++;
			       }
				
			    }
	
			    int c1= m1.size();
				
				writer1.println(""); 
				System.out.println("count "+c1+" "+cc);
				m1.clear();
			  }
	
						
						bf1.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				//}	 

			 writer1.close();
		
	}
	
}
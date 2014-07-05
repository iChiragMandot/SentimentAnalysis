package wordnet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import edu.smu.tspell.wordnet.*;
import edu.smu.tspell.wordnet.Synset;
import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.relationship.*;

public class WordNetDemo {
	
	public static int stdMean()

    {

       int sum = 0;

       for(int i = 0; i <score.length; i++)

       {

           sum = score[i] + sum;                         

        }

       return sum/score.length;
        

   }

   public static double stdDev ()

   {

       int mean = stdMean();

       int sum = 0;

       

        for(int i = 0; i <score.length; i++)

       {

           int value = score[i]-mean;

           sum += Math.pow(value, 2);

           System.out.println("Variance " + sum);

       }

       float variance = sum/score.length;

         

       double stdDev = Math.sqrt(variance);

       return stdDev;

        

         }

public static void scorefinal()
{
	for(int i=0;i<score.length;i++)
	{
		if(score[i]!=0)
	score[i]=score[i]*(1-(distinct[i]/score[i]));
	}
	
}
	public static int highestcategory(HashMap<String, Integer> map)
	{
		//double[] score1=new double[score.length];
		
		int max=0;
		double temp=stdDev()+stdMean();
	scorefinal();
		for(int i=0;i<score.length;i++)
		{
		if((score[max]<score[i])&&(score[i]>=temp))
		{			System.out.println(score.toString()+emotions[max]+" score i "+score[i]+"scoremax "+score[max]+"temp "+temp);

			max=i;
		
		System.out.println(emotions[max]+" map "+map);
		System.out.println(emotions[max]+" score i "+score[i]+"scoremax "+score[max]+"temp "+temp);

		}
		}
		if(score[max]<temp)
		{
			return -1;
		}
		return max;
		
	}
	public static int actualcategory(String cat)
	{
		for(int i=0;i<emotions.length;i++)
		{
			if(emotions[i].equalsIgnoreCase(cat))
				return i;
		}
		return -1;
	}
	public static ArrayList<String> addlist(String[] a,ArrayList<String> b)
	{
		for(int i=0;i<a.length;i++ )
		{
			if(!b.contains(a[i]))
			b.add(a[i]);
			System.setProperty("wordnet.database.dir", "G:\\WordNet-3.0\\dict");
	    	WordNetDatabase database = WordNetDatabase.getFileInstance();
	    	Synset[] synsets = database.getSynsets(a[i]);
	    	if (synsets.length > 0)
			{
				//System.out.println("The following synsets contain  or a possible base form " +"of that text:");
				for (int i1 = 0; i1 < synsets.length; i1++)
				{
					//System.out.println("");
					String[] wordForms = synsets[i1].getWordForms();
					for (int j = 0; j < wordForms.length; j++)
					{
						//System.out.println(wordForms[j]);
						if(!b.contains(wordForms[j]))
								{
							b.add(wordForms[j]);
								}
					}
				
				}
			}
	    	
		}
		
		
		return b;
	}
	public static int calculatescore(HashMap<String, Integer> map,ArrayList<String> list,int index)
	{
	
		int count=0; int distinct=0;
		for(int i=0;i<list.size();i++)
		{
			if(map.containsKey(list.get(i)))
			{
				count=count+map.get(list.get(i));
				distinct++;
			}
		}
		System.out.println("score array "+map);
		score[index]=count;
		return distinct;
	}
	
	
	static String [] sad = {"Unhappy", "depressed", "melancholy","sad", "depressed","bitter" , "dismal" , "heartbroken" , "melancholy" , "mournful" , "pessimistic" , "somber" , "sorrowful" , "sorry" , "wistful" , "bereaved" , "blue" , "cheerless" , "dejected" , "despairing" , "despondent" , "disconsolate" , "distressed" , "doleful" , "down" , "down in dumps" , "down in mouth" , "downcast" , "forlorn" , "gloomy" , "glum" , "grief-stricken" , "grieved" , "heartsick" , "heavyhearted" , "hurting" , "in doldrums" , "in grief" , "in the dumps" , "languishing" , "low" , "low-spirited" , "lugubrious" , "morbid" , "morose" , "out of sorts" , "pensive" , "sick at heart" , "troubled" , "weeping" , "woebegone" };
	static String [] emotions={	"stupid",	"scary"	,"amusing"	,"interesting"};
	static String [] stupid = {"stupid","not intelligent", "irresponsible","dull" , "dumb" , "foolish" , "futile" , "ill-advised" , "irrelevant" , "laughable" , "ludicrous" , "naive" , "senseless" , "shortsighted" , "simple" , "trivial" , "dummy" , "loser" , "rash" , "thick" , "unintelligent" , "brainless" , "dazed" , "deficient" , "dense" , "dim" , "doltish" , "dopey" , "gullible" , "half-baked" , "half-witted" , "idiotic" , "imbecilic" , "inane" , "indiscreet" , "insensate" , "meaningless" , "mindless" , "moronic" , "nonsensical" , "obtuse" , "out to lunch" , "pointless" , "puerile" , "simpleminded" , "slow" , "sluggish" , "stolid" , "stupefied" , "thick-headed" , "unthinking" , "witless" };
	static String [] scary = {"Afraid" , "Alarmed" , "alarming" , "Anxious" , "Appalled" , "Apprehensive" , "Awkward" , "bloodcurdling" , "Careful" , "Cautious" , "chilling" , "creepy" , "Defensive" , "Desperate" , "Disquieted" , "Distressed" , "eerie" , "Fearful" , "Fidgety" , "Fretful" , "Frightened" , "frightening" , "Goose-bumpy" , "hair-raising" , "hairy" , "horrendous" , "Horrified" , "horrifying" , "Intimidated" , "intimidating" , "Jumpy" , "Nervous" , "Panicky" , "Paralyzed" , "Petrified" , "Scared" , "scary" , "Shaky" , "Shocked" , "shocking" , "Shy" , "Skittish" , "spine-chilling" , "Spineless" , "spooky" , "Taut" , "Tense" , "Terrified" , "terrifying" , "Terror-stricken" , "Threatened" , "Timid" , "Troubled" , "Uneasy" , "unnerving" , "Unsure" , "Watchful" , "Wired" , "Worried " , "Wrecked"};
	static String [] amusing = {"amusing","entertaining", "funny","campy" , "charming" , "comical" , "delightful" , "diverting" , "droll" , "engaging" , "enjoyable" , "entertaining" , "fun" , "gratifying" , "humorous" , "interesting" , "lively" , "pleasant" , "pleasing" , "camp" , "cheering" , "enchanting" , "gladdening" , "joshing" , "screaming" , "agreeable" , "boffo" , "cheerful" , "cut up" , "for grins" , "gut-busting" , "jocular" , "jokey" , "laughable" , "merry" , "priceless" , "sidesplitting" , "too funny for words" , "witty" };
	static String [] strange = {"strange","deviating", "unfamiliar","astonishing" , "bizarre" , "curious" , "different" , "extraordinary" , "fantastic" , "funny" , "new" , "odd" , "offbeat" , "outlandish" , "peculiar" , "rare" , "remarkable" , "unusual" , "weird" , "wonderful" , "aberrant" , "abnormal" , "astounding" , "atypical" , "eccentric" , "erratic" , "exceptional" , "far-out" , "idiosyncratic" , "ignorant" , "inexperienced" , "irregular" , "marvelous" , "mystifying" , "newfangled" , "oddball" , "off" , "out-of-the-way" , "perplexing" , "quaint" , "queer" , "singular" , "unaccountable" , "unaccustomed" , "uncanny" , "uncommon" , "unheard of" , "unseasoned" };
	static String [] interesting={"absorbed" , "absorbing" , "affected" , "affecting" , "alluring" , "amusing" , "appealing" , "arresting" , "attractive" , "beautiful" , "captivating" , "charismatic" , "compelling" , "concerned" , "curious" , "delightful" , "elegant" , "enchanting" , "engaging" , "engrossed" , "engrossing" , "entertaining" , "enthralling" , "entrancing" , "exceptional" , "exotic" , "fascinated" , "fascinating" , "fine" , "gracious" , "gripping" , "impressive" , "inquisitive" , "interesting" , "intrigued" , "intriguing" , "inviting" , "lovely" , "magnetic" , "nosy" , "pleasing" , "pleasurable" , "prepossessing" , "provocative" , "readable" , "refreshing" , "riveting" , "snoopy" , "stimulating" , "stirring" , "striking" , "suspicious" , "thought-provoking" , "unusual" , "winning"};
	
	static ArrayList<String> sad1=new ArrayList<String>();
	static ArrayList<String> stupid1=new ArrayList<String>();
	static ArrayList<String> scary1=new ArrayList<String>();
	static ArrayList<String> amusing1=new ArrayList<String>();
	static ArrayList<String> strange1=new ArrayList<String>();
	static ArrayList<String> interesting1=new ArrayList<String>();
	
	static int[] truepositive = new int [emotions.length];
	static int[] truenegative = new int [emotions.length];
	static int[] falsepositive = new int [emotions.length];
	static int[] falsenegative = new int [emotions.length];
	
	static int[] score = new int[emotions.length];
	static int[] distinct = new int[emotions.length];
	static int[] homogenityindex =new int[score.length];
	static int[] categoryaccuracy=new int[emotions.length];
	static int[] categorywrong=new int[emotions.length];
	
	static float[] precision=new float[score.length];
	static float[] recall=new float[score.length];
    static int accuracy=0;
    static int totalcount=0;
	static String category=null;
	
	//MAin start
	
	public static void main(String[] args) throws JWNLException {
		for( int i=0;i<score.length;i++)
		{
	    truepositive[i]=0;
	    truenegative[i]=0;
	    falsepositive[i]=0;
	    falsenegative[i]=0;
		}
		sad1=addlist(sad,sad1);
		/*for(int i=0;i<sad1.size();i++)
		{
			System.out.println("sad1 "+sad1.get(i));
		}*/
		stupid1=addlist(stupid,stupid1);
		scary1=addlist(scary,scary1);
		amusing1=addlist(amusing,amusing1);
		strange1=addlist(strange,strange1);
		interesting1=addlist(interesting,interesting1);
		
	/*	for(int i=0;i<sad1.size();i++)
		{
			System.out.println("sad1 "+sad1.get(i));
		}*/
    	 FileReader fr = null;
		try {
			fr = new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\allemotionsheadlinecontent.txt");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			BufferedReader bf=new BufferedReader(fr);
			HashMap<String, Integer> map =new HashMap<String, Integer>();
			String aLine;
			int count=1;
				try {
					while((aLine=bf.readLine())!=null)
					{
						HashMap<String, Integer> synmap =new HashMap<String, Integer>();
						int first=0;

						String[] arr = aLine.split(" ");    
					
					 for ( String ss : arr) 
					{
							
							 first++;
							 if(first==1)
								 {
								
								category=ss;
								 }
							 else
							 {
								 if(!map.containsKey(ss) )
								 {
								 map.put(ss, new Integer(1));
							     
								 }
								 else
								 {
									count=map.get(ss);
									map.put(ss,count+1);
									 
								 }
						  }
						}
					// System.out.println("map "+map);
					 HashMap<String, Integer> copymap=new HashMap<String,Integer>();
					 copymap.putAll(map);
					   Set set = map.entrySet();
					    // Get an iterator
					    Iterator i1 = set.iterator();
					    Iterator<Map.Entry<String,Integer>> iter = map.entrySet().iterator();
					    while (iter.hasNext()) {
					    	Map.Entry<String,Integer> entry = iter.next();
					    	String search = new String();
					    	search=entry.getKey();
					   // 	System.out.println("search "+search);
					    	iter.remove();
					        
					    
					   

					    	System.setProperty("wordnet.database.dir", "G:\\WordNet-3.0\\dict");
					    	WordNetDatabase database = WordNetDatabase.getFileInstance();
					    	Synset[] synsets = database.getSynsets(search);
					    	ArrayList<String> synlist=new ArrayList<String>();
					    	if (synsets.length > 0)
							{
								//System.out.println("The following synsets contain  or a possible base form " +"of that text:");
								for (int i = 0; i < synsets.length; i++)
								{
									//System.out.println("");
									String[] wordForms = synsets[i].getWordForms();
									for (int j = 0; j < wordForms.length; j++)
									{
										//System.out.println(wordForms[j]);
										if(!synlist.contains(wordForms[j]))
												{
											synlist.add(wordForms[j]);
												}
									}
								
								}
							}
					    	
							for(int s=0;s<synlist.size();s++)
							{
								if(copymap.containsKey(synlist.get(s)))
								{
							
									 if(!synmap.containsKey(search) )
											 {
											 synmap.put(search,copymap.get(synlist.get(s)));
										      // System.out.println(ss);
											 }
											 else
											 {
												count=synmap.get(search);
												synmap.put(search,count+1);
												 
											 }
									copymap.remove(synlist.get(s));
								}
							}

					    	
					    	}
					    for(int i=0; i<score.length;i++)
					    {
					    	score[i]=0;
					    	
					    }
					  //  System.out.println("synlist "+synmap);
					   // distinct[0]=calculatescore(synmap,sad1,0);
					    distinct[0]=calculatescore(synmap,stupid1,0);
					    distinct[1]=calculatescore(synmap,scary1,1);
					    distinct[2]=calculatescore(synmap,amusing1,2);
					   // distinct[4]=calculatescore(synmap,strange1,4);
					    distinct[3]=calculatescore(synmap,interesting1,3);
					    
					    for(int i=0;i<score.length;i++)
					    {   System.out.println("score "+i+" "+score[i]);
				
					    
					    }
					  
					    if((actualcategory(category)!=-1)&&(highestcategory(synmap)!=-1))
					    { System.out.println("highest "+emotions[highestcategory(synmap)]+"actual "+category);
					    System.out.println(synmap);
					    totalcount++;
					    for(int i=0;i<score.length;i++)
					    {
					    	if(highestcategory(synmap)==i)
					    	{
					    		if(actualcategory(category)==i)
					    		{
					    			truepositive[i]++;
					    			System.out.println("truepositive "+category);
					    		}
					    		else
					    			{
					    			falsepositive[i]++;
					    			System.out.println("falsepositive "+category);
					    			}
					    	}
					    	else
					    	{
					    		if(actualcategory(category)==i)
					    		{
					    			falsenegative[i]++;
					    			System.out.println("falsenegative "+category);
					    		}
					    		else
					    		{
					    			truenegative[i]++;
					    			System.out.println("truenegative "+category);
					    		}
					    	}
					    }
					    if(actualcategory(category)==highestcategory(synmap))
					    {
					    	accuracy++;
					    }
					    }
					    
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
    //	String[] syn =database.getS
    	//		getsynonyms("company", "NOUN")

        // Demo finding parts of speech
				for(int i=0;i<score.length;i++)
				{
					System.out.println(" true positive "+truepositive[i]);
					System.out.println(" true negative "+truenegative[i]);
					System.out.println(" false positive "+falsepositive[i]);
					System.out.println(" false negative "+falsenegative[i]);
					recall[i]=(float) truepositive[i]/(truepositive[i]+falsenegative[i]);
					precision[i]=(float) truepositive[i]/(truepositive[i]+falsepositive[i]);
					System.out.println("recall "+recall[i]+" "+emotions[i]);
					System.out.println("precision "+precision[i]+" "+emotions[i]);
				}
    	    	System.out.println("accuracy "+accuracy+" totalcount "+totalcount);
           }


}

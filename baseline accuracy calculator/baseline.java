import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


public class baseline {
// dictionaries created for various emotional categories
	static String [] sad = {"Unhappy", "depressed", "melancholy","sad", "unhappy", "depressed","bitter" , "dismal" , "heartbroken" , "melancholy" , "mournful" , "pessimistic" , "somber" , "sorrowful" , "sorry" , "wistful" , "bereaved" , "blue" , "cheerless" , "dejected" , "despairing" , "despondent" , "disconsolate" , "distressed" , "doleful" , "down" , "down in dumps" , "down in mouth" , "downcast" , "forlorn" , "gloomy" , "glum" , "grief-stricken" , "grieved" , "heartsick" , "heavyhearted" , "hurting" , "in doldrums" , "in grief" , "in the dumps" , "languishing" , "low" , "low-spirited" , "lugubrious" , "morbid" , "morose" , "out of sorts" , "pensive" , "sick at heart" , "troubled" , "weeping" , "woebegone" };
	static String [] emotions={"stupid","scary","amusing","sad","strange","interesting"};
	static String [] stupid = {"stupid","not intelligent", "irresponsible","dull" , "dumb" , "foolish" , "futile" , "ill-advised" , "irrelevant" , "laughable" , "ludicrous" , "naive" , "senseless" , "shortsighted" , "simple" , "trivial" , "dummy" , "loser" , "rash" , "thick" , "unintelligent" , "brainless" , "dazed" , "deficient" , "dense" , "dim" , "doltish" , "dopey" , "gullible" , "half-baked" , "half-witted" , "idiotic" , "imbecilic" , "inane" , "indiscreet" , "insensate" , "meaningless" , "mindless" , "moronic" , "nonsensical" , "obtuse" , "out to lunch" , "pointless" , "puerile" , "simpleminded" , "slow" , "sluggish" , "stolid" , "stupefied" , "thick-headed" , "unthinking" , "witless" };
	static String [] scary = {"scary","frightening", "terrifying","alarming" , "chilling" , "creepy" , "eerie" , "hairy" , "horrifying" , "intimidating" , "shocking" , "spooky" , "bloodcurdling" , "hair-raising" , "horrendous" , "spine-chilling" , "unnerving" };
	static String [] amusing = {"amusing","entertaining", "funny","campy" , "charming" , "comical" , "delightful" , "diverting" , "droll" , "engaging" , "enjoyable" , "entertaining" , "fun" , "gratifying" , "humorous" , "interesting" , "lively" , "pleasant" , "pleasing" , "camp" , "cheering" , "enchanting" , "gladdening" , "joshing" , "screaming" , "agreeable" , "boffo" , "cheerful" , "cut up" , "for grins" , "gut-busting" , "jocular" , "jokey" , "laughable" , "merry" , "priceless" , "sidesplitting" , "too funny for words" , "witty" };
	static String [] strange = {"strange","deviating", "unfamiliar","astonishing" , "bizarre" , "curious" , "different" , "extraordinary" , "fantastic" , "funny" , "new" , "odd" , "offbeat" , "outlandish" , "peculiar" , "rare" , "remarkable" , "unusual" , "weird" , "wonderful" , "aberrant" , "abnormal" , "astounding" , "atypical" , "eccentric" , "erratic" , "exceptional" , "far-out" , "idiosyncratic" , "ignorant" , "inexperienced" , "irregular" , "marvelous" , "mystifying" , "newfangled" , "oddball" , "off" , "out-of-the-way" , "perplexing" , "quaint" , "queer" , "singular" , "unaccountable" , "unaccustomed" , "uncanny" , "uncommon" , "unheard of" , "unseasoned" };
	static String [] interesting={"interesting","appealing", "entertaining","alluring" , "amusing" , "attractive" , "beautiful" , "compelling" , "curious" , "delightful" , "engaging" , "exotic" , "fascinating" , "impressive" , "intriguing" , "lovely" , "pleasing" , "provocative" , "readable" , "refreshing" , "stimulating" , "striking" , "thought-provoking" , "unusual" , "absorbing" , "affecting" , "arresting" , "captivating" , "enchanting" , "engrossing" , "enthralling" , "entrancing" , "fine" , "gripping" , "inviting" , "prepossessing" , "riveting" , "stirring" , "winning" , "charismatic" , "elegant" , "exceptional" , "gracious" , "magnetic" , "pleasurable" , "suspicious"};
	static int[] score = new int[emotions.length];
	static int accuracy=0;
	static int wrong=0;
	public static void main(String[] args) throws SQLException, IOException {
		FileReader fr=new FileReader("C:\\Users\\sai preethy\\Dropbox\\nlp\\allemotions.txt");
		BufferedReader bf=new BufferedReader(fr);
		String aLine;
		

		
			while((aLine=bf.readLine())!=null)
			{
				String arr[] = aLine.split(" ", 2);
				//target emotion
				String firstWord = arr[0];
				String theRest = arr[1];
				
				for(int i=0;i<emotions.length;i++)
				{
					
					score[i]=0;
				}
				//aLine.toLowerCase().contains(str2.toLowerCase())
				
				int outputclass=calculatescore(theRest);
				System.out.println("output emotion "+emotions[outputclass]+" actual "+firstWord);	
				if(emotions[outputclass].equalsIgnoreCase(firstWord))
				{
					accuracy++;
				}
				else
				{
					if(!firstWord.equalsIgnoreCase("hero"))
					wrong++;
				}
				System.out.println("accuracy "+accuracy+"wrong "+wrong);			

			}
			System.out.println("accuracy "+accuracy+"wrong "+wrong);			

		bf.close();
	}
	//gives emotion with highest count of occurence
	static int calculatescore(String aline)
	{
		for(int i=0;i<stupid.length;i++)
		{
			if((aline.toLowerCase().contains(stupid[i].toLowerCase())))
			{
				score[0]++;
				System.out.println("stupid "+ stupid[i].toLowerCase());
			}
		}
		for(int i=0;i<scary.length;i++)
		{
			if((aline.toLowerCase().contains(scary[i].toLowerCase())))
			{
				score[1]++;
				System.out.println("scary "+scary[i].toLowerCase());
			}
		}
		for(int i=0;i<amusing.length;i++)
		{
			if((aline.toLowerCase().contains(amusing[i].toLowerCase())))
			{
				score[2]++;
				System.out.println("amusing "+amusing[i].toLowerCase());
			}
		}
		for(int i=0;i<sad.length;i++)
		{
			if((aline.toLowerCase().contains(sad[i].toLowerCase())))
			{
				score[3]++;	
				System.out.println("sad "+sad[i].toLowerCase());
			}
		}
		for(int i=0;i<strange.length;i++)
		{
			if((aline.toLowerCase().contains(strange[i].toLowerCase())))
			{
				score[4]++;	
				System.out.println("strange "+strange[i].toLowerCase());
			}
		}
		for(int i=0;i<interesting.length;i++)
		{
			if((aline.toLowerCase().contains(interesting[i].toLowerCase())))
			{
				score[5]++;	
				System.out.println("iteresting "+interesting[i].toLowerCase());
			}
		}
		int maxindex=0;
		for(int i=0;i<emotions.length;i++)
		{
			//int max=score[0];
			if(score[i]>score[maxindex])
			{
				maxindex=i;
				//System.out.println(stupid[i].toLowerCase());
			}
			
		}
		System.out.println("max score "+score[maxindex]);
		return maxindex;
	}
	
	
}

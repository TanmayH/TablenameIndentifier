package openNLPpostagger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class bigram{
	public static ArrayList<String> Trainlist = new ArrayList<String>();
	public String[][] bigramizedWords = new String[100][1000];
	public String[] testwordbigram = new String[100];
	public String[] words = new String[100];
    public static String testword;
    public static int tracker=0, tracker1 = 0,tracker2=0;
    public int[] bigramcount = new int[100];
	public double matches = 0;
	public double denominator = 0; //This will hold the sum of the bigrams of the 2 words
	public double[] results = new double[100];

public void BiGram()
{
	    
	
	     initialize();
	     bigramize();

}
	
public void initialisenoun(String noun) 
{
	testword=noun;
}




public void getbigramready()
{
	String s = "";
    
    //System.out.println("Enter words to train:");
    //Scanner sc2 = new Scanner(System.in);
   // s = sc2.nextLine();
   // String[] words = s.split("\\s+");
    
	 
   
	 String line = null;

     try {
         // FileReader reads text files in the default encoding.
         FileReader fileReader = new FileReader("tablenames.txt");

         // Always wrap FileReader in BufferedReader.
         BufferedReader bufferedReader = new BufferedReader(fileReader);

         while((line = bufferedReader.readLine()) != null)
         {
            // System.out.println(line);
             line=line.replaceAll("`","");
             Trainlist.add(line);
         
         }   
         bufferedReader.close();         
         }
     catch(IOException ex)
     {
             System.out.println(
             "Unable to open file tablename.txt ");                
     }


    //for (int i = 0; i < words.length; i++) {
      //  Trainlist.add(words[i]);
    //}
    //System.out.println("done");
//     System.out.println(Trainlist.size());
//    for (int i = 0; i < Trainlist.size(); i++) {
//       
//    	System.out.println(Trainlist.get(i));
//    }
   // System.out.println("done");
}

public void initialize()
{
	
	/*(Convert all words to bigram*/

   	   for (int i = 0; i < Trainlist.size(); i++) {

           String gram  = Trainlist.get(i);

         //pw1.println(gram);    -----you had this originally, we don't need this
         int line = gram.length(); 

         for(int x=0;x<=line-2;x++)
         {
            bigramizedWords[tracker][x] = gram.substring(x, x+2);
          //  System.out.println(gram.substring(x, x+2) + "");  
         }

         //System.out.println("");

         words[tracker] = gram;
         tracker++;  
        
        //Store the number of bigrams for a given word in a array
        bigramcount[i]= line-1;

      }
   	  
   	   /*convert testword to bigram */

     //pw1.println(gram);    -----you had this originally, we don't need this
      int line = testword.length(); 
     
   //   System.out.println(line);

      for(int x=0;x<=line-2;x++)
      { 

        testwordbigram[x] = testword.substring(x, x+2);
        //System.out.println(testwordbigram[x] + "");

       tracker2++;
      }

     // System.out.println("");
    
     //words[tracker] = gram;
}


public void bigramize()
{

	double max=0;
	int id=-1;
	for (int i = 0; i < Trainlist.size(); i++)
	{
  
		matches =0;
		denominator=0;
   
   
   for(int k = 0; k < bigramizedWords[i].length; k++)
   {
      if(bigramizedWords[i][k] != null)
      {


         for(int j = 0; j < testwordbigram.length; j++)
         {

            if(testwordbigram[j] != null)
            {

               if(bigramizedWords[i][k].equals(testwordbigram[j]))
               {

                  matches++;

               }

            }        

         }

      } 

   }
   matches*=2;
   //System.out.printf("matches= %d,%d",bigramcount[i],tracker2);
   denominator = bigramcount[i] + tracker2;
   results[i] = matches/denominator;
  // System.out.printf("matches= %f,%f",matches,denominator);
  // System.out.println("\n\nThe Bigram Similarity value between " + words[i] + " and " + testword + " is " + results[i]  + ".");
   if(results[i]>max)
   {
	   max=results[i]; 
	   id=i;
   }
  }
	if(id!=-1&&results[id]>0.50)
	{
		System.out.println("The table is :" + words[id]);
	}
	
}
 }




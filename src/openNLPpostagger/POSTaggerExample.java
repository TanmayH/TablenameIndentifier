package openNLPpostagger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import java.util.*; 

public class POSTaggerExample {
	 public static  String tablename="";
	 static HashMap<String,ArrayList<String>> map=new HashMap<>();	
	// static HashMap<String,ArrayList<String>> tab_colnames=new HashMap<>();
	 
		 
	public static void mapper(String mapkey,String Item)
	{
		ArrayList<String> itemlist=map.get(mapkey);
		if(itemlist==null)
		{
			itemlist=new ArrayList<String>();
			itemlist.add(Item);
			map.put(mapkey, itemlist);
			
		}
		else
		{
			if(!itemlist.contains(Item))
			{
				itemlist.add(Item);
			}
		}
		
	}
	
	public static void main(String[] args) 
	{	
        InputStream tokenModelIn = null;
        InputStream posModelIn = null;
        
        try {
           
        	String sentence = "customers from New York";
            tokenModelIn = new FileInputStream("en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String tokens[] = tokenizer.tokenize(sentence);
 
            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream
            posModelIn = new FileInputStream("en-pos-maxent.bin");
            // loading the parts-of-speech model from stream
            POSModel posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model 
            POSTaggerME posTagger = new POSTaggerME(posModel);
            // Tagger tagging the tokens
            String tags[] = posTagger.tag(tokens);
            // Getting the probabilities of the tags given to the tokens
            double probs[] = posTagger.probs();
            
            System.out.println("Token\t:\tTag\t:\tProbability\n---------------------------------------------");
            for(int i=0;i<tokens.length;i++)
            {
                System.out.println(tokens[i]+"\t:\t"+tags[i]+"\t:\t"+probs[i]);
            }
            System.out.println("\n\n");  
            
            for(int i=0;i<tags.length;i++)
            {
            	if(tags[i].matches("NNS")||tags[i].matches("NNP")||tags[i].matches("NNPS"))
            	{
            		tags[i]="NN";
            	}
            	if(tags[i].matches("VBD")||tags[i].matches("VBG")||tags[i].matches("VBN")||tags[i].matches("VBP")||tags[i].matches("VBZ"))
            	{
            		tags[i]="VB";
            	}
            	mapper(tags[i],tokens[i]);
            }
            
      ArrayList<String> nounlist=map.get("NN");
      ArrayList<String> tablename=new ArrayList<String>();
      ArrayList<String> colname=new ArrayList<String>();
//      String tablename1="";
      
 //     System.out.println("The nouns in the sentence are:");
//            for(String noun:nounlist)
//            {
//          //  	System.out.println(noun);    
//            }
            bigram b=new bigram();
             b.getbigramready();
             
           for(int i=0;i<nounlist.size();i++) 
          {
        	  b.initialisenoun(nounlist.get(i));
        	  b.BiGram();
           }
           if(!b.tablename.matches(""))
           System.out.println("The table name is "+b.tablename);
          }      
        catch (IOException e)
        {

        }
            
        }
    }


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main { 
      
    // Alphabet size (# of symbols) 
    static final int ALPHABET_SIZE = 300; 
      
    // trie node 
    static class TrieNode 
    { 
        TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 
       
        // isEndOfWord is true if the node represents 
        // end of a word 
        boolean isEndOfWord; 
          
        TrieNode(){ 
            isEndOfWord = false; 
            for (int i = 0; i < ALPHABET_SIZE; i++) 
                children[i] = null; 
        } 
    }; 
       
    static TrieNode root;  
      
    // If not present, inserts key into trie 
    // If the key is prefix of trie node,  
    // just marks leaf node 
    static void insert(String key) 
    { 
        int level; 
        int length = key.length(); 
        int index; 
       
        TrieNode pCrawl = root; 
       
        for (level = 0; level < length; level++) 
        { 
            index = key.charAt(level) - 'a'; 
            if (pCrawl.children[index] == null) 
                pCrawl.children[index] = new TrieNode(); 
       
            pCrawl = pCrawl.children[index]; 
        } 
       
        // mark last node as leaf 
        pCrawl.isEndOfWord = true; 
    } 
       
    // Returns true if key presents in trie, else false 
    static boolean search(String key) 
    { 
        int level; 
        int length = key.length(); 
        int index; 
        TrieNode pCrawl = root; 
       
        for (level = 0; level < length; level++) 
        { 
            index = key.charAt(level) - 'a'; 
       
            if (pCrawl.children[index] == null) 
                return false; 
       
            pCrawl = pCrawl.children[index]; 
        } 
       
        return (pCrawl != null && pCrawl.isEndOfWord); 
    } 
       
    public static void main(String[]args) {
    	String text_dosyasi=args[1];
		//Trie_Imp trie_Imp=new Trie_Imp();
		root = new TrieNode(); 
		ArrayList<String> dict_list=new ArrayList<>();
		Scanner k=new Scanner(System.in);
		try {
			Scanner scanner=new Scanner(new File(text_dosyasi));
			
			while (scanner.hasNextLine()) {
				String input="";
				input=scanner.nextLine();
				//System.out.println(input);
				dict_list.add(input.toLowerCase());
			}
			
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<dict_list.size();i++) {
			insert(dict_list.get(i));
		}
		
		String girdi=k.nextLine();
		String kelime="";
		int sayac=0;
		for(int j=0;j<girdi.length();j++) {
			kelime+=girdi.charAt(j)+"";
			if(search(kelime)) {
				System.out.println(kelime);
				sayac++;
			}
			
		}
		if(!(search(kelime)) && sayac==0) {
			System.out.println("Mevcut degil.");
		}
		
	}
} 
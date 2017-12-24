import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BUfferedReader;

public final class WordNet {
    private Sap sap;
    private Map<String, List<Integer>> idsOfWord;
    private Map<Integer, String> wordsOfId;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        idsOfWord = new HashMap<String, List<Intger>>();
        wordsOfId = new HashMap<Integer, String>();
        Scanner file_syn = null;
        Scanner file_hyper = null;
        try{
            file_syn = new Scanner(new BufferedReader(new FileReader(synsets)));
            int V = 0;
            while(file_syn.hasNext()){
                V++;
                String line = file_syn.nextLine();
                String[] substrings = line.split(",");
                int id = Integer.parseInt(substrings[0]);
                String[] splittedWords = items[1].split(" "); 
                for(int i = 0; i < splittedWords.length(); i++){
                    if(idsOfWord.containsKey(splittedWords[i])){
                        idsOfWord.get(splittedWords[i]).add(id);
                    }
                    else{
                        List<Integer> arrayList = new ArrayList<Integer>();
                        arrayList.add(id);
                        idsOfWord.put(splittedWords[i], arrayList);
                    }
                    wordsOfId.put(id, splittedWords[1]);
                }
            }
            Digraph G = new Digraph(V + 1);
            file_hyper = new Scanner(new BufferedReader(new FileReader(hypernyms)));
            while(file_hyper.hasNext()){
                String line = file_hyper.nextLine();
                String[] substrings = line.split(",");
                 int v = Integer.parseInt(items[0]);
                 for(int i = 1; i < substrings.length; i++){
                    int w = Integer.parseInt(substrings[i]);
                    G.addEdge(v, w);
                 }
            }
            sap = new SAP(G;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
    // is the word a WordNet noun? This can be used to search for existing
    // nouns at the beginning of the printSap method
    public boolean isNoun(String word) {
        if(idsOfWord.containsKey(word)){
            return true;
        }else{
            return false;
        }
    }
    // print the synset (second field of synsets.txt) that is the common ancestor
    // of nounA and nounB in a shortest ancestral path as well as the length of the path,
    // following this format: "sap<space>=<space><number>,<space>ancestor<space>=<space><synsettext>"
    // If no such path exists the sap should contain -1 and ancestor should say "null"
    // This method should use the previously defined SAP datatype
    public void printSap(String nounA, String nounB) {
        if(!isNoun(nounA)||!isNoun(nounB)){
            System.out.println("sap = -1, ancestor = null");
            return;           
         }
        
        List<Integer> list_v = idsOfWord.get(nounA);
        List<Integer> list_w = idsOfWord.get(nounB);
        int ancestor = -1;
        int length = Integer.MAX_VALUE;
        for(int v : list_v){
            for(int w : list_w){
                int j = sap.length(v, w);
                if(j == -1){
                    continue;
                }
                if(j < length){
                    length = j;
                    ancestor = sap.ancestor(v, w);
                }
            }
        }

        if(length == Integer.MAX_VALUE){
            System.out.println("sap = -1, ancestor = null");
            return;
        }
        System.out.println("sap = "+len+", ancestor = "+ wordsOfId.get(ancestor));
    }

    
    public static void main(String[] args){
        String syn = args[0];
        String hyper = args[1];
        String input = args[2];
        WordNet wordnet = new WordNet(syn, hyper);
        Scanner in = null;
        try{
            in = new Scanner(new BufferedReader(new FileReader(in)));
            while(in.hasNext()){
                String[] substrings = in.nextLine().split(" ");
                wordnet.printSap(items[0], substrings[1]);
            } 
        } catch (FileNotFoundException e){
                e.printStackTrace();
        }
    }
}

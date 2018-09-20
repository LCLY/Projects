import java.util.*;
import java.io.*; 

public class hw1{

    public static void main(String[] args) throws Exception  {
        FileWriter writer = new FileWriter("input.txt");  
        BufferedWriter buffer = new BufferedWriter(writer);  
        buffer.write("Welcome to javaTpoint.");  
        buffer.close();  
        System.out.println("Success");  

        FileReader fr=new FileReader("input.txt");    
        BufferedReader br=new BufferedReader(fr);    

        int i;    
        while((i=br.read())!=-1){  
        System.out.print((char)i);  
        }  
        System.out.println();
        br.close();    
        fr.close();        
    }

}
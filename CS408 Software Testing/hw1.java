import java.util.*;
import java.io.*; 

public class hw1{
    
    public static void write() throws Exception{
        FileWriter writer = new FileWriter("input.txt");  
        BufferedWriter buffer = new BufferedWriter(writer);  
        buffer.write("Welcome to javaTpoint.");  
        buffer.close();  
    }

    public static void read() throws Exception{
        FileReader fr = new FileReader("input.txt");    
        BufferedReader br = new BufferedReader(fr);   
        String line = "";    
        while((line=br.readLine())!= null){  
            System.out.println("The printed line is: "+line);
        }  

        br.close();    
        fr.close();      
    }
    public static void main(String[] args) throws Exception  {
      write();
      read();
        
    }

}
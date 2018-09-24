import java.util.*;
import java.io.*; 

public class hw1{
    public static void main(String[] args) throws Exception  {    
    /* ---------- READ ---------- */
    int N = 0;
    String[] firstLine = new String[3];
    String[][] data = new String[0][0]; //store 2d information, [1][0] = 1, [1][1] = S
    String[] temp = new String[2];
    FileReader fr = new FileReader("input.txt");    
    BufferedReader br = new BufferedReader(fr);   
    String line = "";
    int i = 0;
    int flag = 0;
    int midpoint;
    int A = 0;
    int B = 0;
    int swordCount = 0;

    while((line = br.readLine()) != null){  
        if(flag == 0){
            firstLine = line.split(" ");
            N = Integer.parseInt(firstLine[0]);
            A = Integer.parseInt(firstLine[1]);
            B = Integer.parseInt(firstLine[2]);
            data = new String[N][2];
            System.out.println("N is: "+ N);
            flag = 1;
        }else{
            //storing element into new 2d array
            temp = line.split(" ");
            data[i][0] = temp[1];
            data[i][1] = temp[0];
            i++;
        }
      
    }      

    br.close();    
    fr.close();  

    // System.out.println("BEFORE");
    // for(int j = 0; j < data.length; j++){
    //     System.out.println(data[j][0] + " " + data[j][1]);
    // }

    //sort array
    java.util.Arrays.sort(data, new Comparator <String[]>(){
        @Override
        public int compare(final String[] entry1, String[] entry2){
            if(Integer.parseInt(entry1[0]) > Integer.parseInt(entry2[0])){
                return 1;
            }else{
                return -1;
            }
        }
    });

    // System.out.println("AFTER");
    // for(int j = 0; j < data.length; j++){
    //     System.out.println(data[j][0] + " " + data[j][1]);
    // }
    int count = 0;
    int count2 = 1;
    int value1 = 0;
    int value2 = 0;    
    //check if first friend is same as A
    
    System.out.println("data[0][0] is: " + data[0][0]);
    //N = 1, A = 1, B = 1
    if(data.length == 1 && A == 1 && B == 1){
        if(data[0][1].equals("S")){
            swordCount = 1;
        }
    }

    // while(Integer.parseInt(data[1][0]) <= data.length){
    //     //in range
    //     value1 = Integer.parseInt(data[count][0]);
    //     value2 = Integer.parseInt(data[count2][0]);
    //     System.out.println("value 1 is " + value1 + "value 2 is " + value2);
    

    //     if(data[count][1] == data[count+1][1]){ //compare sword
    //         swordCount += (value2 - value1 + 1);
    //     }   
    //     count++;
    //     count2++;
    // }

    int min = Integer.parseInt(data[0][0]);
    int max = Integer.parseInt(data[N - 1][0]);
    int midpoint = 0;
    int mod = 0;
    for(int i = 0; i < data.length - 1; i++){
        value1 = Integer.parseInt(data[count][1]);
        value2 = Integer.parseInt(data[count + 1][1]);
    
        if(min < A){
            if(data[count][1].equals("S") && (data[count + 1][1]).equals("S")){
              swordCount += Integer.parseInt(data[count][0]) - Integer.parseInt(data[count+1][0]) + 1;
            }else if(data[count][1].equals("S") && data[count+1][1].equals("NS")){
              midpoint = (value1 + value2) / 2;
              if(value2 - midpoint > midpoint - value1){
                  swordCount += Integer.parseInt(data[count][0]);
              }else if(){
                  
              }
            }else if(data[count][1].equals("NS") && data[count+1][1].equals("S")){
              midpoint = ()
            }
          
          }else if(A < min && B <= max){
      
          }else if(min < A && max < B){
      
          }else if(A < min && max < B){
      
          }
    }

   


    // System.err.println("SWORD IS NOW: " + swordCount);




        
    /* ---------- WRITE ---------- */
        // FileWriter writer = new FileWriter("input.txt");  
        // BufferedWriter buffer = new BufferedWriter(writer); 
        // buffer.close();  
    }

}
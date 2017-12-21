import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by LeyYen on 4/18/2016.
 */
public class Stats {

    public static HashMap wins(String filename) throws IOException {
        File f = new File(filename);
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        boolean valid = true;

        while (valid) {
            String s = bfr.readLine();
            if (s != null) {
                if (s.substring(0, 1).equals("0")) {
                    String[] string = s.substring(2, s.indexOf(".") - 3).split(" ");
                    for (int i = 0; i < string.length; i++) {
                        if (map.get(string[i]) == null) {
                            map.put(string[i], 1);
                        } else {
                            int temp = map.get(string[i]);
                            map.remove(string[i]);
                            map.put(string[i], temp + 1);
                        }
                    }
                } else {
                    String[] string2 = s.substring(s.indexOf(".") + 2).split(" ");
                    for (int i = 0; i < string2.length; i++) {
                        if (map.get(string2[i]) == null) {
                            map.put(string2[i], 1);
                        } else {
                            int temp = map.get(string2[i]);
                            map.remove(string2[i]);
                            map.put(string2[i], temp + 1);
                        }
                    }
                }
            } else {
                valid = false;
                bfr.close();
            }
        }
        return map;
    }

    public static String winner(HashMap map) {

        Set set = map.entrySet();
        Iterator i = set.iterator();
        int largest = 0;
        String result = "";
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            if ((int) me.getValue() > largest) {
                largest = (int) me.getValue();
                result = (String) me.getKey() + "\n";

            } else if ((int) me.getValue() == largest) {
                result += (String) me.getKey() + "\n";
            }

        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Stats s = new Stats();
        System.out.print(s.winner(s.wins("/homes/lchoo/cs180/inputfile.txt")));

    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    String filePath;
    int queriesNumber;
    String userName;

    public void parse(String filePath) throws WrongFileFormatException, WrongNumberOfQueriesException
            , InvalidInputException, MalformedQueryException, IOException {
        this.filePath = filePath;
        File f = new File(filePath);
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        ArrayList<String> list = new ArrayList<>();
        int queriesNumber;
        while (true) {
            String s = bfr.readLine();
            list.add(s);
            if (s == null)
                break;
        }
        bfr.close();
        this.userName = list.get(1);
        /**
         * WrongFileFormatException
         */

        if (!list.get(0).equals("C") || !list.get(2).equals("c") || !list.get(3).equals("N") || !list.get(5).equals("n")
                || !list.get(6).equals("Q") || !list.get((list.size() - 2)).equals("q")) {
            throw new WrongFileFormatException("WrongFileFormatException");
        }

        /**
         * InvalidInputException
         */
        try {
            queriesNumber = Integer.parseInt(list.get(4));
            this.queriesNumber = queriesNumber;
        } catch (
                NumberFormatException e) {
            throw new InvalidInputException("InvalidInputException");
        }
        if (queriesNumber < 1) {
            throw new InvalidInputException("InvalidInputException");
        }

        /**
         * WrongNumberOfQueriesException
         */
        if (list.size() - 9 != queriesNumber) {
            throw new WrongNumberOfQueriesException("WrongNumberOfQueriesException");
        }

        /**
         * MalformedQueryException
         */
        for (int i = 7; i < list.size() - 2; i++) {
            if (!list.get(i).contains("SELECT") && !list.get(i).contains("UPDATE") && !list.get(i).contains("INSERT")
                    && !list.get(i).contains("DELETE")) {
                throw new MalformedQueryException("MalformedQueryException");
            }
        }
    }


    public String getUserName() {

        return this.userName;
    }

    public int getNumQueries() {

        return queriesNumber;
    }
}

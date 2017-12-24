import java.io.*;
import java.util.Scanner;

import cs180.net.Socket;
import cs180.net.ServerSocket;

public class OnlineEmailServer extends EmailServer {
    private ServerSocket serverSocket;
    private PrintWriter pw;
    private Socket client;

    public OnlineEmailServer(String filename, int port) throws IOException {
        new EmailServer(filename);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
    }


    @Override
    public void run() {
        client = null;
        try {
            while (true) {
                this.client = this.serverSocket.accept();
                this.client.setSoTimeout(60000);
                System.out.println("Accepted");
                this.client.setReuseAddress(true);
                processClient(this.client);
                stop();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void processClient(Socket client) throws IOException {
        this.client.setSoTimeout(60000);
        System.out.println("1");
        Scanner s = new Scanner(client.getInputStream());
        System.out.println("2");
        while (s.hasNextLine()) {
            System.out.println("Processing");
            pw = new PrintWriter(client.getOutputStream());
            String read = s.nextLine();
            System.out.println("Receiving: " + read);
            String[] readArray = read.split("[\t\r\n]");
            if (User.checkBoth(readArray[1], readArray[2])) {
                switch (readArray[0]) {
                    case "ADD-USER":
                        pw.printf(addUser(readArray));
                        pw.flush();
                        break;
                    case "DELETE-USER":
                        pw.printf(deleteUser(readArray));
                        pw.flush();
                        break;
                    case "GET-ALL-USERS":
                        pw.printf(getAllUsers(readArray));
                        pw.flush();
                        break;
                    case "SEND-EMAILS":
                        pw.printf(sendEmail(readArray));
                        pw.flush();
                        break;
                    case "GET-EMAILS":
                        pw.printf(getEmails(readArray));
                        pw.flush();
                        break;
                    case "DELETE-EMAIL":
                        pw.printf(deleteEmail(readArray));
                        pw.flush();
                        break;
                }

            }
        }
        s.close();
    }

    public void stop() {
        try {
            pw.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            OnlineEmailServer oms = new OnlineEmailServer("test.csv", 1234);
            oms.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
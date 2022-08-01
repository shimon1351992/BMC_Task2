import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {
        InetAddress add = InetAddress.getLocalHost();
        System.out.print(add);
        ServerSocket ss = new ServerSocket(6666);
        Socket s = ss.accept(); //conection is accepted

        DataInputStream dis = new DataInputStream(s.getInputStream()); //get the data
        String str = (String) dis.readUTF();

        // get the folders and file names from the root directory the str and add the to message arry
        File folder = new File(str);
        File[] listOfFiles = folder.listFiles();
        String[] message = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                message[i] = listOfFiles[i].getName();
            } else if (listOfFiles[i].isDirectory()) {
                message[i] = listOfFiles[i].getName();
            }
        }
        // send back the message to the client
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        out.writeObject(message);
        ss.close();
    }
}

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //args[0] hold the ip from jenkinsfile and create socket
        Socket s = new Socket(args[0],6666);

        // args[1] hold the path for directory from jenkinsfile and  send to server
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF(args[1]);

        // get the message from server
        ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
        String[] arrt = (String[]) objectInputStream.readObject();

        for(int i =0; i< arrt.length; i++){
            System.out.println(arrt[i]);
        }
        dout.flush();
        dout.close();
        s.close();
    }
}

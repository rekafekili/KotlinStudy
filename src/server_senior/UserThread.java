package server_senior;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread extends Thread {
    private Socket connection;
    private InputStream inStream;
    private OutputStream outStream;;
    private BufferedReader input;
    private PrintWriter output;
    private boolean keepListening;

    public UserThread(Socket s){
        // initialize variables
        connection = s;
        keepListening = true;

        // get input and output streams
        try {
            inStream = connection.getInputStream();
            input = new BufferedReader(new InputStreamReader(inStream));
            outStream = connection.getOutputStream();
            output = new PrintWriter(new OutputStreamWriter(outStream));
        }
        catch ( IOException e ) {
            e.printStackTrace();
            System.exit( 1 );
        }
    }
    public void stopThread() {
        this.interrupt();
    }

    public void run(){
        try {
            while ( keepListening ) {
                String message = input.readLine( );
                System.out.println(message);
                System.out.println("End of Input");
                if(message != null) {
                    String[] messplit = message.split(",");
                    if(messplit[0].equals("getServiceID")) {
                        output.println("Apple_cooking,Samsung_cooking,Apple_guitar");
                        output.flush();
                    }if(messplit[0].equals("getlist")) {
                        output.println("{\"data\" : [{\"url\" : \"https://www.youtube.com/watch?v=NuZqsKzYM08\",\"title\" : \"면역력과 뼈건강을 지켜요 양배추바나나우유\" ,\"author\" : \"만개의레시피\"},{\"url\" : \"https://www.youtube.com/watch?v=XrPfSyByGH8\",\"title\" : \"2썸에서 파는 오레오아이스박스케이크! 노오븐으로 집에서 쉽게 디저트 만들기\" ,\"author\" : \"만개의레시피\"},{\"url\" : \"https://www.youtube.com/watch?v=JylSaWiNSqY\",\"title\" : \"두부조림 맛있게 만드는 방법 대공개! 두부조림레시피 5가지\" ,\"author\" : \"만개의레시피\"},{\"url\" : \"https://www.youtube.com/watch?v=zRpU7QS083g\",\"title\" : \"주꾸미볶음 매워서 싫다면 바로 확인! 간장쭈꾸미볶음\" ,\"author\" : \"만개의레시피\"}]}");
                        output.flush();
                    }if(messplit[0].equals("getmeta")) {
                        output.flush();
                    }if(messplit[0].equals("end")) {
                        keepListening = false;
                    }
                }else {
                    keepListening = false;
                }
            }
            inStream.close();
            outStream.close();
            connection.close();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        stopThread();
    }
}

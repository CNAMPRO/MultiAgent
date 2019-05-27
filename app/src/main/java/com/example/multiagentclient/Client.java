package com.example.multiagentclient;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class Client {

    public static final String TAG = Client.class.getSimpleName();
    public static final String SERVER_IP = "192.168.43.146"; //server IP address  193.49.96.14
    public static final int SERVER_PORT = 40000;
    // message to send to the server
    private String mServerMessage;
    // sends message received notifications
    private OnMessageReceived mMessageListener = null;
    // while this is true, the server will continue running
    private boolean mRun = false;
    // used to send messages
    private PrintWriter mBufferOut;
    // used to read messages from the server
    private BufferedReader mBufferIn;

    /**
     * Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public Client(/*OnMessageReceived listener*/) {
       // mMessageListener = listener;
    }

    /**
     * Sends the message entered by client to the server
     *
     * //@param mmessage text entered by client
     */
    public void sendNid( CBase nid) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               // if (mBufferOut != null) {
                //    Log.d(TAG, "Sending: " + message);
                  //  mBufferOut.println(message);
                   // mBufferOut.flush();
                //}
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private CEnvironement readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
        in.defaultReadObject();
        CEnvironement env = (CEnvironement)in.readObject();
        return env ;
    }

    public void sendObject(CBase object,OutputStream outputStream ) throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        System.out.println("Closing socket and terminating program.");
    }

    /**
     * Close the connection and release the members
     */
    public void stopClient() {

        mRun = false;

        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }

        mMessageListener = null;
        mBufferIn = null;
        mBufferOut = null;
        mServerMessage = null;
    }

    public void run() {

        mRun = true;

        try {
            //here you must put your computer's IP address.
            //InetAddress serverAddr = InetAddress.getByName(SERVER_IP);



            //create a socket to make the connection with the server
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);



            try {

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String message = dataInputStream.readUTF();

                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(message);



                //DataOutputStream outputTrame = new DataOutputStream(socket.getOutputStream());

                Log.d("RESPONSE FROM SERVER", "S: Received Message: '" + mServerMessage + "'");

            } catch (Exception e) {
                Log.e("TCP", "S: Error", e);
            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                socket.close();
            }

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }

    }

    //Declare the interface
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }

}
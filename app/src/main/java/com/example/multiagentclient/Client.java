package com.example.multiagentclient;
// A Java program for a Client
import java.net.*;
import java.io.*;

public class Client
{
    // init
    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream outputTram	 = null;

    public Client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String msg = dis.readUTF();

            System.out.println(msg);

            input = new DataInputStream(System.in);
            outputTram = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        String line = "";

        while (!line.equals("Fin"))
        {
            try
            { 	//line = input.readUTF();
                line = input.readLine();
                outputTram.writeUTF(line);
                //System.out.println("ouai");

            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        try
        {
            input.close();
            outputTram.close();
            socket.close();
            System.exit(0);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    //public static void main(String args[])
    //{
    //ClientDuDu client = new ClientDuDu("localhost", 40000);
    //}
}

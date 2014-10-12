/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.hackzurich.mongoose.boundary;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bogdanpetcu
 */
public class FileReceiver implements Runnable {

    public void run() {
        try {
            ServerSocket serverSocket = null;

            serverSocket = new ServerSocket(4444);

            Socket socket = null;
            InputStream is = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            int bufferSize = 0;

            socket = serverSocket.accept();
            
            is = socket.getInputStream();

            bufferSize = socket.getReceiveBufferSize();
            System.out.println("Buffer size: " + bufferSize);

            fos = new FileOutputStream("/home/bogdanpetcu/received.png");
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[bufferSize];

            int count;

            while ((count = is.read(bytes)) > 0) {
                bos.write(bytes, 0, count);
            }

            bos.flush();
            bos.close();
            is.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            
        }
    }
}

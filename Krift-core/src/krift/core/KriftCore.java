/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import krift.core.skeleton.Skeleton;

/**
 *
 * @author aluno
 */
public class KriftCore {

    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(2223);
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("STUFF");
                Skeleton skeleton = new Skeleton(socket);
                Thread t = new Thread(skeleton);
                t.start();
            }
        } catch (Exception e) {
            if (server != null) {
                server.close();
            }
            e.printStackTrace();
        }
    }
}

package fr.epsi.ServerThread;

import fr.epsi.Utils.AbstractLogger;

import java.net.Socket;

public class ClientThread implements Runnable{
    private String username;
    private Socket socket;
    private boolean stop;

    public ClientThread(String username, Socket socket){
        this.socket = socket;
        this.username = username;
        this.stop = false;
    }

    public void run(){
        helloImANewClient();
    }

    private void helloImANewClient(){
        AbstractLogger.log("Salut je suis un nouveau client authentifiée et mon nom est : " + username);
    }
}
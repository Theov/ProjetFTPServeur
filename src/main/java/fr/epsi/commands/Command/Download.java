package fr.epsi.commands.Command;

import fr.epsi.commands.Core.CommandData;
import fr.epsi.commands.Core.MasterCommand;
import fr.epsi.utils.AbstractLogger;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Download extends MasterCommand{
    DataInputStream din;
    DataOutputStream dout;

    public Download(CommandData p_commandData) {
        super(p_commandData);
        try {
            din=new DataInputStream(clientSocket.getInputStream());
            dout=new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execCommand(){
        AbstractLogger.log("Transfert entrant en cours");
        if(destinationDirectory().exists()){
            FileOutputStream fout= null;
            AbstractLogger.log("envoi du fichier " + destinationDirectory().toString() + " en cours");

            try {
                fout = new FileOutputStream(destinationDirectory);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                IOUtils.copy(clientSocket().getInputStream(),fout);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        isExecuted = true;
    }

    @Override
    public String result(){
        return "download : OK";
    }
}
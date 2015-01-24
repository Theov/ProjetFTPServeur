package fr.epsi.commands.Command;

import fr.epsi.commands.Core.CommandData;
import fr.epsi.commands.Core.MasterCommand;

import java.io.*;

public class Ls extends MasterCommand {
    private File[] filesList;
    private final String prefixAnswer = "ls:";

    public Ls(CommandData p_commandData){
        super(p_commandData);
    }

    @Override
    public void execCommand() {
        this.filesList = sourceDirectory().listFiles();
    }

    @Override
    public void sendResultToClient() {
        try {
            PrintWriter clientSocketOutput = new PrintWriter(clientSocket.getOutputStream());
            clientSocketOutput.println(prefixAnswer + filesList.length);
            clientSocketOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectOutput out = null;

        try {
            for (int i = 0; i < filesList.length; ++i){
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(filesList[i]);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

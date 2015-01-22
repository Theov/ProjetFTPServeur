package fr.epsi.commands;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MasterCommand implements ICommand{
    protected Path sourcePath;
    protected Path destinationPath;

    protected File sourceDirectory;
    protected File destinationDirectory;

    protected Socket clientSocket;

    protected CommandData commandData;
    protected String parameter;

    public MasterCommand(CommandData p_commandData){
        this.clientSocket = p_commandData.clientSocket();
        this.commandData = p_commandData;

        setSourcePath(p_commandData.locationOfTheClientOnTheServer());
        setDesinationPath(p_commandData.locationOfTheClientOnTheServer() + "/" + p_commandData.commandParameter());
    }

    @Override
    public CommandData commandData(){
        return this.commandData;
    }

    @Override
     public void setSourcePath(String path){
        this.sourcePath = Paths.get(path);
        setSourceDirectory();
    }

    @Override
    public String clientLocationAfterCommandExectution(){
        return commandData.locationOfTheClientOnTheServer();
    }

    @Override
    public void setDesinationPath(String destinationPath){
        this.destinationPath = Paths.get(destinationPath);
        setDestinationDirectory();
    }

    @Override
    public void setParameters(){
        parameter = this.commandData.commandParameter();
    }

    @Override
    public void execCommand() {
    }

    @Override
    public byte[] result() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;

        out = new ObjectOutputStream(bos);
        out.writeObject("unknow-request");
        byte[] arrayBytes = bos.toByteArray();
        out.close();

        return arrayBytes;
    }

    @Override
    public void sendResultToClient(){
        try {
            PrintWriter clientSocketOutput = new PrintWriter(clientSocket.getOutputStream());
            clientSocketOutput.println(result());
            clientSocketOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSourceDirectory(){
        sourceDirectory = new File(sourcePath.toString());
    }

    public void setDestinationDirectory(){
        destinationDirectory = new File(destinationPath.toString());
    }

    public File sourceDirectory(){
        return sourceDirectory;
    }

    public Socket clientSocket(){
        return clientSocket;
    }
}

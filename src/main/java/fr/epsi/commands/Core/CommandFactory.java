package fr.epsi.commands.Core;

import fr.epsi.commands.Command.*;
import fr.epsi.commands.Command.Error;

public class CommandFactory {
    public static ICommand createCommand(CommandData commandData){
        ICommand command;

        if(commandData.commandType().equals("ls")){
            command = new Ls(commandData);
        }else if(commandData.commandType().equals("rm")){
            command = new Rm(commandData);
        }else if(commandData.commandType().equals("mkdir")){
            command = new Mkdir(commandData);
        }else if(commandData.commandType().equals("mv")){
            command = new Mv(commandData);
        }else if(commandData.commandType().equals("copy")){
            command = new Copy(commandData);
        }else{
            command = new Error(commandData);
        }

        return command;
    }
}

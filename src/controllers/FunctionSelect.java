package controllers;

import models.FileManager;
import models.NoteCreate;
import view.UI;

import java.nio.file.Path;

public class FunctionSelect {

    public void select()
    {
        //Create FileManager object
        FileManager fileManager = new FileManager();
        UI ui = new UI();
        boolean run = true;



        //Data check and import
        if(fileManager.fileValidity() == -1){
            return;
        }

        //Full file display
        System.out.println("Checked and/or created");

        while(run)
        {
            fileManager.updateFileList();

            for(Path path : fileManager.allFileList){
                System.out.println(fileManager.allFileList);
            }

            String fullNote = "";
            NoteCreate noteCreate = new NoteCreate();
            int identifier = ui.homePage();

            if(identifier == 1)
            {
                fullNote = noteCreate.buildNote(-1);
            }
            else if(identifier == 2)
            {
                noteCreate.listNote();
            }
            else if(identifier == 3)
            {
                ui.printSomething(fileManager.getFileContents(ui.fileSelect()));
            }
            else if(identifier == 4)
            {
                int file = ui.fileSelect();
                ui.printSomething((fileManager.getFileContents(file)));
                fullNote = noteCreate.buildNote(file);
                fileManager.CreateFile(fullNote, file);
            }
            else if(identifier == 5)
            {
                return;
            }
        }
    }

    public boolean isNumber(char a)
    {
        boolean number = false;
        number = Character.isDigit(a);
        return number;
    }

}

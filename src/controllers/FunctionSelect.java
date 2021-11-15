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



        //Data check and import
        if(fileManager.fileValidity() == -1){
            return;
        }

        //Full file display
        System.out.println("Checked and/or created");

        fileManager.updateFileList();

        for(Path path : fileManager.allFileList){
            System.out.println(fileManager.allFileList);
        }

        //1. new note
        //2. list note
        //3. view note
        //4. edit note
        //5. quit

        String fullNote = "";
        NoteCreate noteCreate = new NoteCreate();
        int identifier = ui.homePage();

        if(identifier == 1)
        {
            fullNote = noteCreate.buildNote();
        }
    }

}

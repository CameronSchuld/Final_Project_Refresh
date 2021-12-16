package models;

import java.io.File;
import java.util.Scanner;
import view.UI;
import models.FileManager;

public class NoteCreate {

    public String buildNote(){
        String noteString = "";
        FileManager fileManager = new FileManager();

        fileManager.CreateFile(noteBody());





        return noteString;
    }

    public String noteBody(){
        UI ui = new UI();
        String bodyText = "";
        String lineText = "";

        while(true)
        {
            ui.textEdit(lineText, bodyText);

            Scanner input = new Scanner(System.in);

            lineText = input.nextLine();

            if(lineText == "")
            {
                //ui.textEditSelect();
                return bodyText;
            }
            else
            {
                bodyText = bodyText + "\n" + lineText;
            }
        }



    }


}

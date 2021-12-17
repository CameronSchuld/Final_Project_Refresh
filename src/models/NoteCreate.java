package models;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import view.UI;
import java.text.SimpleDateFormat;
import models.FileManager;

public class NoteCreate {

    public String dataFormat(String noteName)
    {
        String fullDataString = "/[";

        fullDataString += noteName;
        fullDataString += "]\\";
        fullDataString += "\n";

        return fullDataString;
    }

    public boolean dataFormatComplete(String fullDataString)
    {
        boolean completeCheck = false;

        char[] row = new char[6];
        row[0] = '/';
        row[1] = '[';
        row[2] = ':';
        row[3] = ':';
        row[4] = ']';
        row[5] = '\\';

        for(int a = 0, b = 0; a < fullDataString.length(); a++)
        {
            if(fullDataString.charAt(a) == row[b])
            {
                b++;
            }
            if(b >= 6)
            {
                completeCheck = true;
            }
        }
        return completeCheck;
    }

    public String buildNote(int file){
        String noteString = "";
        FileManager fileManager = new FileManager();


        fileManager.CreateFile(noteBody(), file);


        return noteString;
    }

    public void listNote()
    {
        FileManager fileManager = new FileManager();
        fileManager.listAllFiles();
        return;
    }

    public String noteBody(){
        UI ui = new UI();
        String bodyText = "";
        String lineText = "";

        String title = "";
        String date = "";

        while(true)
        {
            ui.textEdit(lineText, bodyText);

            Scanner input = new Scanner(System.in);

            lineText = input.nextLine();

            if(lineText == "")
            {
                //ui.textEditSelect();
                title = ui.setTitle();


                return dataFormat(title) + bodyText;
            }
            else
            {
                bodyText = bodyText + "\n" + lineText;
            }
        }



    }


}

package models;

import java.util.Scanner;

public class NoteCreate {

    public String buildNote(){
        String noteString = "";

        Scanner input = new Scanner(System.in);
        noteString = input.nextLine();
        return noteString;
    }


}

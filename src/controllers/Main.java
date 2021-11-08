package controllers;

import models.FileManager;

import java.nio.file.Path;


public class Main {

    public static void main(String[] args) {
        //Create FileManager object
        FileManager fileManager = new FileManager();

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



    }
}

package models;

import view.UI;

import java.io.*;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileManager {

    private Path dataDirectoryPath;
    private Path noteFilesDirectoryPath;
    private String dataDirectoryString;
    private String noteFilesDirectoryString;
    private String noteFileString;
    private Path noteFilesPath;
    private UI ui;
    public List<Path> allFileList = new ArrayList<Path>();


    public FileManager()
    {
        ui = new UI();

        //Fill path for 'data' directory and fill complete string to access data directory
        dataDirectoryPath = Paths.get("data");
        dataDirectoryString = dataDirectoryPath.toFile().getAbsolutePath();
        //Fill path for 'Note_Files' and fill complete string to access 'Note_Files' directory
        noteFilesDirectoryPath = Paths.get("data\\Note_Files");
        noteFilesDirectoryString = noteFilesDirectoryPath.toFile().getAbsolutePath();

        //Fill first note file to access
        noteFileString = "note0.txt";



        //Fill path for 'Note_Files' directory
        noteFilesDirectoryPath = Paths.get(noteFilesDirectoryString);
        //Fill path for first file access
        noteFilesPath = Paths.get(noteFilesDirectoryString, noteFileString);
    }



    public int fileValidity()
    {
        int error = 0;
        boolean exists = false;
        String fileName = "";
        String fatalErrorCatch = "";
        String errorCatch = "";

        //Check if data directory exists
        if(Files.notExists(dataDirectoryPath)){
            try{
                Files.createDirectories(dataDirectoryPath);
                errorCatch = "Directory " + dataDirectoryString + " not found. Creating directory...\n";
            } catch (IOException e){
                e.printStackTrace();
                fatalErrorCatch += "Unable to create directory " + dataDirectoryString + ".\n\n";
            }
        }


        //Check if 'Note_Files' directory exists
        if(Files.notExists(noteFilesDirectoryPath)){
            try{
                Files.createDirectories(noteFilesDirectoryPath);
                errorCatch += "Directory " + noteFilesDirectoryString + " not found. Creating directory...\n";
            } catch(IOException e){
                e.printStackTrace();
                fatalErrorCatch += "Unable to create directory " + noteFilesDirectoryString + ".\n\n";
            }
        }


        //Check if first text file exists
        if(Files.notExists(noteFilesPath)){
            try{
                Files.createFile(noteFilesPath);
                errorCatch += "File " + noteFileString + " not found. Creating file...\n";
            } catch(IOException e){
                e.printStackTrace();
                fatalErrorCatch += "Unable to create directory " + noteFileString + ".\n\n";
            }
        }


        if(fatalErrorCatch != ""){
            ui.fatalErrorMessage(fatalErrorCatch);
            error = -1;
        }
        if(errorCatch != "") {
            ui.errorMessage(errorCatch);
            error = 1;
        }

        return error;
    }


    public int updateFileList()
    {
        int error = 0;

        //Check if file path exists
        if(Files.exists(noteFilesDirectoryPath) && Files.isDirectory(noteFilesDirectoryPath)){
            //Create directory stream
            try{
                DirectoryStream<Path> noteFilesDirectoryStream = Files.newDirectoryStream(noteFilesDirectoryPath);
                //fill allFileList with file names
                for(Path p: noteFilesDirectoryStream){
                    if(Files.isRegularFile(p)){
                        allFileList.add(p.getFileName());
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return error;
    }


    public int autoRenameNoteFiles()
    {
        int error = 0;



        return error;
    }

}

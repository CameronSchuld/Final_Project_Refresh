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
    private String notExists;



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

    public int CreateFile(String noteBody)
    {
        String errorCatch = "";
        String fatalErrorCatch = "";
        String lastFileName = "";
        updateFileList();

        noteFilesPath = Paths.get(noteFilesDirectoryString, notExists);
        if(Files.notExists(noteFilesPath)){
            try{
                Files.createFile(noteFilesPath);
                errorCatch += "File " + noteFileString + " not found. Creating file...\n";
            } catch(IOException e){
                e.printStackTrace();
                fatalErrorCatch += "Unable to create directory " + noteFileString + ".\n\n";
            }
        }

        System.out.print("\n" + notExists);


        return 1;
    }


    public int WriteFile()
    {
        String errorCatch = "";
        String fatalErrorCatch = "";
        String lastFile = "";
        String fileName = "";
        int intErrorCatch = 0;

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

        //Getting filename string
        lastFile = allFileList.get(allFileList.size()).toString();

        int fileNumber = Character.getNumericValue(lastFile.charAt(4)) + 1;

        fileName = "note" + fileNumber;

        noteFilesPath = Paths.get(noteFilesDirectoryString, fileName);

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
            intErrorCatch = -1;
        }
        if(errorCatch != "") {
            ui.errorMessage(errorCatch);
            intErrorCatch = 1;
        }

        return intErrorCatch;
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
        boolean fileExists = true;
        int fileNumber = 0;
        String fileNameCheck = "";

        while(fileExists)
        {
            fileNameCheck = "note" + String.valueOf(fileNumber) + ".txt";
            fileNumber = fileNumber + 1;
            Path filePathCheck = Paths.get(noteFilesDirectoryString + "\\" + fileNameCheck);
            System.out.println(noteFilesDirectoryString + fileNameCheck);

            if(Files.notExists(filePathCheck))
            {
                System.out.print(fileNameCheck);
                notExists = fileNameCheck;
                fileExists = false;
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

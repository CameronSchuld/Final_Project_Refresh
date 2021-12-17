package models;

import view.UI;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.io.BufferedReader;
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
    List<String> lines = new ArrayList<>();



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

    public int CreateFile(String noteBody, int file)
    {
        String errorCatch = "";
        String fatalErrorCatch = "";
        String lastFileName = "";
        updateFileList();

        String fileName = "note" + String.valueOf(file) + ".txt";

        //Create file
        if(file < 0)
            noteFilesPath = Paths.get(noteFilesDirectoryString, notExists);
        else
            noteFilesPath = Paths.get(noteFilesDirectoryString, fileName);

        System.out.println(noteFilesPath);

        if(Files.notExists(noteFilesPath)){
            try{
                Files.createFile(noteFilesPath);
                errorCatch += "File " + noteFileString + " not found. Creating file...\n";
            } catch(IOException e){
                e.printStackTrace();
                fatalErrorCatch += "Unable to create directory " + noteFileString + ".\n\n";
            }
        }

        //Write file contents
        try{
            Files.write(noteFilesPath, noteBody.getBytes());
        } catch(IOException e){
            e.printStackTrace();
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

    public String extractTitle(int noteNumber)
    {
        String dataContents = "";
        String title = "";
        String fileName = "note" + String.valueOf(noteNumber) + ".txt";
        String currentLine = "";

        Path filePath = Paths.get(noteFilesDirectoryString + "\\" + fileName);

        BufferedReader in;


        try{
            in = new BufferedReader(new FileReader(String.valueOf(filePath)));

            try{
                currentLine = in.readLine();
                in.close();


            } catch(IOException e){
                e.printStackTrace();
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        boolean consistent = false;

        if(currentLine != null)
        {
            for(int a = 2; currentLine.charAt(a) != ']' && currentLine != ""; a++)
            {
                title += currentLine.charAt(a);
            }
        }

        return title;
    }

    public String getFileContents(int noteNumber)
    {
        String fileContents = "";
        String fileName = "note";
        String fullFileName = "";
        String currentLine = "";

        fullFileName = fileName + String.valueOf(noteNumber) + ".txt";
        Path filePath = Paths.get(noteFilesDirectoryString + "\\" + fullFileName);

        BufferedReader in;



        try{
            in = new BufferedReader(new FileReader(String.valueOf(filePath)));

            try{
                currentLine = in.readLine();

                while(currentLine != null){
                    lines.add(currentLine);
                    currentLine = in.readLine();
                }
                in.close();


            } catch(IOException e){
                e.printStackTrace();
            }

        } catch(IOException e){
            e.printStackTrace();
        }

        for(int a = 0; a < lines.size(); a++)
        {
            fileContents = fileContents + "\n" + lines.get(a);
        }

        return fileContents;
    }

    public void listAllFiles()
    {
        int error = 0;
        boolean fileExists = true;
        int fileNumber = 0;
        String fileNameCheck = "";

        while(fileExists)
        {
            fileNameCheck = "note" + String.valueOf(fileNumber) + ".txt";
            Path filePathCheck = Paths.get(noteFilesDirectoryString + "\\" + fileNameCheck);


            if(Files.notExists(filePathCheck))
            {
                fileExists = false;
            }
            else
            {
                ui.printSomething(fileNameCheck);
                ui.printTitle(extractTitle(fileNumber));
            }
            fileNumber = fileNumber + 1;
        }
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

            if(Files.notExists(filePathCheck))
            {
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

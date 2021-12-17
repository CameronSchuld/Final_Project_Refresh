package view;

import java.util.Scanner;
import controllers.FunctionSelect;

public class UI {

    FunctionSelect functionSelect = new FunctionSelect();

    public int homePage()
    {
        String inputString = "";
        int select = 0;
        boolean inputValidation = false;

        while(!inputValidation) {
            //Set new scanner to capture input
            Scanner input = new Scanner(System.in);

            System.out.print("Would you like to...\n");
            System.out.print("1. Create new note\n");
            System.out.print("2. List notes\n");
            System.out.print("3. View note\n");
            System.out.print("4. Edit note\n");
            System.out.print("5. Quit\n");
            System.out.print(": ");

            //Gather input
            inputString = input.next();

            if(functionSelect.isNumber(inputString.charAt(0)))
            {
                inputValidation = true;
                break;
            }
            System.out.print(inputString.charAt(0) + " is not a number please enter again...\n\n");

        }

        select = Character.getNumericValue(inputString.charAt(0));

        return select;

    }

    public int fileSelect()
    {
        int fileNumber = 0;
        String stringInput = "";
        Scanner input = new Scanner(System.in);
        boolean inputValidation = false;

        while(!inputValidation)
        {
            System.out.print("Enter the number of the file you want to select\n: ");
            stringInput = input.next();

            inputValidation = true;
            for(int a = 0; a < stringInput.length(); a++)
            {
                if(!Character.isDigit(stringInput.charAt(a))){
                    inputValidation = false;
                }
            }
            if(inputValidation)
                break;
            else
                System.out.print("Enter a digit...\n\n");
        }

        return Integer.parseInt(stringInput);

    }

    public void printTitle(String print)
    {
        System.out.print("Title: " + print + "\n\n");
        return;
    }

    public void printSomething(String printThisThing)
    {
        System.out.println(printThisThing);
        return;
    }

    public String setTitle()
    {
        String title = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a title for you note: ");
        title = input.nextLine();
        return title;
    }

    public void fatalErrorMessage(String message)
    {
        System.out.print("\n\n----Program Error Unable To Continue!----\n");
        System.out.print(message);
        System.out.print("-----------------------------------------\n\n");
        return;
    }

    public void errorMessage(String message)
    {
        System.out.print("\n\n--------------Program Error!--------------\n");
        System.out.print(message);
        System.out.print("------------------------------------------\n\n");
        return;
    }

    public void textEdit(String currentLine, String fullText)
    {
        System.out.print("\n\n--------------Edit Mode---------------\n");
        System.out.print(" Press enter twice to save\n\n");

        System.out.print(fullText);
        return;
    }

    public void textEditSelect()
    {
        System.out.print("\ns: save note and quit\n");
        System.out.print("q: quit without saving\n");
        System.out.print("l: select line to edit\n");
    }

}

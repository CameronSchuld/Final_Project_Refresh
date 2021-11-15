package view;

import java.util.Scanner;

public class UI {

    public int homePage()
    {
        String inputString;
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

            //Validate input
            for (int i = 1; i < 6 || !inputValidation; i++) {
                if (i == Integer.parseInt(inputString))
                    inputValidation = true;

                System.out.println(i + "==" + Integer.parseInt((inputString)));
            }

        }

        return select;

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

}

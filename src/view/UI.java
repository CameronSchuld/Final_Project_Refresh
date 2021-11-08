package view;

public class UI {

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

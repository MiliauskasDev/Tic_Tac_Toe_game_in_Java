package tictactoe;

import java.util.Scanner;

public class ConsoleView extends View {

    @Override
    public void printString(String string) {
        System.out.println(string);
    }

    @Override
    public String getUserInput() {
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}

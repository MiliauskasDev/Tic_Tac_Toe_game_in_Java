package tictactoe;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Controller controller = new Controller(new ConsoleView());
        controller.startGame();
    }
}

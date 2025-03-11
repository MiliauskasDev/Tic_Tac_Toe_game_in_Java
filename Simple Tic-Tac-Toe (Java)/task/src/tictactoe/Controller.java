package tictactoe;

public class Controller {

    private final View view;
    private Board board;

    public Controller(View view){
        this.view = view;
    }

    public void startGame(){
        this.board = new Board();
        do {
            this.printCurrentBoard();
            this.inputMove();
        } while(this.board.getGameState().equals("Game not finished"));
        this.printCurrentBoard();
        view.printString(board.getGameState());
    }

    private void inputMove() {
        int y, x;
        boolean isValidMove = false;
        do {
            String move = this.view.getUserInput();
            try {
                y = Integer.parseInt(move.split(" ")[0]);
                x = Integer.parseInt(move.split(" ")[1]);

                if (y > 3 || y < 1 || x > 3 || x < 1) {
                    view.printString("Coordinates should be from 1 to 3!");
                } else if (this.board.squareIsOccupied(y, x)) {
                    view.printString("This cell is occupied! Choose another one!");
                } else {
                    this.board.placeMove(y, x);
                    isValidMove = true; //used to exit the loop
                }
            } catch(NumberFormatException e){
                view.printString("You should enter numbers!");
            } catch(IndexOutOfBoundsException e){
                view.printString("Incorrect coordinate format, it should look like e.g. 3 1");
            }
        } while(!isValidMove);
    }

    private void printCurrentBoard() {
        view.printString(this.board.toString());
    }

}

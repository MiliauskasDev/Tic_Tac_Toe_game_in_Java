package tictactoe;

public class Board {

    private char[][] board;
    private char playerToMove;

    public Board() {
        this.board = new char[][] {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'},};
        playerToMove = 'X';
    }

    @Override
    public String toString() {
        String board;
        board = "---------\n";
        for (int y = 0; y < 3; y++) {
            board += "| " + this.board[y][0] + " " + this.board[y][1] + " " + this.board[y][2] + " |\n";
        }
        board += "---------";
        return board;
    }

    public String getGameState(){
        boolean isDraw = true; //this will become false if we detect any empty spaces on board
        String winningPlayer = "";

        //counts how many Xs and Os there were for validation
        int countX = 0;
        int countO = 0;

        // used to store current marks for diagonal winning condition
        String firstDiagonal = "";
        String secondDiagonal = "";


        for (int i = 0; i < 3; i++) {
            String currentLineX = ""; //current line being checked for winning condition
            String currentLineY = ""; //current line being checked for winning condition
            for (int ii = 0; ii < 3; ii++){
                //collect horizontal and vertical lines marks
                currentLineX += this.board[i][ii];
                currentLineY += this.board[ii][i];

                //count Xs and Os for validation
                if(this.board[i][ii] == 'X'){
                    countX++;
                } else if (this.board[i][ii] == 'O'){
                    countO++;
                }

                //check if the game will not draw
                if(this.board[ii][i] == '_'){
                    isDraw = false;
                }
            }
            //collecting diagonal marks for checking winning condition
            firstDiagonal += this.board[i][i];
            secondDiagonal += this.board[2-i][i];//2-i because we want to check from bottom left diagonal
            
            winningPlayer += getWinningPlayer(currentLineX) + getWinningPlayer(currentLineY) + getWinningPlayer(firstDiagonal) + getWinningPlayer(secondDiagonal);
        }

        if(Math.abs(countX - countO) > 1){
            return "Impossible";
        }

        char theWinner = '_';
        for(char winner : winningPlayer.toCharArray()){
            if(theWinner == '_'){
                theWinner = winner;
            } else if(theWinner != winner){
                return "Impossible";
            }
        }
        
        if(theWinner != '_'){
            return theWinner + " wins";
        }

        if(isDraw){
            return "Draw";
        }

        return "Game not finished"; //if all checks fail, game is not finished
    }


    private String getWinningPlayer(String line){
        if(line.equals("XXX") || line.equals("OOO")){
            return line.charAt(0) + "";
        } else {
            return "";
        }
    }

    private char getSquareMark(int y, int x) {
        return this.board[y - 1][x - 1];
    }

    private void setSquareMark(int y, int x, char mark) {
        this.board[y - 1][x - 1]  = mark;
    }

    public boolean squareIsOccupied(int y, int x) {
        return getSquareMark(y, x) != '_';
    }

    public void placeMove(int y, int x) {
        if(!squareIsOccupied(y, x)){
            setSquareMark(y, x, playerToMove); // returns the mark that was placed
            if (playerToMove == 'X') {
                playerToMove = 'O';
            } else {
                playerToMove = 'X';
            }
        }
    }
}

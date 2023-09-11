import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class Game {
    private Board board;

    private Board tutorialBoard;
    private Player[] players;
    private int playerMove;
    private TutorialPanel tutorialPanel;
    private BoardPanel boardPanel;
    private JFrame frame;

    private JFrame tutorialFrame;
    private boolean moveState;

    private boolean toggleHint;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.tutorialBoard = new Board();
        this.players = new Player[]{player1, player2};
        this.playerMove = 0;
        this.boardPanel = new BoardPanel(board,this);
        this.tutorialPanel = new TutorialPanel(tutorialBoard,this);
        this.frame = new JFrame("Nine Mens Morris Game");

        this.moveState = false;
        this.toggleHint = false;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        boardPanel.setLayout(null);
        tutorialPanel.setLayout(null);
        frame.add(boardPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void play() {

        boolean moveState = false;

        Scanner scanner = new Scanner(System.in);


        System.out.println("Would you like to complete a tutorial before beginning?");
        System.out.println("Please enter Yes or No: ");
        String tutorial = scanner.next();
        if (tutorial.toUpperCase().equals("YES")){
//            System.out.println("okay");
            Tutorial();
        }
        System.out.println("Welcome to the Nine Mens Morris Game!");
        System.out.println("Player 1 is Black (*)");
        System.out.println("Player 2 is White (&)");
        System.out.println("To place a piece, enter the position where you want to place it (e.g. a1)");
        board.printBoard();

        while (true) {

            if (moveState) {
                board.printBoard();
            }

            Player currentPlayer = players[playerMove];
            System.out.println(currentPlayer.getName() + ", it's your turn!");


            HashMap<PieceType, Integer> pieceCounts = board.countPieces();//counts pieces for each



// Check if plyer has no more pieces on them
            if (currentPlayer.getPiecesRemaining() == 0) {
                movePieceAdjacent(currentPlayer);
                continue;
            }
            else if (currentPlayer.getPiecesRemaining() == 0 && pieceCounts.get(currentPlayer.getPiece().getType()) == 3) {//for flying
                movePieceOnBoard(currentPlayer);
                continue;
            }


            else {
                //if toggleHint is true
                if (toggleHint) {
                System.out.println("The possible positions to place your pieces are: ");
                System.out.println(getValidMoves());
                //show the empty positions
                System.out.println("Your current pieces are at: ");
                board.showMyPieces(currentPlayer);
                    System.out.println("");
                }
                else {
                    System.out.println("Hint Toggle is OFF");
                    System.out.println("Type HINT to toggle or");
                }
                System.out.println("Enter the position to place your piece: ");
                String position = scanner.next();
                position = position.toUpperCase();

                if (position.equals("HINT")){
                    ToggleHint();
                    //toggle on or off method
                    continue;
                }
                if (placePiece(position, currentPlayer)) {
                    this.boardPanel = new BoardPanel(this.board,this);
                    this.boardPanel.setLayout(null);
                    frame.setContentPane(this.boardPanel);
                    frame.revalidate();
                    frame.repaint();
                    // Check if the player has formed a mill
                    if (board.checkForMill(position, currentPlayer.getPiece().getType())) {
                        removeOpponentPiece(currentPlayer);
                        this.boardPanel = new BoardPanel(this.board,this);
                        this.boardPanel.setLayout(null);
                        frame.setContentPane(this.boardPanel);
                        frame.revalidate();
                        frame.repaint();
                    } else {
                        // Switch to next player's turn
                        playerMove = (playerMove + 1) % players.length;
                    }
                } else {
                    System.out.println("Can't place piece there, please try again!");
                    continue;
                }
            }

            HashMap<PieceType, Integer> anotherCount = board.countPieces();

            if (players[0].getPiecesRemaining() == 0 && anotherCount.get(players[0].getPiece().getType()) <= 2 || players[1].getPiecesRemaining() == 0 && anotherCount.get(players[1].getPiece().getType()) <= 2) { //something like players[0].getPiecesRemaining() == 0 and <2 PieceType.WHITE on board

                System.out.println("Game Over!");

                if (anotherCount.get(players[0].getPiece().getType()) <= 2) {
                    System.out.println("Player 2 wins!");
                }

                if (anotherCount.get(players[1].getPiece().getType()) <= 2) {
                    System.out.println("Player 1 wins!");
                }

                moveState = true;
            }

            else if (currentPlayer.getPiecesRemaining() == 0 && board.anyValidMoves(currentPlayer) == false) {
                System.out.println("Game OVERRRRR");
                System.out.println("Sorry " + currentPlayer.getName() + " you have no valid moves left!");
                playerMove = (playerMove + 1) % players.length;
                System.out.println(currentPlayer.getName() + " WINS");
                moveState = true;
            }
        }
    }

    private void Tutorial() {
        Scanner scanner = new Scanner(System.in);
        this.tutorialFrame = new JFrame("Tutorial");
        tutorialFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //show the tutorial frame
        tutorialFrame.add(tutorialPanel);
        tutorialFrame.pack();
        tutorialFrame.setVisible(true);
        //start tutorial
        System.out.println("Tutorial:");
        System.out.println("Nine Men Morris is a game of strategy that requires players to place pieces strategically,");
        System.out.println("the game can be split into 3 phases. Firstly, both players place their 9 tokens onto the board, which looks like this:");
        board.printBoard();
        System.out.println("You should now see an empty board in a frame called tutorial. Enter 'A' to place your piece!");
        String input = scanner.next();

        if (input.toUpperCase().equals("A")) {
            tutorialPanel.placeTokenImage();
// place piece at A (change the panel image to A token being filled)
            System.out.println("Once all 9 pieces from each of the players are placed, the game reaches a second phase");
            System.out.println("where the players must move their pieces that are already on the board, adjacently.");
            System.out.println("In the game you will be prompted to move a piece. However since the only piece is the one in A it has already been selected");
            System.out.println("Enter either the position 'B' or 'J' to move your piece adjacently");
            String moveIn = scanner.next();
            if(moveIn.toUpperCase().equals("B")){
                tutorialPanel.moveToB();

            }
            if(moveIn.toUpperCase().equals("J")){
                tutorialPanel.moveToJ();
            }
            System.out.println("The third state of the game is forming mills. Which are 3 of a player's pieces");
            System.out.println("forming either a horizontal or vertical line. (Diagonal doesn't count)");
            System.out.println("By forming a mill. The player that formed a mill can remove a piece from the other player.");
            System.out.println("Are you ready to continue onto mills? enter y to continue: ");
            String contIn = scanner.next();
            // show the mill panel
            tutorialPanel.showMill();
            System.out.println("To form a mill you can place a piece at 'C' to complete a horizontal line Please Enter C: ");
            String millForm = scanner.next();
            if (millForm.toUpperCase().equals("C")){
                tutorialPanel.makeMill();
                System.out.println("Now that a mill has been formed you can remove your opponent's piece. Enter 'J' to remove their piece: ");
                String removeJ= scanner.next();
                tutorialPanel.removePiece();
                System.out.println("enter any value to continue: ");
                String cont = scanner.next();
                // show the mill formed and continue onto win condition
            }
            System.out.println("Once a player only has 3 pieces left on the board. They are able to move any of those pieces anywhere on the board.");
            System.out.println("The win condition for a player is if their opponent only has 2 pieces left or no longer have valid moves.");
            System.out.println("Enter ANY value to continue to the game: ");
            System.out.println("and please close the Tutorial Frame :)");
            tutorialPanel.tutComplete();
            String in = scanner.next();
            //complete the tutorial
        }

    }

    private boolean ToggleHint(){
        toggleHint = !toggleHint;
        return toggleHint;
    }

    private boolean placePiece(String position, Player currentPlayer) {
        if (board.placePiece(position, currentPlayer.getPiece().getType())) {
            System.out.println(currentPlayer.getName() + " placed their piece at " + position);
            currentPlayer.decrementNumPieces();
            System.out.println();
            board.printBoard();
            return true;
        }
        return false;
    }

    private void removeOpponentPiece(Player currentPlayer) {
        System.out.println(currentPlayer.getName() + " formed a mill!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the position of the piece you want to remove: ");
            String removePosition = scanner.next();
            removePosition = removePosition.toUpperCase();
            // Check if the player is trying to remove their own piece
            if (board.getPiece(removePosition).getType() == currentPlayer.getPiece().getType()) {
                System.out.println("You can't remove your own piece, please try again!");
                continue;
            }

            if (board.getPiece(removePosition).getType() == PieceType.EMPTY) {
                System.out.println("There is no piece there, try again!");
                continue;
            }

            // Check if the piece is part of a mill
            if (board.checkForMill(removePosition, currentPlayer.getPiece().getType())) {
                System.out.println("The piece is part of a mill, please try again!");
                continue;
            }

            // Remove the piece and switch to the next player's turn
            board.removePiece(removePosition);
            board.printBoard();
            System.out.println(currentPlayer.getName() + " removed their opponent's piece at " + removePosition);
            playerMove = (playerMove + 1) % players.length;
            break;
        }
    }


    private void movePieceOnBoard(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (toggleHint) {
                System.out.println(getValidMoves());
            }
            System.out.print("Enter the position of the piece you want to move: ");
            String startPos = scanner.next();
            startPos = startPos.toUpperCase();
            PieceType pieceType = currentPlayer.getPiece().getType();
            if (board.getPiece(startPos) == null || board.getPiece(startPos).getType() != pieceType) {
                System.out.println("Invalid position, please try again!");
                continue;
            }
            System.out.print("Enter the position to move the piece to: ");
            String endPos = scanner.next();
            endPos = endPos.toUpperCase();
            Move move = new Move(startPos, endPos, pieceType);
            if (board.movePiece(move)) {
                System.out.println(currentPlayer.getName() + " moved their piece from " + startPos + " to " + endPos);
                board.printBoard();
                this.boardPanel = new BoardPanel(this.board,this);
                this.boardPanel.setLayout(null);
                frame.setContentPane(this.boardPanel);
                frame.revalidate();
                frame.repaint();
                // Check if the player has formed a mill
                if (board.checkForMill(endPos, pieceType)) {
                    removeOpponentPiece(currentPlayer);
                } else {
                    // Switch to next player's turn
                    playerMove = (playerMove + 1) % players.length;
                }
                break;
            } else {
                System.out.println("Invalid move, please try again!");
                continue;
            }
        }
    }



    private void movePieceAdjacent(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //check if there are no more valid moves for the current player
            if (board.anyValidMoves(currentPlayer)==false){
                System.out.println("Game OVERRRRR");
                System.out.println("Sorry " + currentPlayer.getName() + " you have no valid moves left!");
                playerMove = (playerMove + 1) % players.length;
                System.out.println(currentPlayer.getName() + " WINS");
                System.exit(0);
            }
            System.out.println("The pieces you have on the board are: ");
            board.showMyPieces(currentPlayer);
            System.out.print("Enter the position of the piece you want to move: ");
            String startPos = scanner.next();
            startPos = startPos.toUpperCase();
            PieceType pieceType = currentPlayer.getPiece().getType();
            ArrayList<String> emptyAdjacent = board.hintAdjacent(startPos);
            //check which ones adjacent moves are empty and display them to player
            if (board.getPiece(startPos) == null || board.getPiece(startPos).getType() != pieceType || emptyAdjacent.size()==0) {
                System.out.println("Invalid position, please try again!");
                continue;
            }

            if (toggleHint) {

                System.out.println("You can move your piece from " + startPos + " to this position(s): " + emptyAdjacent);

            }

            System.out.print("Enter the position to move the piece to (adjacent position): ");
            String endPos = scanner.next();
            endPos = endPos.toUpperCase();

            // Check if the move is valid based on adjacency
            if (!board.isAdjacentPosition(startPos, endPos)) {
                System.out.println("Invalid move, please try again!");
                continue;
            }

            Move move = new Move(startPos, endPos, pieceType);
            if (board.movePiece(move)) {
                System.out.println(currentPlayer.getName() + " moved their piece from " + startPos + " to " + endPos);
                board.printBoard();
                this.boardPanel = new BoardPanel(this.board,this);
                this.boardPanel.setLayout(null);
                frame.setContentPane(this.boardPanel);
                frame.revalidate();
                frame.repaint();
                // Check if the player has formed a mill
                if (board.checkForMill(endPos, pieceType)) {
                    removeOpponentPiece(currentPlayer);
                } else {
                    // Switch to the next player's turn
                    playerMove = (playerMove + 1) % players.length;
                }
                break;
            } else {
                System.out.println("Invalid move, please try again!");
                continue;
            }
        }
    }


    private List<String> getValidMoves() {
        List<String> validMoves = new ArrayList<>();

        // Iterate through all positions on the board
        for (String position : board.countEmptyPositions()) {
            // Check if the current player can place a piece at the position
            if (board.placePiece(position, players[playerMove].getPiece().getType())) {
                validMoves.add(position);
                board.removePiece(position); // Undo the placement for checking the next position
            }
        }

        return validMoves;
    }



    private boolean getMoveState(){
        return moveState;
    }


    private Player getCurrentPlayer(){
        return players[playerMove];
    }




    public static void main(String[] args){
        Player player1 = new Player("Player 1", new Piece("",PieceType.BLUE));
        Player player2 = new Player("Player 2", new Piece("",PieceType.RED));
        Game game = new Game(player1, player2);
        game.play();

    }

}

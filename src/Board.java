import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private ArrayList<String> board;
    private ArrayList<Piece> pieces;

    private int numMills;

    public Board() {
        /*
         *
         * Constructor for a board.
         * Generates the basic board layout and places pieces of type "Empty" at each position.
         *
         */
        board = new ArrayList<String>();
        board.add("[A]---------------[B]---------------[C]\n");
        board.add(" |                 |                 | \n");
        board.add(" |    [D]---------[E]---------[F]    | \n");
        board.add(" |     |           |           |     | \n");
        board.add(" |     |    [G]---[H]---[I]    |     | \n");
        board.add(" |     |     |           |     |     | \n");
        board.add("[J]---[K]---[L]         [M]---[N]---[O]\n");
        board.add(" |     |     |           |     |     | \n");
        board.add(" |     |    [P]---[Q]---[R]    |     | \n");
        board.add(" |     |           |           |     | \n");
        board.add(" |    [S]---------[T]---------[U]    | \n");
        board.add(" |                 |                 | \n");
        board.add("[V]---------------[W]---------------[X]\n");

        pieces = new ArrayList<Piece>();
        pieces.add(new Piece("A", PieceType.EMPTY));
        pieces.add(new Piece("B", PieceType.EMPTY));
        pieces.add(new Piece("C", PieceType.EMPTY));
        pieces.add(new Piece("D", PieceType.EMPTY));
        pieces.add(new Piece("E", PieceType.EMPTY));
        pieces.add(new Piece("F", PieceType.EMPTY));
        pieces.add(new Piece("G", PieceType.EMPTY));
        pieces.add(new Piece("H", PieceType.EMPTY));
        pieces.add(new Piece("I", PieceType.EMPTY));
        pieces.add(new Piece("J", PieceType.EMPTY));
        pieces.add(new Piece("K", PieceType.EMPTY));
        pieces.add(new Piece("L", PieceType.EMPTY));
        pieces.add(new Piece("M", PieceType.EMPTY));
        pieces.add(new Piece("N", PieceType.EMPTY));
        pieces.add(new Piece("O", PieceType.EMPTY));
        pieces.add(new Piece("P", PieceType.EMPTY));
        pieces.add(new Piece("Q", PieceType.EMPTY));
        pieces.add(new Piece("R", PieceType.EMPTY));
        pieces.add(new Piece("S", PieceType.EMPTY));
        pieces.add(new Piece("T", PieceType.EMPTY));
        pieces.add(new Piece("U", PieceType.EMPTY));
        pieces.add(new Piece("V", PieceType.EMPTY));
        pieces.add(new Piece("W", PieceType.EMPTY));
        pieces.add(new Piece("X", PieceType.EMPTY));
    }

    public ArrayList<String> getBoard() {
        /*
         *
         * Accessor for the board variable.
         * Returns the board.
         *
         */
        return board;
    }


    public boolean placePiece(String position, PieceType type) {
        /*
         *
         * Places a piece of type (PieceType) at a position (String).
         * Returns true if the placement was successful, else false.
         *
         *
         */
        for (Piece p : pieces) {
            if (p.getPosition().equals(position) && p.getType() == PieceType.EMPTY) {
                p.setType(type);
                return true;
            }
        }
        return false;
    }

    public boolean removePiece(String position) {
        /*
         *
         * Removes a piece from the board located at position (String).
         *
         */
        for (Piece p : pieces) {
            if (p.getPosition().equals(position) && p.getType() != PieceType.EMPTY) {
                p.setType(PieceType.EMPTY);
                return true;
            }
        }
        return false;
    }

    public boolean movePiece(Move move) {
        /*
         * Moves a piece using an input Move object.
         * Returns true if the move was successful, else returns false.
         *
         */
        String currentPosition = move.getStartPosition();
        String newPosition = move.getEndPosition();
        PieceType type = move.getType();
        // loop through pieces on the board
        for (Piece p : pieces) {
            // set the current position to EMPTY and the new position to type provided by move object
            if (p.getPosition().equals(currentPosition) && p.getType() == type) {
                for (Piece q : pieces) {
                    if (q.getPosition().equals(newPosition) && q.getType() == PieceType.EMPTY) {
                        p.setType(PieceType.EMPTY);
                        q.setType(type);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Piece getPiece(String position) {
        /*
         * Gets piece based on a position (String)
         *
         */
        for (Piece p : pieces) {
            if (p.getPosition().equals(position)) {
                return p;
            }
        }
        return null;
    }


    public void printBoard() {
        /*
         * Prints a text representation of the board
         *
         */

        // construct a hashmap which includes * for player 1 and & for player 2
        HashMap<String, String> pieceMap = new HashMap<>();
        for (Piece p : pieces) {
            if (p.getType() == PieceType.BLUE) {
                String type = "*";
                pieceMap.put(p.getPosition(), type);
            } else if (p.getType() == PieceType.RED) {
                String type = "&";
                pieceMap.put(p.getPosition(), type);
            } else {
                pieceMap.put(p.getPosition(), p.getPosition());
            }

        }

        // print the board based on the hashmap generated
        for (String line : board) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    String pos = "" + c;
                    if (pieceMap.containsKey(pos)) {
                        System.out.print(pieceMap.get(pos));
                    } else {
                        System.out.print(c);
                    }
                } else {
                    System.out.print(c);
                }
            }
        }


    }
    public ArrayList<String> countEmptyPositions(){
        ArrayList<String> empty = null;
        empty = new ArrayList<String>();
        String e;
        for (Piece piece : pieces) {
            if (piece.getType() == PieceType.EMPTY) {
                e = piece.getPosition();
                empty.add(e);
            }
        }
        return empty;
    }

    public HashMap<PieceType, Integer> countPieces() {
        HashMap<PieceType, Integer> countMap = new HashMap<>();
        int blueCount = 0;
        int redCount = 0;

        for (Piece piece : pieces) {
            if (piece.getType() == PieceType.BLUE) {
                blueCount++;
            } else if (piece.getType() == PieceType.RED) {
                redCount++;
            }
        }

        countMap.put(PieceType.BLUE, blueCount);
        countMap.put(PieceType.RED, redCount);

        return countMap;
    }

    public boolean checkForMill(String position, PieceType pieceType) {
        // check if the given position is valid
        if (!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        // get the piece at the given position
        Piece piece = getPiece(position);
        if (piece.getType() != pieceType) {
            // the piece at the given position is not of the specified type
            return false;
        }

        // check if any mill is formed with the given piece
        String[] mills = getMills(position);
        for (String mill : mills) {
            String[] positions = mill.split("");
            Piece p1 = getPiece(positions[0]);
            Piece p2 = getPiece(positions[1]);
            Piece p3 = getPiece(positions[2]);
            if (p1.getType() == pieceType && p2.getType() == pieceType && p3.getType() == pieceType) {
                // a mill is formed with the given piece
                return true;
            }
        }

        // no mill is formed with the given piece
        return false;
    }



    // helper method to check if a position is valid
    private boolean isValidPosition(String position) {
        for (Piece piece : pieces) {
            if (piece.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }


    // helper method to get all the mills that contain a given position
    private String[] getMills(String position) {
        switch (position) {
            case "A":
                return new String[]{"ABC", "AJV"};
            case "B":
                return new String[]{"ABC", "BEH"};
            case "C":
                return new String[]{"ABC", "COX"};
            case "D":
                return new String[]{"DEF", "DKS"};
            case "E":
                return new String[]{"DEF", "BEH"};
            case "F":
                return new String[]{"DEF", "FNU"};
            case "G":
                return new String[]{"GHI", "GLP"};
            case "H":
                return new String[]{"GHI", "BEH"};
            case "I":
                return new String[]{"GHI", "IMR"};
            case "J":
                return new String[]{"AJV", "JKL"};
            case "K":
                return new String[]{"JKL", "DKS"};
            case "L":
                return new String[]{"JKL", "GLP"};
            case "M":
                return new String[]{"MNO", "IMR"};
            case "N":
                return new String[]{"MNO", "FNU"};
            case "O":
                return new String[]{"MNO", "COX"};
            case "P":
                return new String[]{"PQR", "GLP"};
            case "Q":
                return new String[]{"PQR", "QTW"};
            case "R":
                return new String[]{"PQR", "IMR"};
            case "S":
                return new String[]{"STU", "DKS"};
            case "T":
                return new String[]{"STU", "QTW"};
            case "U":
                return new String[]{"STU", "FNU"};
            case "V":
                return new String[]{"VWX", "AJV"};
            case "W":
                return new String[]{"VWX", "QTW"};
            case "X":
                return new String[]{"VWX", "COX"};
            default:
                return new String[]{};
        }

    }


    public ArrayList<String> hintAdjacent(String startPos){
        ArrayList<String> emptyAdjacent = null;
        emptyAdjacent = new ArrayList<String>();
        String eA;
        HashMap<String, String[]> adjacentMap = new HashMap<>();
        adjacentMap.put("A", new String[]{"B", "J"});
        adjacentMap.put("B", new String[]{"A", "C", "E"});
        adjacentMap.put("C", new String[]{"B", "O"});
        adjacentMap.put("D", new String[]{"E", "K"});
        adjacentMap.put("E", new String[]{"B", "D", "F", "H"});
        adjacentMap.put("F", new String[]{"E", "N"});
        adjacentMap.put("G", new String[]{"H", "L"});
        adjacentMap.put("H", new String[]{"E", "I", "G"});
        adjacentMap.put("I", new String[]{"M", "H"});
        adjacentMap.put("J", new String[]{"A", "K", "V"});
        adjacentMap.put("K", new String[]{"D", "J", "L", "S"});
        adjacentMap.put("L", new String[]{"G", "K", "P"});
        adjacentMap.put("M", new String[]{"I", "N", "R"});
        adjacentMap.put("N", new String[]{"F", "M", "O", "U"});
        adjacentMap.put("O", new String[]{"C", "N", "X"});
        adjacentMap.put("P", new String[]{"L", "Q"});
        adjacentMap.put("Q", new String[]{"P", "R", "T"});
        adjacentMap.put("R", new String[]{"M", "Q"});
        adjacentMap.put("S", new String[]{"K", "T"});
        adjacentMap.put("T", new String[]{"Q", "S", "U", "W"});
        adjacentMap.put("U", new String[]{"N", "T"});
        adjacentMap.put("V", new String[]{"J", "W"});
        adjacentMap.put("W", new String[]{"T", "V", "X"});
        adjacentMap.put("X", new String[]{"O", "W"});

        String[] adjacentPositions = adjacentMap.get(startPos);
        //checks the adjacent positions next to startPos
        for (String pos : adjacentPositions) {
            Piece piece = getPiece(pos);
            if (piece.getType() == PieceType.EMPTY){
                eA = piece.getPosition();
                emptyAdjacent.add(eA);
            }
        }

        return emptyAdjacent;
    }

    public boolean anyValidMoves(Player currentPlayer){
        ArrayList<String> playerPieces = new ArrayList<String>();

        ArrayList<String> possibleMoves = new ArrayList<String>();
        int hasEmpty = 0;

        for (Piece piece : pieces) {
            if(piece.getType() == currentPlayer.getPiece().getType()){
                playerPieces.add(piece.getPosition());
                String position = piece.getPosition();
                HashMap<String, String[]> adjacentMap = new HashMap<>();
                adjacentMap.put("A", new String[]{"B", "J"});
                adjacentMap.put("B", new String[]{"A", "C", "E"});
                adjacentMap.put("C", new String[]{"B", "O"});
                adjacentMap.put("D", new String[]{"E", "K"});
                adjacentMap.put("E", new String[]{"B", "D", "F", "H"});
                adjacentMap.put("F", new String[]{"E", "N"});
                adjacentMap.put("G", new String[]{"H", "L"});
                adjacentMap.put("H", new String[]{"E", "I", "G"});
                adjacentMap.put("I", new String[]{"M", "H"});
                adjacentMap.put("J", new String[]{"A", "K", "V"});
                adjacentMap.put("K", new String[]{"D", "J", "L", "S"});
                adjacentMap.put("L", new String[]{"G", "K", "P"});
                adjacentMap.put("M", new String[]{"I", "N", "R"});
                adjacentMap.put("N", new String[]{"F", "M", "O", "U"});
                adjacentMap.put("O", new String[]{"C", "N", "X"});
                adjacentMap.put("P", new String[]{"L", "Q"});
                adjacentMap.put("Q", new String[]{"P", "R", "T"});
                adjacentMap.put("R", new String[]{"M", "Q"});
                adjacentMap.put("S", new String[]{"K", "T"});
                adjacentMap.put("T", new String[]{"Q", "S", "U", "W"});
                adjacentMap.put("U", new String[]{"N", "T"});
                adjacentMap.put("V", new String[]{"J", "W"});
                adjacentMap.put("W", new String[]{"T", "V", "X"});
                adjacentMap.put("X", new String[]{"O", "W"});
                String[] adjacentPositions = adjacentMap.get(position);
                for (String adjacentPos : adjacentPositions) {
                    Piece adjacentPiece = getPiece(adjacentPos);
                    if (adjacentPiece.getType() == PieceType.EMPTY) {
                        possibleMoves.add(piece.getPosition());
                        hasEmpty++; // Found a valid move
                    }
                }
        }
        }
//      checks if there is any valid moves of the pieces next to the current player's pieces
        if (hasEmpty >= 1){
            return true;
        }
        else {return false;}

    }
    public ArrayList<String> showMyPieces(Player currentPlayer){
        //iterates through the board to show the positions of the current player's pieces
        ArrayList<String> playerPieces = new ArrayList<String>();
        for (Piece piece : pieces) {
            if (piece.getType() == currentPlayer.getPiece().getType()) {
                playerPieces.add(piece.getPosition());
            }
        }
        System.out.println(playerPieces);
        return playerPieces;
    }

    public boolean isAdjacentPosition(String startPos, String endPos) {
        HashMap<String, String[]> adjacentMap = new HashMap<>();
        adjacentMap.put("A", new String[]{"B", "J"});
        adjacentMap.put("B", new String[]{"A", "C", "E"});
        adjacentMap.put("C", new String[]{"B", "O"});
        adjacentMap.put("D", new String[]{"E", "K"});
        adjacentMap.put("E", new String[]{"B", "D", "F", "H"});
        adjacentMap.put("F", new String[]{"E", "N"});
        adjacentMap.put("G", new String[]{"H", "L"});
        adjacentMap.put("H", new String[]{"E", "I", "G"});
        adjacentMap.put("I", new String[]{"M", "H"});
        adjacentMap.put("J", new String[]{"A", "K", "V"});
        adjacentMap.put("K", new String[]{"D", "J", "L", "S"});
        adjacentMap.put("L", new String[]{"G", "K", "P"});
        adjacentMap.put("M", new String[]{"I", "N", "R"});
        adjacentMap.put("N", new String[]{"F", "M", "O", "U"});
        adjacentMap.put("O", new String[]{"C", "N", "X"});
        adjacentMap.put("P", new String[]{"L", "Q"});
        adjacentMap.put("Q", new String[]{"P", "R", "T"});
        adjacentMap.put("R", new String[]{"M", "Q"});
        adjacentMap.put("S", new String[]{"K", "T"});
        adjacentMap.put("T", new String[]{"Q", "S", "U", "W"});
        adjacentMap.put("U", new String[]{"N", "T"});
        adjacentMap.put("V", new String[]{"J", "W"});
        adjacentMap.put("W", new String[]{"T", "V", "X"});
        adjacentMap.put("X", new String[]{"O", "W"});

        String[] adjacentPositions = adjacentMap.get(startPos);
        for (String pos : adjacentPositions) {
            if (pos.equals(endPos)) {
                return true;
            }
        }
        return false;
    }



}

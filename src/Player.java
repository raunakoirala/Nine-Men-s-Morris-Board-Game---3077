public class Player {
    private String name;
    private Piece piece;

    private int piecesRemaining;

    private int numMills;


    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
        this.piecesRemaining = 9;
        this.numMills = 0;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean decrementNumPieces(){
        if (this.piecesRemaining > 0) {
            this.piecesRemaining--;
            return true;
        }
        return false;
    }

    public void incrementNumPieces() {
        piecesRemaining++;
    }

    public int getNumMills() {
        return numMills;
    }

    public void incrementNumMills() {
        numMills++;
    }

    public void decrementNumMills() {
        numMills--;
    }

    public int  getPiecesRemaining(){
        return piecesRemaining;
    }


}


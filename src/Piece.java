public class Piece {
    private String position;
    private PieceType pieceType;

    public Piece(String position, PieceType pieceType) {

        this.position = position;
        this.pieceType = pieceType;

    }

    public Piece() {

        this.position = null;
        this.pieceType = null;

    }

    public String getPosition() {
        return position;
    }

    public PieceType getType(){return pieceType; }

    public void setType(PieceType pieceType) {
        this.pieceType = pieceType;
    }
}
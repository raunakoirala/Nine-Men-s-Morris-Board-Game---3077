public class Move {
    private String startPosition;
    private String endPosition;
    private PieceType type;

    public Move(String startPosition, String endPosition, PieceType type) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.type = type;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public PieceType getType() {
        return type;
    }
}
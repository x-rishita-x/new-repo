package chess;

public class Spot {
    private Piece piece;
    private int x;
    private int y;

    public Spot(int x, int y, Piece piece) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }
}

package chess;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (Math.abs(x1 - x2) == 1 && y1 == y2) {
            return true;
        } else if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) {
            return true;
        } else {
            return false;
        }
    }
}

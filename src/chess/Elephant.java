package chess;

public class Elephant extends Piece {
    public Elephant(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();


        if (x1 == x2 && y1 != y2) {

            int minY = Math.min(y1, y2) + 1;
            int maxY = Math.max(y1, y2);
            for (int y = minY; y < maxY; y++) {
                if (board.getBox(x1, y).getPiece() != null) {
                    return false;
                }
            }
        } else if (y1 == y2 && x1 != x2) {

            int minX = Math.min(x1, x2) + 1;
            int maxX = Math.max(x1, x2);
            for (int x = minX; x < maxX; x++) {
                if (board.getBox(x, y1).getPiece() != null) {
                    return false;
                }
            }
        } else {
            return false;
        }


        if (end.getPiece() == null || end.getPiece().isWhite() != start.getPiece().isWhite()) {
            return true;
        }

        return false;
    }

}

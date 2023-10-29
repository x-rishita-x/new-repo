package chess;

public class Horse extends Piece {
    public Horse(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        int deltaX = Math.abs(x1 - x2);
        int deltaY = Math.abs(y1 - y2);


        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}

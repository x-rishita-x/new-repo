package chess;
public class Soldier extends Piece {
    public Soldier(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        boolean isWhite = start.getPiece().isWhite();


        if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
            return false;
        }

        if(isWhite){
            if(y1==y2){
                if(x2-x1==1){
                    return true;
                }
            }
            else{
                if(x2-x1==1&&(y2-y1==1||y2-y1==-1)){
                    return true;
                }
            }
        }
        else{
            if(y1==y2){
                if(x2-x1==-1){
                    return true;
                }
            }
            else{
                if(x2-x1==-1&&(y2-y1==1||y2-y1==-1)){
                    return true;
                }
            }
        }

        return false;
    }

}

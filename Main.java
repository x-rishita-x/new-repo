import java.util.*;
import java.lang.Exception;

//spot represents a cell on the chess board
class Spot {

         private Piece piece;
         private int x;
         private int y;

         public Spot(int x, int y, Piece piece)
         {
             this.piece=piece;
             this.x = x;
             this.y=y;
         }

         public Piece getPiece()
         {
             return this.piece;
         }
         public int getX()
         {
             return this.x;
         }
         public int getY()
         {
             return this.y;
         }
}

public abstract class Piece {

    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white)
    {
        this.setWhite(white);
    }

    public boolean isWhite()
    {
        return this.white;
    }

    public void setWhite(boolean white)
    {
        this.white = white;
    }

    public boolean isKilled()
    {
        return this.killed;
    }

    public void setKilled(boolean killed)
    {
        this.killed = killed;
    }

    public abstract boolean canMove(Board board,
                                    Spot start, Spot end);
}
class King extends Piece{
    int x1,x2;
    int y1,y2;
    public  boolean canMove(Board board,
                                    Spot start, Spot end){

    }
}

abstract class  Game{

}

abstract class Player{

}
abstract class PlayerEngine{

}


public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}
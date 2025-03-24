
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//Name: Joel A.

//INTRODUCING Ace:
    /*
     * The Ace introduces a new wild card to your chess tactics and strategy 
     * It allows you to get your pieces out of danger or make them become the danger places with any other piece on the board you control on the
     * board by simply dragging it to the square of a piece you control
     */
/*
*YOU WILL NEED TO MODIFY YOUR MOUSERELEASED TO GET THE ACE PIECE TO WORK 
*CHECK THE MOUSE RELEASED FOR ACE FILE TO KNOW WHAT TO ADD/EDIT
*/
public class Ace extends Piece{
    private BufferedImage img;
    
    public Ace(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }
    
    
  
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      ArrayList<Square> controlledSquares = new ArrayList<Square>();
      return controlledSquares;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //INTRODUCING Ace:
    /*
     * The Ace introduces a new wild card to your chess tactics and strategy 
     * It allows you to get your pieces out of danger or make them become the danger places with any other piece on the board you control on the
     * board by simply dragging it to the square of a piece you control
     */
    public ArrayList<Square> getLegalMoves(Board b, Square start){
    	ArrayList<Square> moves = new ArrayList<Square>();
      
      for (Square [] row: b.getSquareArray()){
        for (Square potentialMove: row) {
          //Gets rid of swapping with itself as a move
          if ((potentialMove.getOccupyingPiece() != null) && 
          (potentialMove != start) && 
          (potentialMove.getOccupyingPiece().getColor() == start.getOccupyingPiece().getColor())) {
            moves.add(potentialMove);
          }
        }
      }
      

      return moves;
    }

    public String toString() {
      if (this.getColor()) {
        return "A White Ace";
      }

      return "A Black Ace";

    }
}
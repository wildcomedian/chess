// Eli Geller
// Rook Rocket
// Can move just like a normal rook, but has a 20% chance of exploding and killing every 
// piece within a 1 block radius of it, including itself and it's own color pieces

//                              *IMPORTANT*
// *If you want this to work, add the following code to your mouseReleased method
// inside the loop through all the legal moves, otherwise it will move like a normal rook:

/* if (currPiece instanceof RookRocket){
    endSquare.put(fromMoveSquare.getOccupyingPiece());
    fromMoveSquare.put(null); 
    if (Math.random() < .2) {
        if (endSquare.getRow() - 1 >= 0) {
            board[endSquare.getRow()-1][endSquare.getCol()].put(null);
        }
        if (endSquare.getRow() + 1 <= 7) {
            board[endSquare.getRow()+1][endSquare.getCol()].put(null);
        }
        if (endSquare.getCol() - 1 >= 0) {
            board[endSquare.getRow()][endSquare.getCol()-1].put(null);
        }    
        if (endSquare.getCol() + 1 <= 7) {
            board[endSquare.getRow()][endSquare.getCol()+1].put(null);
        }
        if (endSquare.getRow() - 1 >= 0 && endSquare.getCol() - 1 >= 0) {
            board[endSquare.getRow()-1][endSquare.getCol()-1].put(null);
        }
        if (endSquare.getRow() - 1 >= 0 && endSquare.getCol() + 1 <= 7) {
            board[endSquare.getRow()-1][endSquare.getCol()+1].put(null);
        }
        if (endSquare.getRow() + 1 <= 7 && endSquare.getCol() - 1 >= 0) {
            board[endSquare.getRow()+1][endSquare.getCol()-1].put(null);
        }
        if (endSquare.getRow() + 1 <= 7 && endSquare.getCol() + 1 <= 7) {
            board[endSquare.getRow()+1][endSquare.getCol()+1].put(null);
        }
        board[endSquare.getRow()][endSquare.getCol()].put(null);
    }
}
*/

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class RookRocket extends Piece {
    
    public RookRocket(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }

    //prints the name (class) and color of the piece
    public String toString() {
        String color;
        if (this.getColor() == false) {
            color = "Black";
        }
        else {
            color = "White";
        }
        return "A " + color + " " + "Rook Rocket";
    }
    
    //TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      
      ArrayList<Square> controlledSquares = new ArrayList<Square>();

      //to the right in a column
      for (int i = 1; i < 8; i++) {
          if (start.getCol() + i < 8) {
              Square currSquare = board[start.getRow()][start.getCol() + i];
              if (currSquare.isOccupied()) {
                  controlledSquares.add(currSquare);
                  break;
              }
              controlledSquares.add(currSquare);
          } 
          else {
            break;
          }
      }
  
      //to the left in a column
      for (int i = 1; i < 8; i++) {
          if (start.getCol() - i >= 0) {
              Square currSquare = board[start.getRow()][start.getCol() - i];
              if (currSquare.isOccupied()) {
                  controlledSquares.add(currSquare);
                  break;
              }
              controlledSquares.add(currSquare);
          } 
          else {
            break;
          }
      }
  
      //down in rows
      for (int i = 1; i < 8; i++) {
          if (start.getRow() + i < 8) {
              Square currSquare = board[start.getRow() + i][start.getCol()];
              if (currSquare.isOccupied()) {
                  controlledSquares.add(currSquare);
                  break;
              }
              controlledSquares.add(currSquare);
          } 
          else {
            break;
          }
      }
  
      //up in rows
      for (int i = 1; i < 8; i++) {
          if (start.getRow() - i >= 0) {
              Square currSquare = board[start.getRow() - i][start.getCol()];
              if (currSquare.isOccupied()) {
                  controlledSquares.add(currSquare);
                  break;
              }
              controlledSquares.add(currSquare);
          } 
          else {
            break;
          }
      }
      return controlledSquares;
  }
  
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //the piece is a rook with a bomb (rook rocket), it moves like a normal rook but has a 15% chance of exploding and 
    //destroying itself and the 8 pieces around it
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
    ArrayList<Square> moves = new ArrayList<Square>();

    for (int i = 1; i < 8; i++) {
        if (start.getCol() + i < 8) {
            Square currSquare = b.getSquareArray()[start.getRow()][start.getCol() + i];
            if (currSquare.getOccupyingPiece() != null) {
                if (start.getOccupyingPiece().getColor() != currSquare.getOccupyingPiece().getColor()) {
                    moves.add(currSquare); 
                }
                break; 
            }
            moves.add(currSquare);
        } 
        else {
          break;
        }
    }

    for (int i = 1; i < 8; i++) {
        if (start.getCol() - i >= 0) {
            Square currSquare = b.getSquareArray()[start.getRow()][start.getCol() - i];
            if (currSquare.getOccupyingPiece() != null) {
                if (start.getOccupyingPiece().getColor() != currSquare.getOccupyingPiece().getColor()) {
                    moves.add(currSquare);
                }
                break;
            }
            moves.add(currSquare);
        } 
        else {
          break;
        }
    }

    // Moving down
    for (int i = 1; i < 8; i++) {
        if (start.getRow() + i < 8) {
            Square currSquare = b.getSquareArray()[start.getRow() + i][start.getCol()];
            if (currSquare.getOccupyingPiece() != null) {
                if (start.getOccupyingPiece().getColor() != currSquare.getOccupyingPiece().getColor()) {
                    moves.add(currSquare);
                }
                break;
            }
            moves.add(currSquare);
        } 
        else {
          break;
        }
    }

    for (int i = 1; i < 8; i++) {
        if (start.getRow() - i >= 0) {
            Square currSquare = b.getSquareArray()[start.getRow() - i][start.getCol()];
            if (currSquare.getOccupyingPiece() != null) {
                if (start.getOccupyingPiece().getColor() != currSquare.getOccupyingPiece().getColor()) {
                    moves.add(currSquare);
                }
                break;
            }
            moves.add(currSquare);
        } 
        else {
          break;
        }
    }

    return moves;
}

}
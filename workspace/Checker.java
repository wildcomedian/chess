
//Code by Liam Walker
//Date 3/18/2025
//The following code is for a checkers piece that will interact with other pieces on the chess board
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Checker extends Piece {

	public Checker(boolean isWhite, String img_file) {
		super(isWhite, img_file);
	}
	
	//The following method converts a given Piece object to a string in the format:
	//"I am a (color) (piece name)"
	public String toString() {
		String s = "I am a ";
		if (getColor()) {
			s = s + "white ";
		}
		else {
			s = s + "black ";
		}
		s = s + "piece";
		return s;
	}

	// TO BE IMPLEMENTED!
	// return a list of every square that is "controlled" by this piece. A square is
	// controlled
	// if the piece capture into it legally.
	public ArrayList<Square> getControlledSquares(Board b, Square start) {
		ArrayList<Square> moves = new ArrayList<Square>();
		// for black pieces selects correct controlled squares
		if (!getColor()) {
			if (start.getRow() + 1 < 8) {
				if (start.getCol() + 1 < 8) {
					moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
				}
				if (start.getCol() - 1 > 0) {
					moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
				}
			}

		}
		// for white pieces selects correct controlled squares
		else {
			if (start.getRow() - 1 > 0) {
				if (start.getCol() + 1 < 8) {
					moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
				}
				if (start.getCol() - 1 > 0) {
					moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
				}
			}
		}
		return moves;
	}

	// TO BE IMPLEMENTED!
	// implement the move function here
	// it's up to you how the piece moves, but at the very least the rules should be
	// logical and it should never move off the board!
	// returns an arraylist of squares which are legal to move to
	// please note that your piece must have some sort of logic. Just being able to
	// move to every square on the board is not
	// going to score any points.

	// Piece moves as one does in the game checkers:
	// It can move forward diagonally one space if the space in not occupied
	// Otherwise if that square is occupied by an enemy piece then it can move
	// forward diagonally an additional square as long as that square is not
	// occupied
	public ArrayList<Square> getLegalMoves(Board b, Square start) {
		ArrayList<Square> moves = new ArrayList<Square>();
		// for black pieces selects correct legal move squares
		if (getColor()) {
			if (start.getRow() + 1 < 8) {
				if ((start.getCol() + 1 < 8)
						&& !b.getSquareArray()[start.getRow() + 1][start.getCol() + 1].isOccupied()) {
					moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
				}
				if ((start.getCol() - 1 >= 0) //Joel edit from 1>0  to  1>=0
						&& !b.getSquareArray()[start.getRow() + 1][start.getCol() - 1].isOccupied()) {
					moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
				}
				if (start.getRow() + 2 < 8) {
					if ((start.getCol() + 2 < 8)
							&& !b.getSquareArray()[start.getRow() + 2][start.getCol() + 2].isOccupied()
							&& b.getSquareArray()[start.getRow() + 1][start.getCol() + 1].isOccupied()
							&& (b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]).getOccupyingPiece()
									.getColor()) {
						moves.add(b.getSquareArray()[start.getRow() + 2][start.getCol() + 2]);
					}
					if ((start.getCol() - 2 > 0)
							&& !b.getSquareArray()[start.getRow() + 2][start.getCol() - 2].isOccupied()
							&& b.getSquareArray()[start.getRow() + 1][start.getCol() - 1].isOccupied()
							&& (b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]).getOccupyingPiece()
									.getColor()) {
						moves.add(b.getSquareArray()[start.getRow() + 2][start.getCol() - 2]);
					}
				}
			}

		}
		// for black pieces selects correct legal move squares
		else {
			if (start.getRow() - 1 > 0) {
				if ((start.getCol() + 1 < 8)
						&& !b.getSquareArray()[start.getRow() - 1][start.getCol() + 1].isOccupied()) {
					moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
				}
				if ((start.getCol() - 1 >= 0) //Joel edit from 1>0  to  1>=0
						&& !b.getSquareArray()[start.getRow() - 1][start.getCol() - 1].isOccupied()) {
					moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
				}
				if (start.getRow() - 2 < 8) {
					if ((start.getCol() + 2 < 8)
							&& !b.getSquareArray()[start.getRow() - 2][start.getCol() + 2].isOccupied()
							&& b.getSquareArray()[start.getRow() - 1][start.getCol() + 1].isOccupied()
							&& !(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]).getOccupyingPiece()
									.getColor()) {
						moves.add(b.getSquareArray()[start.getRow() - 2][start.getCol() + 2]);
					}
					if ((start.getCol() - 2 > 0)
							&& !b.getSquareArray()[start.getRow() - 2][start.getCol() - 2].isOccupied()
							&& b.getSquareArray()[start.getRow() - 1][start.getCol() - 1].isOccupied()
							&& !(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]).getOccupyingPiece()
									.getColor()) {
						moves.add(b.getSquareArray()[start.getRow() - 2][start.getCol() - 2]);
					}
				}
			}
		}
		return moves;
	}
}
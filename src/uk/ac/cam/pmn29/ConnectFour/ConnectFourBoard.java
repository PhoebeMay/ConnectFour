package uk.ac.cam.pmn29.ConnectFour;

import javax.swing.JOptionPane;

public class ConnectFourBoard {
	/*
	A 2-D integer array represents the connect-four board. Each player is represented
	by a unique integer, and an empty cell is represented by 0.
	*/
	private int[][] mBoard;

	//Height of the board
	private int mHeight;

	//Width of the board
	private int mWidth;

	//Number of different players using the board
	private int mNumPlayers;

	//Number of chips in a line needed to win the game
	private int mNumToWin;

	public ConnectFourBoard(int w, int h, int toWin, int numP) {
		mWidth = w;
		mHeight = h;
		mBoard = new int[mWidth][mHeight];
		mNumToWin = toWin;
		mNumPlayers = numP;
	}

	/*
	Places a 'chip' in the column specified by the user, filling the column up from the bottom.
	Returns a boolean to signify whether the chip was placed in a 'winning' position or not.
	This boolean is essentially used for control flow.
	*/
	protected boolean placeChip(int col, int playerNum) {

		//Get the sub-array representing the column
		int[] column = mBoard[col];

		//Check if a the column is full
		if (column[0] != 0) {
			JOptionPane.showMessageDialog(null, "This column is full");
			return false;
		}

		//Find the first empty space in the column
		int row = -1;
		for (int index = column.length - 1; index >= 0; index--) {
			if (column[index] == 0) {
				column[index] = playerNum;
				row = index;
				break;
			}
		}

		return (isNLine(mNumToWin, playerNum, col, row));
	}

	//Returns the integer associated with a position in the board
	protected int getCell(int col, int row) {

		//Always returns an empty cell (0) if the cell is outside the board
		if ((col > mWidth - 1) || (col < 0) || (row > mHeight - 1) || (row < 0)) {
			return 0;
		}
		return mBoard[col][row];
	}

	//For debugging (prints the board as text to stdout)
	protected void printBoard() {
		System.out.println(mBoard.length);
		for (int[] cols : mBoard) {
			for (int val : cols) {
				System.out.print(val);
			}
			System.out.println();
		}
	}

	//Determines whether a chip is in a straight line with n other chips of the same type
	private boolean isNLine(int n, int playerNum, int col, int row) {

		//Calculates the number of chips of each type along all lines through the cell
		int posGradDiag = getNumDirection(playerNum, col, row, 1, 1) + getNumDirection(playerNum, col, row, -1, -1) + 1;
		int negGradDiag = getNumDirection(playerNum, col, row, 1, -1) + getNumDirection(playerNum, col, row, -1, 1) + 1;
		int verts = getNumDirection(playerNum, col, row, 0, 1) + getNumDirection(playerNum, col, row, 0, -1) + 1;
		int horis = getNumDirection(playerNum, col, row, 1, 0) + getNumDirection(playerNum, col, row, -1, 0) + 1;

		return (posGradDiag >= n) || (negGradDiag >= n) || (verts >= n) || (horis >= n);
	}

	/*
	Returns the number of chips found of the same type as the specified cell, along
	a specified vector [xdir, ydir].
	*/
	private int getNumDirection(int playerNum, int col, int row, int xdir, int ydir) {
		if (getCell(col + xdir, row + ydir) == playerNum) {
			return 1 + getNumDirection(playerNum, col + xdir, row + ydir, xdir, ydir);
		} else return 0;
	}

	int getWidth() {
		return mWidth;
	}

	int getHeight() {
		return mHeight;
	}

	int getNumPlayers() {
		return mNumPlayers;
	}

	//Tests whether the board is completely full
	boolean isFull() {
		for (int[] col : mBoard) {
			if (col[0] == 0) {
				return false;
			}
		}
		return true;
	}
}

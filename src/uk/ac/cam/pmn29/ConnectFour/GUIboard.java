package uk.ac.cam.pmn29.ConnectFour;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;


class GUIboard extends JFrame {
	//ConnectFourBoard to be displayed
	private ConnectFourBoard mBoard;
	
	//JPanel to display the board graphically
	private GamePanel mGamePanel;
	
	//JLabel used to tell the user(s) whose turn it is
	private JLabel mCurrentPlayerLabel;
	
	//Integer used to keep track of player whose turn it is 
	private int mCurrentPlayer;
	
	//The number of players (= the greatest player number)
	private int mMaxPlayer;

	
	GUIboard(ConnectFourBoard b){
		//Sets up the name of the window
		super("Connect N!");
		
		//Initialise the stored board
		mBoard = b;
		
		//Set the current player to the first player - ie player 1
		mCurrentPlayer = 1;
		
		//Update the maximum player value
		mMaxPlayer = mBoard.getNumPlayers();
		
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1024,768);
        
        //Add components to the JFrame
        add(createGamePanel(),BorderLayout.CENTER);
        add(createControlPanel(),BorderLayout.SOUTH);
        add(createPlayingPanel(),BorderLayout.NORTH);
	}
	
	//Creates a panel to display whose turn it is
	private JPanel createPlayingPanel(){
		JPanel nowPlaying = new JPanel();
		mCurrentPlayerLabel = new JLabel("It is player "+mCurrentPlayer+"'s turn");
		nowPlaying.add(mCurrentPlayerLabel);
		return nowPlaying;
	}


	//Creates a panel to allow the current player to place a chip
	private JPanel createControlPanel() {
		JPanel ctrl =  new JPanel(new GridLayout(1,3));
		
		//Takes text input of the column chosen
		JTextField colNum = new JTextField();
		
		//Adds a description of what to put in the JTextField
		ctrl.add(new JLabel(" Place in column "));
		ctrl.add(colNum);
		
	    JButton playChip = new JButton("Place");
	    playChip.addActionListener(e->{
	    	
	    	int col;
	    	
	    	//Input sanitation
	    	
	    	try{
	    		col = Integer.parseInt(colNum.getText());
	    	}
	    	
	    	catch( NumberFormatException expn){
	    		JOptionPane.showMessageDialog(null, "Invalid number, please try again");
	    		return;
	    	}
	    	
	    	if ( (col <= 0) || (col > mBoard.getWidth()) ){
	    		JOptionPane.showMessageDialog(null, "Column out of range");
	    		return;
	    	}
	    	
	    	
	    	//Place the chip and check if the player wins
	    	if (mBoard.placeChip(col-1,mCurrentPlayer)){
	    		printBoard();
	    		win(mCurrentPlayer);
	    		return;
	    	}
	    	
	    	printBoard();
	    	
	    	//Check to see if the move caused the board to become full 
	    	if (mBoard.isFull()){
	    		JOptionPane.showMessageDialog(null, "No possible moves, game is a draw");
	    		ResetGame();
	    		return;
	    	}
	    	
	    	//Move on to the next player
	    	mCurrentPlayer = mCurrentPlayer + 1 ;
	    	if (mCurrentPlayer > mMaxPlayer){
	    		mCurrentPlayer = 1;
	    	}
	    	
	    	//Update the text to tell the users who the next player is
	    	updateNextPlayer();
	    	
	    	
	    });
	    ctrl.add(playChip);
	    return ctrl;
	}

	//End the game due to a player winning
	private void win(int mCurrentPlayer) {
		JOptionPane.showMessageDialog(null, "Player " + mCurrentPlayer +" wins!");
		ResetGame();
	}

	//Delete the current game, and start another
	private void ResetGame() {
		this.dispose();
		StartFrame start = new StartFrame();
		start.setVisible(true);
	}
	
	//Create a new GamePanel to display the board
	private GamePanel createGamePanel() {
		mGamePanel = new GamePanel();
	    return mGamePanel;
	}
	
	//Update the text to tell the users who the next player is
	private void updateNextPlayer(){
	 	mCurrentPlayerLabel.setText("It is player "+mCurrentPlayer+"'s turn");
	}
	
	//Display the board
	 private void printBoard(){
	    	mGamePanel.display(mBoard);
	    }
	 
	//Display the board initially (when not visible)
	void play() {
		setVisible(true);
		printBoard();
	    }
	 
	 

	
}

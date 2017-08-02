package uk.ac.cam.pmn29.ConnectFour;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    //The connect four board  to be displayed
    private ConnectFourBoard mBoard;
    
    @Override
    protected void paintComponent(java.awt.Graphics g) {
    	
        //Get dimensions of the window
    	int window_width = this.getWidth();
    	int window_height = this.getHeight();
    	
    	//Fill window with white
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, window_width, window_height);
       
        //If the board associated with this class has not been initialised, do nothing
        if (mBoard == null){
        	return;
        }    	
    	
        //Get dimensions of the board
    	int game_width = mBoard.getWidth();
    	int game_height = mBoard.getHeight();
    	
    	//Get width of one square cell
    	float draw = Math.min( ((float) window_width /game_width) , ((float)window_height/game_height) );
    	
    	//Draw the cells from the board
    	for (int col=0; col < game_width; col++ ){
	    	for (int row=0; row < game_height; row++){
	    		drawCell(g, (int) (col*draw), (int) (row*draw), 
	    					(int) ( (col+1)*draw - (col*draw)),
	    					(int) ( (row+1)*draw - (row*draw)),
	    					mBoard.getCell(col, row));
	    	}
    	}
    
    	//Draw bottom and right edges in (needed because each cell only drawn with two lines)
    	g.setColor(Color.LIGHT_GRAY);
    	g.drawLine((int) (game_width*draw), 0, (int) (game_width*draw), (int) (game_height*draw));
        g.drawLine(0, (int) (game_height*draw), (int) (game_width*draw) ,(int) (game_height*draw));
        
    }

    //Draws a cell and colours it depending on the player
    private void drawCell(java.awt.Graphics g, int x, int y, int w, int h, int playerNum){
    	
    	//Choose colour depending on the player (getting nice colours here is the reason I chose a limit of 10 players)
        List<Color> colours = new ArrayList<Color>() {{ 
        	add(Color.white);
        	add(Color.green);
        	add(Color.magenta);
        	add(Color.red);
        	add(Color.blue);
        	add(Color.cyan);
        	add(Color.pink);
        	add(Color.orange);
        	add(Color.DARK_GRAY);
        	add(new Color(138,43,226));
        	add(new Color(176,224,230));
        	}};
        g.setColor(colours.get(playerNum));
        
        //Fill in the centre of the cell
	    g.fillRect(x, y, w+1, h+1);
        
        //Draw the outline of the cell
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(x, y, x+w, y);
        g.drawLine(x, y, x, y+h);
    }
    
    protected void display(ConnectFourBoard board) {
        mBoard = board;
        repaint();
    }
}
package uk.ac.cam.pmn29.ConnectFour;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartFrame extends JFrame{
	
	public StartFrame(){
		//Sets the name of the window
		super("Connect N!");
		
		//Sets the size of the window
        setSize(500,500);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createInputField());
	}
	
	//Creates an input form to take the information needed to start the game
	private JPanel createInputField() {
	    JPanel inputPanel = new JPanel();	    
	    inputPanel.setLayout(new GridLayout(5,2));

	    JTextField width = new JTextField();
	    JTextField height = new JTextField();
	    JTextField numplay = new JTextField();
	    JTextField winLength = new JTextField();
	    JButton go = new JButton("Play");
	    
	    //Controls what happens when the button is pressed
	    go.addActionListener(e->{
	    	try{
	    		
	    	 //Getting information from text fields
	    	 int w = Integer.parseInt(width.getText());
	    	 int h = Integer.parseInt(height.getText());
	    	 int players = Integer.parseInt(numplay.getText());
	    	 int toWin = Integer.parseInt(winLength.getText());
	    	 
	    	 //Various input sanitisation 
	    	 
	    	 if ((w<1)||(h<1)||(players<1)||(toWin<1)){
	    		 JOptionPane.showMessageDialog(null, "All input values must be greater than 0");
	    		 return;
	    	 }
	    	 
	    	 if (players > 10){
	    		 JOptionPane.showMessageDialog(null, "Number of players cannot exceed 10");
	    		 return;
	    	 }
	    	 
	    	 if (toWin > Math.ceil(Math.sqrt(w*w + h*h))){
	    		 JOptionPane.showMessageDialog(null, "Length to win too large, game not winnable");
	    		 return;
	    	 }
	    	 
	    	 //Initialise game
	    	 ConnectFourBoard b = new ConnectFourBoard(w,h,toWin,players);
	    	 GUIboard bg = new GUIboard(b);
	    	 bg.play();
	    	 this.dispose();
	    	 
	    	}
	    	
	    	//Catch any cases where the user does not input a number
	    	catch( NumberFormatException expn){
	    		JOptionPane.showMessageDialog(null, "Input not a valid number, please try again");
	    	}
	    	
	    }    		
	   );
	    
	    //Add text to match the input forms
	    inputPanel.add(new JLabel(" Width"));
	    inputPanel.add(width);
	    inputPanel.add(new JLabel(" Height"));
	    inputPanel.add(height);
	    inputPanel.add(new JLabel(" Number in a line to win"));
	    inputPanel.add(winLength);
	    inputPanel.add(new JLabel(" Numer of Players (max 10)"));
	    inputPanel.add(numplay);
	    inputPanel.add(new JLabel(""));
	    inputPanel.add(go);
	    
	    return inputPanel;
	}
	
	//Main method called to initialise the whole application
    public static void main(String[] args){
    	StartFrame frame = new StartFrame();
		frame.setVisible(true);
    }
     
}

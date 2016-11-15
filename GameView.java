import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.*;

//GameController gameController;
public class GameView extends JFrame{
	GameModel model;
	GameController gameController;
	JPanel p;
	JPanel otherButtons;
	JButton reset;
	JButton quit;
	BoardView boardView;
	public GameView(GameModel model, GameController gameController){
		super("Circle the Dot");
		this.model=model;
		this.gameController=gameController;
		// Setting for whole JFrame
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Creating a new board panel
		boardView = new BoardView(model,gameController);
		
		//Adding quit and reset buttons to another JPanel
		reset = new JButton ("Reset");
		reset.addActionListener(gameController);
		quit = new JButton ("Quit");
		quit.addActionListener(gameController);

		otherButtons = new JPanel();
		otherButtons.setLayout(new FlowLayout());
		otherButtons.add(reset);
		otherButtons.add(quit);
		otherButtons.setBackground(Color.WHITE);
		//Final panel containing the board and reset/quit buttons
		p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add (boardView);
		p.add(otherButtons);
		setVisible (true);
		setResizable(false);
		//Adding final panel to JFrame as a whole
		add(p);
		pack();
	}
	public BoardView getBoardView(){
		return boardView;
	}	
}
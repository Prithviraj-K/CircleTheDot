import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class BoardView extends JPanel{
	GameModel model;
	GameController gameController;
	DotButton[][] dots;
	JPanel[] panels;
	DotButton x;
	JPanel[] rows;
	public BoardView(GameModel model, GameController gameController){
		setBackground(Color.WHITE);
		this.model = model;
		this.gameController = gameController;
		setLayout(new GridLayout(model.size,1));

		rows = new JPanel [model.size];
		dots = new DotButton [model.size][model.size];

		for (int i=0; i<model.size; i++){
			rows[i]=new JPanel();
			rows[i].setBackground(Color.WHITE);
			rows[i].setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
			if ((i%2)==0){
				rows[i].setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
			}
			else if ((i%2)==1){
				rows[i].setBorder(BorderFactory.createEmptyBorder(0,25,0,5));
			}
			for (int j=0; j<model.size;j++){
				x = new DotButton (i,j,model.dotArray[i][j]);
				dots [i][j]=x;

				x.setMargin(new Insets(0, 0, 0, 0));
				x.setBackground(Color.WHITE);
				x.setBorderPainted (false);
				x.setContentAreaFilled (false);
				x.addActionListener(gameController);
				rows[i].add(x);
			}
			add(rows[i]);
		}
	}
	public void update(){
		for (int i=0; i<model.size;i++){
			for (int j=0; j<model.size;j++){
				if (model.getCurrentStatus(i,j)==0){
					dots[i][j].setType(0);
				}
				else if (model.getCurrentStatus(i,j)==1){
					dots[i][j].setType(1);
				}
				else if (model.getCurrentStatus(i,j)==2){
					dots[i][j].setType(2);
				}
			}
		}
	}
}
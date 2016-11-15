import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
public class DotButton extends JButton{
	ImageIcon icon;
	int row;
	int column;
	int type;
	

	public DotButton(int row, int column,int type){
		this.row=row;
		this.column=column;
		this.type=type;
		setType(type);
	}
	public void setType(int t){
		type=t;
		if (t ==0){
			icon=new ImageIcon("data/ball-0.png");
		}
		else if (t==1){
			icon=new ImageIcon("data/ball-1.png");
		}

		else if (t==2){
			icon=new ImageIcon("data/ball-2.png");
		}
		setIcon(icon);
	}
	public int getRow(){
		return(row);
	}
	public int getColumn(){
		return(column);
	}
}
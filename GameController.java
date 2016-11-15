import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
public class GameController implements ActionListener{
	private GameModel gameModel;
	private GameView gameView;
	private int size;
	private int xx;
	private int yy;
	private List<String>ll;
  private List<String>ll2;
  private List<String>ll3;
  private String nodename;
  private int finaly=1;
  private int finalx=1;
	public GameController (int size){
		this.size=size;
	}
	public void start(){
		gameModel = new GameModel (size);
		gameView = new GameView (gameModel,this);
	}
	public void reset(){
		gameView.dispose();
		start();
	}
	public void actionPerformed(ActionEvent e){
		Thread hilo = new Thread (new Runnable(){
			@Override
			public void run(){
				if ( e.getSource() == gameView.reset) {
					reset();
				} 
				else if ( e.getSource() == gameView.quit) {
					gameView.dispose();
				}
				else if(e.getSource() instanceof DotButton){
        			DotButton x = (DotButton) e.getSource();
        			//coordinates of the click
        			int xval = x.row;
        			int yval = x.column;
        			//set dot to orange only if dot is available
        			if (gameModel.getCurrentStatus(xval,yval)==0){
        				x.setType(1); // updates icon of the clicked point to orange
        				gameModel.select(xval,yval); // makes the clicked point orange if available
        				//get points from original blue dot
        				xx = gameModel.getCurrentDot().getX();
    					yy = gameModel.getCurrentDot().getY();
    					int shortestpath = thereispath(xx,yy,gameModel.dotArray); // Runs BFS to find int shortest path
    					int index = (ll.size())-1;
    					if(shortestpath<500){		// if the blue dot is not surrounded by orange dots
    						ll3.add(ll.get(index));				// making ll3 the points that the blue dot travels fastest
    						for(int i=0;i<=shortestpath;i++){	//^
      							nodename = ll2.get(index);		//^
      							index = ll.indexOf(nodename);	//^
      							ll3.add(nodename);				//^
    						}
    						int sizeofll3 = ll3.size()-3;		// removes first two points because first is 00 and next is bluedot itself
    						String result = ll3.remove(sizeofll3);	// makes the first point bluedot moves into string
    						String [] finale = result.split("\\.");	 // splits it at the x and y coordinates and stores at [0] and [1]
    						finalx = Integer.parseInt(finale[0]);
    						finaly = Integer.parseInt(finale[1]);
    					}
    					else{	// if bluedot is surrounded by orange dots, the shortestpath is very huge
    						JFrame frame = new JFrame();
    						JOptionPane.showMessageDialog(frame, "You have won the game! It took you "+ gameModel.getNumberOfSteps()+" tries. Resetting the Board.");
    						reset();
    					}
    					gameModel.setAvailable(xx,yy);	//make original dot AVAILABLE
    					gameModel.setCurrentDot(finalx,finaly);  //make the new bfs point the new dots
    					gameView.getBoardView().update();  //update the board for new view
    					if(finalx==0 || finalx==size-1 || finaly==0 || finaly==size-1){   // if you have lost and bluedot reaches edges
    						JFrame frame = new JFrame();
    						JOptionPane.showMessageDialog(frame, "You have lost the game. Resetting the Board.");
    						reset();
    					}
        			}
    			}
			}
		});
		hilo.start();
	}

	//will return shortest path along with two lists, one with nodes and previous nodes    DONE DONT TOUCH!!!!!!
	private int thereispath(int curx, int cury, int [][]arr){
    	Queue<Integer>q=new LinkedList<Integer>();
    	ll=new ArrayList<String>();
    	ll2=new ArrayList<String>();
    	ll3=new ArrayList<String>();
    	ll2.add("0.0");
    	q.add(curx);
    	q.add(cury); 
    	q.add(0); //adding 1 value means length has increased by 1
    	int shortest = Integer.MAX_VALUE; //to store the shortest path
    	//for even rows
    	int[]dx1={0,0,-1,-1,1,1};
    	int[]dy1={-1,1,-1,0,-1,0};
    	//for odd rows
    	int[]dx2={0,0,-1,-1,1,1};
    	int[]dy2={-1,1,0,1,0,1};
    	//add beggining points as checked
    	ll.add(curx+"."+cury);
    	while(!q.isEmpty()){
      		int x=q.poll();
      		int y=q.poll(); 
      		int len=q.poll();
      		//even row moves differently
      		if(x%2==0){
        		for(int i=0;i<6;i++){
          			int xx=x+dx1[i];
          			int yy=y+dy1[i];
          			if(xx>=0 && yy>=0 && xx<size && yy<size){
            			if (((ll.indexOf(xx+"."+yy)<0) && (gameModel.getCurrentStatus(xx,yy)==0))){                
              				if(xx==0 || xx==(size-1) || yy==0 || yy==(size-1)){
                				shortest=Math.min(shortest,len+1);
                				ll.add(xx+"."+yy);
                				ll2.add(x+"."+y);
                				return shortest;
              				}
              				q.add(xx);
              				q.add(yy);
              				q.add(len+1);//this keep track of the length
              				ll.add(xx+"."+yy);
              				ll2.add(x+"."+y);
            			}
          			}
        		}
      		}
      		//odd row moves differently
      		else if (x%2==1){
        		for(int i=0;i<6;i++){
          			int xx=x+dx2[i];
          			int yy=y+dy2[i];
          			if(xx>=0 && yy>=0 && xx<size && yy<size){
            			if (((ll.indexOf(xx+"."+yy)<0) && (gameModel.getCurrentStatus(xx,yy)==0))){                
              				if(xx==0 || xx==(size-1) || yy==0 || yy==(size-1)){
                				shortest=Math.min(shortest,len+1);
                				ll.add(xx+"."+yy);
                				ll2.add(x+"."+y);
                				return shortest;
              				}
              				q.add(xx);
              				q.add(yy);
              				q.add(len+1);//this keep track of the length
              				ll.add(xx+"."+yy);
              				ll2.add(x+"."+y);
            			}
          			}
        		}
      		}
    	}
    	return (shortest);//returning shortest path for each case
  	}
}
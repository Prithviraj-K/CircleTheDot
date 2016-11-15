import java.util.Random;

public class GameModel{
  public static final int AVAILABLE = 0;
  public static final int SELECTED = 1;
  public static final int DOT = 2;
  //Point blue;

  int numberOfSteps;
  
  int size ;
  
  Random test = new Random();
  int[][] dotArray;

  Point bluedot;
  
  public GameModel(int size){
    this.size=size;
    dotArray=new int[size][size];
    for (int i=0;i<size;i++){
      for(int j=0;j<size;j++){
        if(rand(0,9)==9){
          dotArray[j][i]=SELECTED;
        }
        else{
          dotArray[j][i]=AVAILABLE;
        }
      }
    }

    if ((size%2)==0){
      int y=rand((size/2)-1, (size/2));
      int z= rand((size/2)-1, (size/2));
      dotArray[y][z]=DOT;
      bluedot = new Point (y,z);
    }
    else if ((size%2)==1){
      int y = rand((size/2)-1,(size/2)+1);
      int z = rand((size/2)-1,(size/2)+1);
      dotArray[y][z]=DOT;
      bluedot = new Point (y,z);
    }
  }
  private int rand(int min, int max){
    int randomNumber=test.nextInt((max-min)+1)+min;
    return randomNumber;
  }
  
  public int getCurrentStatus(int i, int j){
    return (dotArray[i][j]);
    
  }
  public int getNumberOfSteps(){
    return(numberOfSteps);
    
  }
  public int getSize(){
    return(size);
  }
  public void select(int i, int j){
    dotArray[i][j]=SELECTED;
    numberOfSteps+=1;
  }
  public void setCurrentDot(int i, int j){
    dotArray[i][j]=DOT;
    bluedot.reset(i,j);
  }
  public void setAvailable(int i, int j){
    dotArray[i][j]=AVAILABLE;
  }

  public Point getCurrentDot(){
    return(bluedot);
  }
}
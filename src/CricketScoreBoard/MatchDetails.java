
package CricketScoreBoard;

public class MatchDetails
{
    //Both the team players list    
    static final String[] teamAPlayersList=new String[]{"Nithish","Vignesh","Rajesh","Prasanth","Harish","Raja","Naveen","Mathi","Deevakar","Krishna","Ajay"}; 
    static final String[] teamBPlayersList=new String[]{"Balaji","Rahul","Pradeep","Sakthi","Sibi","Aadhi","Vasanth","Gobi","Kavi","Sriram","Hari"}; 
    
    //No of Overs
    final int over=2;
    
    //battingDetails array is batsman scoring details like "index 0->run index 1-> 4's index 2-> 6's index 3-> Ball Count"
    final int[] batterDetails=new int[]{0,0,0,0}; 
    
    //bowlingDetails array is bowler scoring details like "index 0->run index 1-> Wickets index 2-> Ball Count"
    final int[] bowlerDetails=new int[]{0,0,0};
    
    final int[] tossInputChecker=new int[]{0,1};            //details like index 0-> head , index ->1 tail
    
    final int[] fieldInputChecker=new int[]{1,2};           //details like intdex 0-> batting, index 1->bowling
    
    final int[] ballOutComeInputChecker=new int[]{1,2,3};   //details like index 0->run ,index 1-> Extras , index 2->wickets
    
    final int[] viewOptionChecker=new int[]{1,2,3};         //details like index 0->view player name, index 1->view scoreboare,index 2->terminate the class
    
    final int[] runInputChecker=new int[]{0,1,2,3,4,6};     //details like runs 
    
    final int[] teamNameChecker=new int[]{1,2};             //details like index 0 -> view the Team A Score Board,index ->2 team B Score Board
}



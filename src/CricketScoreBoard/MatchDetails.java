
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
}



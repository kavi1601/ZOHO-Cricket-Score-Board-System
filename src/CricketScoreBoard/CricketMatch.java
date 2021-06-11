
package CricketScoreBoard;

import java.util.Scanner;

public class CricketMatch 
{
    static Scanner scanner=new Scanner(System.in);
    static Play play=new Play();
    static MatchDetails matchDetails=new MatchDetails();
    ScoreBoard scoreBoard=new ScoreBoard();
    
    private void winningDetails(String teamName,int teamScore,String opponentTeamName,int opponentTeamScore)
    {
        if(teamScore==opponentTeamScore)
        {
            System.out.println("\t******** Match Tie ********");
            System.out.println("Team Name : "+teamName+" Team Score : "+teamScore+"\nOpponent Team Name : "+opponentTeamName+" Offonent Team Score :"+opponentTeamScore);
        }
        else if(teamScore>opponentTeamScore)
        {
            System.out.printf("\t******** Team %s Won the Match ********\n",teamName);
            System.out.println("Team Name : "+teamName+" Team Score : "+teamScore+"\nOpponent Team Name : "+opponentTeamName+" Offonent Team Score :"+opponentTeamScore);
        }
        else
        {
            System.out.printf("\t******** Team %s Won the Match ********\n",opponentTeamName); 
            String temp=teamName;teamName=opponentTeamName;opponentTeamName=temp;
            System.out.println("Team Name : "+teamName+" Team Score : "+opponentTeamScore+"\nOpponent Team Name : "+opponentTeamName+" Offonent Team Score :"+teamScore);
        }
        
        System.out.printf("\t\t\tTeam %s Score Board \n",teamName);
        scoreBoard.printScoreBoard(teamName);
        
        System.out.printf("\t\t\tTeam %s Score Board \n",opponentTeamName);
        scoreBoard.printScoreBoard(opponentTeamName);
        
    }
    
    //When teamA bats this function will update batting card of teamA and bowling card of teamB
    public void teamABatting()
    {
        final int teamAScore,teamBScore;
        
        System.out.println("Team A Batting && Team B Bowling");
        //play.gamePlay method returns the teamAScore for first innings
        teamAScore=play.gamePlay(matchDetails.teamAPlayersList, matchDetails.teamBPlayersList, matchDetails.over,"A",null);  
        
        System.out.println("Team A Score :"+teamAScore); 
        System.out.println("Team B Target Score "+(teamAScore+1));
        
        System.out.println("\nTeam B Batting && Team A Bowling");
        //play.gamePlay method returns the teamBScore for second innings
        teamBScore=play.gamePlay(matchDetails.teamBPlayersList, matchDetails.teamAPlayersList, matchDetails.over,"B",teamAScore); 
        
        winningDetails("A",teamAScore,"B",teamBScore);
    }
    
    //When teamB bats this function will update batting card of teamB and bowling card of teamA
    public void teamBBatting()
    {
        final int teamAScore,teamBScore;
        
        System.out.println("Team B Batting && Team A Bowling");
        //play.gamePlay method returns the teamBScore for first innings
        teamBScore=play.gamePlay(matchDetails.teamBPlayersList, matchDetails.teamAPlayersList, matchDetails.over,"B",null);
        
        System.out.println("Team B Score:"+teamBScore);
        System.out.println("Team A Target Score "+(teamBScore+1));
      
        System.out.println("\nTeam A Batting && Team B Bowling");
        //play.gamePlay method returns the teamAScore for second innings
        teamAScore=play.gamePlay(matchDetails.teamAPlayersList, matchDetails.teamBPlayersList, matchDetails.over,"A",teamBScore);
        
        winningDetails("B",teamBScore,"A",teamAScore);
    }
    
    public static void main(String[] args) 
    {
        
        System.out.println("Welcome to the cricketMatch\nChoose the toss 1 or 0");
        int toss=play.getInput(matchDetails.tossInputChecker);//scanner.nextInt(); 
        //predicting the toss result randomly
        int tossResult=(int)(Math.random()*10)%2;
        CricketMatch cricketMatch=new CricketMatch();
    
        //base on the toss result the match preceeds as follows
        //if toss won ->  teamA Bats | teamB Bowls
        //otherwise teamB Bats | teamA Bowls
        if(toss==tossResult)
        {
            System.out.println("Toss won by 'Team A'\nChoose for Batting Enter 1\nChoose for Bowling Enter 2");
            int fieldResult=play.getInput(matchDetails.fieldInputChecker);
            if(fieldResult==1)
            {
                cricketMatch.teamABatting();
            }
            else
            {
                cricketMatch.teamBBatting();
            }
        }
        
        else
        {
            System.out.println("Toss won by 'Team B'\nChoose for Batting Enter 1\nChoose for Bowling Enter 2");
            int fieldResult=play.getInput(matchDetails.fieldInputChecker);
            if(fieldResult==1)
            {
                cricketMatch.teamBBatting();
            }
            else
            {
                cricketMatch.teamABatting();
            }
        }
    }
    
}


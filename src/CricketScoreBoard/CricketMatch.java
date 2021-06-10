
package CricketScoreBoard;

import java.util.Scanner;

public class CricketMatch 
{
    static Scanner scanner=new Scanner(System.in);
    MatchDetails matchDetails=new MatchDetails();
    Play play=new Play();
    ScoreBoard scoreBoard=new ScoreBoard();
    
    private void winningDetails(String teamName,int teamScore,String offonentTeamName,int offonentTeamScore)
    {
        if(teamScore==offonentTeamScore)
        {
            System.out.println("\t******** Match Tie ********");
        }
        else if(teamScore>offonentTeamScore)
        {
            System.out.printf("\t******** Team %s Won the Match ********\n",teamName);
        }
        else
        {
            System.out.printf("\t******** Team %s Won the Match ********\n",offonentTeamName); 
            String temp=teamName;teamName=offonentTeamName;offonentTeamName=temp;
        }
        
        System.out.printf("\t\t\tTeam %s Score Board \n",teamName);
        scoreBoard.printScoreBoard(teamName);
        
        System.out.printf("\t\t\tTeam %s Score Board \n",offonentTeamName);
        scoreBoard.printScoreBoard(offonentTeamName);
        
    }
    
    //When teamA bats this function will update batting card of teamA and bowling card of teamB
    public void teamABatting()
    {
        final int teamAScore,teamBScore;
        
        System.out.println("Team A Batting && Team B Bowling");
        //play.gamePlay method returns the teamAScore for first innings
        teamAScore=play.gamePlay(MatchDetails.teamAPlayersList, MatchDetails.teamBPlayersList, matchDetails.over,"A",null);  
        
        System.out.println("Team A Score :"+teamAScore); 
        System.out.println("Team B Target Runs "+(teamAScore+1));
        
        System.out.println("\nTeam B Batting && Team A Bowling");
        //play.gamePlay method returns the teamBScore for second innings
        teamBScore=play.gamePlay(MatchDetails.teamBPlayersList, MatchDetails.teamAPlayersList, matchDetails.over,"B",teamAScore); 
        
        winningDetails("A",teamAScore,"B",teamBScore);
    }
    
    //When teamB bats this function will update batting card of teamB and bowling card of teamA
    public void teamBBatting()
    {
        final int teamAScore,teamBScore;
        
        System.out.println("Team B Batting && Team A Bowling");
        //play.gamePlay method returns the teamBScore for first innings
        teamBScore=play.gamePlay(MatchDetails.teamBPlayersList, MatchDetails.teamAPlayersList, matchDetails.over,"B",null);
        
        System.out.println("Team B Score:"+teamBScore);
        System.out.println("Team A Target Runs "+(teamBScore+1));
      
        System.out.println("\nTeam A Batting && Team B Bowling");
        //play.gamePlay method returns the teamAScore for second innings
        teamAScore=play.gamePlay(MatchDetails.teamAPlayersList, MatchDetails.teamBPlayersList, matchDetails.over,"A",teamBScore);
        
        winningDetails("B",teamBScore,"A",teamAScore);
    }
    
    public static void main(String[] args) 
    {
        
        System.out.println("Welcome to the cricketMatch\nChoose the toss 1 or 0");
        int toss=scanner.nextInt(); 
        //predicting the toss result randomly
        int tossResult=(int)(Math.random()*10)%2;
        CricketMatch cricketMatch=new CricketMatch();
    
        //base on the toss result the match preceeds as follows
        //if toss won ->  teamA Bats | teamB Bowls
        //otherwise teamB Bats | teamA Bowls
        if(toss==tossResult)
        {
            System.out.println("Toss won by 'Team A'\nEnter 1 for Batting\nEnter 2 for Bowling");
            int rs=scanner.nextInt();
            if(rs==1)
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
            System.out.println("Toss won by 'Team B'\nEnter 1 for Batting\nEnter 2 for Bowling");
            int rs=scanner.nextInt();
            if(rs==1)
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



package CricketScoreBoard;

import java.util.HashMap;

public class ScoreBoard 
{
    //updating the batting and bowling details of both the team
    static HashMap<String,String> battingScoreBoardA;
    static HashMap<String,String> bowlingScoreBoardA;
    static HashMap<String,String> battingScoreBoardB;
    static HashMap<String,String> bowlingScoreBoardB;
    
    public void printScoreBoard(String teamName)
    {
        MatchDetails matchDetails=new MatchDetails();
        switch(teamName)
        {
            
            case "A" -> 
            {
                if(battingScoreBoardA==null)
                {
                    System.out.println("Second Innings NOT Yet Played");
                    break;
                }
                
                System.out.println("-------------------------------------------------------\n\t\tTeam A Batting Score Board");
                System.out.printf("%-29s | Runs | 4's | 6's | Ball\n","Player Name");
                battingScoreBoardA.keySet().forEach(name -> 
                {
                    String[] scoreDetails=battingScoreBoardA.get(name).replaceAll("[\\[\\]\\,]","").split(" ");
                    if(scoreDetails.length==matchDetails.batterDetails.length) 
                        System.out.printf("%-30s|%6s|%5s|%5s|%5s\n",name,scoreDetails[0],scoreDetails[1],scoreDetails[2],scoreDetails[3]);
                    else 
                        System.out.println(name);
                });
                
                System.out.println("-------------------------------------------------------\n\t\tTeam B Bowling Score Board");
                System.out.printf("%-20s| Runs | Wickets | Ball\n","Bowler Name");
                bowlingScoreBoardB.keySet().forEach(name -> 
                {
                    String[] scoreDetails=bowlingScoreBoardB.get(name).replaceAll("[\\[\\]\\,]","").split(" ");
                    if(scoreDetails.length==matchDetails.bowlerDetails.length) 
                        System.out.printf("%-20s|%6s|%9s|%5s\n",name,scoreDetails[0],scoreDetails[1],scoreDetails[2]);
                    else 
                        System.out.println(name);
                });
                System.out.println("-------------------------------------------------------\n");
            }
                
            case "B" -> 
            {
                if(battingScoreBoardB==null)
                {
                    System.out.println("Second Innings NOT Yet Played");
                    break;
                }
                System.out.println("-------------------------------------------------------\n\t\tTeam B Batting Score Board");
                System.out.printf("%-30s| Runs | 4's | 6's | Ball\n","Player Name");
                battingScoreBoardB.keySet().forEach(name -> 
                {
                    String[] scoreDetails=battingScoreBoardB.get(name).replaceAll("[\\[\\]\\,]","").split(" ");
                    if(scoreDetails.length==matchDetails.batterDetails.length) 
                        System.out.printf("%-30s|%6s|%5s|%5s|%5s\n",name,scoreDetails[0],scoreDetails[1],scoreDetails[2],scoreDetails[3]);
                    else 
                        System.out.println(name);
                });
                
                System.out.println("-------------------------------------------------------\n\t\tTeam A Bowling Score Board");
                System.out.printf("%-20s| Runs | Wickets | Ball\n","Bowler Name");
                bowlingScoreBoardA.keySet().forEach(name ->
                {
                    String[] scoreDetails=bowlingScoreBoardA.get(name).replaceAll("[\\[\\]\\,]","").split(" ");
                    if(scoreDetails.length==matchDetails.bowlerDetails.length) 
                        System.out.printf("%-20s|%6s|%9s|%5s\n",name,scoreDetails[0],scoreDetails[1],scoreDetails[2]);
                    else 
                        System.out.println(name);
                });
                
                System.out.println("-------------------------------------------------------\n");
            }
        }
    }
}

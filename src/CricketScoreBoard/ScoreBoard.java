
package CricketScoreBoard;

import java.util.HashMap;
import java.util.Scanner;

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
    
    public void viewDetails()
    {
        
        Scanner scanner=new Scanner(System.in); 
        MatchDetails matchDetails=new MatchDetails();
        Play play=new Play();
        boolean end=false;
        
        OUTER:while (!end) {
            System.out.println("Enter 1 to view the Players name 2 to view the ScoreBoard 3 to terminate the view Board");
            int input=play.getInput(matchDetails.viewOptionChecker);//scanner.nextInt();
            switch (input) 
            {
                //to view the both team players List
                case 1 -> 
                {
                    
                    System.out.println("********** Team A Players Name List **********");
                    for (String teamAPlayersList : matchDetails.teamAPlayersList)
                    {
                        System.out.println("Player Name :" + teamAPlayersList);
                    }   
                    
                    System.out.println("********** Team B Players Name List **********");
                    for (String teamBPlayersList : matchDetails.teamBPlayersList)
                    {
                        System.out.println("Player Name :" + teamBPlayersList);
                    }
                }
                
                //to view the Score Board
                case 2 -> 
                {                    
                    
                    System.out.println("Enter 1 to view the A team Batting Score Board && B team Bowling Score Board\nEnter 2 to view the B team Batting Score Board && A team Bowling Score Board");
                    int teamName=play.getInput(matchDetails.teamNameChecker);//scanner.nextInt();
                    
                    switch(teamName)
                    {
                        case 1 -> printScoreBoard("A"); 
                            
                        case 2 -> printScoreBoard("B");
                    }             
                }
                
                default -> 
                {
                    break OUTER;
                }
            }
        }
    }
}

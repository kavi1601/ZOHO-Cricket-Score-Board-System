
package CricketScoreBoard;

import java.util.Scanner;

public class ViewBoard 
{
    
    public void viewDetails()
    {
        
        Scanner scanner=new Scanner(System.in); 
        MatchDetails matchDetails=new MatchDetails();
        Play play=new Play();
        ScoreBoard scoreBoard=new ScoreBoard();
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
                        case 1 -> scoreBoard.printScoreBoard("A"); 
                            
                        case 2 -> scoreBoard.printScoreBoard("B");
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

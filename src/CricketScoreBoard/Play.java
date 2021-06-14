
package CricketScoreBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Play 
{
        Scanner scanner=new Scanner(System.in);
        //ViewBoard viewBoard=new ViewBoard();
        ScoreBoard scoreBoard=new ScoreBoard();
        MatchDetails matchDetails=new MatchDetails();

        //it returns the Strike Rate of Batsman
        final double strikeRate(int noOfBalls,int noOfRuns)
        {
            if(noOfBalls==0)
                return 0;
            return (noOfRuns*100)/(double)noOfBalls;
        }

        //it returns the team run rate 
        final double runRate(int noOfOvers,int noOfRuns)
        {
            if(noOfOvers==0)
                return 0;
            return noOfRuns/noOfOvers;
        }

        //it prints the batsman score details after the wicket
        private void strikerScoreDetails(String name,int[] array)
        {
            System.out.println("\nPlayer Name :"+name);
            System.out.printf("Runs :%d\tNo of Fours :%d\tNo of Sixs :%d\tNo of Balls :%d\tStrike Rate :%.2f\n",array[0],array[1],array[2],array[3],strikeRate(array[3],array[0]));    
        }

        private void bowlerScoreDetails(String name,int[] array)
        {
            System.out.println("\nBowler Name :"+name);
            System.out.printf("Runs :%d\tNo of Wickets :%d\tNo of Balls :%d\n",array[0],array[1],array[2]);
        }

        private void currentPlayerName(String strikerName,String nonStrikerName,String bowlerName)
        {
            System.out.println("\nStriker Name :"+strikerName+"\tNon Striker Name :"+nonStrikerName+"\nBowler Name :"+bowlerName);
        }

        //bowler score and batter score board is added to the Score Board after the innings
        private void addScoreBoardDetails(HashMap batterDetails,HashMap bowlerDetails,String teamName)
        {        
            switch(teamName)
            {
                case "A" -> 
                { 
                    scoreBoard.battingScoreBoardA=batterDetails;
                    scoreBoard.bowlingScoreBoardB=bowlerDetails;
                }
                case "B" -> 
                {
                    scoreBoard.battingScoreBoardB=batterDetails;
                    scoreBoard.bowlingScoreBoardA=bowlerDetails;
                }

                default -> {break;}
            }
        }
        public int getInput(int[] checkInput)
        {
            int input=-1;
            OUTER:while(true)
            {
                try
                {
                    input=scanner.nextInt(); 
                    for(int index=0;index<checkInput.length;index++)
                    {
                        if(input==checkInput[index])
                        {
                            break OUTER;
                        }
                    }
                    System.out.println("Enter the correct option "+Arrays.toString(checkInput));

                }
                catch(InputMismatchException e)
                {
                    scanner.next();
                    System.out.println("Invalid input enter the correct format");
                }
            }
            return input;
        }
        public int gamePlay(String[] battingTeam,String[] bowlingTeam,int over,String teamName,Integer score)
        {       
            LinkedHashMap<String,String> battingScoreBoard=new LinkedHashMap<>();
            LinkedHashMap<String,String> bowlingScoreBoard=new LinkedHashMap<>();
            ArrayList<Object> overSummary=null;
            int overCount,ballCount,bowlerId=0,batterId=0,teamScore=0,wicketCount=0; 

            //getting the current striker, non striker and bowler name 
            String striker=battingTeam[batterId++]; 
            String nonStriker=battingTeam[batterId++];
            String bowlerName=bowlingTeam[bowlerId++];

            String tempName;
            int[] tempArray;
            int check=0;
            //current striker, nonstriker details 
            int[] strikerScoreDetails=matchDetails.batterDetails.clone();
            int[] nonStrikerScoreDetails=matchDetails.batterDetails.clone();  
            int[] bowlingDetails;     

            //looping the entire match until over exists or team players all out
            OUTER: for(overCount=0;overCount<over;overCount++)
            {
                ballCount=0;
                overSummary=new ArrayList<>();
                //current bowler details 
                if(bowlingScoreBoard.containsKey(bowlerName)) 
                {
                    bowlingDetails= Arrays.stream(bowlingScoreBoard.get(bowlerName).replaceAll("[\\[\\]\\,]", "").split(" ")).mapToInt(Integer::parseInt).toArray(); 
                }
                else
                {
                    bowlingDetails=matchDetails.bowlerDetails.clone();
                }

                currentPlayerName(striker,nonStriker,bowlerName);

                //looping over until team players not all out
                while(ballCount<6)
                {
                    ballCount+=1;
                    bowlingDetails[2]+=1;
                    System.out.println("1:Runs 2:Extras 3:Wicket");
                    int ballOutCome=getInput(matchDetails.ballOutComeInputChecker);//scanner.nextInt(); 
                    switch(ballOutCome)
                    {
                        case 1 -> 
                        {

                            System.out.println("Enter the run");
                            int run=getInput(matchDetails.runInputChecker); 
                            overSummary.add(run);
                            strikerScoreDetails[3]+=1;
                            teamScore+=run; 
                            bowlingDetails[0]+=run;
                            strikerScoreDetails[0]+=run;
                            if(run==4 || run==6) 
                            {
                                if(run==4)
                                {
                                    strikerScoreDetails[1]+=1;
                                }
                                if(run==6) 
                                {
                                    strikerScoreDetails[2]+=1;
                                }
                            }

                            //Swapping the stiker and non-sticker batsman when odd number run comes
                            if(run%2!=0)
                            {
                                tempArray=strikerScoreDetails;
                                strikerScoreDetails=nonStrikerScoreDetails;
                                nonStrikerScoreDetails=tempArray; 

                                tempName=striker;
                                striker=nonStriker;
                                nonStriker=tempName;
                            }
                        }

                        //if bowler bowls wide or no ball that team score increases and ball count not considered
                        case 2 -> 
                        { 
                            bowlingDetails[0]+=1;
                            overSummary.add("EX");          //"EX" means extra ball
                            teamScore+=1;
                            ballCount-=1;
                        }

                        //if the bowler takes the wicket, the new batsman will comes to the striker position
                        case 3 -> 
                        { 
                            //bowler taken wicket incremented by 1
                            bowlingDetails[1]+=1;
                            strikerScoreDetails[3]+=1;
                            overSummary.add("W");           //"W" means wicket
                            wicketCount+=1;

                            String strikerOutByBowler=striker+" Out by "+bowlerName;
                            //striker details is added to the batting score board after the wicket  
                            battingScoreBoard.put(strikerOutByBowler,Arrays.toString(strikerScoreDetails));
                            strikerScoreDetails(strikerOutByBowler,strikerScoreDetails);
                            System.out.println("\nFall Of Wicket :"+teamScore+"/"+wicketCount);


                            //if the team all out,the innings will end
                            if(batterId==battingTeam.length)
                            {
                                //if(!battingScoreBoard.containsKey(nonStriker))
                                battingScoreBoard.put(nonStriker+" Not Out", Arrays.toString(nonStrikerScoreDetails)); 
                                bowlingScoreBoard.put(bowlerName, Arrays.toString(bowlingDetails));
                                check+=1;
                                System.out.println("All Out\n");break OUTER;
                            }

                            striker=battingTeam[batterId++];
                            strikerScoreDetails=matchDetails.batterDetails.clone(); 
                            currentPlayerName(striker,nonStriker,bowlerName);                        
                        }

                        default -> 
                        {
                            ballCount-=1;
                            break;
                        } 
                    }

                    //second innings score is greater than first innings score match will end
                    if(score!=null && teamScore>score)
                    {
                        System.out.printf("Team %s Won by %d Wickets\n",teamName,10-wicketCount);
                        bowlingDetails[2]+=ballCount;
                        bowlingScoreBoard.put(bowlerName, Arrays.toString(bowlingDetails));
                        System.out.printf("Team Name :%s\tTeam Score-Wicket :%d-%d\tOver :%d.%d\n",teamName,teamScore,wicketCount,overCount,ballCount);
                        break OUTER;
                    }

                    //print the runs to win in remaining ball 
                    if(score!=null)
                    {
                        System.out.printf("Team %s Need %d Runs From %d Balls\n",teamName,(score-teamScore+1),(over*6)-(overCount*6+ballCount));
                    }

                    
                    System.out.printf("Team Name :%s\tTeam Score-Wicket :%d-%d\tOver :%d.%d\n",teamName,teamScore,wicketCount,overCount,ballCount);

                    System.out.println("Over Summary : "+overSummary);
                    
                }

                System.out.printf("\nRun Rate :%.2f\n",runRate((overCount+1),teamScore));
                System.out.println("\n\t\tOver is done by "+bowlerName);

                  
                bowlingScoreBoard.put(bowlerName, Arrays.toString(bowlingDetails));

                //Swapping the stiker and non-sticker batsman when over ends
                tempArray=strikerScoreDetails;strikerScoreDetails=nonStrikerScoreDetails;nonStrikerScoreDetails=tempArray; 
                tempName=striker;striker=nonStriker;nonStriker=tempName;

                bowlerScoreDetails(bowlerName,bowlingDetails);
                bowlerName=bowlingTeam[bowlerId++];

                //if bowler id is equal to team length again it comes to '0'
                if(bowlerId==10)
                {
                    bowlerId=0;
                } 
            }  
            if(score==null) 
            {
                System.out.println("\t\tFirst Innings End");
            }
            else
            {
                System.out.println("\t\tMatch End");
            }
            //striker and non striker details added to the battingScoreBoard will innings over
            if(check==0)    
            {
                if(!battingScoreBoard.containsKey(striker))
                    battingScoreBoard.put(striker+" Not Out", Arrays.toString(strikerScoreDetails));
                if(!battingScoreBoard.containsKey(nonStriker))
                    battingScoreBoard.put(nonStriker+" Not Out", Arrays.toString(nonStrikerScoreDetails));
            }
            
            while(batterId<battingTeam.length)
            {
                battingScoreBoard.put(battingTeam[batterId++],"");
            }

            addScoreBoardDetails(battingScoreBoard,bowlingScoreBoard,teamName);         
            scoreBoard.viewDetails();
            return teamScore;
        }
}

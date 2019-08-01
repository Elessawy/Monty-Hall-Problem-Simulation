import java.util.Arrays;
import java.util.Random;

/**
 *  This is a simulation for the Monty Hall Problem different strategies
 * 
 *  The Monty Hall Problem gets its name from the TV game show,
 *  Let's Make A Deal, hosted by Monty Hall 1. 
 *  The scenario is such: you are given the opportunity to select 
 *  one closed door of three, behind one of which there is a prize. 
 *  The other two doors hide “goats” (or some other such “non-prize”), 
 *  or nothing at all. Once you have made your selection, 
 *  Monty Hall will open one of the remaining doors, 
 *  revealing that it does not contain the prize 2. 
 *  
 *  He then asks you if you would like to switch your selection 
 *  to the other unopened door, or stay with your original choice. 
 *  
 *  Here is the problem: Does it matter if you switch?
 *  
 *  Here we will count the results of three strategies:
 *  	1- not to switch my selection
 *  	2- switch my selection
 *  	3- randomly selecting between the 2 previous strategies
 * 
 * @author  Abd-Elrahman Elessawy
 * */

public class MontyHallSimulation {

	public static void main(String[] args) {

		int simCount = 1000000;

		Random rand = new Random();
		
		boolean [] doors = new boolean[3];
		
		int mySelectionStrategyWinCount = 0;
		int switchSelectionStrategyWinCount = 0;
		int randStrategyWinCount = 0;

		while(simCount-- > 0) {
			
			//set the price door randomly
			Arrays.fill(doors, false);
			doors[rand.nextInt(3)] = true; 
			
			//select a door randomly
			int mySelectedDoor = rand.nextInt(3);
			
			//monty door:
			//	1- is randomly selected
			// 	2- not equals my selected door
			//	3- is not the price door
			int montyOpenedDoor = -1;
			while((montyOpenedDoor = rand.nextInt(3)) == mySelectedDoor || doors[montyOpenedDoor]);
			
			//loop on the 3 doors to get the switched door 
			//(not my selected door and not the monty door)
			for(int switchedDoor = 0; switchedDoor < 3; switchedDoor++) {
				
				if(switchedDoor != montyOpenedDoor && switchedDoor != mySelectedDoor) {
					
					//switched door is found
					//increase the win counters on different strategies
					
					if(doors[mySelectedDoor]) mySelectionStrategyWinCount++;
					else if(doors[switchedDoor]) switchSelectionStrategyWinCount++;
					
					if(rand.nextInt(2) == 0) if(doors[mySelectedDoor]) randStrategyWinCount++;
					else if(doors[switchedDoor]) randStrategyWinCount++;
					
					break;
				}
			}
		}
		
		// print the counter of all strategies
		System.out.println("Don't Switch Strategy Wins = " + mySelectionStrategyWinCount);
		System.out.println("Switch Strategy Wins = " + switchSelectionStrategyWinCount);
		System.out.println("Random Strategy Wins = " + randStrategyWinCount);

	}

}

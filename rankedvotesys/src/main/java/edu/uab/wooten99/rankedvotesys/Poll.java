package edu.uab.wooten99.rankedvotesys;


import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * @author Cole Wooten
 * EE-333
 * Ranked Voting System
 *
 */
public class Poll {

	private final String COMMAND = "\\go";
	private VotingSystem system;

	public Poll(){
		System.out.println(
                                "Enter 1 to begin voting");		
		Scanner scanner = new Scanner(System.in);
                
		int choice = scanner.nextInt();
		
		//scanner.close();
		
		System.out.println(
				"\n\nHELLO VOTER\nPlease read instructions before operating this ranked vote system.\n\n"
                                        + "-One line represents the ranked vote choice of one voter,\ntwo lines means two voters, so on.\n-This is a single-round instant runoff style vote.\n"
                                        + "-You must enter the names of your candidates from most desirable to least desirable\nin each round.\n"
                                        + "-The names of your candidates must be seperated by a semicolon or comma.\n"
                                        + "-This voting system is easily customizable, you are not provided with a\npredetermined ballot, instead, the names of candidates you enter are\nautomatically treated as ballot entries\n"
                                        + "-It would be best to test this system with multiple\noperators to get an accurate feel of how the system works;\nhowever, an individual user could just provide multiple\ndifferent entries.\n\n"
                                        + "Enter input now. Then enter " + COMMAND + " to compute results.");
		
		ArrayList<Ballot> b = parseInput();
		switch(choice){
			case 1: system = new rankedvotesys(b.toArray(new Ballot[b.size()]));
			break;
			default: system = new rankedvotesys(b.toArray(new Ballot[b.size()]));
		}
		
		System.out.println("WINNER: " + system.computeWinner());
		System.out.println(system.results());
	}

	private ArrayList<Ballot> parseInput(){
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();

		Scanner input = new Scanner(System.in);

		String ballot;

		while((ballot = input.nextLine().trim()) != null){
			
			if(ballot.equalsIgnoreCase(COMMAND)){
				input.close();
				return ballots;
			}

			int n = 1; 
                        
			int index = ballot.indexOf(' ');

			if(index != - 1 && index < ballot.length() - 1){
				try{
					n = Integer.parseInt(ballot.substring(0, index));
					ballot = ballot.substring(index);
				} catch(NumberFormatException e){ 
					n = 1;
				}	

			}

			for(int i = 0; i < n; i++){
				if(ballot.length() > 0)
					ballots.add(new Ballot(ballot));
			}
		}

		input.close();

		return ballots;

	}

	public static void main(String[] args){
		Poll p = new Poll();
	}

}

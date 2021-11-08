package edu.uab.wooten99.rankedvotesys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VotingSystem {

	
	protected List<Ballot> voterBallots;

	
	protected Map<String, Integer> candVotes;

	
	public VotingSystem(){}

	/**
     * @param ballots
	 */
        
	public VotingSystem(Ballot[] ballots){
		initBallots(ballots);
		initCandidates(ballots);
	}
	
	protected void initBallots(Ballot[] ballots){
		voterBallots = new ArrayList<Ballot>(); 

		for(int i = 0; i < ballots.length; i++){	
			if(ballots[i] != null){					

                            voterBallots.add(new Ballot(ballots[i].toArray()));
			}
		}
	}


	/**
     * @param ballots
	 */
        
	protected void initCandidates(Ballot[] ballots){
		candVotes = new HashMap<String, Integer>();

		for(Ballot ballot : ballots){
			for(String cand : ballot.toArray()){
				if(!candVotes.containsKey(cand))
					candVotes.put(cand, 0);
			}

		}
	}

	
	public String computeWinner() {
		String winner = getWinner();		

		if(isTied(winner) || winner.length() == 0){	
			return "Tie";					
		} else {
			return winner;
		}
	}


	protected String getWinner(){
		String winner = "";
		int maxVotes = 0;

		for(Map.Entry<String, Integer> entry : candVotes.entrySet()){
			if(entry.getValue() > maxVotes || maxVotes == 0){
				maxVotes = entry.getValue();
				winner = entry.getKey();
			}
		}

		return winner;
	}

	/**
     * @param candidate
     * @return 
	 */
	protected boolean isTied(String candidate){
		boolean isTie = false;
		int candsVotes = candVotes.get(candidate);

		for(Map.Entry<String, Integer> entry : candVotes.entrySet()){

			if(entry.getValue() == candsVotes && 
					!candidate.equals(entry.getKey())){
				isTie = true; 
			}
		}

		return isTie;
	}

	
	public String results(){
		String results = "";

		String[] candidates = getSortedCandidateList(); 

		for(String candidate : candidates){
			results += String.format("%-18s %d", 
					candidate + ":", candVotes.get(candidate)) + "\n";
		}

		return results;
	}

	/**
     * @return 
	 */
	protected String[] getSortedCandidateList(){
		String[] candidates = new String[candVotes.size()];

		int i = 0;

		for(Map.Entry<String, Integer> entry : candVotes.entrySet())
			candidates[i++] = entry.getKey();

		insertionSort(candidates);

		return candidates;
	}

	private void insertionSort(String[] a){
		for(int i = 1; i < a.length; i++){
			String val = a[i];
			int index = i;

			while(index > 0 && candVotes.get(val) > candVotes.get(a[index - 1])){
				a[index] = a[index - 1];
				index--;
			}

			a[index] = val;
		}
	}


} 

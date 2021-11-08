package edu.uab.wooten99.rankedvotesys;

import java.util.ArrayList;
import java.util.List;

public class Ballot {
	
	private List<String> theBallot;

	
	public Ballot(){
		theBallot = new ArrayList<String>();
	}

	/**
	 * Setups a ballot with the candidates names in order of ranking.
	 * 
	 * @param candidates	The candidates to be voted on in order preference. 
	 * 						Lower index values in the array correspond with 
	 * 						higher preferences.
	 */
	public Ballot(String[] candidates){
		this();

		setBallot(candidates);
	}

	/**
	 * Setups a ballot with the candidates names in order of ranking.
	 * 
	 * @param candidates	The candidates to be voted on in order preference. 
	 * 						Lower index values in the array correspond with 
	 * 						higher preferences.
	 */
	public Ballot(ArrayList<String> candidates){
		this();

		setBallot(candidates);
	}

	public Ballot(String candidates){
		this();

		setBallot(candidates);
	}

	/**
	 * @param ballot	
	 */
        
	public void setBallot(String[] ballot){
		theBallot.removeAll(theBallot);

		for(String candidate : ballot){
			if(!theBallot.contains(candidate))
				theBallot.add(candidate); 
		}									
	}

	public void setBallot(ArrayList<String> candidates){
		setBallot(candidates.toArray(new String[candidates.size()]));
	}

	/**
	 * 
	 * @param ballot	
	 * 					
	 */
	public void setBallot(String ballot){
		theBallot.removeAll(theBallot);

		int index = -1; 

		char[] separators = {',', ';', '>', '/'};

		do {
			int newIndex = -1;

			for(char c : separators)
				newIndex = Math.max(ballot.indexOf(c, index + 1), newIndex);

			String candidate;
			
			if(newIndex != -1)
				candidate = ballot.substring(index + 1, newIndex).trim();
			else
				candidate = ballot.substring(index + 1).trim();
			
			if(!theBallot.contains(candidate))
				theBallot.add(candidate);

			index = newIndex;

		} while(index != -1);
	}

	/**
	 * @param index	 
	 * @return	
	 */
        
	public String getCandidate(int index){
		try {
			return theBallot.get(index); 
		} catch(IndexOutOfBoundsException e){
			return "";
		}
	}

	/**
	 * @return 
	 */
	public int getBallotLength(){
		return theBallot.size();
	}

	/**
	 * @param candidate 
         * */
        
	public void eliminateCandidate(String candidate){
		if(theBallot.contains(candidate))
			theBallot.remove(getIndex(candidate));
	}

	/**
	 * @param index 
	 */
	public void eliminateCandidate(int index){
		theBallot.remove(index);
	}


	/**
	 * @param candidate
     * @return 
	 */
        
	public int getIndex(String candidate){
		return theBallot.indexOf(candidate);
	}

	/**
	 * @param candidate
     * @return 
	 */
	public int getPosition(String candidate){
		return getIndex(candidate) + 1;
	}

	/**
	 * @return 
	 */
        
	public List<String> toList(){
		return theBallot;
	}

	/**
	 * @return
	 */
	public String[] toArray(){
		return theBallot.toArray(new String[theBallot.size()]);
	}
} //end

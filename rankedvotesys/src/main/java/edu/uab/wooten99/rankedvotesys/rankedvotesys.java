package edu.uab.wooten99.rankedvotesys;

public class rankedvotesys extends VotingSystem{
	
	public rankedvotesys(){}

	public rankedvotesys(Ballot[] ballots){
		super(ballots);
	}

	protected void setVotes(){
            voterBallots.forEach(ballot -> {
                for(String candidate : ballot.toArray()){
                    int score = candVotes.get(candidate) +
                            candVotes.size() - ballot.getPosition(candidate);
                    
                    candVotes.put(candidate, score);
                }
            });
	}
}//end
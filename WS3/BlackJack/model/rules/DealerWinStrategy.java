package BlackJack.model.rules;

public class DealerWinStrategy implements IWinStrategy {

	@Override
	public boolean DealerIsWinner(int a_dealerScore, int a_playerScore, int a_score) {
		if (a_playerScore > a_score){
			return true;
		}else if ( a_dealerScore > a_score){
			return false;
		}
		
		return a_playerScore <= a_dealerScore;
		
	}
	}


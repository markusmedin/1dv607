package BlackJack.model.rules;

public class PlayerWinStrategy implements IWinStrategy {

	@Override
	public boolean DealerIsWinner(int a_dealerScore, int a_playerScore, int a_score) {
		if (a_dealerScore > a_score){
			return false;
		}else if ( a_playerScore > a_score){
			return true;
		}
		
		return a_playerScore < a_dealerScore;
		
	}

}

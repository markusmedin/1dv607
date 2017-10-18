package BlackJack.model.rules;

public interface IWinStrategy {
	
	boolean DealerIsWinner(int a_dealerScore, int a_playerScore, int a_Score);

}

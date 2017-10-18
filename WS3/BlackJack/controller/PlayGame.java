package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame {

  public boolean Play(Game a_game, IView a_view) {
    a_view.DisplayWelcomeMessage();
    
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }
    
    InputMenu input = a_view.GetInput();
    if (input == InputMenu.p)
    {
        a_game.NewGame();
    }
    else if (input == InputMenu.h)
    {
        a_game.Hit();
    }
    else if (input == InputMenu.s)
    {
        a_game.Stand();
    }

    return input != InputMenu.q;
  }
  
  public enum InputMenu{
		p,
		h,
		s,
		q,
		NotValid
  }
}


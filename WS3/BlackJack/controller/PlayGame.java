package BlackJack.controller;

import BlackJack.view.IView;
import BlackJack.model.Game;
import BlackJack.model.Observer;

public class PlayGame extends Observer{
	
	private Game m_game;
	private IView m_view;
	
	public PlayGame(Game a_game, IView a_view){
		m_view = a_view;
		m_game = a_game;
		m_game.addObserver(this);
	}

  public boolean Play() {
    m_view.DisplayWelcomeMessage();
    
    m_view.DisplayDealerHand(m_game.GetDealerHand(), m_game.GetDealerScore());
    m_view.DisplayPlayerHand(m_game.GetPlayerHand(), m_game.GetPlayerScore());

    if (m_game.IsGameOver())
    {
    	m_view.DisplayGameOver(m_game.IsDealerWinner());
    }
    
    InputMenu input = m_view.GetInput();
    if (input == InputMenu.p)
    {
    	m_game.NewGame();
    }
    else if (input == InputMenu.h)
    {
    	m_game.Hit();
    }
    else if (input == InputMenu.s)
    {
    	m_game.Stand();
    }

    return input != InputMenu.q;
  }
  public void update(){
      m_view.DisplayDealerHand(m_game.GetDealerHand(), m_game.GetDealerScore());
      m_view.DisplayPlayerHand(m_game.GetPlayerHand(), m_game.GetPlayerScore());
  };
  
  public enum InputMenu{
		p,
		h,
		s,
		q,
		NotValid
  }
}


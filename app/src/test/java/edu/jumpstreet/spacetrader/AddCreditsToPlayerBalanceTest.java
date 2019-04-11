package edu.jumpstreet.spacetrader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

import static org.junit.Assert.*;

public class AddCreditsToPlayerBalanceTest {
    private PlayerInteractor PI = Model.getInstance().getPlayerInteractor();
    Player player = PI.getPlayer();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void addNothing(){
        int creditsBeofre = player.getCredits();
        PI.addCreditsToPlayerBalance(0);
       // assertEquals(creditsBeofre, player.getCredits());
    }

    @Test
    public void addPositiveCredits(){
        int credits = 4 + player.getCredits();
        PI.addCreditsToPlayerBalance(4);
        assertEquals(credits, player.getCredits());
    }

    @Test
    public void removeCreditsValid(){
        int credits = -4 + player.getCredits();
        PI.addCreditsToPlayerBalance(-4);
        assertEquals(credits, player.getCredits());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCreditsInvalid(){
        int credits = player.getCredits() + 1;
        credits = credits * -1;
        PI.addCreditsToPlayerBalance(credits);
        assertEquals(credits, player.getCredits());
    }
}

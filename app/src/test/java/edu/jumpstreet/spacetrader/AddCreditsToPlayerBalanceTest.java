package edu.jumpstreet.spacetrader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

import static org.junit.Assert.*;


/**
 * Junit tests for changing the players balance
 */
public class AddCreditsToPlayerBalanceTest {
    private PlayerInteractor PI = Model.getInstance().getPlayerInteractor();
    Player player = PI.getPlayer();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * checks that adding 0 credits throws an error
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNothing(){
        int creditsBeofre = player.getCredits();
        PI.addCreditsToPlayerBalance(0);
       // assertEquals(creditsBeofre, player.getCredits());
    }

    /**
     * tests the functionality of adding credits to the balance
     */
    @Test
    public void addPositiveCredits(){
        int credits = 4 + player.getCredits();
        PI.addCreditsToPlayerBalance(4);
        assertEquals(credits, player.getCredits());
    }

    /**
     * tests the ability to remove credits from the player
     */
    @Test
    public void removeCreditsValid(){
        int credits = -4 + player.getCredits();
        PI.addCreditsToPlayerBalance(-4);
        assertEquals(credits, player.getCredits());
    }

    /**
     * tests that trying to remove more credits than the player has throws an exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeCreditsInvalid(){
        int credits = player.getCredits() + 1;
        credits = credits * -1;
        PI.addCreditsToPlayerBalance(credits);
        assertEquals(credits, player.getCredits());
    }
}

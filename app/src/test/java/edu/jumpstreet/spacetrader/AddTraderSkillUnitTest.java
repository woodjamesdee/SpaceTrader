package edu.jumpstreet.spacetrader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

import static org.junit.Assert.*;

/**
 * Tests for the addPlayerTraderSkill method in PlayerInteractor.
 */
public class AddTraderSkillUnitTest {

    PlayerInteractor interactor = Model.getInstance().getPlayerInteractor();
    Player player = interactor.getPlayer();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test for when 0 is passed in to the method, no change expected.
     */
    @Test
    public void addZeroPoints() {
        int tradeSkillBefore = player.getTrader();
        int unallocatedSkillBefore = player.getSkillpoints();
        interactor.addPlayerTraderSkill(0);
        assertEquals(tradeSkillBefore, player.getTrader());
        assertEquals(unallocatedSkillBefore, player.getSkillpoints());
    }

    /**
     * Test for when a number in the good range is passed in to the method.
     */
    @Test
    public void addSomePointsWithUnallocatedRemaining() {
        int tradeSkillBefore = player.getTrader();
        int unallocatedSkillBefore = player.getSkillpoints();
        interactor.addPlayerTraderSkill(4);
        assertEquals(tradeSkillBefore + 4, player.getTrader());
        assertEquals(unallocatedSkillBefore - 4, player.getSkillpoints());
    }

    /**
     * Test for when a number in the good range is passed in to the method.
     */
    @Test
    public void subtractSomePointsWithTraderRemaining() {
        player.setTrader(4);
        player.setSkillpoints(11);
        int tradeSkillBefore = player.getTrader();
        int unallocatedSkillBefore = player.getSkillpoints();
        interactor.addPlayerTraderSkill(-4);
        assertEquals(tradeSkillBefore - 4, player.getTrader());
        assertEquals(unallocatedSkillBefore + 4, player.getSkillpoints());
    }

    /**
     * Test for when a number is passed in with no unallocated points remaining.
     */
    @Test
    public void addSomePointsWithNoUnallocatedRemaining() {
        thrown.expect(IllegalArgumentException.class);
        player.setSkillpoints(0);
        interactor.addPlayerTraderSkill(4);
    }

    /**
     * Test for when a number is passed in with no trader points remaining.
     */
    @Test
    public void subtractSomePointsWithNoTraderRemaining() {
        thrown.expect(IllegalArgumentException.class);
        interactor.addPlayerTraderSkill(-4);
    }


}

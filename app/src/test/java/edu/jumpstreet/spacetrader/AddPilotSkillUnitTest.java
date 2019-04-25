package edu.jumpstreet.spacetrader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

import static org.junit.Assert.*;

/**
 * This is a basic set of unit tests for pilot skill in playerInteractor.
 *
 */
public class AddPilotSkillUnitTest {
    private PlayerInteractor interactor = Model.getInstance().getPlayerInteractor();
    private Player player = interactor.getPlayer();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test for when 0 is passed in to the method, no change expected.
     */
    @Test
    public void addZeroPoints() {
        int origPilotSkill = player.getPilot();
        int pointsLeft = player.getSkillpoints();
        interactor.addPlayerPilotSkill(0);
        assertEquals(origPilotSkill, player.getPilot());
        assertEquals(pointsLeft, player.getSkillpoints());
    }

    /**
     * Test for when a number in the good range is passed in to the method.
     */
    @Test
    public void addSomePointsWithUnallocatedRemaining() {
        int origPilotSkill = player.getPilot();
        int pointsLeft = player.getSkillpoints();
        interactor.addPlayerPilotSkill(1);
        assertEquals(origPilotSkill + 1, player.getPilot());
        assertEquals(pointsLeft - 1, player.getSkillpoints());
    }

    /**
     * Test for when a number in the good range is passed in to the method.
     */
    @Test
    public void subtractSomePointsWithPilotRemaining() {
        player.setPilot(4);
        player.setSkillpoints(11);
        int origPilotSkill = player.getPilot();
        int pointsLeft = player.getSkillpoints();
        interactor.addPlayerPilotSkill(-1);
        assertEquals(origPilotSkill - 1, player.getPilot());
        assertEquals(pointsLeft + 1, player.getSkillpoints());
    }

    /**
     * Test for when a number is passed in with no unallocated points remaining.
     */
    @Test
    public void addSomePointsWithNoUnallocatedRemaining() {
        thrown.expect(IllegalArgumentException.class);
        player.setSkillpoints(0);
        interactor.addPlayerPilotSkill(4);
    }

    /**
     * Test for when a number is passed in with no pilot points remaining.
     */
    @Test(expected = IllegalArgumentException.class)
    public void subtractWithNoPilotRemaining() {
        player.setPilot(0);
        interactor.addPlayerPilotSkill(-1);
    }
}
package edu.jumpstreet.spacetrader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

import static org.junit.Assert.*;

/**
 * J-Unit tests for the addPlayerFighterSkill method in PlayerInteractor under Model.
 */
public class AddFighterSkillUnitTest {

    private PlayerInteractor interactor = Model.getInstance().getPlayerInteractor();
    Player player = interactor.getPlayer();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * J-Unit test for when 0 is passed in to the method. The fighter skill should not change.
     */
    @Test
    public void addZeroPoints() {
        int fighterSkillBefore = player.getFighter();
        int unallocatedSkillBefore = player.getSkillpoints();
        interactor.addPlayerFighterSkill(0);
        assertEquals(fighterSkillBefore, player.getFighter());
        assertEquals(unallocatedSkillBefore, player.getSkillpoints());
    }

    /**
     * J-Unit test for when a number in the given range is passed in to the method to add
     * to the total points.
     */
    @Test
    public void addSomePointsWithUnallocatedRemaining() {
        int fighterSkillBefore = player.getFighter();
        int unallocatedSkillBefore = player.getSkillpoints();
        interactor.addPlayerFighterSkill(4);
        assertEquals(fighterSkillBefore + 4, player.getFighter());
        assertEquals(unallocatedSkillBefore - 4, player.getSkillpoints());
    }

    /**
     * J-Unit test for when a number in the given range is passed in to the method to subtract
     * from the total points.
     */
    @Test
    public void subtractSomePointsWithFighterRemaining() {
        player.setFighter(4);
        player.setSkillpoints(11);
        int fighterSkillBefore = player.getFighter();
        int unallocatedSkillBefore = player.getSkillpoints();
        interactor.addPlayerFighterSkill(-4);
        assertEquals(fighterSkillBefore - 4, player.getFighter());
        assertEquals(unallocatedSkillBefore + 4, player.getSkillpoints());
    }

    /**
     * J-Unit test for when a number in the given range is passed in with
     * no unallocated points remaining, so no more skill points can be added.
     */
    @Test
    public void addSomePointsWithNoUnallocatedRemaining() {
        thrown.expect(IllegalArgumentException.class);
        player.setSkillpoints(0);
        interactor.addPlayerFighterSkill(4);
    }

    /**
     * J-Unit test for when a number in the given range is passed in to subtract with
     * no unallocated points remaining.
     */
    @Test
    public void subtractSomePointsWithNoFighterRemaining() {
        thrown.expect(IllegalArgumentException.class);
        interactor.addPlayerFighterSkill(-4);
    }


}
package me.fen.atomgame;

import me.fen.atomgame.gamemodes.DefaultGame;
import me.fen.atomgame.gamemodes.Gamemode;
import me.fen.atomgame.particles.Particle;
import org.junit.Test;

import java.util.List;

import static me.fen.atomgame.parse.ParticleGenerators.generateList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReactionTests {
    static Gamemode constructGame(List<Particle> field, List<Particle> next) {
        DefaultGame game = new DefaultGame(new RepeatParticleRandomizer(next), new DefaultScoringStrategy());
        game.getParticles().addAll(field);
        return game;
    }

    static Gamemode constructGame(String field, String next) {
        return constructGame(generateList(field), generateList(next));
    }

    @Test
    public void barebonesReactionTest() throws GameOverException {
        Gamemode g = constructGame("1 1", "+");
        g.doMove(0);
        assertEquals(g.getParticles(), generateList("2"));
    }
}

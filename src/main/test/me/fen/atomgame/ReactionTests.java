package me.fen.atomgame;

import me.fen.atomgame.gamemodes.DefaultGame;
import me.fen.atomgame.gamemodes.Gamemode;
import me.fen.atomgame.particles.Particle;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static me.fen.atomgame.parse.ParticleGenerators.generateList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReactionTests {
    static boolean particleListSame(List<Particle> fst, List<Particle> snd) {
        if (fst.size() != snd.size()) return false;

        class ParticlePair {
            final Particle fst;
            final Particle snd;

            public ParticlePair(Particle fst, Particle snd) {
                this.fst = fst;
                this.snd = snd;
            }
        }

        return IntStream.range(0, fst.size())
                .mapToObj(i -> new ParticlePair(fst.get(i), snd.get(i)))
                .allMatch(i -> i.fst.weakEquals(i.snd));
    }

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
        assertTrue(particleListSame(g.getParticles(), generateList("2")));
    }
}

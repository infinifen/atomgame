package me.fen.atomgame;

import me.fen.atomgame.gamemodes.Gamemode;
import me.fen.atomgame.particles.*;

import java.util.Random;

public class DefaultParticleRandomizer implements ParticleRandomizer {
    public static final double ATOM_CUTOFF = 0.0;
    public static final double PLUS_CUTOFF = 0.7; // 70% atom chance
    public static final double NEUTRINO_CUTOFF = 0.9; // 1% neutrino chance
    private static final double MINUS_CUTOFF = 0.91; // 20% plus chance
    public static final double DARK_PLUS_CUTOFF = 0.985; // 7.5% minus chance, 1.5% dark plus chance


    private final Random rng = new Random();
    //remembers the highest average yet
    private long highAvg = 1;

    @Override
    public Particle generateNext(Gamemode game) {
        double roll = rng.nextDouble();
        if (roll > DARK_PLUS_CUTOFF) {
            return new DarkPlus();
        }
        if (roll > MINUS_CUTOFF) {
            if (game.getParticles().size() > 0) return new Minus();
            else return new Plus();
        }

        if (roll > NEUTRINO_CUTOFF) {
            if (highAvg > 10 && game.getParticles().size() > 0) {
                return new Neutrino();
            } else {
                return generateAtom(game);
            }
        }

        if (roll > PLUS_CUTOFF) {
            return new Plus();
        }
        return generateAtom(game);
    }

    private Atom generateAtom(Gamemode game) {
        //results in 0 if there's no atoms
        double avg = game.getParticles().stream().filter(Utils::isAtom)
                .mapToInt(p -> ((Atom) p).getAtomicNumber())
                .average().orElse(0.0);
        highAvg = Math.max((int) avg, highAvg);
        long z = Math.round(rng.nextGaussian() * 0.8 + (int) highAvg);
        // eliminate anything below hydrogen
        z = Math.max(z, 1);
        // eliminate anything too low
        z = Math.max(z, highAvg - 2);
        // eliminate anything too high
        z = Math.min(z, highAvg+2);
        return new Atom((int) z);
    }
}

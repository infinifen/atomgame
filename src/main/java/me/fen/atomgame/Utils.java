package me.fen.atomgame;

import me.fen.atomgame.particles.Particle;
import me.fen.atomgame.particles.ParticleType;

public class Utils {
    public static boolean isAtom(Particle p) {
        return p.getParticleType() == ParticleType.ATOM;
    }

    public static boolean isMinus(Particle p) {
        return p.getParticleType() == ParticleType.MINUS;
    }

    public static boolean isPlus(Particle p) {
        return p.getParticleType() == ParticleType.PLUS;
    }

    public static boolean isDarkPlus(Particle p) {
        return p.getParticleType() == ParticleType.DARK_PLUS;
    }

    public static boolean isNeutrino(Particle p) {
        return p.getParticleType() == ParticleType.NEUTRINO;
    }
}

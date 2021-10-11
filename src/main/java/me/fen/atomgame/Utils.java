package me.fen.atomgame;

public class Utils {
    public static boolean isAtom(Particle p) {
        return p.getParticleType() == ParticleType.ATOM;
    }

    public static boolean isPlus(Particle p) {
        return p.getParticleType() == ParticleType.PLUS;
    }
    public static boolean isDarkPlus(Particle p) {
        return p.getParticleType() == ParticleType.DARK_PLUS;
    }
}

package me.fen.atomgame;

public class DarkPlus implements Particle {
    public DarkPlus() {
    }

    @Override
    public ParticleType getParticleType() {
        return ParticleType.DARK_PLUS;
    }

    @Override
    public int getReactionValue() {
        return 1;
    }

    @Override
    public String toString() {
        return "le plus negro";
    }
}
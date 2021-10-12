package me.fen.atomgame.particles;

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
        return "dark plus";
    }
}
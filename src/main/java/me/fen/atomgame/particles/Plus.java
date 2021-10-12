package me.fen.atomgame.particles;

public class Plus implements Particle {
    public Plus() {
    }

    @Override
    public ParticleType getParticleType() {
        return ParticleType.PLUS;
    }

    @Override
    public int getReactionValue() {
        return 1;
    }

    @Override
    public String toString() {
        return "plus";
    }
}

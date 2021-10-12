package me.fen.atomgame.particles;

public class Minus implements Particle {
    @Override
    public int getReactionValue() {
        return 1;
    }

    public Minus() {
    }

    @Override
    public ParticleType getParticleType() {
        return ParticleType.MINUS;
    }

    @Override
    public String toString() {
        return "minus";
    }
}
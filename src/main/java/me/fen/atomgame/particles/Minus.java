package me.fen.atomgame.particles;

public class Minus implements Particle {
    @Override
    public int getReactionValue() {
        return 1;
    }

    @Override
    public boolean isAtomOperation() {
        return true;
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

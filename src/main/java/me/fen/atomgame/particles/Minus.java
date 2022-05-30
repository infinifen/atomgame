package me.fen.atomgame.particles;

public class Minus implements Particle {
    public Minus() {
    }

    @Override
    public int getReactionValue() {
        return 1;
    }

    @Override
    public boolean isAtomOperation() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Minus;
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

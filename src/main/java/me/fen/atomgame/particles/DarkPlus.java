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
    public boolean isAtomOperation() {
        return false;
    }

    @Override
    public boolean weakEquals(Particle obj) {
        return obj instanceof DarkPlus;
    }

    @Override
    public String toString() {
        return "dark plus";
    }

    @Override
    public Particle copy() {
        return new DarkPlus();
    }
}
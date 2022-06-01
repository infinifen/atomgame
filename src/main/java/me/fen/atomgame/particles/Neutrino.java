package me.fen.atomgame.particles;

public class Neutrino implements Particle {
    public Neutrino() {
    }

    @Override
    public ParticleType getParticleType() {
        return ParticleType.NEUTRINO;
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
        return obj instanceof Neutrino;
    }

    @Override
    public String toString() {
        return "ntr";
    }

    @Override
    public Particle copy() {
        return new Neutrino();
    }
}

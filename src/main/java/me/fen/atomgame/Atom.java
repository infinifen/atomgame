package me.fen.atomgame;

public class Atom implements Particle {

    private Integer atomicNumber;

    Atom(int z) {
        atomicNumber = z;
    }

    public ParticleType getParticleType() {
        return ParticleType.ATOM;
    }

    @Override
    public int getReactionValue() {
        return atomicNumber;
    }

    public Integer getAtomicNumber() {
        return atomicNumber;
    }

    @Override
    public String toString() {
        return "atom " + atomicNumber;
    }

}


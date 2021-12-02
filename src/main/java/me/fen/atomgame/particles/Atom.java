package me.fen.atomgame.particles;

import me.fen.atomgame.AtomData;

public class Atom implements Particle {

    private Integer atomicNumber;

    public Atom(int z) {
        atomicNumber = z;
    }

    public ParticleType getParticleType() {
        return ParticleType.ATOM;
    }

    @Override
    public int getReactionValue() {
        return atomicNumber;
    }

    @Override
    public boolean isAtomOperation() {
        return false;
    }

    public Integer getAtomicNumber() {
        return atomicNumber;
    }


    // maybe do something else for atoms >118?
    @Override
    public String toString() {
        return AtomData.atoms[atomicNumber - 1][1] + " " + atomicNumber;
    }

    public String fullName() {
        return AtomData.atoms[atomicNumber - 1][0];
    }

}


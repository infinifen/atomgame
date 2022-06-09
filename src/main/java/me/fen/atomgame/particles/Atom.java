package me.fen.atomgame.particles;

import me.fen.atomgame.AtomData;

import java.util.Objects;

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

    @Override
    public boolean weakEquals(Particle obj) {
        return obj instanceof Atom && Objects.equals(((Atom) obj).atomicNumber, this.atomicNumber);
    }


    // maybe do something else for atoms >118?
    @Override
    public String toString() {
        return AtomData.atoms[atomicNumber - 1][1] + " " + atomicNumber;
    }

    public String fullName() {
        return AtomData.atoms[atomicNumber - 1][0];
    }

    @Override
    public Particle copy() {
        return new Atom(atomicNumber);
    }
}


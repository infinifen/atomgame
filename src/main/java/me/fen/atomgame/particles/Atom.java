package me.fen.atomgame.particles;

import me.fen.atomgame.particles.Particle;
import me.fen.atomgame.particles.ParticleType;

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

    public Integer getAtomicNumber() {
        return atomicNumber;
    }

    @Override
    public String toString() {
        return "atom " + atomicNumber;
    }

}


package me.fen.atomgame.particles;

public interface Particle {
    ParticleType getParticleType();
    int getReactionValue();

    boolean isAtomOperation();
}

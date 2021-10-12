package me.fen.atomgame;

import me.fen.atomgame.particles.Particle;

public interface ParticleRandomizer {
    Particle generateNext(Game game);
}

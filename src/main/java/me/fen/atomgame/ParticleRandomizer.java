package me.fen.atomgame;

import me.fen.atomgame.gamemodes.DefaultGame;
import me.fen.atomgame.gamemodes.Gamemode;
import me.fen.atomgame.particles.Particle;

public interface ParticleRandomizer {
    Particle generateNext(Gamemode game);
}

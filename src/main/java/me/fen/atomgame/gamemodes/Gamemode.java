package me.fen.atomgame.gamemodes;

import me.fen.atomgame.CircularList;
import me.fen.atomgame.GameOverException;
import me.fen.atomgame.TickResult;
import me.fen.atomgame.particles.Particle;

import java.util.List;

public interface Gamemode {
    CircularList<Particle> getParticles();

    List<Particle> getNext();

    TickResult doMove(int placementIndex) throws GameOverException;

    long getScore();

    boolean isGameOver();

    void specialAbility();
}

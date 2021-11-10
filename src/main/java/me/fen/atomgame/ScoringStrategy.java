package me.fen.atomgame;

import me.fen.atomgame.gamemodes.Gamemode;

public interface ScoringStrategy {
    long getScore();

    long scoreFusion(FusionResult result, Gamemode game);

    long scoreGameOver(Gamemode game);
}
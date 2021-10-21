package me.fen.atomgame;

import me.fen.atomgame.gamemodes.DefaultGame;

public interface ScoringStrategy {
    long getScore();

    long scoreFusion(FusionResult result, DefaultGame game);

    long scoreGameOver(DefaultGame game);
}
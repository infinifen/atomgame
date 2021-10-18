package me.fen.atomgame;

public interface ScoringStrategy {
    long getScore();

    long scoreFusion(FusionResult result, Game game);

    long scoreGameOver(Game game);
}
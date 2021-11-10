package me.fen.atomgame.gamemodes;

import me.fen.atomgame.DefaultParticleRandomizer;
import me.fen.atomgame.DefaultScoringStrategy;

public class ClassicGame extends DefaultGame implements Gamemode {
    public ClassicGame() {
        super(new DefaultParticleRandomizer(), new DefaultScoringStrategy());
    }
}

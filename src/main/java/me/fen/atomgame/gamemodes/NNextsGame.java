package me.fen.atomgame.gamemodes;

import java.util.ArrayList;

public class NNextsGame extends ClassicGame implements Gamemode {
    public NNextsGame(int nexts) {
        super();
        next = new ArrayList<>(nexts + 1);
        for (int i = 0; i < nexts; i++) {
            next.add(randomizer.generateNext(this));
        }

    }

    @Override
    protected void rerollNext() {
        if (next == null) return;
        next.add(randomizer.generateNext(this));
        next.remove(0);
    }

    @Override
    public String toString() {
        return "NNexts{" +
                "particles=" + particles +
                ", next=" + next +
                ", score=" + getScore() +
                '}';
    }
}

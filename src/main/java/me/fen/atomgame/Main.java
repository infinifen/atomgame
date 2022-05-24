package me.fen.atomgame;

import me.fen.atomgame.gamemodes.Gamemode;
import me.fen.atomgame.gamemodes.NNextsGame;
import me.fen.atomgame.particles.Atom;
import me.fen.atomgame.particles.Minus;
import me.fen.atomgame.particles.Plus;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gamemode g = new NNextsGame(4, new DefaultParticleRandomizer(), new DefaultScoringStrategy());
//        g.getParticles().addAll(List.of(new Atom(3), new Plus(), new Atom(2), new Plus(), new Atom(2), new Atom(110), new Atom(110), new Atom(110)));
        g.getParticles().addAll(List.of(new Atom(98), new Atom(3), new Atom(99), new Plus(), new Atom(3), new Atom(101)));
        g.getNext().set(0, new Minus());
//        Particle[] a = {new Atom(4), new Atom(3), new DarkPlus(), new Plus(), new Atom(3)};
//        g.particles = new CircularList<>(List.of(a));
        while (!g.isGameOver()) {
            System.out.println(g);
            System.out.print("enter idx to insert after: ");
            String line = sc.nextLine();

            if (line.toLowerCase().startsWith("sp"))
                g.specialAbility();
            else {
                int idx = Integer.parseInt(line);
                try {
                    TickResult tr = g.doMove(idx);
                    List<FusionResult> fr = tr.fusions;
                    System.out.println("tick result: " + tr);
                } catch (GameOverException e) {
                    System.out.println("Game over! Final score: " + g.getScore());
                    System.exit(0);
                }

            }

        }
    }
}

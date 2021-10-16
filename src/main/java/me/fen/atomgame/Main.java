package me.fen.atomgame;

import me.fen.atomgame.particles.Atom;
import me.fen.atomgame.particles.DarkPlus;
import me.fen.atomgame.particles.Particle;
import me.fen.atomgame.particles.Plus;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game g = new Game(new DefaultParticleRandomizer());
        Particle[] a = {new Atom(4), new Atom(3), new DarkPlus(), new Plus(), new Atom(3)};
        g.particles = new CircularList<>(List.of(a));
        while (!g.isGameOver()) {
            System.out.println(g);
            System.out.print("enter idx to insert after: ");
            String line = sc.nextLine();

            if (line.toLowerCase().startsWith("sp"))
                g.specialAbility();
            else {
                int idx = Integer.parseInt(line);
                try {
                    List<FusionResult> fr = g.doMove(idx);
                    if (fr.size() > 0) {
                        System.out.println("Reaction(s) occured: " + fr);
                    }
                } catch (GameOverException e) {
                    System.out.println("Game over!");
                    System.exit(0);
                }

            }

        }
    }
}

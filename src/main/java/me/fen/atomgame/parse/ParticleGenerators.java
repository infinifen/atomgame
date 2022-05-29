package me.fen.atomgame.parse;

import me.fen.atomgame.particles.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// very simple space delimited format for generating particle lists
public class ParticleGenerators {
    public static List<Particle> generateList(String str) {
        return Arrays.stream(str.split(" ")).map(x -> switch (x) {
            case "+" -> new Plus();
            case "-" -> new Minus();
            case "++" -> new DarkPlus();
            default -> new Atom(Integer.parseInt(x));
        }).collect(Collectors.toList());
    }

//    public static
}

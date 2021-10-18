package me.fen.atomgame;

import java.util.List;

public class FusionResult {
    int newAtomicNumber;
    int center;
    int radius;
    List<Integer> atomicNumberSteps; // convenience field so scoring strategies don't need to replicate the fusion logic

    public FusionResult(int newAtomicNumber, int center, int radius, List<Integer> atomicNumberSteps) {
        this.newAtomicNumber = newAtomicNumber;
        this.center = center;
        this.radius = radius;
        this.atomicNumberSteps = atomicNumberSteps;
    }

    @Override
    public String toString() {
        return "FusionResult{" +
                "newAtomicNumber=" + newAtomicNumber +
                ", center=" + center +
                ", radius=" + radius +
                ", atomicNumberSteps=" + atomicNumberSteps +
                '}';
    }
}

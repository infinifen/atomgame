package me.fen.atomgame;

public class FusionResult {
    int newAtomicNumber;
    int center;
    int radius;

    public FusionResult(int newAtomicNumber, int center, int radius) {
        this.newAtomicNumber = newAtomicNumber;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "FusionResult{" +
                "newAtomicNumber=" + newAtomicNumber +
                ", center=" + center +
                ", radius=" + radius +
                '}';
    }
}

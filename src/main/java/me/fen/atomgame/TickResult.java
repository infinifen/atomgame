package me.fen.atomgame;

import me.fen.atomgame.particles.Particle;

import java.util.List;

public class TickResult {
    public List<FusionResult> fusions;
    public CircularList<Particle> initialParticles;

    public TickResult(List<FusionResult> fusions, CircularList<Particle> initialParticles) {
        this.fusions = fusions;
        this.initialParticles = initialParticles;
    }

    @Override
    public String toString() {
        return "TickResult{" +
                "fusions=" + fusions +
                ", initialParticles=" + initialParticles +
                '}';
    }
}

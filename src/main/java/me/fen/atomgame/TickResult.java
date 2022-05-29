package me.fen.atomgame;

import me.fen.atomgame.particles.Particle;

import java.util.List;

public class TickResult {
    public List<FusionResult> fusions;
    public CircularList<Particle> initialParticles;
    public CircularList<Particle> afterInput;

    public TickResult(List<FusionResult> fusions, CircularList<Particle> initialParticles, CircularList<Particle> afterInput) {
        this.fusions = fusions;
        this.initialParticles = initialParticles;
        this.afterInput = afterInput;
    }

    public TickResult(List<FusionResult> fusions, CircularList<Particle> afterInput) {
        this.fusions = fusions;
        this.afterInput = afterInput;
    }

    @Override
    public String toString() {
        return "TickResult{" +
                "fusions=" + fusions +
                ", initialParticles=" + initialParticles +
                ", afterInput=" + afterInput +
                '}';
    }
}

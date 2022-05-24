package me.fen.atomgame;

import me.fen.atomgame.particles.Particle;

import java.util.List;

public class TickResult {
    public List<FusionResult> fusions;
    public CircularList<Particle> beforeInput;
    public CircularList<Particle> afterInput;

    public TickResult(List<FusionResult> fusions, CircularList<Particle> beforeInput, CircularList<Particle> afterInput) {
        this.fusions = fusions;
        this.beforeInput = beforeInput;
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
                ", beforeInput=" + beforeInput +
                ", afterInput=" + afterInput +
                '}';
    }
}

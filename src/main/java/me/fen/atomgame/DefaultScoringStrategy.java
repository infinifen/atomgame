package me.fen.atomgame;

import me.fen.atomgame.particles.Particle;

public class DefaultScoringStrategy implements ScoringStrategy {
    protected long score;

    @Override
    public long getScore() {
        return score;
    }

    @Override
    public long scoreFusion(FusionResult result, Game game) {
        // algorithm as described at https://atomas.fandom.com/wiki/Score
        // FIXME: 16.10.2021 initial reaction from a dark plus should not be calculated as a +2 reaction
        long finalScore = 0;
        for (int i = 1; i < result.atomicNumberSteps.size(); i++) {
            int before = result.atomicNumberSteps.get(i - 1);
            int after = result.atomicNumberSteps.get(i);
            float m = 1 + i / 2.0f;
            long sr = (long) Math.floor(m * before + 1);

            if (after - before > 1) { // +2 reaction?
                // Zo - Z in the article corresponds to after - 2 - before here
                long diff = after - 2 - before;
                long b = (long) (2 * m * (diff + 1)); // 2 * m will always be a whole number because m only goes up by 0.5
                finalScore += sr + b;
            } else { // + 1 reaction
                finalScore += sr;
            }
        }
        System.out.println("finalScore = " + finalScore);
        score += finalScore;
        return finalScore;
    }

    @Override
    public long scoreGameOver(Game game) {
        return game.particles.stream().mapToInt(Particle::getReactionValue).sum();
    }
}

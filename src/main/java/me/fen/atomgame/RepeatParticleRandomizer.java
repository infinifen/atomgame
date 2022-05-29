package me.fen.atomgame;

import me.fen.atomgame.gamemodes.Gamemode;
import me.fen.atomgame.particles.Particle;

import java.util.List;


/**
 * Will repeat the given list of particles, generating up to and including {@code limit} before throwing {@code IllegalStateException} if it is not null, or indefinitely otherwise
 */
public class RepeatParticleRandomizer implements ParticleRandomizer {
    public final Integer limit;
    public CircularList<Particle> parts;
    int idx = 0;

    public RepeatParticleRandomizer(List<Particle> parts, Integer limit) {
        this.limit = limit;
        this.parts = new CircularList<>(parts);
    }

    public RepeatParticleRandomizer(List<Particle> parts) {
        this.limit = null;
        this.parts = new CircularList<>(parts);
    }

    @Override
    public Particle generateNext(Gamemode game) {
        if (limit != null) {
            if (idx > limit) {
                throw new IllegalStateException("limit of particles exhausted");
            }
        }
        return parts.get(idx++);
    }
}

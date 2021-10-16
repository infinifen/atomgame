package me.fen.atomgame;

import me.fen.atomgame.particles.Atom;
import me.fen.atomgame.particles.Particle;
import me.fen.atomgame.particles.ParticleType;
import me.fen.atomgame.particles.Plus;

import java.util.ArrayList;
import java.util.List;

public class Game {
    boolean isNextMinusAbsorbed = false;
    CircularList<Particle> particles;
    Particle next;
    ParticleRandomizer randomizer;
    public static final int PARTICLE_LIMIT = 18;

    public Game(ParticleRandomizer rand) {
        particles = new CircularList<>(19);
        randomizer = rand;
        rerollNext();
    }

    protected void rerollNext() {
        next = randomizer.generateNext(this);
    }

    public List<FusionResult> processTick() {
        System.out.println("pt " + particles);
        List<FusionResult> r = new ArrayList<>();
        Integer fusionCenter = findFusion();
        while (fusionCenter != null) {
            FusionResult result = processFusion(fusionCenter);
            removeFusedParticles(result);
            r.add(result);
            fusionCenter = findFusion();
        }

        return r;
    }

    protected void removeFusedParticles(FusionResult result) {
        int start = result.center - result.radius;
        int end = result.center + result.radius;
        System.out.println(result);
        // can't replace with sublist because circular array magic can make it not work
        for (int i = end; i >= start; i--) {
            particles.remove(i);
        } // remove all particles affected by reaction
        particles.add(start, new Atom(result.newAtomicNumber));
    }

    /**
     * @return center of fusion if any else null
     */
    protected Integer findFusion() {
        if (particles.size() < 3) // no fusion can possibly occur with less than 3 particles
            return null;
        for (int i = 0; i < particles.size(); i++) {
//            System.out.format("checking %d\n", i);
            Particle center = particles.get(i);
            Particle next = particles.get(i + 1);
            Particle prev = particles.get(i - 1);
//            System.out.println(prev);
//            System.out.println(center);
//            System.out.println(next);
            if (Utils.isDarkPlus(center)) {
                return i;
            }
            if (Utils.isAtom(next) && Utils.isAtom(prev) && Utils.isPlus(center)) {
                if (prev.getReactionValue() == next.getReactionValue()){
                    System.out.format("Fusion with center on %d\n", i);
                    return i;
                }
            }
        }
        return null;
    }

    protected FusionResult processFusion(int center) {
        Particle centerParticle = particles.get(center);
        int radius;
        int newAtomicNumber;
        if (Utils.isDarkPlus(centerParticle)) {
            radius = 2; // ignore the nearest two particles because dark pluses will join anything
            Particle next = particles.get(center + 1);
            Particle prev = particles.get(center - 1);
            // dark plus increments the atomic number of the larger of the things by 3
            newAtomicNumber = Math.max(prev.getReactionValue(), next.getReactionValue()) + 3;
            System.out.println(newAtomicNumber);
        } else {
            radius = 1;
            newAtomicNumber = particles.get(center + 1).getReactionValue();
        }
        // the particles next to the plus are guaranteed to be the same and atoms
        while (radius <= ((particles.size() - 1) / 2)) {
            Particle pNext = particles.get(center + radius);
            Particle pPrev = particles.get(center - radius);
            if (Utils.isAtom(pNext) && Utils.isAtom(pPrev)) {
                Atom next = (Atom) particles.get(center + radius);
                Atom prev = (Atom) particles.get(center - radius);
                if (next.getAtomicNumber().equals(prev.getAtomicNumber())) {
                    if (radius == 1) {
                        // first fusion always increments by 1
                        newAtomicNumber++;
                    } else {
                        if (next.getAtomicNumber() < newAtomicNumber) {
                            newAtomicNumber++;
                        } else {
                            newAtomicNumber = Math.max(newAtomicNumber, next.getAtomicNumber()) + 2;
                        }
                    }
                    radius++;
                    continue;
                }
            }
            break;
        }
        return new FusionResult(newAtomicNumber, center, radius - 1);
    }

    public CircularList<Particle> getParticles() {
        return particles;
    }

    public Particle getNext() {
        return next;
    }

    /**
     * Applies the next particle to the given index and processes physics
     *
     * @param placementIndex index to which the current particle should be applied
     * @return Result of fusion or null if no fusion occured
     */
    public List<FusionResult> doMove(int placementIndex) throws GameOverException {
        if (isGameOver()) {
            throw new GameOverException();
        }
        ParticleType particleType = next.getParticleType();
        return switch (particleType) {
            case ATOM, PLUS, DARK_PLUS -> {
                particles.add(placementIndex, next);
                isNextMinusAbsorbed = false;
                rerollNext();
                yield processTick();
            }
            case MINUS -> {
                next = particles.remove(placementIndex);
                isNextMinusAbsorbed = true;
                yield processTick();
            }
        };
    }

    public boolean isGameOver() {
        return particles.size() > PARTICLE_LIMIT;
    }

    public void specialAbility() {
        if (isNextMinusAbsorbed) {
            next = new Plus();
            isNextMinusAbsorbed = false;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "particles=" + particles +
                ", next=" + next +
                '}';
    }
}

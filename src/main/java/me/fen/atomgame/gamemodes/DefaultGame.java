package me.fen.atomgame.gamemodes;

import me.fen.atomgame.*;
import me.fen.atomgame.particles.Atom;
import me.fen.atomgame.particles.Particle;
import me.fen.atomgame.particles.ParticleType;
import me.fen.atomgame.particles.Plus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultGame implements Gamemode {
    public static int PARTICLE_LIMIT = 18;
    boolean isNextMinusAbsorbed = false;
    protected CircularList<Particle> particles;
    protected List<Particle> next = new ArrayList<>(1);
    protected ParticleRandomizer randomizer;
    protected ScoringStrategy scoringStrategy;

    public DefaultGame(ParticleRandomizer rand, ScoringStrategy sc) {
        particles = new CircularList<>(19);
        randomizer = rand;
        scoringStrategy = sc;
        rerollNext();
    }

    protected void rerollNext() {
        next.set(0, randomizer.generateNext(this));
    }

    protected TickResult processTick() {
        System.out.println("pt " + particles);
        CircularList<Particle> beforeFusions = new CircularList<>(particles);
        List<FusionResult> frs = new ArrayList<>();
        Integer fusionCenter = findFusion();
        while (fusionCenter != null) {
            FusionResult result = processFusion(fusionCenter);
            scoringStrategy.scoreFusion(result, this);
            replaceFusedParticles(result);
            result.particlesAfter = new CircularList<>(particles);
            frs.add(result);
            fusionCenter = findFusion();
        }
        return new TickResult(frs, beforeFusions);
    }

    protected void replaceFusedParticles(FusionResult result) {
        int start = result.center - result.radius;
        int end = result.center + result.radius;
        System.out.println(result);
        // can't replace with sublist because circular array magic can make it not work
        // remove all particles affected by reaction
        for (int i = end; i >= start; i--) {
            particles.set(i, null);
        }
        particles.removeIf(Objects::isNull);
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
                if (prev.getReactionValue() == next.getReactionValue()) {
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
        List<Integer> atomicNumberSteps = new ArrayList<>();
        if (Utils.isDarkPlus(centerParticle)) {
            radius = 2; // ignore the nearest two particles because dark pluses will join anything
            Particle next = particles.get(center + 1);
            Particle prev = particles.get(center - 1);
            atomicNumberSteps.add(prev.getReactionValue());
            // dark plus increments the atomic number of the larger of the things by 3
            newAtomicNumber = Math.max(prev.getReactionValue(), next.getReactionValue()) + 3;
            atomicNumberSteps.add(newAtomicNumber);
            System.out.println(newAtomicNumber);
        } else {
            radius = 1;
            newAtomicNumber = particles.get(center + 1).getReactionValue();
            atomicNumberSteps.add(newAtomicNumber);
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
                    atomicNumberSteps.add(newAtomicNumber);
                    continue;
                }
            }
            break;
        }
        return new FusionResult(newAtomicNumber, center, radius - 1, atomicNumberSteps, null);
    }

    public CircularList<Particle> getParticles() {
        return particles;
    }

    public List<Particle> getNext() {
        return next;
    }

    /**
     * Applies the next particle to the given index and processes physics
     *
     * @param placementIndex index to which the current particle should be applied
     * @return result of move
     */
    public TickResult doMove(int placementIndex) throws GameOverException {
        CircularList<Particle> beforeInput = new CircularList<>(particles);
        insertParticle(placementIndex);
        TickResult tr = processLogic();
        tr.beforeInput = beforeInput;
        return tr;
    }

    public void insertParticle(int placementIndex) {
        ParticleType particleType = next.get(0).getParticleType();
        switch (particleType) {
            case ATOM, PLUS, DARK_PLUS -> {
                particles.add(placementIndex, next.get(0));
                isNextMinusAbsorbed = false;
                rerollNext();
            }
            case MINUS -> {
                next.set(0, particles.remove(placementIndex));
                isNextMinusAbsorbed = true;
            }
        }
    }

    public TickResult processLogic() throws GameOverException {
        if (isGameOver()) {
            scoringStrategy.scoreGameOver(this);
            throw new GameOverException();
        } else {
            return processTick();
        }
    }

    public long getScore() {
        return scoringStrategy.getScore();
    }

    public boolean isGameOver() {
        return particles.size() > PARTICLE_LIMIT;
    }

    public void specialAbility() {
        if (isNextMinusAbsorbed) {
            next.set(0, new Plus());
            isNextMinusAbsorbed = false;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "particles=" + particles +
                ", next=" + next +
                ", score=" + getScore() +
                '}';
    }
}

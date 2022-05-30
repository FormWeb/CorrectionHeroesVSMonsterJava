package be.bxl.formation.models;

import be.bxl.formation.helpers.CaracHelper;

public abstract class Personnage {
    private final int endurance;
    private final int force;
    private int pdv;

    public Personnage() {
        this.endurance = CaracHelper.getCarac();
        this.force = CaracHelper.getCarac();
        this.pdv = this.getPdvMax();
    }

    public int getEndurance() {
        return endurance;
    }

    public int getForce() {
        return force;
    }

    public int getPdv() {
        if (this.pdv < 1) {
            return 0;
        }
        return pdv;
    }

    public boolean estMort() {
        return this.pdv < 1;
    }

    public int getPdvMax() {
        return this.getEndurance() + CaracHelper.getModif(this.getEndurance());
    }

    public void attaquer(Personnage personnage) {
        int degat = CaracHelper.getDegat(this.getForce());
        personnage.pdv = personnage.pdv - degat;
    }

    public void seReposer() {
        this.pdv = this.getPdvMax();
    }

    @Override
    public String toString() {
        return "\nEndurance=" + endurance +
                "\nForce=" + force +
                "\nPdv =" + pdv;
    }
}

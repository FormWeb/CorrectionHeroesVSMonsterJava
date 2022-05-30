package be.bxl.formation.models;

import be.bxl.formation.helpers.CaracHelper;
import be.bxl.formation.interfaces.IOr;

public class Orque extends Monstre implements IOr {
    private final int or;

    public Orque() {
        super();
        this.or = CaracHelper.getOr();
    }

    @Override
    public int getOr() {
        return or;
    }

    @Override
    public int getForce() {
        return super.getForce() + 1;
    }
}

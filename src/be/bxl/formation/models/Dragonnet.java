package be.bxl.formation.models;

import be.bxl.formation.helpers.CaracHelper;
import be.bxl.formation.interfaces.ICuir;
import be.bxl.formation.interfaces.IOr;

public class Dragonnet extends Monstre implements IOr, ICuir {
    private final int or;
    private final int cuir;

    public Dragonnet() {
        super();
        this.or = CaracHelper.getOr();
        this.cuir = CaracHelper.getCuir();
    }

    @Override
    public int getOr() {
        return or;
    }

    @Override
    public int getCuir() {
        return cuir;
    }

    @Override
    public int getEndurance() {
        return super.getEndurance() + 1;
    }
}

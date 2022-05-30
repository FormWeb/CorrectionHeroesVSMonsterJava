package be.bxl.formation.models;

import be.bxl.formation.helpers.CaracHelper;
import be.bxl.formation.interfaces.ICuir;

public class Loup extends Monstre implements ICuir {
    private final int cuir;

    public Loup() {
        super();
        this.cuir = CaracHelper.getCuir();
    }

    @Override
    public int getCuir() {
        return cuir;
    }


}

package be.bxl.formation.models;

import be.bxl.formation.interfaces.ICuir;
import be.bxl.formation.interfaces.IOr;

public abstract class Hero extends Personnage implements ICuir, IOr {
    private String name;
    private int or;
    private int cuir;

    public Hero(String name) {
        super();
        this.name = name;
        this.or = 0;
        this.cuir = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getOr() {
        return or;
    }

    @Override
    public int getCuir() {
        return cuir;
    }

    public void depouiller(Monstre monstre) {
        if (monstre instanceof IOr) {
            this.or += ((IOr) monstre).getOr();
        }

        if (monstre instanceof ICuir) {
            this.cuir += ((ICuir) monstre).getCuir();
        }
    }

    @Override
    public String toString() {
        return "Name=" + name + super.toString() +
                "\nOr=" + or +
                "\nCuir=" + cuir;
    }
}

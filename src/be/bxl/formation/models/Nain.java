package be.bxl.formation.models;

public class Nain extends Hero{
    public Nain(String name) {
        super(name);
    }

    @Override
    public int getEndurance() {
        return super.getEndurance() + 2;
    }
}

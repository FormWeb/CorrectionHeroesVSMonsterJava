package be.bxl.formation.models;

public class Humain extends Hero{
    public Humain(String name) {
        super(name);
    }

    @Override
    public int getEndurance() {
        return super.getEndurance() + 1;
    }

    @Override
    public int getForce() {
        return super.getForce() + 1;
    }
}

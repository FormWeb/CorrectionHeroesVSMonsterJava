package be.bxl.formation.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CaracHelper {
    public static int getCarac() {
        ArrayList<Integer> jets = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            jets.add(rand.nextInt(6) + 1);
        }

        Collections.sort(jets);

        jets.remove(0);

        int somme = 0;
        for (int jet : jets) {
            somme += jet;
        }

        return somme;
    }

    public static int getModif(int carac) {
        if (carac < 5) {
            return -1;
        }
        else if (carac < 10) {
            return 0;
        }
        else if (carac < 15) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public static int getDegat(int carac) {
        Random rand = new Random();
        return rand.nextInt(4) + 1 + getModif(carac);
    }

    public static int getCuir() {
        Random random = new Random();
        return random.nextInt(4) + 1;
    }

    public static int getOr() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}

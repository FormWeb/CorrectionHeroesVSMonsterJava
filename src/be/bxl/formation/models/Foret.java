package be.bxl.formation.models;

import be.bxl.formation.enums.Direction;

import java.util.Arrays;
import java.util.Random;

public class Foret {
    public static String emptyCell = " ";

    private final String[][] plateau;

    private Hero joueur;
    private Position posJoueur;

    public Foret(int taille, int nbrMonstre) {
        if (nbrMonstre > 5) {
            nbrMonstre = 4;
        }
        Random rand = new Random();

        this.posJoueur = new Position(rand.nextInt(taille), rand.nextInt(taille));
        this.plateau = this.initialiserPlateau(taille, nbrMonstre);
    }

    public Position getPosJoueur() {
        return posJoueur;
    }

    public void setPosJoueur(Position posJoueur) {
        this.posJoueur = posJoueur;
    }

    public Hero getJoueur() {
        return joueur;
    }

    public void setJoueur(Hero joueur) {
        this.joueur = joueur;
    }

    public boolean isFinish() {
        if (joueur.estMort()) {
            return true;
        }

        for (String[] strings : this.plateau) {
            for (int j = 0; j < this.plateau.length; j++) {
                if (!strings[j].equals(emptyCell) && !strings[j].equals("P")) {
                    return false;
                }
            }
        }

        return true;
    }

    public void moveJoueur(Direction direction) {
        this.plateau[this.posJoueur.getLigne()][this.posJoueur.getColonne()] = emptyCell;

        if (direction == Direction.BOTTOM) {
            if (posJoueur.getLigne() < this.plateau.length - 1) {
                this.posJoueur.setLigne(this.posJoueur.getLigne() + 1);
            }
        }

        if (direction == Direction.TOP) {
            if (posJoueur.getLigne() > 0) {
                this.posJoueur.setLigne(this.posJoueur.getLigne() - 1);
            }
        }

        if (direction == Direction.LEFT) {
            if (posJoueur.getColonne() > 0) {
                this.posJoueur.setColonne(this.posJoueur.getColonne() - 1);
            }
        }

        if (direction == Direction.RIGHT) {
            if (posJoueur.getColonne() < this.plateau.length - 1) {
                this.posJoueur.setColonne(this.posJoueur.getColonne() + 1);
            }
        }

        this.plateau[this.posJoueur.getLigne()][this.posJoueur.getColonne()] = "P";

        this.checkBattle();
    }

    private void checkBattle() {
        int posLigne = this.posJoueur.getLigne();
        int posColonne = this.posJoueur.getColonne();
        for (int i = -1; i < 2; i = i + 2) {
            if (posLigne + i >= 0 && posLigne + i < this.plateau.length) {
                if (!this.plateau[posLigne + i][posColonne].equals(emptyCell)) {
                    this.fight(this.plateau[posLigne + i][posColonne], new Position(posLigne + i, posColonne));
                }
            }

            if (posColonne + i >= 0 && posColonne + i < this.plateau.length) {
                if (!this.plateau[posLigne][posColonne + i].equals(emptyCell)) {
                    this.fight(this.plateau[posLigne][posColonne + i], new Position(posLigne, posColonne + i));
                }
            }
        }
    }

    private void fight(String monsterCode, Position monsterPosition) {
        Monstre monstre;

        switch (monsterCode) {
            case "L":
                monstre = new Loup();
                break;
            case "D":
                monstre = new Dragonnet();
                break;
            case "O":
                monstre = new Orque();
                break;
            default:
                monstre = new Loup();
                break;
        }

        Random random = new Random();

        System.out.println("Vous rencontrez un " + monstre.getClass().getSimpleName());

        int turn = random.nextInt(2);

        while (!monstre.estMort() && !joueur.estMort()) {
            if (turn == 0) {
                joueur.attaquer(monstre);
                turn = 1;
                System.out.printf("Vous attaquer %s, il lui reste %s pdv \n", monstre.getClass().getSimpleName(), monstre.getPdv());
            }
            else {
                monstre.attaquer(joueur);
                turn = 0;
                System.out.printf("%s vous attaque ! Il vous reste %s pdv \n", monstre.getClass().getSimpleName(), joueur.getPdv());
            }
        }

        if (!joueur.estMort()) {
            System.out.println("Vous avez vaincu " + monstre.getClass().getSimpleName());
            joueur.depouiller(monstre);
            System.out.println();
            System.out.println(joueur);
            joueur.seReposer();
            this.plateau[monsterPosition.getLigne()][monsterPosition.getColonne()] = emptyCell;
            System.out.println("--------------------------------");
        }
        else {
            System.out.println("Vous avez été vaincu par " + monstre.getClass().getSimpleName());
            System.out.println("---------------------------------");
            System.out.println(joueur);
        }
    }

    private String[][] initialiserPlateau(int taille, int nbrMonstre) {
        Random rand = new Random();
        String[][] newPlateau = new String[taille][taille];

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                newPlateau[i][j] = emptyCell;
            }
        }

        newPlateau[posJoueur.getLigne()][posJoueur.getColonne()] = "P";

        newPlateau = placeMonster(newPlateau, nbrMonstre);

        return newPlateau;
    }

    private String[][] placeMonster(String[][] p, int nbrMonstre) {
        Random rand = new Random();
        String[][] newP = p.clone();
        for (int i = 0; i < nbrMonstre; i++) {
            boolean wellPlaced = false;
            Position positionMonstre = null;
            while (!wellPlaced) {
                positionMonstre = new Position(rand.nextInt(p.length), rand.nextInt(p.length));
                wellPlaced = checkWellPlaced(positionMonstre, p);
            }
            newP[positionMonstre.getLigne()][positionMonstre.getColonne()] = generateMonsterCell();
        }
        return newP;
    }

    private boolean checkWellPlaced(Position positionMonstre, String[][] p) {
        int posLigne = positionMonstre.getLigne();
        int posColonne = positionMonstre.getColonne();
        for (int i = -2; i < 3; i++) {
            if (posLigne + i >= 0 && posLigne + i < p.length) {
                if (!p[posLigne + i][posColonne].equals(emptyCell)) {
                    return false;
                }
            }

            if (posColonne + i >= 0 && posColonne + i < p.length) {
                if (!p[posLigne][posColonne + i].equals(emptyCell)) {
                    return false;
                }
            }
        }

        if (posLigne - 1 >= 0 && posLigne + 1 < p.length && posColonne - 1 >= 0 && posColonne + 1 < p.length) {
            return p[posLigne + 1][posColonne - 1].equals(emptyCell)
                    && p[posLigne - 1][posColonne + 1].equals(emptyCell)
                    && p[posLigne + 1][posColonne + 1].equals(emptyCell)
                    && p[posLigne - 1][posColonne - 1].equals(emptyCell);
        }

        return true;
    }

    private String generateMonsterCell() {
        Random rand = new Random();
        int i = rand.nextInt(4) + 1;
        if (i == 1) {
            return "L";
        }
        else if (i == 2) {
            return "O";
        }
        else {
            return "D";
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String[] ligne : this.plateau) {
            result.append(Arrays.toString(ligne));
            result.append("\n");
        }
        return result.toString();
    }
}

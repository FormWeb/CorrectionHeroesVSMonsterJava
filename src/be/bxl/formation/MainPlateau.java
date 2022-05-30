package be.bxl.formation;

import be.bxl.formation.enums.Direction;
import be.bxl.formation.models.Foret;
import be.bxl.formation.models.Hero;
import be.bxl.formation.models.Humain;
import be.bxl.formation.models.Nain;

import java.util.Arrays;
import java.util.Scanner;

public class MainPlateau {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Foret foret = new Foret(10, 5);
        System.out.println("Bienvenue dans Heroes VS Monster");
        System.out.println("Voulez-vous incarner un nain (1) ou un humain (2) ?");
        int race = Integer.parseInt(sc.nextLine());
        System.out.println("Quel est votre nom ?");
        String nom = sc.nextLine();
        Hero hero;
        if (race == 1) {
            hero = new Nain(nom);
        }
        else {
            hero = new Humain(nom);
        }

        foret.setJoueur(hero);

        System.out.println(hero);
        System.out.println();

        String choix = "";
        while (!choix.equals("l") && !foret.isFinish()) {
            System.out.println(foret);
            System.out.println("Par où voulez aller (z, q, s, d and l pour quitter)");
            choix = sc.nextLine();
            switch (choix) {
                case "z":
                    foret.moveJoueur(Direction.TOP);
                    break;
                case "q":
                    foret.moveJoueur(Direction.LEFT);
                    break;
                case "s":
                    foret.moveJoueur(Direction.BOTTOM);
                    break;
                case "d":
                    foret.moveJoueur(Direction.RIGHT);
                    break;
                case "l":
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Mauvais encodage !");
            }
        }

        if (foret.getJoueur().estMort()) {
            System.out.println("Vous vous êtes bien battu !");
        }
        else {
            System.out.println("Vous avez fini le jeu !");
        }
    }
}

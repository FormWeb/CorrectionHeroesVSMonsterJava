package be.bxl.formation;

import be.bxl.formation.models.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bienvenue dans Heroes vs Monster");
        System.out.println("Voulez-vous incarner un nain (1) ou un humain (2) ?");
        int choix = Integer.parseInt(sc.nextLine());
        System.out.println("Quel est votre nom ?");
        String nom = sc.nextLine();
        Hero hero;
        if (choix == 1) {
            hero = new Nain(nom);
        }
        else {
            hero = new Humain(nom);
        }

        System.out.println(hero);
        System.out.println();

        while (!hero.estMort()) {
            Monstre monstre = generateMonstre(random);

            System.out.println("Vous rencontrez un " + monstre.getClass().getSimpleName());

            int turn = random.nextInt(2);

            while (!monstre.estMort() && !hero.estMort()) {
                if (turn == 0) {
                    hero.attaquer(monstre);
                    turn = 1;
                    System.out.printf("Vous attaquer %s, il lui reste %s pdv \n", monstre.getClass().getSimpleName(), monstre.getPdv());
                }
                else {
                    monstre.attaquer(hero);
                    turn = 0;
                    System.out.printf("%s vous attaque ! Il vous reste %s pdv \n", monstre.getClass().getSimpleName(), hero.getPdv());
                }
            }

            if (!hero.estMort()) {
                System.out.println("Vous avez vaincu " + monstre.getClass().getSimpleName());
                hero.depouiller(monstre);
                System.out.println();
                System.out.println(hero);
                hero.seReposer();
                System.out.println("--------------------------------");
            }
            else {
                System.out.println("Vous avez été vaincu par " + monstre.getClass().getSimpleName());
                System.out.println("---------------------------------");
                System.out.println(hero);
            }
        }
    }

    private static Monstre generateMonstre(Random random) {
        int i = random.nextInt(3) + 1;
        if (i == 1) {
            return new Loup();
        }
        else if (i == 2) {
            return new Orque();
        }
        else {
            return new Dragonnet();
        }
    }
}

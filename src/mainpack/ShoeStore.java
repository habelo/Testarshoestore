package mainpack;


import tables.Costumer;

import java.util.Scanner;

public class ShoeStore {

    private Repository rep;
    private Costumer user;


    private ShoeStore() {
         rep = new Repository();
        welcome2ShoeStore();

    }

    private void welcome2ShoeStore() {

        String input;
        Scanner sc = new Scanner(System.in);
        rep.printStage1();
        System.out.println("Välkommen till KärleksfylliSkor, vem är du: ");

        while(true) {
            input = sc.nextLine();
            user = rep.idInCostumerList(input);
            if (user == null)
                rep.printStage1();
            else
                break;
        }
        System.out.println(rep.printStage2());
        rep.printStage3(user);
        System.out.println("Vilken beställning (siffra)");
        input = sc.nextLine();
        rep.addToCart(input, user);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        new ShoeStore();
    }
}

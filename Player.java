import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private String guess;
    private double cash;
    private double bet;
    private double addCash;
    private double subCash;
    private int round = 0;
    private int won = 0;

    public Player(String playerName) {
        name = playerName;
        guess = " ";
        cash = 0;
        round = getRound();
    }

    public void initialCash() {
        Scanner b = new Scanner(System.in);
        boolean good = false;
        do{
            System.out.println("Enter your initial cash ammount > ");
            cash = b.nextDouble();
            if (cash > 0)
                good = true;
        }
        while(!good);
    }

    public void makeBet() {
        Scanner b = new Scanner(System.in);
        boolean good = false;
        do{
            System.out.println("Enter your bet ammount > ");
            bet = b.nextDouble();
            if (bet <= cash && bet > 0)
                good = true;
        }
        while(!good);
    }

    public void makeGuess() {
        Scanner sc = new Scanner(System.in);
        boolean good = false;
        do{
            System.out.println("Enter your guess for (O)ver, (U)nder, or (S)even > ");
            String guessLetter = sc.next();
            switch (guessLetter) {
                case "u":
                    guess = "Under";
                    good = true;
                    break;
                case "U":
                    guess = "Under";
                    good = true;
                    break;
                case "s":
                    guess = "Seven";
                    good = true;
                    break;
                case "S":
                    guess = "Seven";
                    good = true;
                    break;
                case "o":
                    guess = "Over";
                    good = true;
                    break;
                case "O":
                    guess = "Over";
                    good = true;
                    break;
            }
        }
        while(!good);
    }

    public void atZero() {
        if (cash == 0) {
        System.out.println("Sorry, you're out of money.");
        System.out.println("Thank you for playing Over and Under!");
        System.exit(0);
        }
    }

    public void newPerson() {
        initialCash();
        System.out.println("Welcome to Over and Under new player!");
        System.out.println("Your name: " + getName());
        System.out.printf("You currently have: $%.2f", getCash());
        System.out.println(" ");
    }

    public void addCash() {
        cash = cash + (bet * 2);
    }

    public void subtractCash() {
        cash = cash - bet;
    }

    public String getName() {
        return name;
    }

    public String getGuess() {
        return guess;
    }

    public double getAddBet() {
        bet = bet * 2;
        return bet;
    }

    public double getSubBet() {
        return bet;
    }

    public void addRound() {
        round = round + 1;
    }

    public void roundsWon() {
        won = won + 1;
    }

    public int getWon() {
        return won;
    }

    public int getRound() {
        return round;
    }

    public double getCash() {
        return cash;
    }
}
        
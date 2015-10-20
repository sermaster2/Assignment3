//Sercan Cagatay, CS0401, 3pm, Professor Laboon
//Player, Contains player data and first time player methods

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Player {
    private String name; //variables initialized
    private String guess;
    private double cash;
    private double bet;
    private double addCash;
    private double subCash;
    private int round = 0;
    private int won = 0;


    public Player(String playerName) { //player data
        name = playerName;
        guess = " ";
        cash = 0;
        round = getRound();
    }

    public void initialCash() { //Asks for initial cash ammount for first time players
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

    public void makeBet() { //Allows for players to make a bet
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

    public void makeGuess() { //Allows for players to make a guess
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

    public void atZero() { //Checks for 0 on money balances
        if (cash == 0) {
        System.out.println("Sorry, you're out of money.");
        System.out.println("Thank you for playing Over and Under!");
        System.exit(0);
        }
    }

    public void newPerson() { //Executes if new player is found
        initialCash();
        System.out.println("Welcome to Over and Under new player!");
        System.out.println("Your name: " + getName());
        System.out.printf("You currently have: $%.2f", getCash());
        System.out.println(" ");
    }

    public void addCash() { //These methods calculate the cash won or lost and sets/returns variables to the main program
        cash = cash + (bet * 2);
    }

    public void addSeven() {
        cash = cash + (bet * 4);
    }

    public double getAddSeven() {
        bet = bet + (bet * 4);
        return bet;
    }

    public void subtractCash() {
        cash = cash - bet;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
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

    public void setWon(int w) {
        won = w;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int r) {
        round = r;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double c) {
        cash = c;
    }
}
        
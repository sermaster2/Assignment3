//Sercan Cagatay, CS0401, 3pm, Professor Laboon
//Assignment3, Over and Under Gambling Program

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Assignment3 {

    public static void main(String []args) throws IOException{
        String playerNameFirst;
        String playerNameLast;
        String decision;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your first name: ");
        playerNameFirst = sc.nextLine();

        Player player = new Player(playerNameFirst); //Player identified by first name
        Dice dice = new Dice();
        File file = new File(playerNameFirst);

        if (!file.exists()) {  //Executes this part of program if there is no file present that corresponds to the first name entered
            System.out.println("Enter your last name: "); 
            playerNameLast = sc.nextLine();

            dice = new Dice();

            player = new Player(playerNameFirst + " " + playerNameLast); //Player re-established

            player.newPerson();
            boolean good = false;
        do{
            System.out.println("Would you like to play a round (y/n) > "); //Asks player to keep going
            decision = sc.nextLine();
            switch (decision){
            case "y":
                player.atZero(); //Does not allow player to continue if $ balance is 0
                player.addRound(); //Adds a round to the round counter
                System.out.println("---------------------------------");
                System.out.printf("Now playing round %d.\n", player.getRound()); //Round counter

                player.makeBet(); //Allows players to make a bet

                dice.rollDice(); //Rolls the dice and gets results

                player.makeGuess(); //Allows players to guess Over, Under, or Seven

                roundResults(dice, player); //Gives results of round
                break;
            case "n": //Gives final overall results
                good = true;
                break;
            }
        }
        while(!good);

            displayTotalResults(player); //Final results

            File f = new File(playerNameFirst); //Outputs the player's name, balance, round total, and win total in txt file
            PrintWriter outputFile = new PrintWriter (f);           
            outputFile.println(player.getName());
            outputFile.printf("%.2f\n", player.getCash());
            outputFile.println(player.getRound());
            outputFile.println(player.getWon());
            outputFile.close();
            System.exit(0);
        }
        if (file.exists()) { //Executes this part of the program if a file is found
            Scanner inputFile = new Scanner(file);
            while (inputFile.hasNext()) {
                String n = inputFile.nextLine(); //Values are re-established here
                player.setName(n); //These methods establish said values
                String c = inputFile.nextLine();
                String r = inputFile.nextLine();
                String w = inputFile.nextLine();
                double cash = Double.parseDouble(c);
                player.setCash(cash);
                int round = Integer.parseInt(r);
                player.setRound(round);
                int won = Integer.parseInt(w);
                player.setWon(won);
                System.out.println("Welcome back! Here is where you left off last time:");
                System.out.println("Your name: " + player.getName());
                System.out.printf("Money left: %.2f\n", player.getCash());
                System.out.printf("Rounds played: %d\n", player.getRound());
                System.out.printf("Rounds won: %d\n", player.getWon());
            }
            boolean good2 = false;

        do{
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Would you like to play a round (y/n) > ");
            decision = keyboard.nextLine();
            switch (decision){
            case "y":
                player.atZero();
                player.addRound();
                System.out.println("---------------------------------");
                System.out.printf("Now playing round %d.\n", player.getRound());

                player.makeBet();

                dice.rollDice();

                player.makeGuess();

                roundResults(dice, player);
                break;
            case "n":
                good2 = true;
                break;
            }
        }
        while(!good2);

        displayTotalResults(player);

            File f = new File(playerNameFirst);
            PrintWriter outputFile = new PrintWriter (f);           
            outputFile.println(player.getName());
            outputFile.printf("%.2f\n", player.getCash());
            outputFile.println(player.getRound());
            outputFile.println(player.getWon());
            outputFile.close();

            System.exit(0);

        }
    }

    public static void roundResults(Dice dice, Player player) {
        System.out.printf("The dice rolled %d and %d.\n", dice.getDie1Value(), dice.getDie2Value());
        System.out.printf("The total is %d.\n", (dice.getDie1Value() + dice.getDie2Value()));
        System.out.printf("Result: %s\n", dice.getOverorUnder()); //Dice is rolled within this method
        checkGuess(player, dice); //Checks if the guess corresponds to the actual value
    }

    public static void checkGuess(Player player, Dice dice) {
        String guess = player.getGuess();
        String overOrUnderResult = dice.getOverorUnder();

        System.out.printf("The player %s guessed %s.\n", player.getName(), player.getGuess());

        if (guess.equalsIgnoreCase(overOrUnderResult)) {
            if (guess == "Seven") { //Seven allows for the bet to be quadrupled
            player.addSeven();
            System.out.println("You WON this round.");
            System.out.printf("Awarding $%.2f to %s.\n", player.getAddSeven(), player.getName()); //Thes methods get the actual winnings and player name
            System.out.printf("Your current balance $%.2f.\n", player.getCash());
            }
            else {
            player.addCash();
            System.out.println("You WON this round.");
            player.roundsWon();
            System.out.printf("Awarding $%.2f to %s.\n", player.getAddBet(), player.getName());
            System.out.printf("Your current balance $%.2f.\n", player.getCash());
            }
        }
        else {
            player.subtractCash();
            System.out.println("You LOST this round.");
            System.out.printf("Subtracting $%.2f from %s.\n", player.getSubBet(), player.getName()); //The Sub Methods subtract from the total winnings
            System.out.printf("Your current balance $%.2f.\n", player.getCash());
        }

    }

    public static void displayTotalResults(Player player) { //Displays final results when player quits program
        System.out.println("-----------------------------------");
        System.out.println("Thank you for playing Over and Under!");
        System.out.println("Here is your updated information:");
        System.out.println("Your name: " + player.getName());
        System.out.printf("Money left: $%.2f\n", player.getCash());
        System.out.printf("Rounds played: %d\n", player.getRound());
        System.out.printf("Rounds won: %d\n", player.getWon());

    }

}      
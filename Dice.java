//Sercan Cagatay, CS0401, 3pm, Professor Laboon
//Dice, This part of the program rolls the dice

public class Dice {
     
        private int die1Value; //variables initialized
        private int die2Value;
        
        public Dice() {
            die1Value = 0;
            die2Value = 0;
        }
        
        public void rollDice() {
            die1Value = (int)(Math.random()*6) + 1; //Rolls the 6-sided die randomly
            die2Value = (int)(Math.random()*6) + 1;
        }

    public String getOverorUnder() { //Sets what the values should be interpreted as
        String result;
        int sum = die1Value + die2Value;
        if (sum > 7)
            result = "Over";
        else if (sum == 7)
            result = "Seven";
        else
            result = "Under";
        return result;
    }

    public int getDie1Value() { //returns die values to main program
        return die1Value;
    }

    public int getDie2Value() {
        return die2Value;
    }

}

        
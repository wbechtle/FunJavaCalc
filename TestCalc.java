//Name: Wyatt Bechtle
//Date: 9 April 2023
//Program: Test Calculator Class

//Algorithm
//---------
//Step 1) Display ">>>" prompt.
//Step 2) Pass user input to parseInput.
//Step 3) Display return values if needed.
//Step 4) Do again.(Inifinite loop)
import java.util.Scanner;

public class TestCalc {
    public static void main (String [] args) {

        //Create calculator object for arithmetic.
        Calculator calcObject = new Calculator();

        //Create scanner object to get input from keyboard.
        Scanner scannerObject = new Scanner(System.in);

        //Declare variable for user input.
        String userInput;

        //Declare string variable to receive data from the class.
        String calcClassOutput;

        do {
            //Display input prompt and get input.
            System.out.print(">>>");
            userInput = scannerObject.nextLine();

            //Pass the input to calc's parse input method.
            calcClassOutput = calcObject.parseInput(userInput);

            //Check if there is anything to display.
            if (!calcClassOutput.isEmpty()) {
                System.out.println(calcClassOutput);
            }
        } while (true);

        
    }
}

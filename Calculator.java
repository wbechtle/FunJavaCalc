//Name: Wyatt Bechtle
//Date: 9 April 2023
//Program: Calculator Class
//----------------------------------------
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    //Private instance variables and their default values.
    private BigDecimal rx = new BigDecimal(0);
    private BigDecimal ry = new BigDecimal(0);
    private BigDecimal rm = new BigDecimal(0);
    private BigDecimal rz = new BigDecimal(0);
    private BigDecimal value;
    private String command;
    private String register;

    //No arg. constructor.
    public Calculator() {
    }

    //Calculate Methods...
    //
    //This method is used to parse and categories user input.
    public String parseInput(String input) {

        String errorMessageCommand = "ERROR: Invalid Command";

        //Get string length.
        int inputLength = input.length();

        //Selection statements used to avoid exception.
        if (inputLength >= 3) {
            //Extract command.
            command = input.substring(0, 3);
        }
        else {
            command = "Short Input";
        }
        //Swith finds what command to call.
        switch (input.length()) {

            //ADD, SUB, MUL, DIV, ZMM
            case 3:
                //ADD
                if (command.equals("ADD")) {
                    this.addCommand();
                }
                //SUB
                else if (command.equals("SUB")) {
                    this.subCommand();
                }
                //MUL
                else if (command.equals("MUL")) {
                    this.mulCommand();
                }
                //DIV
                else if (command.equals("DIV")) {
                    this.divCommand();
                }
                //ZMM
                else if (command.equals("ZMM")) {
                    this.zmmCommand();
                }
                else {
                    return errorMessageCommand;
                }
                break;

            //SHO, CLR, MEM
            case 6:

                //Extract register.
                register = input.substring(4, inputLength);

                //SHO
                if (command.equals("SHO")) {
                    String registerContents = this.shoCommand(register);
                    return registerContents;
                }
                //CLR
                else if (command.equals("CLR")) {
                    String possibleErrorCode = this.clrCommand(register);
                    return possibleErrorCode;
                }
                //MEM
                else if (command.equals("MEM")) {
                    String possibleErrorCode = this.memCommand(register);
                    return possibleErrorCode;
                }
                else {
                    return errorMessageCommand;
                }
            //MOV or Incorrect input.
            default:
                
                //Determine if MOV command.
                if (command.equals("MOV")) {

                    //Extract register.
                    register = input.substring(input.indexOf(',') + 2, inputLength);

                    //Extract value and create variable to validate input.
                    String valueString = input.substring(4, input.indexOf(','));
                    boolean isNumeric = true;
                    int dotCount = 0;

                    //Check each character in the valueString.
                    for (int i = 0; i < valueString.length(); i++) {
                        char c = valueString.charAt(i);

                        //Check if not digit.
                        if (!Character.isDigit(c)) {
                            
                            //Check if decimal point.
                            if (c == '.') {
                                if (dotCount == 0) {
                                    dotCount++;
                                } 
                                else {
                                    //Can only be one decimal place.
                                    isNumeric = false;
                                    break;
                                }
                            } 
                            else {
                                //Not a decimal or a digit, invalid.
                                isNumeric = false;
                                break;
                            }
                        }
                    }
                    //switch used to avoid exception.
                    switch (register) {

                        //move value to rx
                        case "rx":  
                            //Only if numeric value.
                            if (isNumeric) {
                                //Create and pass big decimal value and register.
                                BigDecimal valueRX = new BigDecimal(valueString);
                                this.movCommand(valueRX, register);
                            } 
                            else {
                                return "ERROR: Invalid Input";
                            }        
                            break;
            
                        //move value to ry
                        case "ry":
                            //Only if numeric value.
                            if (isNumeric) {
                                //Create and pass big decimal value and register.
                                BigDecimal valueRY = new BigDecimal(valueString);
                                this.movCommand(valueRY, register);
                            } 
                            else {
                                return "ERROR: Invalid Input";
                            }        
                            break;
                        //move value to rm
                        case "rm":
                            //Only if numeric value.
                            if (isNumeric) {
                                //Create and pass big decimal value and register.
                                BigDecimal valueRM = new BigDecimal(valueString);
                                this.movCommand(valueRM, register);
                            } 
                            else {
                                return "ERROR: Invalid Input";
                            }        
                            break;
            
                        //Error, invalid register is entered.
                        default:
                            return errorMessageCommand;
                    } 
                }
                //Error, invalid command is entered.
                else {
                    return errorMessageCommand;
                }
        }
        //Return empty string if successfull.
        return "";
    }
    //This method is used to move operand one into operand two.
    public String movCommand(BigDecimal value, String register) { 

        String errorMessageRegister = "ERROR: Invalid Register";

        //switch used to avoid exception.
        switch (register) {

            //move value to rx
            case "rx":               
                this.rx = value;
                break;

            //move value to ry
            case "ry":
                this.ry = value;
                break;

            //move value to rm
            case "rm":
                this.rm = value;
                break;

            //Error, invalid register is entered.
            default:
                return errorMessageRegister;
        } 
        //Return empty string if successfull.
        return "";        
    } 
    //This method is used to multiply x with y register.
    public void mulCommand() { 
        this.rz = this.rx.multiply(this.ry);
    }
    //This method is used to divide x with y register.
    public void divCommand() { 
        //.stripTrailingZeros() was not in the book, however, I learned about it while looking
        //in RoundingMode.CEILING.
        this.rz = this.rx.divide(this.ry, 20, RoundingMode.CEILING).stripTrailingZeros();
    }
    //This method is used to add x with y register.
    public void addCommand() { 
        this.rz = this.rx.add(this.ry);
    }
    //This method is used to subtract x with y register.
    public void subCommand() { 
        this.rz = this.rx.subtract(this.ry);
    }
    //This method is used to show the value of operand.
    public String shoCommand(String register) { 

        String errorMessageRegister = "ERROR: Invalid Register";

        switch (register) {

            //Return rx register value.
            case "rx":
                return this.rx.toString();

            //Return ry register value.
            case "ry":
                return this.ry.toString();

            //Return rz register value.
            case "rz":
                return this.rz.toString();

            //Return rm register value.
            case "rm":
                return this.rm.toString();

            //Return ERROR message.
            default:
                return errorMessageRegister;
        }
    }
    //This method is used to clear the specified operand.
    public String clrCommand(String register) { 

        String errorMessageRegister = "ERROR: Invalid Register";

        switch (register) {

            //Clear rx register value and set default to 0.
            case "rx":
                this.rx = new BigDecimal(0);
                break;               

            //Clear ry register value and set default to 0.
            case "ry":
                this.ry = new BigDecimal(0);
                break;

            //Clear rz register value and set default to 0.
            case "rz":
                this.rz = new BigDecimal(0);
                break;

            //Clear rm register value and set default to 0.
            case "rm":
                this.rm = new BigDecimal(0);
                break;

            //Return ERROR message.
            default:
                return errorMessageRegister;
        }
        //Return empty string if successfull.
        return "";
    }
    //This method is used to move rz into rm and zero out rz.
    public void zmmCommand() { 
        this.rm = this.rz;
        this.rz = new BigDecimal(0);
    }
    //This method is used to assign a value from mem to the specified operand.
    public String memCommand(String register) { 

        String errorMessageRegister = "ERROR: Invalid Register";

        switch (register) {

            //Assign rx register value.
            case "rx":
                this.rx = this.rm;
                break;               

            //Assign ry register value.
            case "ry":
                this.ry = this.rm;
                break;

            //Return ERROR message.
            default:
                return errorMessageRegister;
        }
        //Return empty string if successfull.
        return "";
    }
}
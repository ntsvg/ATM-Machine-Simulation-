import java.util.Scanner;
public class ATM{
    static int userPIN;
    static double accountBalance;
    static String securityAnswer;
    static final double ASSUMED_BALANCE = 5000.0; //fallback balance shown if recovery is used

    public static void main(String [] args) {
        Scanner scanner=new Scanner(System.in); //Scanner acts as a class and scanner is variable here
        // Step 1. Set up phase
        System.out.println("Welcome to ATM Setup");
        System.out.println("Set a 4-digit Pin: ");
        userPIN = scanner.nextInt();
        while(userPIN < 1000 || userPIN > 9999){
            System.out.println("Invalid Pin. Enter a valid pin");
            userPIN = scanner.nextInt();
        }
        System.out.println("Set your initial account balance: $");
        accountBalance = scanner.nextDouble();
        while(accountBalance < 0){
            System.out.println("Initial balance cannot be negative. Enter again: $");
            accountBalance = scanner.nextDouble();
        }
        scanner.nextLine(); //consume leftover in new line
        System.out.println("Set up security answer !");
        System.out.println("what is your pet name ? ");
        securityAnswer = scanner.nextLine().trim().toLowerCase();

        //Step 2. Simulating card insert
        System.out.println("Please Insert your debit card");
        System.out.println("Card Verified. Connecting to your Bank Server");

        boolean accessGranted = false;

        //Step 3. Ask for Pin
        System.out.println("Enter your 4-digit Pin: ");
        System.out.println("or");
        System.out.println("Type 0 if you have forgotten");
        int enteredPIN = scanner.nextInt();
        if(enteredPIN == 0){
            scanner.nextLine();
            System.out.println("Answer your security question:");
            System.out.println("What is your pet name ?");
            String answer = scanner.nextLine().trim().toLowerCase();
            if(answer.equals(securityAnswer)){
                System.out.println("✅ Access granted via security question.");
                accessGranted = true;
            }
            else{
                System.out.println("❌ Incorrect Answer. Access denied.");
            }
        }
        else if(enteredPIN == userPIN){
            System.out.println("✅ Pin verified. Access granted.");
            accessGranted = true;
        }
        else{
            System.out.println("🚨 Invalid pin entered. Access denied.");
        }
        // Step 4. if access granted it will show the atm menu
        if(accessGranted){
            int choice;
            do{
                System.out.println("---ATM MENU---");
                System.out.println("Click 1 to Check your Account Balance");
                System.out.println("Click 2 to Deposit Money");
                System.out.println("Click 3 to Withdraw Money");
                System.out.println("Click 4 to Change PIN");
                System.out.println("Click 5 to Exit");
                System.out.println("Select one from the above: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Your current account Balance is: $"+accountBalance);
                        break;

                    case 2:
                        System.out.println("Enter the amount to deposit: $");
                        double deposit = scanner.nextDouble();
                        if(deposit <= 0){
                            System.out.println("Invalid deposit amount.");
                        }
                        else{
                            accountBalance += deposit;
                            System.out.println("Deposited Successfully. New balance: $"+accountBalance);
                        }
                        break;

                    case 3:
                        System.out.print("Enter the amount to withdraw: $");
                        double withdraw = scanner.nextDouble();
                        if(withdraw > accountBalance){
                            System.out.println("Error! Insufficient Balance");
                        }
                        else if(withdraw <= 0){
                            System.out.println("Invalid withdrawal amount.");
                        }
                        else{
                                accountBalance -= withdraw;
                                System.out.println("Please collect your cash. New balance: $"+accountBalance);
                        }
                        break;

                    case 4: System.out.println("Enter your current PIN: ");
                    int oldPin = scanner.nextInt();
                    if (oldPin != userPIN){
                        System.out.println("Incorrect current PIN");
                    }
                    else{
                        System.out.println("Enter new 4-digit pin");
                        int newPin = scanner.nextInt();
                        System.out.println("Confirm new PIN: ");
                        int confirmPin = scanner.nextInt();
                        if(newPin == confirmPin && newPin >= 1000 && newPin <= 9999){
                            userPIN = newPin;
                            System.out.println("Pin changed Successfully");
                        }
                        else{
                            System.out.println("PINs do not match or invalid format");
                        }
                    }
                    break;

                    case 5:
                        System.out.println("Exited Successfully !");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (choice != 5);
        }

        scanner.close();
    }
}

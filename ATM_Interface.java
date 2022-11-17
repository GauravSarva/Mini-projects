import java.util.HashMap;
import java.util.Scanner;


class Account{
    private String name;
    private Scanner input;
    private String UserId;
    private int pin;
    private String AccountNo;
    private HashMap<String, Integer> Details;
    private HashMap<String, String> DetailsName;
    private HashMap<String, Integer> DetailsBalance;
    private HashMap<String, String> accountNumber;
    private HashMap<String, String> TransactionHistory;
    private String CheckUserId;
    private int CheckPin;

    Account(){
        input = new Scanner(System.in);
        Details = new HashMap<>();
        DetailsName = new HashMap<>();
        DetailsBalance = new HashMap<>();
        accountNumber = new HashMap<>();
        TransactionHistory = new HashMap<>();
    }

    public void register(){
        System.out.println("\n Enter your name: ");
        name = input.nextLine();

        while(true){
            System.out.println("\n Create your User I'd: ");
            UserId = input.nextLine();

            if(!Details.containsKey(UserId)){
                break;
            }
            else{
                System.out.println("\n Enter valid User I'd: ");
            }
        }

        while(true){
            System.out.println("\n Enter your account number: ");
            AccountNo = input.nextLine();

            if(!Details.containsKey(AccountNo) && AccountNo != name && AccountNo != UserId){
                break;
            }
            else{
                System.out.println("\n Enter valid account number: ");
            }
        }

        while(true){
            System.out.println("Enter your pin: ");
            pin = input.nextInt();
            System.out.print("\n");

            if(pin >= 1000 && pin <= 10000){
                break;
            }
            else{
                System.out.println("Your pin should contain 4 digits!");
            }
        }

        Details.put(UserId, pin);
        DetailsName.put(UserId, name);
        accountNumber.put(AccountNo, UserId);
        input.nextLine();
    }

    public void LogIn(){
        Boolean exit = true;
        int chances = 0;

        while(exit){
            System.out.println("\n Enter your User I'd: ");
            CheckUserId = input.nextLine();
            chances++;

            if(!Details.containsKey(CheckUserId)){
                if(chances == 5){
                    System.out.println("Too many attempts!");
                }
                System.out.println("Enter a valid User I'd!");
            }
            else{
                exit = false;
            }
        }

        exit = true;
        chances = 0;
        
        while(exit){
            System.out.println("\n Enter your pin: ");
            CheckPin = input.nextInt();
            System.out.print("\n");
            chances++;
        
            if(Details.get(CheckUserId) != CheckPin){
        
                if(chances == 5){
                    System.out.println("Too many attempts!");
                }
                System.out.println("Enter a valid User I'd!");
            }
            else{
                exit = false;
                System.out.println("Hello" + this.getName(CheckUserId));
            }
        }
    }

    String getName(String Id){
        return DetailsName.get(Id);
    }

    public void Withdraw(){
        System.out.println("\n Enter the amount to wihdraw from your account: ");
        int amount = input.nextInt();
        System.out.print("\n");

        if(DetailsBalance.get(CheckUserId) != null && amount <= DetailsBalance.get(CheckUserId)){
            DetailsBalance.put(CheckUserId, DetailsBalance.get(CheckUserId) - amount);
            System.out.print("\n");

            String history = amount + " is withdrawn from your account \n";
        
            if(TransactionHistory.get(CheckUserId) == null){
                TransactionHistory.put(CheckUserId, history);
            }
            else{
                history = TransactionHistory.get(CheckUserId) + history;
                TransactionHistory.put(CheckUserId, history);
            }
            System.out.print("\n");
            System.out.println(amount + " has been successfully withdrawn from your account.");
        }
        else{
            System.out.println("Insufficient balance");
        }
    }

    public void deposit(){
        System.out.println("\n Enter the amount to deposit");
        int amount = input.nextInt();
        System.out.print("\n");
        String history = "\n" + amount + " credited to your account \n";

        if(TransactionHistory.get(CheckUserId) == null){
            TransactionHistory.put(CheckUserId, history);
        }
        else{
            history = TransactionHistory.get(CheckUserId) + history;
            TransactionHistory.put(CheckUserId, history);
        }
        System.out.print("\n");
        System.out.println(amount + " successfully credited to your account");
        
        if(DetailsBalance.get(CheckUserId) != null){
            amount += DetailsBalance.get(CheckUserId);
        }
        DetailsBalance.put(CheckUserId, amount);
    }

    public void transfer(){
        System.out.println("\n Enter account number to transfer the ammount: ");
        String AccountNo = input.nextLine();
        System.out.print("\n");
        
        if(!accountNumber.containsKey(AccountNo)){
            System.out.println("\n Enter correct account number");
        }
        else{
            System.out.println("\n Enter the amount: ");
            int TransferAmount = input.nextInt();
            System.out.print("\n");
            int ReceivedAmount = TransferAmount;

            if(TransferAmount <= DetailsBalance.get(CheckUserId) && DetailsBalance.get(CheckUserId) != null){
                DetailsBalance.put(CheckUserId, DetailsBalance.get(CheckUserId) - TransferAmount);
                System.out.print("\n");
                String history = TransferAmount + " has been transferred from your account \n";

                if(TransactionHistory.get(CheckUserId) == null){
                    TransactionHistory.put(CheckUserId, history);
                }
                else{
                    history = TransactionHistory.get(CheckUserId) + history;
                    TransactionHistory.put(CheckUserId, history);
                }
                System.out.println("\n");
                System.out.println(TransferAmount + " successfully transferred from your account");
            }
            else{
                System.out.println("\nInsufficient Balance");
            }
        
            String Id = accountNumber.get(AccountNo);
        
            if(DetailsBalance.get(Id) != null){
                TransferAmount += DetailsBalance.get(CheckUserId);
            }

            DetailsBalance.put(Id, TransferAmount);
            String history = ReceivedAmount + " is received by your account \n";

            if(TransactionHistory.get(Id) == null){
                TransactionHistory.put(Id, history);
            }
            else{
                history = TransactionHistory.get(Id) + history;
                TransactionHistory.put(Id, history);
            }
        }
    }

    public void TransactionHistory(){
        System.out.println("\n Transaction History:");
        String History = TransactionHistory.get(CheckUserId);
        System.out.println(History);
    }
}


public class ATM_Interface {
    public static void main(String args[]){
        Account MyAccount = new Account();
        try (Scanner input = new Scanner(System.in)) {
            int CheckingRegisteration;

            while(true){
                System.out.println("\n");
                System.out.println("Please select from the below options to proceed");
                System.out.println("1. Register");
                System.out.println("2. LogIn \n");
                CheckingRegisteration = input.nextInt();
            
                if(CheckingRegisteration == 1){
                    MyAccount.register();
                }
                else if(CheckingRegisteration == 2){
                    MyAccount.LogIn();
                }
                else{
                    break;
                }
            
                boolean exit = true;
            
                while(exit){
                    System.out.println("1. Transaction History");
                    System.out.println("2. Withdraw money");
                    System.out.println("3. Deposit money");
                    System.out.println("4. Transfer money");
                    System.out.println("5. Exit");

                    int choice = input.nextInt();
            
                    switch(choice){
                        case 1 -> {
                            MyAccount.TransactionHistory();
                        }
                        case 2 -> {
                            MyAccount.Withdraw();
                        }
                        case 3 -> {
                            MyAccount.deposit();
                        }
                        case 4 -> {
                            MyAccount.transfer();
                        }
                        case 5 -> {
                            exit = false;
                        }
                        default -> {
                            exit = false;
                        }
                    }
                }
            }
        }
    }    
}
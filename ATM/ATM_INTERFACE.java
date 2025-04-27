import java.util.Scanner;
class withdraw{
    float withdraw(float balance, Scanner scan){
            System.out.print("please enter the amount to to withdraw = ");
            float amount =scan.nextFloat(); 
            if(amount<=balance){
                balance=balance-amount;
                System.out.println("successfully withdrawed "+amount+" rupees");  
            }
            else{
                System.out.println("Insufficient funds");
            }
            return balance;
    }
}

class deposit{
    float deposit(float balance, Scanner scan){
        System.out.print("please enter the amount to deposit = ");
        float amount = scan.nextFloat();
        balance=balance+amount;
        System.out.println("Successfully withdrawed "+amount);
        return balance;
    }
}

class checkBalance{
    void checkBalance(float balance){
        System.out.println("the balance amount in you account = "+balance);
    }
}

class ATM_INTERFACE{
    static float balance;
    static Scanner scan=new Scanner(System.in);
    public static void main (String args[]){
        withdraw wd=new withdraw();
        deposit dp =new deposit();
        checkBalance cb=new checkBalance();

        System.out.println("__________MENU__________ \n1. WITHDRAW AMOUNT \n2. DEPOSIT AMOUNT\n 3. CHECK ACCOUNT BALANCE \n4. EXIT");
        while (true){
            System.out.print("please enter you option = ");
            int opt=scan.nextInt();
            switch (opt){
                case 1: 
                    balance=wd.withdraw(balance, scan);
                    System.out.println("if you want to check you balance click 3");
                    break;
                case 2:
                    balance=dp.deposit(balance, scan);
                    break;
                case 3:
                    cb.checkBalance(balance);
                    break;
                case 4:
                    System.out.println("ENDING SECION..");
                    break;
                default:
                    System.out.println("please enter a valid input from the menu");
                    break;
            }
        }
    }
}
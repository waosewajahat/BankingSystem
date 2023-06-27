import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Account class (base class)
abstract class Account {
    private String accountNumber;
    private String accountHolder;
    double balance;

    public Account(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public abstract void withdraw(double amount); // Polymorphic method

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
    }
        }

// Savings Account class (derived from Account)
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double interestRate) {
        super(accountNumber, accountHolder);
        this.interestRate = interestRate;
    }

    
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            setBalance(getBalance()-amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }



    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}

// Checking Account class (derived from Account)
class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolder, double overdraftLimit) {
        super(accountNumber, accountHolder);
        this.overdraftLimit = overdraftLimit;
    }

    public void withdraw(double amount) {
        if (getBalance() + overdraftLimit >= amount) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}

// Credit Account class (derived from Account)
class CreditAccount extends Account {
    private double creditLimit;
    private double interestRate;

    public CreditAccount(String accountNumber, String accountHolder, double creditLimit, double interestRate) {
        super(accountNumber, accountHolder);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public void withdraw(double amount) {
        if (getBalance() + creditLimit >= amount) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}

// Main class
public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        while (true) {
            System.out.println("\nBanking System Menu");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Create Credit Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Display Account Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String savingsAccountNumber = scanner.next();
                    System.out.print("Enter account holder name: ");
                    String savingsAccountHolder = scanner.next();
                    System.out.print("Enter interest rate: ");
                    double interestRate = scanner.nextDouble();

                    Account savingsAccount = new SavingsAccount(savingsAccountNumber, savingsAccountHolder, interestRate);
                    accounts.add(savingsAccount);
                    System.out.println("Savings Account created successfully.");
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String checkingAccountNumber = scanner.next();
                    System.out.print("Enter account holder name: ");
                    String checkingAccountHolder = scanner.next();
                    System.out.print("Enter overdraft limit: ");
                    double overdraftLimit = scanner.nextDouble();

                    Account checkingAccount = new CheckingAccount(checkingAccountNumber, checkingAccountHolder, overdraftLimit);
                    accounts.add(checkingAccount);
                    System.out.println("Checking Account created successfully.");
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String creditAccountNumber = scanner.next();
                    System.out.print("Enter account holder name: ");
                    String creditAccountHolder = scanner.next();
                    System.out.print("Enter credit limit: ");
                    double creditLimit = scanner.nextDouble();
                    System.out.print("Enter interest rate: ");
                    double creditInterestRate = scanner.nextDouble();

                    Account creditAccount = new CreditAccount(creditAccountNumber, creditAccountHolder, creditLimit, creditInterestRate);
                    accounts.add(creditAccount);
                    System.out.println("Credit Account created successfully.");
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String depositAccountNumber = scanner.next();
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();

                    Account depositAccount = findAccount(accounts, depositAccountNumber);
                    if (depositAccount != null) {
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    String withdrawalAccountNumber = scanner.next();
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();

                    Account withdrawalAccount = findAccount(accounts, withdrawalAccountNumber);
                    if (withdrawalAccount != null) {
                        withdrawalAccount.withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();

                    Account account = findAccount(accounts, accountNumber);
                    if (account != null) {
                        account.displayAccountDetails();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    System.out.println("Thank you for using the Banking System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Account findAccount(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}

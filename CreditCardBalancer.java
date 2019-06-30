package com.dstoian.projects.creditCardBalancer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CreditCardBalancer {
    private ArrayList<CreditCard> myCreditCards;

    public void createCreditCardsList(){
        System.out.println("1 - Define your credit cards");
        System.out.println("2 - Load your credit cards from a file");
        System.out.printf("0 - Exit\n");

        int choice;
        Scanner scanner = new Scanner(System.in);
        //System.out.println("choice before while = "+choice);
        do {
            choice = scanner.nextInt();
            if ((choice!=0) && (choice!=1) && (choice!=2)) {
                System.out.println("Not a valid choice!");
            }
        } while ((choice!=0) && (choice!=1) && (choice!=2));

        switch (choice) {
            case 1: defineYourCreditCards();
                    break;
            case 2: loadYourCreditCards();
                    break;
        }
        //scanner.close();
        //return creditCardsList;
    }


    private /*ArrayList<CreditCard>*/ void defineYourCreditCards() {
        //ArrayList<CreditCard> creditCardsList =new ArrayList<>();
        String creditCardName;
        double totalCapacity;
        double balance;
        int card=1;
        Scanner scanner = new Scanner(System.in);

        for (int i=1;i<3;i++)
        {
            System.out.println("Credit card "+i+" name: ");
            creditCardName = scanner.nextLine();

            totalCapacity = inputNumber(1, Double.MAX_VALUE,"Credit card "+i+" capacity: ");
            balance = inputNumber(0, totalCapacity,"Credit card "+i+" balance: ");
            myCreditCards.add(new CreditCard(creditCardName, totalCapacity, balance));
        }
        System.out.println("Credit Cards List = "+myCreditCards);

        System.out.println("Would you like to save these credit cards? (Y/N) ");
        String doISave = scanner.nextLine();
        doISave = doISave.toUpperCase();
        //System.out.println("doISave = "+doISave);
        if ((doISave.equals("Y")) || (doISave.equals("YES"))){
            saveCreditCards();
        }

        //scanner.close();
        //return creditCardsList;

    }

    private void saveCreditCards() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the file name: ");
        String fileName = scanner.nextLine();
        fileName="D:\\Programming\\Java\\SortList\\"+fileName+".ser";

        try(ObjectOutputStream saveTheCards = new ObjectOutputStream
                (new BufferedOutputStream(new FileOutputStream(fileName)))){
            saveTheCards.writeObject(myCreditCards.get(0));
            saveTheCards.writeObject(myCreditCards.get(1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //scanner.close();
    }

    ;

    private /*ArrayList<CreditCard>*/ void loadYourCreditCards() {
        //ArrayList<CreditCard> creditCardsList =new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the file name: ");
        String fileName = scanner.nextLine();
        fileName="D:\\Programming\\Java\\SortList\\"+fileName+".ser";

        try(ObjectInputStream loadTheCards = new ObjectInputStream
                (new BufferedInputStream(new FileInputStream(fileName)))){
            CreditCard C1 = new CreditCard();
            C1 = (CreditCard) loadTheCards.readObject();
            CreditCard C2 = new CreditCard();
            C2 = (CreditCard) loadTheCards.readObject();
            myCreditCards.add(C1);
            myCreditCards.add(C2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        System.out.println("The Credit Cards loaded are = ");
        displayCreditCard(myCreditCards.get(0));
        displayCreditCard(myCreditCards.get(1));

        System.out.println("Do you want to update these credit cards? (Y/N)");

        String doIUpdate = scanner.nextLine();
        doIUpdate = doIUpdate.toUpperCase();
        //System.out.println("doISave = "+doISave);
        if ((doIUpdate.equals("Y")) || (doIUpdate.equals("YES"))){
            updateCreditCards();
        }


        //return creditCardsList;
    }

    private void updateCreditCards() {
        String message ="1 - Update First Credit Card"+'\n'+"2 - Update Second Credit Card"+'\n'+
                "0 - Exit"+'\n';
        double choice;
        do {

            choice = inputNumber(0, 2, message);
            if (choice==1) {
                updatingCreditCards(0);
            } else if (choice==2) {
                updatingCreditCards(1);

            };
        } while (choice!=0);
        displayCreditCard(myCreditCards.get(0));
        displayCreditCard(myCreditCards.get(1));




    };

    private void updatingCreditCards(int CreditCardNumber){
        displayCreditCard(myCreditCards.get(CreditCardNumber));
        String choseUpdates ="Update one or all of the followings:"+'\n'+"1 - Credit Card Name"+'\n'+"2 - Credit Card Total Capacity"+'\n'+
                "3 - Balance"+'\n'+"0 - Exit"+'\n';
        Scanner scanner = new Scanner(System.in);
        double whatToUpdate;
        do{
            whatToUpdate = inputNumber(0, 3, choseUpdates);
            if (whatToUpdate==1){
                System.out.println("New Name: ");
                String newName = scanner.nextLine();
                myCreditCards.get(CreditCardNumber).setCreditCardName(newName);
            } else if (whatToUpdate==2){
                double newTotalCapacity = inputNumber(myCreditCards.get(CreditCardNumber).getBalance(), Double.MAX_VALUE,"Total Capacity: ");
                myCreditCards.get(CreditCardNumber).setTotalCapacity(newTotalCapacity);
            } else if (whatToUpdate==3){
                double newBalance = inputNumber(0, myCreditCards.get(CreditCardNumber).getTotalCapacity(),"Balance: ");
                myCreditCards.get(CreditCardNumber).setBalance(newBalance);
            };

        } while (whatToUpdate!=0.0);

    }

    private void displayCreditCard(CreditCard creditCard) {
        System.out.println("Credit Card Name: "+creditCard.getCreditCardName());
        System.out.println("Credit Card Total Capacity: "+creditCard.getTotalCapacity());
        System.out.println("Credit Card Balance: "+creditCard.getBalance());
        System.out.println("Credit Card Debt Level: "+creditCard.getDebtLevel());
    }

    public void balanceCreditCards(){
        //ArrayList<CreditCard> workingCreditCardsList =new ArrayList<>();

        createCreditCardsList();
        //System.out.println("Credit Card List in balanceCreditCards "+myCreditCards);
        if (!myCreditCards.isEmpty()){
            boolean result = generatesThePayments();
        }

    }

    private double inputNumber(double lowLimit, double highLimit, String message){

        System.out.println(message);

        Scanner scanner = new Scanner(System.in);
        double value;
        boolean validNumber=false;

        do {
            value = scanner.nextDouble();
            if (validateNumberInput(lowLimit, highLimit, value)){
                validNumber=true;
            } else {
                System.out.println("Wrong number introduce!");
                System.out.println("Please insert a number between "+lowLimit+" and "+highLimit);
            }

        } while (!validNumber);

        scanner.nextLine();

        return value;
    }

    private boolean validateNumberInput(double lowLimit, double highLimit, double numberInput){
        if (numberInput<0){
            return false;
        } else if ((numberInput<lowLimit) || (numberInput>highLimit)){
            return false;
        } else {
            return true;
        }
    }
    private boolean generatesThePayments() {
        CreditCard C1 = new CreditCard();
        C1 = myCreditCards.get(0);
        CreditCard C2 = new CreditCard();
        C2 = myCreditCards.get(1);

        double availablePayment;
        Scanner scanner = new Scanner(System.in);

        availablePayment = inputNumber(0.5, C1.getBalance()+C2.getBalance(),
                "What is the available amount for debt payments? ");
        double p1, p2;

        p2 = (C1.getTotalCapacity()*C2.getBalance()-C2.getTotalCapacity()*C1.getBalance()
                +availablePayment*C2.getTotalCapacity())/(C1.getTotalCapacity()+C2.getTotalCapacity());
        p1 = availablePayment - p2;

        if ((p1>=0) && (p2>=0)) {
            System.out.println("The payments to balance the Credit cards are:");
            C1.setBalance(C1.getBalance()-p1);
            C1.setPayment(p1);
            C1.setDebtLevel((C1.getBalance()/C1.getTotalCapacity())*100);
            System.out.println("For "+C1.getCreditCardName()+" the payment is: "+p1+" and the new " +
                    "debt level will be: "+C1.getDebtLevel());
            C2.setBalance(C2.getBalance()-p2);
            C2.setPayment(p2);
            C2.setDebtLevel((C2.getBalance()/C2.getTotalCapacity())*100);

            System.out.println("For "+C2.getCreditCardName()+" the payment is: "+p2+" and the new " +
                    "debt level will be: "+C2.getDebtLevel());
            System.out.println("\nThe List is now: "+myCreditCards);
            //scanner.close();
            return true;

        } else {
            System.out.println("Due to insuficient funds the level of debt for the credit cards " +
                    "can't be lowered to the same level");
            //scanner.close();
            return false;
        }
    };


    public static void main(String[] args) {
        CreditCardBalancer balancer = new CreditCardBalancer();
        balancer.myCreditCards = new ArrayList<>();
        balancer.balanceCreditCards();
    }
}

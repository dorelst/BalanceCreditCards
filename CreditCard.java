package com.dstoian.projects.creditCardBalancer;

import java.io.Serializable;

public class CreditCard implements Serializable {
    //static final long serialVersionUID =
    private String creditCardName;
    private double totalCapacity;
    private double balance;
    private double payment;
    private double debtLevel;

    public double getDebtLevel() {
        return debtLevel;
    }

    public void setDebtLevel(double debtLevel) {
        this.debtLevel = debtLevel;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CreditCard(String creditCardName, double totalCapacity, double balance) {
        this.creditCardName = creditCardName;
        this.totalCapacity = totalCapacity;
        this.balance = balance;
        this.debtLevel = (balance/totalCapacity)*100;
        this.payment = 0.0;
    }

    public CreditCard() {
        this.creditCardName = "";
        this.totalCapacity = 0.0;
        this.balance = 0.0;
        this.debtLevel = 0.0;
        this.payment = 0.0;
    }


    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardName='" + creditCardName + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", balance=" + balance +
                ", payment=" + payment +
                ", debtLevel=" + debtLevel +
                '}'+'\n';
    }
}

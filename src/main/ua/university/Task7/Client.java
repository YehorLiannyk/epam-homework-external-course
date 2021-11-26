package main.ua.university.Task7;

import java.math.BigDecimal;

public class Client {
    private Deposit[] deposits;

    public Client() {
        deposits = new Deposit[10];
    }

    public boolean addDeposit(Deposit deposit) {
        boolean success = false;
        if (findFreeIndex(deposits) != -1) {
            deposits[findFreeIndex(deposits)] = deposit;
            success = true;
        }
        return success;
    }

    public BigDecimal totalIncome() {
        BigDecimal total = new BigDecimal(0);
        int actualArrLength = findFreeIndex(deposits) == -1 ? deposits.length : findFreeIndex(deposits);
        for (int i = 0; i < actualArrLength ; i++) {
            total = total.add(deposits[i].getAmount());
        }
        return total;
    }

    public BigDecimal maxIncome() {
        BigDecimal max = new BigDecimal(0);
        int actualArrLength = findFreeIndex(deposits) == -1 ? deposits.length : findFreeIndex(deposits);
        for (int i = 0; i < actualArrLength ; i++) {
            max = max.add(deposits[i].income());
        }
        return max;
    }

    public int findFreeIndex(Deposit[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    public BigDecimal getIncomeByNumber(int index) {
        BigDecimal income;
        index--;
        if (index <= findFreeIndex(deposits))
            income = deposits[index].income();
        else
            income = new BigDecimal(0);
        return income;
    }
}


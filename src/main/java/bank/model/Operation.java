package bank.model;

import java.util.Date;

public class Operation {
    private Date date;
    private String wording;
    private OperationType operationType;
    private double amount;

    public Operation(Date date, String wording, OperationType operationType, double amount) {
        this.date = date;
        this.wording = wording;
        this.operationType = operationType;
        this.amount = amount;
    }

    public Operation(){
    }

    @Override
    public String toString() {
        return "Operation{" +
                "date=" + date +
                ", wording='" + wording + '\'' +
                ", operationType=" + operationType +
                ", amount=" + amount +
                '}';
    }
}

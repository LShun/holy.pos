package order;

import java.time.LocalDateTime;

public class Receipt extends CartOrReceipt {
    private double total;

    public Receipt(Cart c, double total){
        this.billID          = c.getBillID();
        this.staffID         = c.getStaffID();
        this.transactionTime = LocalDateTime.now();
        this.items           = c.getItems();
        this.total           = total;
    }

    public double getTotal(){ return total;}

}

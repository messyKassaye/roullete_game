package clientsModel;

/**
 * Created by Meseret on 11/3/2017.
 */

public class WithdrawalSentModel {
    private String amount;
    private String status;

    public WithdrawalSentModel(String amount, String status) {
        this.amount = amount;
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

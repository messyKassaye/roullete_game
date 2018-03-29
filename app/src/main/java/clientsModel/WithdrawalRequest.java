package clientsModel;

/**
 * Created by Meseret on 11/3/2017.
 */

public class WithdrawalRequest {
    private String amount;

    public WithdrawalRequest(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

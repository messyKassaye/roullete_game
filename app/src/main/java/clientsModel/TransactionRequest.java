package clientsModel;

/**
 * Created by Meseret on 10/27/2017.
 */

public class TransactionRequest {

    private String amount;
    private String transaction_hash_id;

    public TransactionRequest(String amount, String transaction_hash_id) {
        this.amount = amount;
        this.transaction_hash_id = transaction_hash_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransaction_hash_id() {
        return transaction_hash_id;
    }

    public void setTransaction_hash_id(String transaction_hash_id) {
        this.transaction_hash_id = transaction_hash_id;
    }
}

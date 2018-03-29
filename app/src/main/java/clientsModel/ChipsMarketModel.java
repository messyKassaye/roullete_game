package clientsModel;

/**
 * Created by Meseret on 11/8/2017.
 */

public class ChipsMarketModel {
    private String no_of_chips;
    private String transaction_id;

    public ChipsMarketModel(String no_of_chips, String transaction_id) {
        this.no_of_chips = no_of_chips;
        this.transaction_id = transaction_id;
    }

    public String getNo_of_chips() {
        return no_of_chips;
    }

    public void setNo_of_chips(String no_of_chips) {
        this.no_of_chips = no_of_chips;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}

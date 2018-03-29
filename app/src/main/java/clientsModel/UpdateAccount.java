package clientsModel;

/**
 * Created by Meseret on 11/4/2017.
 */

public class UpdateAccount {
    private int index;
    private int amount;

    public UpdateAccount(int index, int amount) {
        this.index = index;
        this.amount = amount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

package clientsModel;

/**
 * Created by Meseret on 10/26/2017.
 */

public class ChipsMarket {
    private String id;
    private String dollar;
    private String chips;
    private String bitcoin;

    public ChipsMarket(String id, String dollar, String chips, String bitcoin) {
        this.id = id;
        this.dollar = dollar;
        this.chips = chips;
        this.bitcoin = bitcoin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    public String getChips() {
        return chips;
    }

    public void setChips(String chips) {
        this.chips = chips;
    }

    public String getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(String bitcoin) {
        this.bitcoin = bitcoin;
    }
}

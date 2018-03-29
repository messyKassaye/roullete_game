package com.example.meseret.roulette;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Gamers {
    private String amount;
    private String bitcoin_address;
    private String email;
    private int id;
    Context mcontext;
    private String referal_code;
    SharedPreferences sharedPreferences = this.mcontext.getSharedPreferences("gamers", 0);
    private String user_name;

    public Gamers(Context context) {
        this.mcontext = context;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return this.sharedPreferences.getString("user_name", "");
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        set_sharedPrefence("user_name", user_name);
    }

    public String getReferal_code() {
        return this.sharedPreferences.getString("referral_code", "");
    }

    public void setReferal_code(String referal_code) {
        this.referal_code = referal_code;
        set_sharedPrefence("referral_code", referal_code);
    }

    public String getEmail() {
        return this.sharedPreferences.getString("email", "");
    }

    public void setEmail(String email) {
        this.email = email;
        set_sharedPrefence("email", email);
    }

    public String getBitcoin_address() {
        return this.sharedPreferences.getString("bitcoin_address", "");
    }

    public void setBitcoin_address(String bitcoin_address) {
        this.bitcoin_address = bitcoin_address;
        set_sharedPrefence("bitcoin_address", bitcoin_address);
    }

    public String getAmount() {
        return this.sharedPreferences.getString("amount", "");
    }

    public void setAmount(String amount) {
        this.amount = amount;
        set_sharedPrefence("amount", amount);
    }

    public void set_sharedPrefence(String key, String value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}

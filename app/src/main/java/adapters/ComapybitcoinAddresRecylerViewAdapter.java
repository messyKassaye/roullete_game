package adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meseret.roulette.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import adapterModel.CompanytListAdapterModel;
import projectstatic.ProjectStatic;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meseret on 10/27/2017.
 */

public class ComapybitcoinAddresRecylerViewAdapter extends RecyclerView.Adapter<ComapybitcoinAddresRecylerViewAdapter.MyViewHolder>{

private Context mContext;
private List<CompanytListAdapterModel> albumList;
private SharedPreferences preferences;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView company_type, address;



    public MyViewHolder(View view) {
        super(view);
        company_type = (TextView) view.findViewById(R.id.company_name);
        address = (TextView) view.findViewById(R.id.bitcoin_address);



    }

}


    public ComapybitcoinAddresRecylerViewAdapter(Context mContext, List<CompanytListAdapterModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_list_recyclerview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CompanytListAdapterModel model = albumList.get(position);
        holder.address.setText(model.getAddress());
        holder.company_type.setText(model.getCompany_type());

        preferences = mContext.getSharedPreferences("token_value", 0);
        // loading album cover using Glide library


    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public Retrofit getAPIUser() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(ProjectStatic.API_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
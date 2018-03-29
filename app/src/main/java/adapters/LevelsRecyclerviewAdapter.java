package adapters;

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
import adapterModel.LevelsAdapterModel;
import projectstatic.ProjectStatic;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Meseret on 11/3/2017.
 */

public class LevelsRecyclerviewAdapter extends RecyclerView.Adapter<LevelsRecyclerviewAdapter.MyViewHolder>{

    private Context mContext;
    private List<LevelsAdapterModel> albumList;
    private SharedPreferences preferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name, levels;



        public MyViewHolder(View view) {
            super(view);
            user_name = (TextView) view.findViewById(R.id.user_name);
            levels = (TextView) view.findViewById(R.id.level_no);



        }

    }


    public LevelsRecyclerviewAdapter(Context mContext, List<LevelsAdapterModel> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.levels_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final LevelsAdapterModel model = albumList.get(position);
        holder.user_name.setText(model.getUser_name());
        holder.levels.setText(model.getLevels());

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

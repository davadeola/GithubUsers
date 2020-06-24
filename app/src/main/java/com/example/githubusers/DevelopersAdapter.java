package com.example.githubusers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.ViewHolder> {

    //declare variables to store the list of items
    private List<DevelopersList> developersLists;
    private Context mContext;

    //keys for intents
        public static final String KEY_NAME = "name";
        public static final String KEY_IMAGE = "image";
        public static String KEY_URL = "url";

    public DevelopersAdapter(List<DevelopersList> developersLists, Context context){
        this.developersLists = developersLists;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DevelopersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.developers_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final DevelopersList currentDeveloperList = developersLists.get(position);

        holder.login.setText(currentDeveloperList.getLogin());
        holder.html_url.setText(currentDeveloperList.getHtml_url());

        Picasso.get().load(currentDeveloperList.getAvatar_url()).into(holder.avatar_url);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevelopersList developersList = developersLists.get(position);
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                skipIntent.putExtra(KEY_NAME, developersList.getLogin());
                skipIntent.putExtra(KEY_IMAGE, developersList.getAvatar_url());
                skipIntent.putExtra(KEY_URL, developersList.getHtml_url());

                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return developersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            login = itemView.findViewById(R.id.username);
            avatar_url = itemView.findViewById(R.id.imageView);
            html_url = itemView.findViewById(R.id.html_url);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}

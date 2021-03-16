package com.superhero.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.superhero.myapplication.R;
import com.superhero.myapplication.activity.SuperHero_Details;
import com.superhero.myapplication.model.Dashboard;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroAdapter.HeroViewHolder>implements Filterable {
//backup list for filtering
    private List<Dashboard> backupHeroList;
//list of heros
    private List<Dashboard> heroList;
    private Context context;

    private static int currentPosition = 0;

    public SuperHeroAdapter(List<Dashboard> heroList, Context context) {
        this.heroList = heroList;
        backupHeroList= new ArrayList<>(heroList);
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list, parent, false);
        return new HeroViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final HeroViewHolder holder, final int position) {
        Dashboard hero = heroList.get(position);
        holder.textViewName.setText(hero.getName());
        String image_url = hero.getImage().getUrl();
        Picasso.with(context)
                .load(image_url)
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //passing field values through intent and starting detail activity on click
                Intent intent = new Intent (context, SuperHero_Details.class);
                intent.putExtra("Name_detail" , hero.getName());
                intent.putExtra("Name" , hero.getBiography().getFullName());
                intent.putExtra("Combat" , hero.getPowerstats().getCombat());
                intent.putExtra("Power" , hero.getPowerstats().getPower());
                intent.putExtra("Strength" , hero.getPowerstats().getStrength());
                intent.putExtra("Apperance" , hero.getBiography().getFirstAppearance());
                intent.putExtra("Gender" , hero.getAppearance().getGender());
                intent.putExtra("Race" , hero.getAppearance().getRace());
                intent.putExtra("Publisher" , hero.getBiography().getPublisher());
                intent.putExtra("image" , hero.getImage().getUrl());
                intent.putExtra("Relatives" , hero.getConnections().getRelatives());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    //filter functionlaity

    @Override
    public Filter getFilter() {
        return filter;
    }


    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Dashboard> filterdata =new ArrayList<>();
            if(charSequence.toString().equalsIgnoreCase("None"))
            {
                //if none populate the same list
                filterdata.addAll(backupHeroList);

            }
            else
            {
                for(Dashboard item : backupHeroList)
                {
                    //based on selection filter the list
                    if(item.getBiography().getPublisher().equalsIgnoreCase(charSequence.toString()) ||item.getAppearance().getGender().equalsIgnoreCase(charSequence.toString()))
                    {
                        filterdata.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values=filterdata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            heroList.clear();
            heroList.addAll((ArrayList<Dashboard>)filterResults.values);
                    notifyDataSetChanged();
        }
    };

    class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageView;
        CardView cardView;

        HeroViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textviewName);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.cardview_heros);

        }
    }
}

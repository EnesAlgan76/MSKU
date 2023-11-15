package com.msku.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msku.example.myapplication.placeholder.Movie;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> mValues;
    private final MovieFragment.OnMovieSelected mListener;
    int selectedIndex;


    public MyMovieRecyclerViewAdapter(List<Movie> items,  MovieFragment.OnMovieSelected listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie,parent,false);
        return new ViewHolder(myView);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(position));
        holder.mContentView.setText(mValues.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mListener !=null){
                    mListener.movieSelected(holder.mItem);
                    notifyItemChanged(selectedIndex);
                    selectedIndex = holder.getLayoutPosition();
                    notifyItemChanged(selectedIndex);

                }
            }
        });

        if (selectedIndex == position) {
            holder.myLayout.setBackgroundColor(Color.blue(2));
        } else {
            holder.myLayout.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final View view;
        public final TextView mContentView;
        public final LinearLayout myLayout;
        public Movie mItem;

        public ViewHolder( View view) {
            super(view);
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            myLayout = view.findViewById(R.id.myLineer);
            this.view = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
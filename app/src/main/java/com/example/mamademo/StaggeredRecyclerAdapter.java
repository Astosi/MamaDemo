package com.example.mamademo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.icu.util.ValueIterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.RequestOptions;
import com.example.mamademo.Models.Job;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaggeredRecyclerAdapter extends RecyclerView.Adapter<StaggeredRecyclerAdapter.ViewHolder> {

    Context mContext;
    List<Job> mdata;
    RequestOptions requestOptions ;

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
   // private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    int distance = 8000;



    public StaggeredRecyclerAdapter(Context mContext, List<Job> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
        requestOptions = new RequestOptions().centerCrop();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_front, viewGroup, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder ViewHolder, int i) {

        final Job job = mdata.get(i);
        Picasso.get().load(job.getImageurl()).into(ViewHolder.imgFront);

        ViewHolder.imgFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                notifyDataSetChanged();
            }
        });

        ViewHolder.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewHolder.isFront = true;

                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgFront;
        ImageView imgBack;
        TextView description;
        Boolean isFront;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFront = itemView.findViewById(R.id.imageItem);
            imgBack = itemView.findViewById(R.id.imgBack);
            description = itemView.findViewById(R.id.description);

            itemView.findViewById(R.id.card_back);
        }
    }
/* class ViewHolder0 extends RecyclerView.ViewHolder {
        ...
        public ViewHolder0(View itemView){
        ...
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        ...
        public ViewHolder2(View itemView){
        ...
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         switch (viewType) {
             case 0: return new ViewHolder0(...);
             case 2: return new ViewHolder2(...);
             ...
         }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder0 viewHolder0 = (ViewHolder0)holder;
                ...
                break;

            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2)holder;
                ...
                break;
        }
    }*/

}

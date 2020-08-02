package com.example.mamademo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.Log;
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
import java.util.Map;
import java.util.TreeMap;




public class AdapterDemo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context mContext;
    List<Job> mdata;
    RequestOptions requestOptions ;

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    // private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    int distance = 8000;
    String TAG = "Astosi";
    Map<Integer , View> VIEWS = new TreeMap<>();
    int counter= 0;
    int position;

    public AdapterDemo(Context mContext, List<Job> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
        requestOptions = new RequestOptions().centerCrop();


    }


    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard1);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard2);
    }


    class ViewHolder0 extends RecyclerView.ViewHolder {
//card front
        ImageView imgFront;
        TextView description;


        public ViewHolder0(View itemView){
            super(itemView);

            imgFront = itemView.findViewById(R.id.imageItem);

            //    description = itemView.findViewById(R.id.description);

        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
//card back
        ImageView imgBack;
        public ViewHolder2(View itemView) {
            super(itemView);

            imgBack = itemView.findViewById(R.id.imgBack);
        }
    }


        @Override
        public int getItemViewType(int position) {

        this.position = position;

           if (mdata.get(position).isFront){
               Log.d(TAG, mdata.get(position).isFront+ " return 1... front");
               return 1;
           }
           else {
               Log.d(TAG, mdata.get(position).isFront+ " return 0... back");
               return 0;

           }
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==1){
            Log.d(TAG, "cardFrontIsInflating...");


            if (counter!=0) {
                Log.d(TAG, "Animation flipCardback is starting");
                flipCardBack(parent);
            }
            counter++;



            View view = LayoutInflater.from(mContext).inflate(R.layout.card_front, parent, false);
            return new ViewHolder0(view);

        }
        else
            Log.d(TAG, "cardBackIsInfilating...");
            Log.d(TAG, "Animation flipCard is starting");
            flipCard(parent);


            View view = LayoutInflater.from(mContext).inflate(R.layout.card_back, parent, false);
            return new ViewHolder2(view);

        }


    public void flipCard(View view) {




    loadAnimations();
        Log.d(TAG, "Animation flipCard is starting");
        mCardFrontLayout=view;
        mCardBackLayout=VIEWS.get(position);


      //  if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            // changeCameraDistance();

    }
    public void flipCardBack(View view) {




       /* loadAnimations();
        Log.d(TAG, "Animation flipcardback is starting");
        mCardFrontLayout=VIEWS.get(position);
        mCardBackLayout=view;


        mSetRightOut.setTarget(mCardBackLayout);
        mSetLeftIn.setTarget(mCardFrontLayout);
        mSetRightOut.start();
        mSetLeftIn.start();
//        changeCameraDistance();


        */
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = mContext.getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }



    @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

             switch (holder.getItemViewType()) {



                 case 0:
                     ViewHolder2 viewHolder2 = (ViewHolder2)holder;
                     Picasso.get().load(mdata.get(position).getImageurl()).into(viewHolder2.imgBack);

                     VIEWS.put(position, viewHolder2.itemView);
                     Log.d(TAG, VIEWS.get(position).toString() );

                     viewHolder2.imgBack.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(final View view) {
                             //mCardBackLayout = view.findViewById(R.id.card_back);
                             //mCardFrontLayout = view.findViewById(R.id.card_front);
                             //float scale = mContext.getResources().getDisplayMetrics().density * distance;
                             //mCardBackLayout.setCameraDistance(scale);
                             //mCardFrontLayout.setCameraDistance(scale);

                             //mSetRightOut.setTarget(mCardBackLayout);
                             //mSetLeftIn.setTarget(mCardFrontLayout);
                             //mSetRightOut.start();
                             //mSetLeftIn.start();


                             Log.d(TAG, mdata.get(position) + " is front is true");
                             mdata.get(position).isFront = true;

                             Log.d(TAG, "Exiting the onClick");
                             notifyDataSetChanged();
                         }
                     });
                     break;


                case 1:

                    ViewHolder0 viewHolder0 = (ViewHolder0)holder;
                    Picasso.get().load(mdata.get(position).getImageurl()).into(viewHolder0.imgFront);
                    Log.d(TAG, mdata.get(position).getImageurl() + "image is uploading" );

                    VIEWS.put(position, viewHolder0.itemView);
                    Log.d(TAG, VIEWS.get(position).toString() );

                    viewHolder0.imgFront.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            //mCardBackLayout = view.findViewById(R.id.card_back);
                            //mCardFrontLayout = view.findViewById(R.id.card_front);
                            //float scale = mContext.getResources().getDisplayMetrics().density * distance;
                            //mCardBackLayout.setCameraDistance(scale);
                            //mCardFrontLayout.setCameraDistance(scale);

                            //mSetRightOut.setTarget(mCardFrontLayout);
                            //mSetLeftIn.setTarget(mCardBackLayout);
                            //mSetRightOut.start();
                            //mSetLeftIn.start();

                                Log.d(TAG, mdata.get(position) + " is front is false");
                                mdata.get(position).isFront = false;


                            Log.d(TAG, "Exiting the onClick");
                            notifyDataSetChanged();
                        }
                    });

                    break;


            }
        }
        @Override
      public int getItemCount() {
          return mdata.size();
    }

    }


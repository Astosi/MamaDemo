package com.example.mamademo;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.mamademo.Models.Job;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdapterTest extends RecyclerView.Adapter<AdapterTest.ViewHolder>  {


    View mCardBackLayout;
    View mCardFrontLayout;
    Context mContext;
    List<Job> mdata;
    RequestOptions requestOptions ;
    Map< Integer, Map<Integer , View>> views = new HashMap();
    Map<Integer, Boolean> isFront = new HashMap<>();

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private AnimatorSet mSetRightOutOP;
    private AnimatorSet mSetLeftInOP;

    String TAG = "Astosi";

    public AdapterTest(Context mContext, List<Job> mdata) {
            this.mContext = mContext;
            this.mdata = mdata;
            requestOptions = new RequestOptions().centerCrop();
     }


    @NonNull
    @Override
    public AdapterTest.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card, viewGroup, false);
        return new AdapterTest.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final AdapterTest.ViewHolder ViewHolder, int i) {


        final Job job = mdata.get(i);

        String uri = "https://3.bp.blogspot.com/-O1WyABCOzI8/Tfvz7_MV7LI/AAAAAAAAEYA/MYshChHvyEo/s1600/Forest+trail.jpg";
        Picasso.get().load(job.getImageurl()).into(ViewHolder.imgFront);
        // Picasso.get().load(job.getImageurl()).into(ViewHolder.imgBack);
   //     Picasso.get().load(uri).into(ViewHolder.imgBack);


        Map<Integer, View> a = new HashMap();

        a.put(0, ViewHolder.itemView.findViewById(R.id.card));
        Log.d(TAG, a.toString());
        a.put(1, ViewHolder.itemView.findViewById(R.id.card_front));
        Log.d(TAG, a.toString());
        a.put(2, ViewHolder.itemView.findViewById(R.id.card_back));
        Log.d(TAG, a.toString());

        views.put(i, a);

        isFront.put(i,true);



        final Integer j = i;
/*
            ViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {

                Log.d(TAG, j + " " + mdata.get(j) + " " + mdata.get(j).isFront);
                notifyDataSetChanged();
            }
        });
*/

            ViewHolder.card.setOnTouchListener(new View.OnTouchListener() {
                float previouspoint = 0 ;
                float startPoint=0;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(v.getId()) {
                        case R.id.card:
                            switch(event.getAction()){
                                case MotionEvent.ACTION_DOWN:
                                    startPoint=event.getX();
                                    System.out.println("Action down,..."+event.getX());
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    break;
                                case MotionEvent.ACTION_CANCEL:
                                    previouspoint=event.getX();
                                    if(previouspoint - startPoint >= 60){
                                        flipCard(j, 0);
                                        //Right side swipe
                                    }else if (startPoint - previouspoint >= 60) {
                                        flipCard(j,1);
                                        // Left side swipe
                                    }
                                    break;
                            }
                            break;
                    }
                    return true;
                }
            }
        );
    }

    //TODO: flippingDown Animation

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgFront;
        ImageView imgBack;
        TextView description;
        Boolean isFront;
        View card ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFront = itemView.findViewById(R.id.imageItem);
            imgBack = itemView.findViewById(R.id.imgBack);
            description = itemView.findViewById(R.id.description);
            card = itemView.findViewById(R.id.card);
            itemView.findViewById(R.id.card_back);
        }
    }



    public void flipCard(int view, int left) {

        loadAnimations();


//        Log.d(TAG, mCardBackLayout + " -- " + mCardFrontLayout);
  //      Log.d(TAG, "flipCard isFront enterence---------------->" + mdata.get(view).isFront);

        switch (left) {
            case 0:

                mCardFrontLayout = views.get(view).get(1);
                mCardBackLayout = views.get(view).get(2);

                if (!isFront.get(view)) {
                    Log.d(TAG, "flipCard isFront false---------------->" + mdata.get(view).isFront);
                    Log.d(TAG, " ----------animation front " + mCardBackLayout + "-----" + mCardFrontLayout);
                    isFront.put(view, true);
                    mSetRightOut.setTarget(mCardFrontLayout);
                    mSetLeftIn.setTarget(mCardBackLayout);
                    mSetRightOut.start();
                    mSetLeftIn.start();
                }
                else {
                    Log.d(TAG, "flipCard isFront---------------->" + mdata.get(view).isFront);
                    Log.d(TAG, " ----------animation back " + mCardBackLayout + "-----" + mCardFrontLayout);
                    isFront.put(view, false);
                    mSetRightOut.setTarget(mCardBackLayout);
                    mSetLeftIn.setTarget(mCardFrontLayout);
                    mSetRightOut.start();
                    mSetLeftIn.start();
                }
                break;

            case 1:

                mCardFrontLayout = views.get(view).get(2);
                mCardBackLayout = views.get(view).get(1);

                if (!isFront.get(view)) {
                    Log.d(TAG, " ----------animation front " + mCardBackLayout + "-----" + mCardFrontLayout);
                    isFront.put(view, true);
                    mSetLeftInOP.setTarget(mCardBackLayout);
                    mSetRightOutOP.setTarget(mCardFrontLayout);
                    mSetLeftInOP.start();
                    mSetRightOutOP.start();

                } else {
                    Log.d(TAG, "flipCard2 isFront---------------->" + mdata.get(view).isFront);
                    Log.d(TAG, " ----------animation back------------ " + mCardBackLayout + "-----" + mCardFrontLayout);
                    isFront.put(view, false);
                    mSetLeftInOP.setTarget(mCardFrontLayout);
                    mSetRightOutOP.setTarget(mCardBackLayout);
                    mSetLeftInOP.start();
                    mSetRightOutOP.start();

                }
                break;
        }
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard1);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard2);
        mSetRightOutOP = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard3);
        mSetLeftInOP =  (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard4);
    }


}
    /*
    *   private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        loadAnimations();
        changeCameraDistance();

    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);
    }

   */

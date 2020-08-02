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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AdapterTest extends RecyclerView.Adapter<AdapterTest.ViewHolder> {


    View mCardBackLayout;
    View mCardFrontLayout;
    Context mContext;
    List<Job> mdata;
    RequestOptions requestOptions ;
    Map< Integer, Map<Integer , View>> views = new HashMap();
    Map<Integer, Boolean> isFront = new HashMap<>();

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
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

        Map<Integer , View> a = new HashMap();


        a.put(0 , ViewHolder.itemView.findViewById(R.id.card));
        Log.d(TAG, a.toString() );
        a.put(1 , ViewHolder.itemView.findViewById(R.id.card_front));
        Log.d(TAG, a.toString() );
        a.put(2 , ViewHolder.itemView.findViewById(R.id.card_back));
        Log.d(TAG, a.toString() );

        views.put(i, a);


        final Job job = mdata.get(i);
        String uri = "https://3.bp.blogspot.com/-O1WyABCOzI8/Tfvz7_MV7LI/AAAAAAAAEYA/MYshChHvyEo/s1600/Forest+trail.jpg";
        Picasso.get().load(job.getImageurl()).into(ViewHolder.imgFront);
        Picasso.get().load(uri).into(ViewHolder.imgBack);

        final Integer j = i;


        ViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(j);
                Log.d(TAG, j + " " + mdata.get(j) + " " + mdata.get(j).isFront);
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



    public void flipCard(int view ) {

        loadAnimations();
        mCardFrontLayout = views.get(view).get(1);
        mCardBackLayout = views.get(view).get(2);

        Log.d(TAG, mCardBackLayout + " " + mCardFrontLayout );
        if (!mdata.get(view).isFront) {
            Log.d(TAG, " !!!!!!!!!!!!!!!!!!animation front " + mCardBackLayout +  mCardFrontLayout );
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mdata.get(view).isFront = true;
        } else {
            Log.d(TAG, " !!!!!!!!!!!!!!!!!!!!animation back " + mCardBackLayout + mCardFrontLayout );
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mdata.get(view).isFront = false;
        }


    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard1);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.animcard2);
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
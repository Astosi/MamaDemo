package com.example.mamademo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mamademo.AdapterDemo;
import com.example.mamademo.AdapterTest;
import com.example.mamademo.R;
import com.example.mamademo.createActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.mamademo.Models.Job;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

     ImageView create;
    private List<Job> jobList;
    private RecyclerView staggeredRv;
  //  AdapterDemo adapter;
    AdapterTest adapterTest;
    FloatingActionButton floatingActionButton;
   // private StaggeredGridLayoutManager manager;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        // recyclerViewPosts = view.findViewById(R.id.);
        //    recyclerViewPosts.setHasFixedSize(true);
        //   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //  linearLayoutManager.setStackFromEnd(true);
        // linearLayoutManager.setReverseLayout(true);

        //   recyclerViewPosts.setLayoutManager(linearLayoutManager);

    floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getContext() , createActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    });


        //   recyclerViewPosts.setAdapter(adapter);

        jobList = new ArrayList<>();

        readPosts();


        staggeredRv = view.findViewById(R.id.recycler_view_posts);
      //  adapterTest =  new AdapterTest(getContext() , jobList);
        //adapter = new AdapterDemo(getContext(), jobList);
        adapterTest = new AdapterTest(getContext(), jobList);
        staggeredRv.setAdapter(adapterTest);
        staggeredRv.setLayoutManager(new GridLayoutManager(getContext(),1));


//        staggeredRv = view.findViewById(R.id.recycler_view_posts);         //home da recylebview olustur
  //      manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        staggeredRv.setLayoutManager(manager);
  //      adapter = new StaggeredRecyclerAdapter(getContext(), jobList);
    //    staggeredRv.setAdapter(adapter);

        return view;

    }


    private void readPosts() {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("Posts").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Job job = snapshot.getValue(Job.class);
                    jobList.add(job);
                }
                adapterTest.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
/*

    private RecyclerView staggeredRv;
    private StaggeredRecyclerAdapter adapter ;
    private StaggeredGridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // let's make the activity on full screen for better ui

        getSupportActionBar().hide();

        staggeredRv = findViewById(R.id.staggered_rv);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredRv.setLayoutManager(manager);

        // lets create a simple array list of images
        List<row> lst = new ArrayList<>();
        lst.add(new row(R.drawable.illustrationtwo));
        lst.add(new row(R.drawable.clothes));
        lst.add(new row(R.drawable.illustration));
        lst.add(new row(R.drawable.clothes));
        lst.add(new row(R.drawable.paint2));
        lst.add(new row(R.drawable.paint));
        lst.add(new row(R.drawable.clothestwo));
        lst.add(new row(R.drawable.wallpaper));
        lst.add(new row(R.drawable.illustrationtwo));
        lst.add(new row(R.drawable.illustration));
        lst.add(new row(R.drawable.clothes));
        lst.add(new row(R.drawable.paint2));
        lst.add(new row(R.drawable.paint));
        lst.add(new row(R.drawable.clothestwo));
        lst.add(new row(R.drawable.wallpaper));
        lst.add(new row(R.drawable.clothes));




        adapter = new StaggeredRecyclerAdapter(this,lst);
        staggeredRv.setAdapter(adapter);

 android:adjustViewBounds="true"
        android:scaleType="centerCrop"


    }
}*/
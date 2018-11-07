package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {


    private ProgressDialog progressDialog;
    private List<Upload> uploads;

    private RecyclerView recyclerView;

    Image image;


    private ArrayList<Image> images;


    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        progressDialog = new ProgressDialog(this);

        uploads = new ArrayList<>();

        images = new ArrayList<>();

        progressDialog.setMessage("Loading Images...");
        progressDialog.show();
        progressDialog.setCancelable(false);


//        Firebase.setAndroidContext(this);
//        final Firebase ref = new Firebase("https://bharatham-2k17.firebaseio.com/main/gallery_app");
//        mDatabase = FirebaseDatabase.getInstance().getReference("gallery");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference("gallery");




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new MyAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new MyAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                Log.d("Gallery",snapshot.getChildrenCount()+"");

                progressDialog.dismiss();
                images.clear();
                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    Log.d("Gallery",upload.getUrl());
                    Image image = new Image();
                    uploads.add(upload);
                    image.setLarge(upload.getUrl());
                    images.add(image);


                }
                //creating adapter
                MyAdapter adapter = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}

package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Updates extends AppCompatActivity {
    
//    final String[] item_upload = new String[]{"LIGHT_MUSIC_VOCAL_MALE","LIGHT_MUSIC_VOCAL_FEMALE","HINDUSTANI_CLASSICAL_MUSIC","CARNATIC_CLASSICAL_MUSIC","WESTERN_VOCAL_SOLO","MAPPILAPATT","INSTRUMENTAL_MUSIC_STRINGS","INSTRUMENTAL_MUSIC_KEYBOARD","INSTRUMENTAL_MUSIC_PERCUSSION","INSTRUMENTAL_MUSIC_WIND","KADHAPRASANGAM","MONOACT","MIMICRY","FANCY_DRESS","BHARATHANATYAM","MOHINIYATTOM","FOLK_DANCE","ADAPT_TUNE","MOCK_PRESS","ELOCUTION_MALAYALAM","ELOCUTION_ENGLISH","RECITATION_MALAYALAM","RECITATION_ENGLISH","EXTEMPORE_MALAYALAM","EXTEMPORE_ENGLISH","TURN_AROUND","AKSHARASLOGAM","MANGLISH","PHOTOGRAPHY","ON_THE_SPOT_PAINTING","JAM"};


    final String[] items_group = new String[]{"PAPER_COLLAGE","PENCIL_DRAWING","POSTER_DESIGNING","ESSAY_WRITING_MALAYALAM","ESSAY_WRITING_ENGLISH","SHORT_STORY_WRITING_MALAYALAM","SHORT_STORY_WRITING_ENGLISH","POETRY_WRITING_MALAYALAM","POETRY_WRITING_ENGLISH","FILM_REVIEW_WRITING","TRAVELOGUE","MEME_MAKING","COMICS_STRIP","CARICATURE","THEME_SHOW","DECO","AD_ON","NADAN_PATTU","BAND_OF_BRAHMAS","BATTLE_OF_BANDS","GROUP_SONG_EASTERN","OLD_MELODY_SONG_DUET","NOSTALGIA_GIRLS","THEMATIC_DANCE_GIRLS","PROP_DANCE_BOYS","MIME","THIRUVATHIRA","MARGAM_KALI","VATTAPATTU","SYNCHRONIZATION","DEBATE_MALAYLAM","DEBATE_ENGLISH","QUIZ","DRAMA","TABLEAU","MOVIE_SCENE_DUBBING"," SHORTFILM","MEHANDI_COMPETITION","TREASURE_FROM_TRASH","FACE_PAINTING","WALL_ART","LIGHT_MUSIC_VOCAL_MALE","LIGHT_MUSIC_VOCAL_FEMALE","HINDUSTANI_CLASSICAL_MUSIC","CARNATIC_CLASSICAL_MUSIC","WESTERN_VOCAL_SOLO","MAPPILAPATT","INSTRUMENTAL_MUSIC_STRINGS","INSTRUMENTAL_MUSIC_KEYBOARD","INSTRUMENTAL_MUSIC_PERCUSSION","INSTRUMENTAL_MUSIC_WIND","KADHAPRASANGAM","MONOACT","MIMICRY","FANCY_DRESS","BHARATHANATYAM","MOHINIYATTOM","FOLK_DANCE","ADAPT_TUNE","MOCK_PRESS","ELOCUTION_MALAYALAM","ELOCUTION_ENGLISH","RECITATION_MALAYALAM","RECITATION_ENGLISH","EXTEMPORE_MALAYALAM","EXTEMPORE_ENGLISH","TURN_AROUND","AKSHARASLOGAM","MANGLISH","PHOTOGRAPHY","ON_THE_SPOT_PAINTING","JAM"};

    int i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    int m = 0;
    int n = 0;
    int[] final_status = new int[100];
    static int[] firstNo = new int[100];
    int[] secondNo = new int[100];
    int[] thirdNo = new int[100];

    String fname,fhouse,fscore,feventName,fposition,fclassName,fuploader;
    String fname2,fhouse2,fscore2,feventName2,fposition2,fclassName2,fuploader2;
    String fname3,fhouse3,fscore3,feventName3,fposition3,fclassName3,fuploader3;

    private DatabaseReference mDatabase;
    private List<Details> details_array1;
    private List<Details> details_array2;
    private List<Details> details_array3;

    String[][] data = new String[100][3];

    int[] dataFlag = new int[6];

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Typeface bebas;
    ProgressDialog pDialog;

    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

//        Toast.makeText(this, Integer.toString(items_group.length), Toast.LENGTH_SHORT).show();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        pDialog = new ProgressDialog(this);

        Log.d("started",".......");

        database = FirebaseDatabase.getInstance();
        DatabaseReference final_ref = database.getReference("main/final");
//        final_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("events_count","countis" + Long.toString(dataSnapshot.getChildrenCount()));
//                Toast.makeText(Updates.this, Long.toString(dataSnapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        for(int i=0;i< item_upload.length;i++){
//            final_ref.child(item_upload[i]).setValue("0");
//        }


        bebas = Typeface.createFromAsset(getResources().getAssets(),  "fonts/bebasneue.ttf");



        pDialog.setMessage("Updating Scores...");
        pDialog.setCancelable(false);
        pDialog.show();



        final_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for here/////////////////////////////////////////////////////////
//                Map<String,String> map = (Map) dataSnapshot.getValue(Map.class);

                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );

//                pDialog.setMessage("Updating Scores...");
//                pDialog.setCancelable(false);
//                pDialog.show();


                Log.d("here","-----");
                Log.d("No. of events",Integer.toString(items_group.length));
                for(i=0;i<=items_group.length-1;i++) {


                    final_status[i] = Integer.parseInt(map.get(items_group[i]));
//                    final_ref.child(items_group[i]).setValue("0");
                    Log.d("Final", Integer.toString(final_status[i]));

                }


                trigger();


            }

            @Override
            public void onCancelled(DatabaseError databaseErro) {

            }
        });


    }



    public void trigger(){



        Log.d("trigger",".........");

        details_array1 = new ArrayList<Details>();
        details_array2 = new ArrayList<Details>();
        details_array3 = new ArrayList<Details>();

        mDatabase = FirebaseDatabase.getInstance().getReference("events");
//        Firebase.setAndroidContext(this);


        DatabaseReference ref = database.getReference("main/events");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("trigger data change",".........");

                for(j=0;j<=items_group.length-1;j++) {
                    if (final_status[j] == 1) {

                        Log.d("trigger for",".........");
                        //dismissing the progress dialog
                        Log.d("Events", dataSnapshot.child(items_group[j]).getChildrenCount() + "");
                        //progressDialog.dismiss();

                        if(dataSnapshot.child(items_group[j]).child("first").getChildrenCount() > 1) {
                            for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("first").getChildren()) {
                                if(fname == null){
                                    fname = postSnapshot.child("name").getValue().toString();
                                    fscore = postSnapshot.child("score").getValue().toString();
                                    feventName = postSnapshot.child("eventName").getValue().toString();
                                    fclassName = postSnapshot.child("className").getValue().toString();
                                    fuploader = postSnapshot.child("uploader").getValue().toString();
                                    fhouse = postSnapshot.child("house").getValue().toString();
                                    fposition = postSnapshot.child("position").getValue().toString();
                                }else{
                                    fname += ", " + postSnapshot.child("name").getValue().toString();
                                    fhouse += ", " + postSnapshot.child("house").getValue().toString();
                                }
                            }

                            Details details1 = new Details(fname,fclassName,fhouse,fscore,feventName,fposition,fuploader);
                            details_array1.add(details1);
                        }else {
                            for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("first").getChildren()) {
                                ///gettinng no of first no
                                Log.d("first data change",".........");

//                            for(DataSnapshot subSnapshot : postSnapshot)
                                firstNo[j] = (int) postSnapshot.getChildrenCount();
                                Log.d("ss", Integer.toString(firstNo[j]));
//                            if(dataSnapshot.child(items_group[j]).child("first").getChildrenCount() > 1) {
//                                if(fname.equals("")){
//                                    fname = postSnapshot.child("name").getValue().toString();
//                                    fscore = postSnapshot.child("score").getValue().toString();
//                                    feventName = postSnapshot.child("eventName").getValue().toString();
//                                    fclassName = postSnapshot.child("className").getValue().toString();
//                                    fuploader = postSnapshot.child("uploader").getValue().toString();
//                                    fhouse = postSnapshot.child("house").getValue().toString();
//                                    fposition = postSnapshot.child("position").getValue().toString();
//                                }else{
//                                    fname += ", " + postSnapshot.child("name").getValue().toString();
//                                    fhouse += ", " + postSnapshot.child("house").getValue().toString();
//                                }
//
//
//
//                            }


//                            Log.d("fname",fname);
                                Details details1 = postSnapshot.getValue(Details.class);
//                                details1.setName(details1.getName() + "1");
                                //Log.d("",details.getName());
                                //Image image = new Image();
                                details_array1.add(details1);
//                            name1.add(details1.getName());
                                Log.d("name",details1.getName());
//                            eventName1.add(details1.getEventName());
//                            className1.add(details1.getClassName());
//                            house1.add(details1.getHouse());
//                            position1.add(details1.getPosition());
//                            score1.add(details1.getScore());

                                ///image.setLarge(details.getUrl());
                                //images.add(image);
                            }



                            //iterating through all the values in database



                        }


//                        TODO

                        if(dataSnapshot.child(items_group[j]).child("second").getChildrenCount() > 1) {
                            for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("second").getChildren()) {
                                if(fname2 == null){
                                    fname2 = postSnapshot.child("name").getValue().toString();
                                    fscore2= postSnapshot.child("score").getValue().toString();
                                    feventName2 = postSnapshot.child("eventName").getValue().toString();
                                    fclassName2 = postSnapshot.child("className").getValue().toString();
                                    fuploader2 = postSnapshot.child("uploader").getValue().toString();
                                    fhouse2 = postSnapshot.child("house").getValue().toString();
                                    fposition2= postSnapshot.child("position").getValue().toString();
                                }else{
                                    fname2 += ", " + postSnapshot.child("name").getValue().toString();
                                    fhouse2 += ", " + postSnapshot.child("house").getValue().toString();
                                }
                            }

                            Details details2 = new Details(fname2,fclassName2,fhouse2,fscore2,feventName2,fposition2,fuploader2);
                            details_array2.add(details2);
                        }else {
                            for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("second").getChildren()) {
                                ///gettinng no of first no
                                Log.d("second data change",".........");

//                            for(DataSnapshot subSnapshot : postSnapshot)
                                secondNo[j] = (int) postSnapshot.getChildrenCount();
                                Log.d("ss", Integer.toString(secondNo[j]));
//                            if(dataSnapshot.child(items_group[j]).child("first").getChildrenCount() > 1) {
//                                if(fname.equals("")){
//                                    fname = postSnapshot.child("name").getValue().toString();
//                                    fscore = postSnapshot.child("score").getValue().toString();
//                                    feventName = postSnapshot.child("eventName").getValue().toString();
//                                    fclassName = postSnapshot.child("className").getValue().toString();
//                                    fuploader = postSnapshot.child("uploader").getValue().toString();
//                                    fhouse = postSnapshot.child("house").getValue().toString();
//                                    fposition = postSnapshot.child("position").getValue().toString();
//                                }else{
//                                    fname += ", " + postSnapshot.child("name").getValue().toString();
//                                    fhouse += ", " + postSnapshot.child("house").getValue().toString();
//                                }
//
//
//
//                            }


//                            Log.d("fname",fname);
                                Details details2 = postSnapshot.getValue(Details.class);
//                                details1.setName(details1.getName() + "1");
                                //Log.d("",details.getName());
                                //Image image = new Image();
                                details_array2.add(details2);
//                            name1.add(details2.getName());
                                Log.d("name",details2.getName());
//                            eventName1.add(details1.getEventName());
//                            className1.add(details1.getClassName());
//                            house1.add(details1.getHouse());
//                            position1.add(details1.getPosition());
//                            score1.add(details1.getScore());

                                ///image.setLarge(details.getUrl());
                                //images.add(image);
                            }



                            //iterating through all the values in database



                        }


//                        TODO

                        if(dataSnapshot.child(items_group[j]).child("third").getChildrenCount() > 1) {
                            for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("third").getChildren()) {
                                if(fname3 == null){
                                    fname3 = postSnapshot.child("name").getValue().toString();
                                    fscore3 = postSnapshot.child("score").getValue().toString();
                                    feventName3 = postSnapshot.child("eventName").getValue().toString();
                                    fclassName3 = postSnapshot.child("className").getValue().toString();
                                    fuploader3 = postSnapshot.child("uploader").getValue().toString();
                                    fhouse3 = postSnapshot.child("house").getValue().toString();
                                    fposition3 = postSnapshot.child("position").getValue().toString();
                                }else{
                                    fname3 += ", " + postSnapshot.child("name").getValue().toString();
                                    fhouse3 += ", " + postSnapshot.child("house").getValue().toString();
                                }
                            }

                            Details details3 = new Details(fname3,fclassName3,fhouse3,fscore3,feventName3,fposition3,fuploader3);
                            details_array3.add(details3);
                        }else {
                            for (DataSnapshot postSnapshot : dataSnapshot.child(items_group[j]).child("third").getChildren()) {
                                ///gettinng no of first no
                                Log.d("third data change",".........");

//                            for(DataSnapshot subSnapshot : postSnapshot)
                                thirdNo[j] = (int) postSnapshot.getChildrenCount();
                                Log.d("ss", Integer.toString(thirdNo[j]));
//                            if(dataSnapshot.child(items_group[j]).child("first").getChildrenCount() > 1) {
//                                if(fname.equals("")){
//                                    fname = postSnapshot.child("name").getValue().toString();
//                                    fscore = postSnapshot.child("score").getValue().toString();
//                                    feventName = postSnapshot.child("eventName").getValue().toString();
//                                    fclassName = postSnapshot.child("className").getValue().toString();
//                                    fuploader = postSnapshot.child("uploader").getValue().toString();
//                                    fhouse = postSnapshot.child("house").getValue().toString();
//                                    fposition = postSnapshot.child("position").getValue().toString();
//                                }else{
//                                    fname += ", " + postSnapshot.child("name").getValue().toString();
//                                    fhouse += ", " + postSnapshot.child("house").getValue().toString();
//                                }
//
//
//
//                            }


//                            Log.d("fname",fname);
                                Details details3= postSnapshot.getValue(Details.class);
//                                details1.setName(details1.getName() + "1");
                                //Log.d("",details.getName());
                                //Image image = new Image();
                                details_array3.add(details3);
//                            name1.add(details1.getName());
                                Log.d("name",details3.getName());
//                            eventName1.add(details1.getEventName());
//                            className1.add(details1.getClassName());
//                            house1.add(details1.getHouse());
//                            position1.add(details1.getPosition());
//                            score1.add(details1.getScore());

                                ///image.setLarge(details.getUrl());
                                //images.add(image);
                            }



                            //iterating through all the values in database



                        }





                        //creating adapter
                        //MyAdapter adapter = new MyAdapter(getApplicationContext(), details);

                        mAdapter = new DetailedAdapter(getApplicationContext(),details_array1,details_array2,details_array3,bebas);

//                        mAdapter = new DetailedAdapter(getApplicationContext(),details_array1,details_array2,details_array3);
                        //adding adapter to recyclerview
                        //recyclerView.setAdapter(adapter);
                        recyclerView.setAdapter(mAdapter);

                        if(pDialog.isShowing()){
                            pDialog.dismiss();
                        }



                    }
                }

//                if(pDialog.isShowing())
//                    pDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });



    }


}

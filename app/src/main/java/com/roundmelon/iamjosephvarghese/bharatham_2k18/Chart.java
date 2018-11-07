package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {

    BarChart chart;
    private ProgressDialog pDialog;


    float oneChart;
    float twoChart;
    float threeChart;
    float fourChart;
    float fiveChart;
    float sixChart;
    String oneCharts;
    String twoCharts;
    String threeCharts;
    String fourCharts;
    String fiveCharts;
    String sixCharts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Score");

        pDialog = new ProgressDialog(this);



        //Getting values to store
        String aryans = "0";
        String mughals = "0";
        String rajputs = "0";
        String spartans = "0";
        String vikings = "0";
        String aghoras ="0";



        //Creating Person object
        House house = new House();

        //Adding values
        house.setAryans(aryans);
        house.setMughals(mughals);
        house.setRajputs(rajputs);
        house.setSpartans(spartans);
        house.setVikings(vikings);
        house.setAghoras(aghoras);


        pDialog.setMessage("Updating Scores...");
        pDialog.setCancelable(false);
        pDialog.show();



        chart = (BarChart) findViewById(R.id.chart);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Snapshot",dataSnapshot.getChildrenCount()+"");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot
                    House house = postSnapshot.getValue(House.class);

                    //Adding it to a string
//                    String string = "Name: "+person.getName()+"\nAddress: "+person.getAddress()+"\n\n";
//                    Log.d("Sna",house.getAll());
                    oneCharts = house.getAryans();
                    twoCharts = house.getMughals();
                    threeCharts = house.getRajputs();
                    fourCharts = house.getSpartans();
                    fiveCharts = house.getVikings();
                    sixCharts = house.getAghoras();


                    oneChart = Float.parseFloat(oneCharts);
                    twoChart = Float.parseFloat(twoCharts);
                    threeChart = Float.parseFloat(threeCharts);
                    fourChart = Float.parseFloat(fourCharts);
                    fiveChart = Float.parseFloat(fiveCharts);
                    sixChart =Float.parseFloat(sixCharts);

                    if(pDialog.isShowing())
                        pDialog.dismiss();
                    BarData data = new BarData(getXAxisValues(), getDataSet());
                    chart.setData(data);
                    chart.setDescription("");
                    chart.animateY(2000);
                    chart.invalidate();

                    //Displaying it on textview
//                    textViewPersons.setText(string);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateY(2000);
        chart.invalidate();


    }


    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(oneChart, 0);
        valueSet1.add(v1e1);


        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(twoChart, 0);
        valueSet2.add(v2e1);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v3e1 = new BarEntry(threeChart, 0);
        valueSet3.add(v3e1);

        ArrayList<BarEntry> valueSet4 = new ArrayList<>();
        BarEntry v4e1 = new BarEntry(fourChart, 0);
        valueSet4.add(v4e1);

        ArrayList<BarEntry> valueSet5 = new ArrayList<>();
        BarEntry v5e1 = new BarEntry(fiveChart, 0);
        valueSet5.add(v5e1);

        ArrayList<BarEntry> valueSet6 = new ArrayList<>();
        BarEntry v6e1 = new BarEntry(sixChart, 0);
        valueSet6.add(v6e1);





        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Aryans");
        barDataSet1.setColor(Color.rgb(131,76,183));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Mughals");
        barDataSet2.setColor(Color.rgb(233,84,3));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Rajputs");
        barDataSet3.setColor(Color.rgb(255,235,59));
        BarDataSet barDataSet4 = new BarDataSet(valueSet4, "Spartans");
        barDataSet4.setColor(Color.rgb(183,28,28));
        BarDataSet barDataSet5 = new BarDataSet(valueSet5, "Vikings");
        barDataSet5.setColor(Color.rgb(13,71,161));
        BarDataSet barDataSet6 = new BarDataSet(valueSet6, "aghoras");
        barDataSet5.setColor(Color.rgb(13,71,161));



        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);
        dataSets.add(barDataSet5);
        dataSets.add(barDataSet6);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("");

        return xAxis;
    }

}

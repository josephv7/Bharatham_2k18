package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.ViewHolder> {
    private List<Details> details_array1;
    private List<Details> details_array2;
    private List<Details> details_array3;
    private Typeface tf;



//    private ArrayList<String> details_array1;
//    private ArrayList<String> details_array2;
//    private ArrayList<String> details_array3;

    private Context context;

public DetailedAdapter(Context context, List<Details> details_array1, List<Details> details_array2, List<Details> details_array3, Typeface tf){
    this.context = context;
    this.details_array1 = details_array1;
    this.details_array2 = details_array2;
    this.details_array3 = details_array3;
    this.tf = tf;
}

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
//        public TextView txtHeader;
//        public TextView txtFooter;
        public TextView event_name;
        public TextView first_prize_group;
        public TextView second_prize_group;
        public TextView third_prize_group;
        public TextView first_group;
        public TextView second_group;
        public TextView third_group;
        public TextView first_points;
        public TextView second_points;
        public TextView third_points;

        public TextView firstPrize;
        public TextView secondPrize;
        public TextView thirdPrize;

        public TextView firstHouse;
        public TextView secondHouse;
        public TextView thirdHouse;


        public TextView firstPoints;
        public TextView secondPoints;
        public TextView thirdPoints;



        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;





//decalre the description text views



            event_name = (TextView) v.findViewById(R.id.event_name);
            first_prize_group = (TextView) v.findViewById(R.id.first_prize_group);
            second_prize_group = (TextView) v.findViewById(R.id.second_prize_group);
            third_prize_group = (TextView) v.findViewById(R.id.third_prize_group);

            first_group = (TextView)v.findViewById(R.id.first_group);
            second_group = (TextView)v.findViewById(R.id.second_group);
            third_group = (TextView)v.findViewById(R.id.third_group);

            first_points = (TextView)v.findViewById(R.id.first_points);
            second_points = (TextView)v.findViewById(R.id.second_points);
            third_points = (TextView)v.findViewById(R.id.third_points);

            firstPrize = (TextView)v.findViewById(R.id.firstPrize);
            secondPrize = (TextView)v.findViewById(R.id.secondPrize);
            thirdPrize = (TextView)v.findViewById(R.id.thirdPrize);

            firstHouse = (TextView)v.findViewById(R.id.firstHouse);
            secondHouse = (TextView)v.findViewById(R.id.secondHouse);
            thirdHouse = (TextView)v.findViewById(R.id.thirdHouse);

            firstPoints = (TextView)v.findViewById(R.id.firstPoints);
            secondPoints = (TextView)v.findViewById(R.id.secondPoints);
            thirdPoints = (TextView)v.findViewById(R.id.thirdPoints);


            firstPoints.setTypeface(tf);
            secondPoints.setTypeface(tf);
            thirdPoints.setTypeface(tf);


            firstHouse.setTypeface(tf);
            secondHouse.setTypeface(tf);
            thirdHouse.setTypeface(tf);

            firstPrize.setTypeface(tf);
            secondPrize.setTypeface(tf);
            thirdPrize.setTypeface(tf);


            event_name.setTypeface(tf);
            first_group.setTypeface(tf);
            second_group.setTypeface(tf);
            third_group.setTypeface(tf);


            first_prize_group.setTypeface(tf);
            second_prize_group.setTypeface(tf);
            third_prize_group.setTypeface(tf);

            first_points.setTypeface(tf);
            second_points.setTypeface(tf);
            third_points.setTypeface(tf);



        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public DetailedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout2, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Details det1 = details_array1.get(position);
        Details det2 = details_array2.get(position);
        Details det3 = details_array3.get(position);
        holder.event_name.setText(det1.getEventName());
        holder.first_prize_group.setText(det1.getName());
        holder.first_group.setText(det1.getHouse());
        holder.first_points.setText(det1.getScore());

        holder.second_prize_group.setText(det2.getName());
        holder.second_group.setText(det2.getHouse());
        holder.second_points.setText(det2.getScore());


        holder.third_prize_group.setText(det3.getName());
        holder.third_group.setText(det3.getHouse());
        holder.third_points.setText(det3.getScore());






    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return details_array1.size();
    }

}
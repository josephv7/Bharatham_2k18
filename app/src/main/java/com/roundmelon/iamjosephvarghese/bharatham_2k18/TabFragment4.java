package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TabFragment4 extends Fragment {

    private String mFIleContents;
    private ListView xmlListView;
    private int downloadRespone;
    private ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView1 =  inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        pDialog = new ProgressDialog(getActivity());


        xmlListView = (ListView)rootView1.findViewById(R.id.xmlListView1);


        pDialog.setMessage("Updating...");
        pDialog.show();
        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://bharatham2k17.blogspot.com//feeds/posts/default/-/pb3");





        return rootView1;

    }




    private class DownloadData extends AsyncTask<String,Void,String> {



        @Override
        protected String doInBackground(String... params) {
            mFIleContents = downloadXmlFile(params[0]);
            if (mFIleContents == null) {
                Log.d("DownloadData", "Error downloading");
            }

            return mFIleContents;

        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("DownloadData","Result was:" + result);
            ParseApplications parseApplications = new ParseApplications(mFIleContents);
            parseApplications.process();

            ArrayAdapter<Application> arrayAdapter = new ArrayAdapter<Application>(
                    getActivity().getApplicationContext(),R.layout.list_item,parseApplications.getApplications());
            xmlListView.setAdapter(arrayAdapter);
            pDialog.dismiss();
        }



        private String downloadXmlFile(String urlPath) {
            StringBuilder tempBuffer = new StringBuilder();
            int response;

            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                response = connection.getResponseCode();
                Log.d("DownloadData", "The response code was" + response);
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);




                int charRead;
                char[] inputBuffer = new char[500];
                while (true) {
                    charRead = isr.read(inputBuffer);
                    if(charRead <= 0) {
                        break;}
                    tempBuffer.append(String.copyValueOf(inputBuffer,0,charRead));

                }
                return tempBuffer.toString();


            }catch(IOException e){
                Log.d("DownloadData", "IO Exception reading data:" + e.getMessage());
                e.printStackTrace();
            }catch(SecurityException e){
                Log.d("DownloadData","Security exception needs permisiion?" + e.getMessage());

            }catch(Exception e){
                e.printStackTrace();
            }

            return null;


        }
    }



}
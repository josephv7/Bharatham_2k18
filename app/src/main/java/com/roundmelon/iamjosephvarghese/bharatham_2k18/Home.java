package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Home extends AppCompatActivity {
    private Button mSelectImage;

    private StorageReference mStorageRef;

    private DatabaseReference mDatabase;
    private static final int GALLERY_INTENT = 2;

    private ProgressDialog mProgressDialog;


    public EditText name;
    public EditText mobile;
    public EditText clg;



    public TextView textView1;
    public TextView textView2;
    public TextView textView3;







    private String Name;
    private String Mobile;
    private String Clg;



    private Context context;


    private StorageMetadata metadata;

    private int STORAGE_PERMISSION_CODE = 23;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Typeface bebas = Typeface.createFromAsset(getAssets(),  "fonts/bebasneue.ttf");


        mSelectImage = (Button) findViewById(R.id.mSelectImage);

        mProgressDialog = new ProgressDialog(this);

        context = getApplicationContext();

        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        clg = (EditText) findViewById(R.id.note);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);



        textView1.setTypeface(bebas);
        textView2.setTypeface(bebas);
        textView3.setTypeface(bebas);


        name.setTypeface(bebas);
        mobile.setTypeface(bebas);
        clg.setTypeface(bebas);

        mSelectImage.setTypeface(bebas);



        name.requestFocus();

        mAuth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference("uploads");


        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Name = ((EditText) findViewById(R.id.name)).getText().toString();
                Mobile = ((EditText) findViewById(R.id.mobile)).getText().toString();
                Clg = ((EditText) findViewById(R.id.note)).getText().toString();


                if (Name.length() > 0 && Mobile.length() > 0 && Clg.length() > 0){


                    metadata = new StorageMetadata.Builder()
                            .setContentType("image/jpg")
                            .setCustomMetadata("Description", Clg)
                            .setCustomMetadata("Ph", Mobile)
                            .setCustomMetadata("Name", Name)
                            .build();


                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");




                    if(isReadStorageAllowed()){
                        //If permission is already having then showing the toast
                        // Toast.makeText(Selfie.this,"You already have the permission",Toast.LENGTH_LONG).show();
                        //Existing the method with return

                        startActivityForResult(intent, GALLERY_INTENT);
                        return;
                    }

                    //If the app has not the permission then asking for the permission
                    requestStoragePermission();


                }else{
                    Toast.makeText(Home.this,"Please Fill In The Necessary Fields.",Toast.LENGTH_LONG).show();
                }

            }
        });
    }




    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted.Click Selfie Again.",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            Uri uri = data.getData();
            StorageReference sRef = mStorageRef.child("Selfie").child(Name + uri.getLastPathSegment());



            sRef.putFile(uri,metadata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Home.this,"Image Uploaded",Toast.LENGTH_LONG).show();

                    Upload upload = new Upload(name.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString());


                    //adding an upload to firebase database
                    String uploadId = mDatabase.push().getKey();
                    mDatabase.child(uploadId).setValue(upload);


                    mProgressDialog.dismiss();
                    name.setText("");
                    mobile.setText("");
                    clg.setText("");
                    name.requestFocus();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Home.this,"Uh-oh, an error occurred!",Toast.LENGTH_LONG).show();
                }
            });

        }

    }
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("FIREBASE", "signInAnonymously:FAILURE", exception);
                    }
                });
    }
}
package com.example.finalprojectodc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class AddRecomenddProduct extends AppCompatActivity {


    EditText mEdTitle,mEdDesc,mEdPrice;
    Button mBtnAdd,mBtnList;
    ImageView mImageView;

    final int REQUEST_CODE_GALLERY=999;
    public static  SQLiteHelper mSQLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recomendd_product);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("new record");
        mEdTitle=findViewById(R.id.edtTitle);
        mEdDesc=findViewById(R.id.eddesc);
        mEdPrice=findViewById(R.id.edPrice);
        mBtnAdd=findViewById(R.id.btnAdd);
        mBtnList=findViewById(R.id.btnList);
        mImageView=findViewById(R.id.imageView);

        //to create database

        mSQLiteHelper= new SQLiteHelper(this,"RECORDDB.sqlite",null,1);

        // i do this to create table to database

        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR,description VARCHAR,price VARCHAR, image BLOB)");



        // i do this to enable me to select image by imageview click

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // i do that to read external storage to select image
                ActivityCompat.requestPermissions(
                        AddRecomenddProduct.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        // i do this to add to sqlLite
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSQLiteHelper.insertData(
                            mEdTitle.getText().toString().trim(),
                            mEdDesc.getText().toString().trim(),
                            mEdPrice.getText().toString().trim(),
                            imageViewToByte(mImageView)
                    );
                    Toast.makeText(AddRecomenddProduct.this,"Added successfuly",Toast.LENGTH_LONG).show();
                    mEdTitle.setText("");
                    mEdDesc.setText("");
                    mEdPrice.setText("");
                    mImageView.setImageResource(R.drawable.addphoto);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        // i do this to show list of data
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddRecomenddProduct.this,RecordListActivity.class));

            }
        });

    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap =((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;


    }

    @Override
    public  void  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Don't have permission to  acces file location", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected  void  onActivityResult (int requestCode,int resultCode,Intent data){
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();

                mImageView.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}
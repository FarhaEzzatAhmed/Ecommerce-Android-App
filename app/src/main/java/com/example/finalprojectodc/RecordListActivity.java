package com.example.finalprojectodc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {

    ListView mListView;
    ImageView homehome;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;

    ImageView imageViewIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Record List");
homehome=findViewById(R.id.homehome);
homehome.setOnClickListener(v -> {
    Intent iho = new Intent(RecordListActivity.this,Screen7.class);
    startActivity(iho);
});
        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row, mList);
        mListView.setAdapter(mAdapter);

        // i do it to get all data from Sqllite

        Cursor cursor = AddRecomenddProduct.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);


            // to add to list
            mList.add(new Model(id, title, description, price, image));


        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            Toast.makeText(this, "NO RECORD FOUND", Toast.LENGTH_LONG).show();
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                // i wil do this to make alert dialog otions of update and delate

                final CharSequence[] items = {"Update","Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(RecordListActivity.this);
                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if( i == 0){

                            Cursor c = AddRecomenddProduct.mSQLiteHelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(RecordListActivity.this,arrID.get(position));

                        }
                        if(i==1){

                            Cursor c = AddRecomenddProduct.mSQLiteHelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));

                        }
                    }
                });
                dialog.show();

                return true;
            }
        });


    }

    private void showDialogDelete(final int idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(RecordListActivity.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure to delete");
        dialogDelete.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    AddRecomenddProduct.mSQLiteHelper.deleteData(idRecord);
                    Toast.makeText(RecordListActivity.this,"Delete successfully",Toast.LENGTH_LONG).show();


                }
                catch(Exception e){
                    Log.e("error",e.getMessage());
                }
                updateRecordList();
            }
        });
        dialogDelete.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        dialogDelete.show();


    }

    private void showDialogUpdate(Activity activity, int position){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        imageViewIcon = dialog.findViewById(R.id.imageViewRecord);
        EditText edtTitle = dialog.findViewById(R.id.edtTitle);
        EditText edtDescription = dialog.findViewById(R.id.edtdesc);
        EditText price = dialog.findViewById(R.id.edtPrice);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        //to update dialog clivk image to update

        imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        RecordListActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddRecomenddProduct.mSQLiteHelper.updateData(
                            edtTitle.getText().toString().trim(),
                            edtDescription.getText().toString().trim(),
                            price.getText().toString().trim(),
                            AddRecomenddProduct.imageViewToByte(imageViewIcon),position


                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Update Successfully",Toast.LENGTH_LONG).show();
                }
                catch (Exception error){
                    Log.e("Update error",error.getMessage());
                }
                updateRecordList();
            }
        });

    }

    private void updateRecordList() {
        Cursor cursor = AddRecomenddProduct.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            mList.add(new Model(id,title,description,price,image));


        }
        mAdapter.notifyDataSetChanged();
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
        if (requestCode == 888){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,888);
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
        if(requestCode == 888 && resultCode == RESULT_OK){
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

                imageViewIcon.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}
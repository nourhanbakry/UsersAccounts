package com.example.usersaccounts;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserInfo extends AppCompatActivity {
    public static  final String USER_INFO_MODEL = "userInfoModel";
    public  static final int RESULT_CODE = 7;
    ImageView profileImage;
    EditText userNameET;
    EditText bioET;
    EditText dateBirthET;
    EditText postsNumberET;
    EditText followersNumbersET;
    EditText followingsNumbersET;
    AppCompatButton addUserProfileBTN;
    // for returned image
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // check on result
            if (result.getResultCode() == RESULT_OK)  {
                //how to get image captured from intent?
                Bitmap newImage= (Bitmap) result.getData().getExtras().get("data");
                // update image
                profileImage.setImageBitmap(newImage);
            } else{
                Toast.makeText(UserInfo.this, "No Image captured, u must take photo", Toast.LENGTH_SHORT).show();
            }


        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inflateViews();
        addUserProfile();
    }

    public void inflateViews(){
        profileImage = findViewById(R.id.info_activity_profile_image);
        userNameET = findViewById(R.id.user_name_et);
        bioET = findViewById(R.id.user_bio_et);
        dateBirthET = findViewById(R.id.data_of_birth_et);
        postsNumberET = findViewById(R.id.posts_no_et);
        followersNumbersET = findViewById(R.id.followers_no_et);
        followingsNumbersET = findViewById(R.id.following_no_et);
        addUserProfileBTN = findViewById(R.id.info_activity_add_user_profile_info_btn);
    }
    private void addUserProfile(){
        addUserProfileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              BitmapDrawable imageDrawable= (BitmapDrawable) profileImage.getDrawable();
              Bitmap image = imageDrawable.getBitmap();

              String userName = userNameET.getText().toString();
              String bio = bioET.getText().toString();
              String date = dateBirthET.getText().toString();
              String posts = postsNumberET.getText().toString();
              String followers = followersNumbersET.getText().toString();
              String following = followingsNumbersET.getText().toString();
              Intent intent = new Intent();
                // pack all data in the intent as an object
              UserInfoModel userInfoModel=new UserInfoModel(followers,image,userName,bio,date,posts,following);
              intent.putExtra(USER_INFO_MODEL,userInfoModel);
              // there is incoming result
              setResult(RESULT_CODE,intent);
              finish();
            }
        });
    }
    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startForResult.launch(intent);
    }


}
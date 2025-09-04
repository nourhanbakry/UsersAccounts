package com.example.usersaccounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AppCompatButton addProfileBTN;
    ArrayList<UserInfoModel> listOfUsersProfiles;
    MyAdapter myAdapter;
    ListView listView;

    // I recive data here
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == UserInfo.RESULT_CODE){
               UserInfoModel userInfoModel = result.getData().getParcelableExtra(UserInfo.USER_INFO_MODEL);
               myAdapter.addItem(userInfoModel);
               myAdapter.notifyDataSetChanged();
            } else{
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       addProfileBTN = findViewById(R.id.main_activity_add_profile_btn);
       listView = findViewById(R.id.list_view);

       // my data
        listOfUsersProfiles = new ArrayList<>();
        addProfile();
        // my adapter
        myAdapter = new MyAdapter(this,listOfUsersProfiles);
        // connect between them
        listView.setAdapter(myAdapter);

    }

    private void addProfile(){
        addProfileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserInfo.class);
                startForResult.launch(intent);

            }
        });
    }
}
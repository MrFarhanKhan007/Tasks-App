package com.example.tasks.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tasks.R;
import com.example.tasks.utils.TaskDatabase;
import com.google.firebase.analytics.FirebaseAnalytics;

public class AddTaskActivity extends AppCompatActivity {

    ImageView backButton;
    EditText taskNameEt;
    TextView doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        backButton = findViewById(R.id.backBn_addTask);
        backButton.setOnClickListener(view -> onBackPressed());

        taskNameEt = findViewById(R.id.addNewTaskEditText);
        doneButton = findViewById(R.id.doneBn_addTask);



        doneButton.setOnClickListener(v -> {
            Bundle eventBundle = new Bundle();
            eventBundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Done Button");
            eventBundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Click");
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, eventBundle);

            TaskDatabase taskDb = new TaskDatabase(AddTaskActivity.this);
            taskDb.addTask(taskNameEt.getText().toString().trim());
            taskNameEt.setText("");
            finish();
        });


    }

}
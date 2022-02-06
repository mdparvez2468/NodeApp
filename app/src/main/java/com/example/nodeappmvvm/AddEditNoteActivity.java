package com.example.nodeappmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.nodeappmvvm.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.nodeappmvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.nodeappmvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.nodeappmvvm.EXTRA_PRIORITY";

    private EditText titleET,descriptionET;
    private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleET = findViewById(R.id.titleET);
        descriptionET = findViewById(R.id.descriptionET);
        numberPicker = findViewById(R.id.priorityPick);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Node");
            titleET.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionET.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setMaxValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {
            setTitle("Add Node");
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_node_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_node:
                saveNode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNode() {
        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();
        int priority = numberPicker.getValue();

        if (title.isEmpty()||description.isEmpty()){
            Toast.makeText(AddEditNoteActivity.this, "Please Enter Node Info", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id!=-1){
            data.putExtra(EXTRA_ID,id);

        }

        setResult(RESULT_OK,data);
        finish();

    }
}
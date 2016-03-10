package doryphoros.me.japanesestudy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import doryphoros.me.japanesestudy.ButtonAdapter;
import doryphoros.me.japanesestudy.R;
import doryphoros.me.japanesestudy.constants.JPConstants;
import doryphoros.me.japanesestudy.models.Card;
import doryphoros.me.japanesestudy.models.Response;
import doryphoros.me.japanesestudy.models.Tag;
import doryphoros.me.japanesestudy.utils.JPUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SelectActivity.selectedTags = new ArrayList<>();
        SelectActivity.badTags = new ArrayList<>();
        drawTags();
    }

    private void drawTags() {
        ArrayList<String> strings = new ArrayList<String>();
        for (Tag t : SelectActivity.tags) {
            strings.add(t.value);
        }
        GridView tagGrid = (GridView) findViewById(R.id.tag_grid);
        tagGrid.setAdapter(new ButtonAdapter(MainActivity.this, R.layout.tag_button, strings, "default"));

        Button studyButton = (Button) findViewById(R.id.study_button);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectActivity.updateSelectedCards();
                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                startActivity(intent);
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.firstSideSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.firstSideArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectActivity.firstSide = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SelectActivity.firstSide = "english";
            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.secondSideSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.secondSideArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectActivity.secondSide = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SelectActivity.secondSide = "japanese";
            }
        });
    }

}

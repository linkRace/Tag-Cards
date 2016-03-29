package doryphoros.me.japanesestudy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import doryphoros.me.japanesestudy.ButtonAdapter;
import doryphoros.me.japanesestudy.R;
import doryphoros.me.japanesestudy.models.Card;
import doryphoros.me.japanesestudy.models.Kanji;
import doryphoros.me.japanesestudy.models.Tag;

public class SelectKanjiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji);
        SelectActivity.selectedKanjiTags = new ArrayList<String>();
        SelectActivity.badKanjiTags = new ArrayList<String>();
        drawTags();
    }

    private void drawTags() {
        ArrayList<String> strings = new ArrayList<String>();
        HashMap<String,Integer> hs = new HashMap<String,Integer>();
        for (Tag t : SelectActivity.kanjiTags) {
            strings.add(t.value);
        }
        GridView tagGrid = (GridView) findViewById(R.id.tag_grid_kanji);
        tagGrid.setAdapter(new ButtonAdapter(SelectKanjiActivity.this, R.layout.tag_button, strings, "kanji"));

        Button studyButton = (Button) findViewById(R.id.study_button_read);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preStudy();
                SelectActivity.read = true;
                Intent intent = new Intent(SelectKanjiActivity.this, KanjiStudyActivity.class);
                startActivity(intent);
            }
        });

        Button drawButton = (Button) findViewById(R.id.study_button_draw);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preStudy();
                SelectActivity.read = false;
                Intent intent = new Intent(SelectKanjiActivity.this, KanjiStudyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void preStudy() {
        SelectActivity.updateSelectedKanji();
    }
}

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

public class SelectKanjiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji);
        SelectActivity.selectedTags = new ArrayList<String>();
        SelectActivity.selectedTags.add("verb");
        SelectActivity.badTags = new ArrayList<>();
        SelectActivity.selectedConTags = new ArrayList<>();
        SelectActivity.badConTags = new ArrayList<>();
        drawTags();
    }

    private void drawTags() {
        ArrayList<String> strings = new ArrayList<String>();
        HashMap<String,Integer> hs = new HashMap<String,Integer>();
        for (Card c : SelectActivity.cards) {
            if (c.tags.contains("verb")) {
                for (String t : c.tags) {
                    if (!hs.containsKey(t)) {
                        hs.put(t,1);
                        strings.add(t);
                    }
                }
            }
        }
        strings.remove("verb");
        GridView tagGrid = (GridView) findViewById(R.id.tag_grid_verb);
        tagGrid.setAdapter(new ButtonAdapter(SelectKanjiActivity.this, R.layout.tag_button, strings, "default"));

        String [] ar = getResources().getStringArray(R.array.verbConjugation);
        ArrayList<String> cons = new ArrayList<String>(Arrays.asList(ar));
        GridView conGrid = (GridView) findViewById(R.id.tag_grid_con);
        conGrid.setAdapter(new ButtonAdapter(SelectKanjiActivity.this, R.layout.tag_button, cons, "verb"));

        Button studyButton = (Button) findViewById(R.id.study_button_read);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preStudy();
                Intent intent = new Intent(SelectKanjiActivity.this, KanjiStudyActivity.class);
                startActivity(intent);
            }
        });

        Button drawButton = (Button) findViewById(R.id.study_button_draw);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preStudy();
                Intent intent = new Intent(SelectKanjiActivity.this, KanjiStudyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void preStudy() {
        SelectActivity.firstSide = "english";
        SelectActivity.secondSide = "japanese";
        SelectActivity.updateSelectedCards();
        ArrayList<Card> tmpCards = SelectActivity.selectedCards;
        SelectActivity.selectedCards = new ArrayList<>();
        for (Card c : tmpCards) {
            for (String s : SelectActivity.selectedConTags) {
            }
        }
    }
}

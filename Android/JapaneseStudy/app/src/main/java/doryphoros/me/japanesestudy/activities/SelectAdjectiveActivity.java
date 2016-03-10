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

public class SelectAdjectiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adj);
        SelectActivity.selectedTags = new ArrayList<String>();
        SelectActivity.selectedTags.add("adjective");
        SelectActivity.badTags = new ArrayList<>();
        SelectActivity.selectedConTags = new ArrayList<>();
        SelectActivity.badConTags = new ArrayList<>();
        drawTags();
    }

    private void drawTags() {
        ArrayList<String> strings = new ArrayList<String>();
        HashMap<String,Integer> hs = new HashMap<String,Integer>();
        for (Card c : SelectActivity.cards) {
            if (c.tags.contains("adjective")) {
                for (String t : c.tags) {
                    if (!hs.containsKey(t)) {
                        hs.put(t,1);
                        strings.add(t);
                    }
                }
            }
        }
        strings.remove("adjective");
        GridView tagGrid = (GridView) findViewById(R.id.tag_grid_adj);
        tagGrid.setAdapter(new ButtonAdapter(SelectAdjectiveActivity.this, R.layout.tag_button, strings, "default"));

        String [] ar = getResources().getStringArray(R.array.adjConjugation);
        ArrayList<String> cons = new ArrayList<String>(Arrays.asList(ar));
        GridView conGrid = (GridView) findViewById(R.id.tag_grid_con_adj);
        conGrid.setAdapter(new ButtonAdapter(SelectAdjectiveActivity.this, R.layout.tag_button, cons, "adj"));

        Button studyButton = (Button) findViewById(R.id.study_button_adj);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preStudy();
                Intent intent = new Intent(SelectAdjectiveActivity.this, StudyActivity.class);
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
                SelectActivity.selectedCards.add(conjugate(c, s));
            }
        }
    }

    private Card conjugate(Card c, String s) {
        String first = "", second = "", third = "",
                japanese = c.japanese,
                finalSym = "",
                form = "";
        first += c.english;
        third = s;
        finalSym += japanese.charAt(japanese.length() - 1);

        if (s.equalsIgnoreCase("presPos")) {
            if (finalSym.equalsIgnoreCase("い")) {
                if (c.tags.contains("ii")) {
                    japanese = japanese.substring(0, japanese.length() - 2);
                    finalSym  = "いいです";
                } else {
                    finalSym = "です";
                }
            } else if (finalSym.equalsIgnoreCase("な")) {
                japanese = japanese.substring(0, japanese.length() - 1);
                finalSym = "です";
            } else {

            }
            second = japanese + finalSym;
        } else if (s.equalsIgnoreCase("presNeg")) {
            if (finalSym.equalsIgnoreCase("い")) {
                if (c.tags.contains("ii")) {
                    japanese = japanese.substring(0, japanese.length() - 2);
                    finalSym  = "よくないです";
                } else {
                    japanese = japanese.substring(0, japanese.length() - 1);
                    finalSym = "くないです";
                }
            } else if (finalSym.equalsIgnoreCase("な")) {
                japanese = japanese.substring(0, japanese.length() - 1);
                finalSym = "じゃないです";
            } else {

            }
            second = japanese + finalSym;
        } else if (s.equalsIgnoreCase("pastPos")) {
            if (finalSym.equalsIgnoreCase("い")) {
                if (c.tags.contains("ii")) {
                    japanese = japanese.substring(0, japanese.length() - 2);
                    finalSym  = "よかったです";
                } else {
                    japanese = japanese.substring(0, japanese.length() - 1);
                    finalSym = "かったです";
                }
            } else if (finalSym.equalsIgnoreCase("な")) {
                japanese = japanese.substring(0, japanese.length() - 1);
                finalSym = "でした";
            } else {

            }
            second = japanese + finalSym;
        } else if (s.equalsIgnoreCase("pastNeg")) {
            if (finalSym.equalsIgnoreCase("い")) {
                if (c.tags.contains("ii")) {
                    japanese = japanese.substring(0, japanese.length() - 2);
                    finalSym  = "よくなかったです";
                } else {
                    japanese = japanese.substring(0, japanese.length() - 1);
                    finalSym = "くなかったです";
                }
            } else if (finalSym.equalsIgnoreCase("な")) {
                japanese = japanese.substring(0, japanese.length() - 1);
                finalSym = "じゃなかったです";
            } else {

            }
            second = japanese + finalSym;
        } else {}
        return new Card(first, second, third);
    }

}

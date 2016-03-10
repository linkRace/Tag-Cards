package doryphoros.me.japanesestudy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import doryphoros.me.japanesestudy.ButtonAdapter;
import doryphoros.me.japanesestudy.R;
import doryphoros.me.japanesestudy.models.Card;
import doryphoros.me.japanesestudy.models.Tag;

public class SelectVerbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verb);
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
        tagGrid.setAdapter(new ButtonAdapter(SelectVerbActivity.this, R.layout.tag_button, strings, "default"));

        String [] ar = getResources().getStringArray(R.array.verbConjugation);
        ArrayList<String> cons = new ArrayList<String>(Arrays.asList(ar));
        GridView conGrid = (GridView) findViewById(R.id.tag_grid_con);
        conGrid.setAdapter(new ButtonAdapter(SelectVerbActivity.this, R.layout.tag_button, cons, "verb"));

        Button studyButton = (Button) findViewById(R.id.study_button_verb);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preStudy();
                Intent intent = new Intent(SelectVerbActivity.this, StudyActivity.class);
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
        if (japanese.contains(" ")) {
            japanese = japanese.substring(0, c.japanese.indexOf(" "));
        }
        first += c.english;
        third = s;
        finalSym += japanese.charAt(japanese.length() - 1);
        if (s.equalsIgnoreCase("presPos")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "り";
                } else {
                    finalSym = "";
                }
            } else if (finalSym.equalsIgnoreCase("う")) {
                finalSym = "い";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "き";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "し";
            } else if (finalSym.equalsIgnoreCase("つ")) {
                finalSym = "ち";
            } else if (finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "に";
            } else if (finalSym.equalsIgnoreCase("ふ")) {
                finalSym = "ひ";
            } else if (finalSym.equalsIgnoreCase("む")) {
                finalSym = "み";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "ぎ";
            } else if (finalSym.equalsIgnoreCase("ず")) {
                finalSym = "じ";
            } else if (finalSym.equalsIgnoreCase("ぶ")) {
                finalSym = "び";
            } else if (finalSym.equalsIgnoreCase("ぷ")) {
                finalSym = "ぴ";
            } else  {

            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym + "ます";
        } else if (s.equalsIgnoreCase("presNeg")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "り";
                } else {
                    finalSym = "";
                }
            } else if (finalSym.equalsIgnoreCase("う")) {
                finalSym = "い";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "き";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "し";
            } else if (finalSym.equalsIgnoreCase("つ")) {
                finalSym = "ち";
            } else if (finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "に";
            } else if (finalSym.equalsIgnoreCase("ふ")) {
                finalSym = "ひ";
            } else if (finalSym.equalsIgnoreCase("む")) {
                finalSym = "み";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "ぎ";
            } else if (finalSym.equalsIgnoreCase("ず")) {
                finalSym = "じ";
            } else if (finalSym.equalsIgnoreCase("ぶ")) {
                finalSym = "び";
            } else if (finalSym.equalsIgnoreCase("ぷ")) {
                finalSym = "ぴ";
            } else  {

            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym + "ません";
        } else if (s.equalsIgnoreCase("pastPos")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "り";
                } else {
                    finalSym = "";
                }
            } else if (finalSym.equalsIgnoreCase("う")) {
                finalSym = "い";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "き";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "し";
            } else if (finalSym.equalsIgnoreCase("つ")) {
                finalSym = "ち";
            } else if (finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "に";
            } else if (finalSym.equalsIgnoreCase("ふ")) {
                finalSym = "ひ";
            } else if (finalSym.equalsIgnoreCase("む")) {
                finalSym = "み";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "ぎ";
            } else if (finalSym.equalsIgnoreCase("ず")) {
                finalSym = "じ";
            } else if (finalSym.equalsIgnoreCase("ぶ")) {
                finalSym = "び";
            } else if (finalSym.equalsIgnoreCase("ぷ")) {
                finalSym = "ぴ";
            } else  {

            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym + "ました";
        } else if (s.equalsIgnoreCase("pastNeg")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "り";
                } else {
                    finalSym = "";
                }
            } else if (finalSym.equalsIgnoreCase("う")) {
                finalSym = "い";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "き";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "し";
            } else if (finalSym.equalsIgnoreCase("つ")) {
                finalSym = "ち";
            } else if (finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "に";
            } else if (finalSym.equalsIgnoreCase("ふ")) {
                finalSym = "ひ";
            } else if (finalSym.equalsIgnoreCase("む")) {
                finalSym = "み";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "ぎ";
            } else if (finalSym.equalsIgnoreCase("ず")) {
                finalSym = "じ";
            } else if (finalSym.equalsIgnoreCase("ぶ")) {
                finalSym = "び";
            } else if (finalSym.equalsIgnoreCase("ぷ")) {
                finalSym = "ぴ";
            } else  {

            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym + "ませんでした";
        } else if (s.equalsIgnoreCase("te")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "って";
                } else {
                    finalSym = "て";
                }
            } else if (finalSym.equalsIgnoreCase("う") || finalSym.equalsIgnoreCase("つ")) {
                finalSym = "って";
            } else if (finalSym.equalsIgnoreCase("む") || finalSym.equalsIgnoreCase("ぶ") || finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "んで";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "いて";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "いで";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "して";
            } else  {
                // iku, suru, and kuru
                if (japanese.equalsIgnoreCase("いく")) {
                    finalSym = "って";
                } else if (japanese.contains("くる")) {
                    japanese = japanese.replace("くる","きて");
                    finalSym = "";
                } else if (japanese.contains("する")) {
                    japanese = japanese.replace("する","して");
                    finalSym = "";
                }
            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym;
        } else if (s.equalsIgnoreCase("permission")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "ってもいいですか";
                } else {
                    finalSym = "てもいいですか";
                }
            } else if (finalSym.equalsIgnoreCase("う") || finalSym.equalsIgnoreCase("つ")) {
                finalSym = "ってもいいですか";
            } else if (finalSym.equalsIgnoreCase("む") || finalSym.equalsIgnoreCase("ぶ") || finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "んでもいいですか";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "いてもいいですか";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "いでもいいですか";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "してもいいですか";
            } else  {
                // iku, suru, and kuru
                if (japanese.equalsIgnoreCase("いく")) {
                    finalSym = "ってもいいですか";
                } else if (japanese.contains("くる")) {
                    japanese = japanese.replace("くる","きて");
                    finalSym = "もいいですか";
                } else if (japanese.contains("する")) {
                    japanese = japanese.replace("する","して");
                    finalSym = "もいいですか";
                }
            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym;
        } else if (s.equalsIgnoreCase("mustnot")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "ってはいけません";
                } else {
                    finalSym = "てはいけません";
                }
            } else if (finalSym.equalsIgnoreCase("う") || finalSym.equalsIgnoreCase("つ")) {
                finalSym = "ってはいけません";
            } else if (finalSym.equalsIgnoreCase("む") || finalSym.equalsIgnoreCase("ぶ") || finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "んではいけません";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "いてはいけません";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "いではいけません";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "してはいけません";
            } else  {
                // iku, suru, and kuru
                if (japanese.equalsIgnoreCase("いく")) {
                    finalSym = "ってはいけません";
                } else if (japanese.contains("くる")) {
                    japanese = japanese.replace("くる","きて");
                    finalSym = "はいけません";
                } else if (japanese.contains("する")) {
                    japanese = japanese.replace("する","して");
                    finalSym = "はいけません";
                }
            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym;
        } else if (s.equalsIgnoreCase("ing")) {
            if (finalSym.equalsIgnoreCase("る")) {
                if (c.tags.contains("u")) {
                    finalSym = "っています";
                } else {
                    finalSym = "ています";
                }
            } else if (finalSym.equalsIgnoreCase("う") || finalSym.equalsIgnoreCase("つ")) {
                finalSym = "っています";
            } else if (finalSym.equalsIgnoreCase("む") || finalSym.equalsIgnoreCase("ぶ") || finalSym.equalsIgnoreCase("ぬ")) {
                finalSym = "んでいます";
            } else if (finalSym.equalsIgnoreCase("く")) {
                finalSym = "いています";
            } else if (finalSym.equalsIgnoreCase("ぐ")) {
                finalSym = "いでいます";
            } else if (finalSym.equalsIgnoreCase("す")) {
                finalSym = "しています";
            } else  {
                // iku, suru, and kuru
                if (japanese.equalsIgnoreCase("いく")) {
                    finalSym = "っています";
                } else if (japanese.contains("くる")) {
                    japanese = japanese.replace("くる","きて");
                    finalSym = "います";
                } else if (japanese.contains("する")) {
                    japanese = japanese.replace("する","して");
                    finalSym = "います";
                }
            }
            second = japanese.substring(0, japanese.length() - 1) + finalSym;
        } else {

        }

        return new Card(first, second, third);
    }
}

package doryphoros.me.japanesestudy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import doryphoros.me.japanesestudy.R;
import doryphoros.me.japanesestudy.models.Card;

/**
 * Created by Link on 2/10/2016.
 */
public class StudyActivity extends Activity {

    private int currentCard;
    private String firstSide;
    private String secondSide;
    private int length;
    private boolean flipped; // false
    private ArrayList<Card> studyCards;
    private ArrayList<Card> starred;
    TextView tv, cc, con;
    Button flipButton, previousButton, nextButton;
    ImageView star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        this.firstSide = SelectActivity.firstSide;
        this.secondSide = SelectActivity.secondSide;
        this.flipped = false;
        tv = (TextView) findViewById(R.id.card_text);
        con = (TextView) findViewById(R.id.card_text_con);
        cc = (TextView) findViewById(R.id.card_count);
        flipButton = (Button) findViewById(R.id.flip_button);
        previousButton = (Button) findViewById(R.id.previous_button);
        nextButton = (Button) findViewById(R.id.next_button);
        star = (ImageView) findViewById(R.id.star);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star.setImageResource(R.drawable.ic_star_black_24dp);
                starred.add(studyCards.get(currentCard));
            }
        });
        studyCards = SelectActivity.selectedCards;
        reshuffle(false);
    }

    private void advanceCard(boolean forward) {
        this.flipped = false;
        if (forward) {
            ++this.currentCard;
        } else {
            --this.currentCard;
        }
        if (this.currentCard < 0) {
            this.currentCard = 0;
        }
        if (this.currentCard > this.length) {
            this.currentCard = this.length - 1;
        }
        if (this.currentCard == this.length) {
            atEnd();
        } else {
            inProgress();
        }

    }

    private void atEnd() {
        tv.setText("Done!");
        previousButton.setText("Return");
        flipButton.setText("Reshuffle");
        nextButton.setText("Study Selected");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reshuffle(true);
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reshuffle(false);
            }
        });
    }

    private void inProgress() {
        tv.setText(studyCards.get(currentCard).hm.get(this.firstSide));
        cc.setText("Card " + (currentCard + 1) + "/" + length);
        con.setText(studyCards.get(currentCard).hm.get("con"));
        previousButton.setText("Previous");
        flipButton.setText("Flip");
        nextButton.setText("Next");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanceCard(true);
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanceCard(false);
            }
        });
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flip();
            }
        });
        star.setImageResource(R.drawable.ic_star_border_black_24dp);
    }

    private void reshuffle(Boolean remove) {
        this.currentCard = -1;
        if (remove && starred.size() > 0) {
            // only use starred cards, unless none have been selected
            studyCards = starred;
        }
        // reset starred
        starred = new ArrayList<>();
        SelectActivity.shuffleSelectedCards();
        this.length = studyCards.size();
        advanceCard(true);
    }

    private void flip() {
        this.flipped = !this.flipped;
        if (this.flipped) {
            tv.setText(studyCards.get(currentCard).hm.get(this.secondSide));
        } else {
            tv.setText(studyCards.get(currentCard).hm.get(this.firstSide));
        }
    }

    private void verbConjugate(Card c, String s) {
        // select string
        // select verb type
    }

}

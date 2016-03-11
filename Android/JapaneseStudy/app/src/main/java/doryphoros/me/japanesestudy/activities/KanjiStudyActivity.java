package doryphoros.me.japanesestudy.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

import doryphoros.me.japanesestudy.R;
import doryphoros.me.japanesestudy.models.Kanji;
import doryphoros.me.japanesestudy.utils.DrawView;

/**
 * Created by Link on 2/10/2016.
 */
public class KanjiStudyActivity extends Activity {

    private int currentCard;
    private String zeroSide;
    private String firstSide;
    private String secondSide;
    private String thirdSide;
    private int length;
    private boolean flipped; // false
    private ArrayList<Kanji> studyCards;
    private ArrayList<Kanji> starred;
    TextView tv, cc, con, stillKanji, smallText;
    Button flipButton, previousButton, nextButton;
    ImageView star, userKanji;
    boolean read;
    VideoView mVideoView;
    android.widget.MediaController mc;
    DrawView kanjiDraw;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_study);
        mVideoView  = (VideoView)findViewById(R.id.video_view);
        kanjiDraw = (DrawView)findViewById(R.id.kanji_draw);
        userKanji = (ImageView)findViewById(R.id.user_kanji);
        mc = new android.widget.MediaController(this);
        mc.hide();
        mVideoView.setMediaController(mc);

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                System.out.println("onError");
                return false;
            }
        });
        stillKanji = (TextView) findViewById(R.id.still_kanji);
        smallText = (TextView) findViewById(R.id.card_text_small);
        this.read = SelectActivity.read;
        if (this.read) {
            this.zeroSide = "";
            this.firstSide = "still";
            this.secondSide = "english";
            this.thirdSide = "japanese";
            mVideoView.setVisibility(View.INVISIBLE);
            kanjiDraw.setVisibility(View.INVISIBLE);
            userKanji.setVisibility(View.INVISIBLE);
            stillKanji.setVisibility(View.INVISIBLE);
            smallText.setVisibility(View.INVISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            this.zeroSide = "japanese";
            this.firstSide = "english";
            this.secondSide = "still";
            this.thirdSide = "";
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        this.flipped = false;
        tv = (TextView) findViewById(R.id.card_text);
        if (!this.read) {
            tv = (TextView) findViewById(R.id.card_text_small);
        }
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
        studyCards = SelectActivity.selectedKanji;
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
        con.setText(studyCards.get(currentCard).hm.get(this.zeroSide));
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
        if (!this.read) {
            stillKanji.setText("");
            kanjiDraw.setVisibility(View.INVISIBLE);
            kanjiDraw.setVisibility(View.VISIBLE);
            kanjiDraw.clear();
            userKanji.setVisibility(View.INVISIBLE);
            mVideoView.setVisibility(View.INVISIBLE);
        }

    }

    private void reshuffle(Boolean remove) {
        this.currentCard = -1;
        if (remove && starred.size() > 0) {
            // only use starred cards, unless none have been selected
            studyCards = starred;
        }
        // reset starred
        starred = new ArrayList<>();
        SelectActivity.shuffleSelectedKanji();
        this.length = studyCards.size();
        advanceCard(true);
    }

    private void flip() {
        this.flipped = !this.flipped;
        if (!this.read) {
            mVideoView.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + getResources().getIdentifier(studyCards.get(currentCard).motion, "raw", getPackageName()));
            mVideoView.setVideoURI(uri);
            mVideoView.requestFocus();
            mVideoView.start();
            stillKanji.setText(studyCards.get(currentCard).still);
            Bitmap b = Bitmap.createBitmap(kanjiDraw.getWidth(), kanjiDraw.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            kanjiDraw.draw(c);
            userKanji.setVisibility(View.VISIBLE);
            userKanji.setImageBitmap(b);
            kanjiDraw.setVisibility(View.INVISIBLE);
        } else {
            if (this.flipped) {
                tv.setText(studyCards.get(currentCard).hm.get(this.secondSide));
                con.setText(studyCards.get(currentCard).hm.get(this.thirdSide));
            } else {
                tv.setText(studyCards.get(currentCard).hm.get(this.firstSide));
                con.setText(studyCards.get(currentCard).hm.get(this.zeroSide));
            }
        }

    }

}

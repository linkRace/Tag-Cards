package doryphoros.me.japanesestudy.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import doryphoros.me.japanesestudy.ButtonAdapter;
import doryphoros.me.japanesestudy.R;
import doryphoros.me.japanesestudy.constants.JPConstants;
import doryphoros.me.japanesestudy.models.Card;
import doryphoros.me.japanesestudy.models.Kanji;
import doryphoros.me.japanesestudy.models.Tag;
import doryphoros.me.japanesestudy.utils.JPUtils;

public class SelectActivity extends AppCompatActivity {

    public static ArrayList<Tag> tags;
    public static ArrayList<String> selectedTags;
    public static ArrayList<String> badTags;
    public static ArrayList<Tag> verbTags;
    public static ArrayList<Tag> adjTags;
    public static ArrayList<String> selectedConTags;
    public static ArrayList<String> badConTags;
    public static List<Card> cards;
    public static List<Kanji> kanji;
    public static ArrayList<Kanji> selectedKanji;
    public static ArrayList<Tag> kanjiTags;
    public static ArrayList<String> selectedKanjiTags;
    public static ArrayList<String> badKanjiTags;
    public static ArrayList<Card> selectedCards;
    public static String firstSide, secondSide;
    public static boolean read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button cardsButton = (Button) findViewById(R.id.cards_button);
        read = true;
        cardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button verbsButton = (Button) findViewById(R.id.verb_button);
        verbsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, SelectVerbActivity.class);
                startActivity(intent);
            }
        });

        Button adjButton = (Button) findViewById(R.id.adjective_button);
        adjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, SelectAdjectiveActivity.class);
                startActivity(intent);
            }
        });

        Button kanjButton = (Button) findViewById(R.id.kanji_button);
        kanjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, SelectKanjiActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getJSON();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        downloadJSON();
        return false;
    }

    private void getJSON() {
        File file = getBaseContext().getFileStreamPath(JPConstants.CARD_DATA_FILENAME);
        JsonElement jsonElement = null;
        if (file.exists()) {
            jsonElement = JPUtils.getJsonFromFile(this, JPConstants.CARD_DATA_FILENAME);
            Log.d("MainACTIVITY", "read from file: " + jsonElement.toString());
            Type listType = new TypeToken<List<Card>>(){}.getType();
                cards = (List<Card>) new Gson().fromJson(jsonElement, listType);
            for (Card c : cards) {
                c.createHash();
            }
            selectedCards = new ArrayList<Card>();
            initTags();
        } else {
            //error
        }
        file = getBaseContext().getFileStreamPath(JPConstants.KANJI_DATA_FILENAME);
        jsonElement = null;
        if (file.exists()) {
            jsonElement = JPUtils.getJsonFromFile(this, JPConstants.KANJI_DATA_FILENAME);
            Log.d("MainACTIVITY", "read from file: " + jsonElement.toString());
            Type listType = new TypeToken<List<Kanji>>(){}.getType();
            kanji = (List<Kanji>) new Gson().fromJson(jsonElement, listType);
            for (Kanji k : kanji) {
                k.createHash();
            }
            selectedKanji = new ArrayList<Kanji>();
            initKanjiTags();
        } else {
            //error
        }
        Log.d("SelectActivity", "Done with getJSON");
    }

    private void downloadJSON() {
        JPUtils.getDataFromApi(this, new JPUtils.DataLoaderCallback() {
            @Override
            public void onDataLoaded() {
                getJSON();
            }
        });
    }

    public void initTags() {
        ArrayList<String> verbcons, adjcons;
        String [] verbar = getResources().getStringArray(R.array.verbConjugation);
        verbcons = new ArrayList<String>(Arrays.asList(verbar));
        String [] adjar = getResources().getStringArray(R.array.adjConjugation);
        adjcons = new ArrayList<String>(Arrays.asList(adjar));
        adjTags = new ArrayList<Tag>();
        verbTags = new ArrayList<Tag>();
        HashMap<String, Integer> hs = new HashMap<String, Integer>();
        for (String s : verbcons) {
            if (!hs.containsKey(s)) {
                hs.put(s, 1);
                verbTags.add(new Tag(0, s));
            }
        }
        hs = new HashMap<>();
        for (String s : adjcons) {
            if (!hs.containsKey(s)) {
                hs.put(s, 1);
                adjTags.add(new Tag(0, s));
            }
        }
        Collections.sort(verbTags, new Comparator<Tag>() {
            @Override
            public int compare(Tag s1, Tag s2) {
                return s1.value.compareToIgnoreCase(s2.value);
            }
        });
        Collections.sort(adjTags, new Comparator<Tag>() {
            @Override
            public int compare(Tag s1, Tag s2) {
                return s1.value.compareToIgnoreCase(s2.value);
            }
        });
        selectedConTags = new ArrayList<String>();
        badConTags = new ArrayList<String>();
        tags = new ArrayList<Tag>();
        selectedTags = new ArrayList<String>();
        badTags = new ArrayList<String>();
        hs = new HashMap<String, Integer>();
        for (Card c : cards) {
            for (String s : c.tags) {
                if (!hs.containsKey(s)) {
                    hs.put(s, 1);
                    tags.add(new Tag(0, s));
                }
            }
        }
        Collections.sort(tags, new Comparator<Tag>() {
            @Override
            public int compare(Tag s1, Tag s2) {
                return s1.value.compareToIgnoreCase(s2.value);
            }
        });
    }

    public void initKanjiTags() {
        selectedKanjiTags = new ArrayList<String>();
        badKanjiTags = new ArrayList<String>();
        kanjiTags = new ArrayList<Tag>();
        HashMap<String,Integer> hs = new HashMap<String, Integer>();
        for (Kanji c : kanji) {
            for (String s : c.tags) {
                if (!hs.containsKey(s)) {
                    hs.put(s, 1);
                    kanjiTags.add(new Tag(0, s));
                }
            }
        }
        Collections.sort(kanjiTags, new Comparator<Tag>() {
            @Override
            public int compare(Tag s1, Tag s2) {
                return s1.value.compareToIgnoreCase(s2.value);
            }
        });
        Log.d("SelectActivity", "kanji:" + kanji);
    }

    public static void updateSelectedCards() {
        selectedCards = new ArrayList<>();
        int stLength = selectedTags.size(), st = 0, matched;
        // add cards that match all tags
        for (int c = 0, length = cards.size(); c < length; ++c) {
            matched = 0;
            for (st = 0; st < stLength; ++st) {
                if (matched == st) {
                    for (int t = 0, length2 = cards.get(c).tags.size(); t < length2; ++t) {
                        if (selectedTags.get(st).equalsIgnoreCase(cards.get(c).tags.get(t))) {
                            ++matched;
                            break;
                        }
                    }
                }
            }
            if (matched == stLength) {
                selectedCards.add(cards.get(c));
            }
        }
        // remove cards with bad tags
        for (int s = 0, length = selectedCards.size(); s < length; ++s) {
            for (st = 0, stLength = badTags.size(); st < stLength; ++st){
                if (selectedCards.get(s).tags.contains(badTags.get(st))) {
                    selectedCards.remove(s);
                    --s;
                    --length;
                }
            }
        }
    }

    public static void updateSelectedKanji() {
        selectedKanji = new ArrayList<>();
        int stLength = selectedKanjiTags.size(), st = 0, matched;
        // add cards that match all tags
        for (int c = 0, length = kanji.size(); c < length; ++c) {
            matched = 0;
            for (st = 0; st < stLength; ++st) {
                if (matched == st) {
                    for (int t = 0, length2 = cards.get(c).tags.size(); t < length2; ++t) {
                        if (selectedKanjiTags.get(st).equalsIgnoreCase(kanji.get(c).tags.get(t))) {
                            ++matched;
                            break;
                        }
                    }
                }
            }
            if (matched == stLength) {
                selectedKanji.add(kanji.get(c));
            }
        }
        // remove cards with bad tags
        for (int s = 0, length = selectedKanji.size(); s < length; ++s) {
            for (st = 0, stLength = badKanjiTags.size(); st < stLength; ++st){
                if (selectedKanji.get(s).tags.contains(badKanjiTags.get(st))) {
                    selectedKanji.remove(s);
                    --s;
                    --length;
                }
            }
        }
    }

    public static void shuffleSelectedCards() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = SelectActivity.selectedCards.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = SelectActivity.selectedCards.get(index);
            SelectActivity.selectedCards.set(index, SelectActivity.selectedCards.get(i));
            SelectActivity.selectedCards.set(i, a);
        }
    }

    public static void shuffleSelectedKanji() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = SelectActivity.selectedKanji.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Kanji a = SelectActivity.selectedKanji.get(index);
            SelectActivity.selectedKanji.set(index, SelectActivity.selectedKanji.get(i));
            SelectActivity.selectedKanji.set(i, a);
        }
    }
}

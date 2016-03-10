package doryphoros.me.japanesestudy;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import doryphoros.me.japanesestudy.activities.MainActivity;
import doryphoros.me.japanesestudy.activities.SelectActivity;
import doryphoros.me.japanesestudy.models.Card;
import doryphoros.me.japanesestudy.models.Tag;

/**
 * Created by Link on 2/10/2016.
 */
public class ButtonAdapter extends ArrayAdapter<String> {

    private String con;

    public ButtonAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ButtonAdapter(Context context, int resource, List<String> strings, String con) {
        super(context, resource, strings);
        this.con = con;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v==null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.tag_button, null);
        }

        String s = getItem(position);
        if (s != null) {
            ((Button)v).setText(s);
        }

        v.setBackgroundColor(Color.GRAY);
        ((Button)v).setTextColor(Color.WHITE);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String text = clickedButton.getText().toString();
                if (con.equalsIgnoreCase("kanji")) {
                    ArrayList<Tag> searchThrough = SelectActivity.kanjiTags;
                    for (Tag t : searchThrough) {
                        if (t.value.equalsIgnoreCase(text)) {
                            ++t.checked;
                            if (t.checked > 2) {
                                t.checked = 0;
                            }
                            if (t.checked == 1) {
                                SelectActivity.selectedKanjiTags.add(t.value);
                                clickedButton.setBackgroundColor(Color.CYAN);
                            } else if (t.checked == 2) {
                                SelectActivity.selectedKanjiTags.remove(t.value);
                                SelectActivity.badKanjiTags.add(t.value);
                                clickedButton.setBackgroundColor(Color.RED);
                            } else {
                                SelectActivity.badKanjiTags.remove(t.value);
                                clickedButton.setBackgroundColor(Color.GRAY);
                            }
                            break;
                        }
                    }
                } else if (!con.equalsIgnoreCase("default")) {
                    ArrayList<Tag> searchThrough;
                    if (con.equalsIgnoreCase("verb")) {
                        searchThrough = SelectActivity.verbTags;
                    } else if (con.equalsIgnoreCase("adj")) {
                        searchThrough = SelectActivity.adjTags;
                    } else {
                        searchThrough = new ArrayList<Tag>();
                    }
                    for (Tag t : searchThrough) {
                        if (t.value.equalsIgnoreCase(text)) {
                            ++t.checked;
                            if (t.checked > 2) {
                                t.checked = 0;
                            }
                            if (t.checked == 1) {
                                SelectActivity.selectedConTags.add(t.value);
                                clickedButton.setBackgroundColor(Color.CYAN);
                            } else if (t.checked == 2) {
                                SelectActivity.selectedConTags.remove(t.value);
                                SelectActivity.badConTags.add(t.value);
                                clickedButton.setBackgroundColor(Color.RED);
                            } else {
                                SelectActivity.badConTags.remove(t.value);
                                clickedButton.setBackgroundColor(Color.GRAY);
                            }
                            break;
                        }
                    }
                } else {
                    //toggle button color
                    for (Tag t : SelectActivity.tags) {
                        if (t.value.equalsIgnoreCase(text)) {
                            ++t.checked;
                            if (t.checked > 2) {
                                t.checked = 0;
                            }
                            if (t.checked == 1) {
                                SelectActivity.selectedTags.add(t.value);
                                clickedButton.setBackgroundColor(Color.CYAN);
                            } else if (t.checked == 2) {
                                SelectActivity.selectedTags.remove(t.value);
                                SelectActivity.badTags.add(t.value);
                                clickedButton.setBackgroundColor(Color.RED);
                            } else {
                                SelectActivity.badTags.remove(t.value);
                                clickedButton.setBackgroundColor(Color.GRAY);
                            }
                            break;
                        }
                    }
                }
            }
        });

        return v;
    }
}

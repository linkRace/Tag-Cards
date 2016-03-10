package doryphoros.me.japanesestudy.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Link on 2/10/2016.
 */
public class Card implements Parcelable {
    public int id;
    public String english;
    public String engrish;
    public String japanese;
    public String kanji;
    public String con;
    public ArrayList<String> tags;
    public HashMap<String, String> hm;

    protected Card(Parcel in) {
        id = in.readInt();
        english = in.readString();
        engrish = in.readString();
        japanese = in.readString();
        kanji = in.readString();
        if (in.readByte() == 0x01) {
            tags = new ArrayList<String>();
            in.readList(tags, String.class.getClassLoader());
        } else {
            tags = null;
        }
    }

    public Card(String first, String second, String third) {
        english = first;
        japanese = second;
        con = third;
        createHash();
    }

    public void createHash() {
        hm = new HashMap<>();
        hm.put("english", english);
        hm.put("engrish", engrish);
        hm.put("japanese", japanese);
        hm.put("con", con);
        hm.put("kanji", kanji);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(english);
        dest.writeString(engrish);
        dest.writeString(japanese);
        dest.writeString(kanji);
        if (tags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tags);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}

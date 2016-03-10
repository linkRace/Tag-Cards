package doryphoros.me.japanesestudy.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Link on 2/10/2016.
 */
public class Kanji implements Parcelable {
    public int id;
    public String english;
    public ArrayList<String> examples;
    public ArrayList<String> japanese;
    public String motion;
    public String still;
    public ArrayList<String> tags;
    public HashMap<String, String> hm;

    protected Kanji(Parcel in) {
        id = in.readInt();
        english = in.readString();
        still = in.readString();
        motion = in.readString();
        if (in.readByte() == 0x01) {
            tags = new ArrayList<String>();
            in.readList(tags, String.class.getClassLoader());
        } else {
            tags = null;
        }
        if (in.readByte() == 0x01) {
            japanese = new ArrayList<String>();
            in.readList(japanese, String.class.getClassLoader());
        } else {
            japanese = null;
        }
        if (in.readByte() == 0x01) {
            examples = new ArrayList<String>();
            in.readList(examples, String.class.getClassLoader());
        } else {
            examples = null;
        }
    }


    public void createHash() {
        hm = new HashMap<>();
        hm.put("english", english);
        hm.put("still", still);
        hm.put("motion", motion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(english);
        dest.writeString(still);
        dest.writeString(motion);
        if (tags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tags);
        }
        if (japanese == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(japanese);
        }
        if (examples == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(examples);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Kanji> CREATOR = new Parcelable.Creator<Kanji>() {
        @Override
        public Kanji createFromParcel(Parcel in) {
            return new Kanji(in);
        }

        @Override
        public Kanji[] newArray(int size) {
            return new Kanji[size];
        }
    };
}

package com.arthenica.ffmpegkit;

import com.arthenica.smartexception.java.Exceptions;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaInformationJsonParser {
    public static final String KEY_CHAPTERS = "chapters";
    public static final String KEY_STREAMS = "streams";

    public static MediaInformation from(String str) {
        try {
            return fromWithError(str);
        } catch (JSONException e) {
            android.util.Log.e("ffmpeg-kit", String.format("MediaInformation parsing failed.%s", Exceptions.getStackTraceString(e)));
            return null;
        }
    }

    public static MediaInformation fromWithError(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(KEY_STREAMS);
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(KEY_CHAPTERS);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; jSONArrayOptJSONArray != null && i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                arrayList.add(new StreamInformation(jSONObjectOptJSONObject));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; jSONArrayOptJSONArray2 != null && i2 < jSONArrayOptJSONArray2.length(); i2++) {
            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
            if (jSONObjectOptJSONObject2 != null) {
                arrayList2.add(new Chapter(jSONObjectOptJSONObject2));
            }
        }
        return new MediaInformation(jSONObject, arrayList, arrayList2);
    }
}

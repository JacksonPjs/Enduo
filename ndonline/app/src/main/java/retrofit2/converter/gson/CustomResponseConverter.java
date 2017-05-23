package retrofit2.converter.gson;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/5/23.
 */

public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String body = value.string();
            Log.e("body",body);

            JSONObject json = new JSONObject(body);

            int    ret = json.optInt("ret");
            String msg = json.optString("msg", "");

            if (ret == 0) {
                if (json.has("data")) {
                    Object data = json.get("data");

                    body = data.toString();

                    return adapter.fromJson(body);
                } else {
                    return (T) msg;
                }
            } else {
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}

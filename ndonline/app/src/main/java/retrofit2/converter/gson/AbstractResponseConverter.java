package retrofit2.converter.gson;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/5/23.
 */

public abstract class AbstractResponseConverter<T> implements Converter<ResponseBody, T> {

    protected Gson gson;

    public AbstractResponseConverter(Gson gson) {
        this.gson = gson;
    }
}
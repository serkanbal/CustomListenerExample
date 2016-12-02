package net.serkanbal.customlistenerexample;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Serkan on 01/12/16.
 */

public class ChildObject {
    private MyCustomObjectListener listener;
    private Activity mActivity;

    public ChildObject(Activity activity) {
        this.listener = null;
        mActivity = activity;
        makeASimpleAPICall();
    }

    public interface MyCustomObjectListener {
        public void onObjectReady(String title);

    }

    public void setCustomObjectListener(MyCustomObjectListener listener) {
        this.listener = listener;
    }

    public void makeASimpleAPICall() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url("http://www.omdbapi.com/?i=tt0092099")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Do naffin'
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Failed " + request);
                } else {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        final String title = object.getString("Title");

                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (listener != null) {
                                    listener.onObjectReady(title);
                                }
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}

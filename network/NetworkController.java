package com.example.lastone.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Created by AndroiderStack.
 * <p>
 * Singleton Network Controller class
 */
public class NetworkController {

    private final String TAG = "NetworkController";

    boolean showDialog = true;
    private ProgressDialog progressDialog;

    private static NetworkController controller = new NetworkController();

    public static NetworkController getInstance() {
        return controller;
    }


    /**
     * Call this method if you want to show/hide loader
     *
     * @param showDialog default value = true
     */
    public void showDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }


    /**
     * This method is responsible to communicate with server
     *
     * @param context        current class context
     * @param method         GET or POST
     * @param requestCode    to identify request
     * @param resultListener to get callback for response
     * @param stringParams   can be null if method is GET
     */
    public void connect(Context context, final int requestCode, String url, int method, HashMap<String, String> stringParams, final ResultListener resultListener) {
        try {
            if (CheckNetworkState.isOnline(context)) {
                if (showDialog)
                    showDialog(context);

                //String url = getUrl(requestCode);

                NetworkRequest networkRequest = new NetworkRequest(context, url, method, stringParams, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonObject) {
                        Log.e(TAG, "onResponse() called");
                        resultListener.onResult(requestCode, true, jsonObject, null, progressDialog);
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "onErrorResponse() called");
                                resultListener.onResult(requestCode, false, null, error, progressDialog);
                                error.printStackTrace();
                            }
                        });

                networkRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(context).add(networkRequest);
            } else {
                Toast.makeText(context, "Network not available!", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception ex) {
            Toast.makeText(context, "catch in connect" + ex.getMessage(), Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    /**
     * To get url of requested code
     *
     *
     */
//    private String getUrl(int requestCode) {
//        String url = "";
//        switch (requestCode)
//        {
//            case UrlConstants.GET_URL_REQUEST_CODE:
//                url = UrlConstants.GET_URL;
//                break;
//            case UrlConstants.POST_URL_REQUEST_CODE:
//                url = UrlConstants.POST_URL;
//                break;
//        }
//        return url;
//    }
    private void showDialog(Context context) {
       /* try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            //progressDialog.setMessage(context.getString(qa.pp.dmr.dmr.R.string.loadingMsg));
            progressDialog.setMessage("Loading");
            progressDialog.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    public interface ResultListener {

        void onResult(int requestCode, boolean isSuccess, String jsonObject, VolleyError volleyError, ProgressDialog progressDialog);

    }
    BufferedReader in = null;
    String res = null;
    public  String executeHttpPost(  final String url) throws InterruptedException  {
        StringBuilder content = new StringBuilder();
        try {
            URL u1 = new URL(url);
            HttpURLConnection uc1 = (HttpURLConnection) u1.openConnection();
            if (uc1.getResponseCode()==HttpURLConnection.HTTP_OK) {

                InputStream is = uc1.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String line;
                while ((line = br.readLine()) != null) {

                    content.append(line).append("\n");

                }

            }//other codes

        } catch (IOException io) {
        }

        return res;
    }



}


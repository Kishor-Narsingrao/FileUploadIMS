package com.ronivi.ims;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTask_WebAPI extends AsyncTask<String,Void,String>
{
    public Context context;
    ProgressDialog dialog;
    JSONObject postJson;
    String strURL;

    IResultCallBack IResultCallBack;

    public AsyncTask_WebAPI(Context context, String strURL, IResultCallBack IResultCallBack)
    {
        this.context = context;
        this.strURL=strURL;
        this.IResultCallBack = IResultCallBack;
    }

    @Override
    protected void onPreExecute() {
        dialog=new ProgressDialog(context);
        dialog.setMessage("Please Wait...");
        dialog.show();
        dialog.setCancelable(false);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String result =null;

        InputStream inputStream = null;

        try {

//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost(strURL);

//            String json = "";

//            Log.v("JSON", json);

//            StringEntity se = new StringEntity(json);
//            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
//            httppost.setEntity(se);

//            httppost.setHeader("Accept", "application/json");
//            httppost.setHeader("Content-type", "application/json");

//            HttpResponse httpResponse = httpclient.execute(httppost);

//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            URL url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            inputStream =conn.getInputStream();/* httpResponse.getEntity().getContent();*/

            if (inputStream != null) {

                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line = "";

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
            } else {
                Log.v("result", "Did not work !");
            }

        }
        catch(Exception e)
        {
            //result="false";
            Log.d("inputStream", e.getLocalizedMessage());
            Toast.makeText(context,"Unable to connect to server", Toast.LENGTH_LONG).show();
        }
//        }
        return result;
    }

    @Override
    protected void onPostExecute(String jsonObject) {
        super.onPostExecute(jsonObject);
        if(dialog.isShowing())
        {
        dialog.dismiss();}
        IResultCallBack.onResultListener(jsonObject);
    }
}

package com.ordrit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ServerConnection {
	// http://localhost:8080/RESTfulExample/json/product/post

	public String getHttpUrlConnection(String requestUrl,String token)
			{
		String jsonString = null; 
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(requestUrl);
		get.setHeader("Authorization", "Token "+token );
	     try {
		HttpResponse response = httpClient.execute(get);
		jsonString = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonString;
	}
public String getHttpUrlConnectionForArray(String requestUrl,String token)
 {
	String jsonString = null; 
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(requestUrl);
		get.setHeader("Authorization", "Token " + token);
		try {
			HttpResponse response = httpClient.execute(get);
			jsonString =
					EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonString;
	}
	/**
	 * Method takes the data for post call and the request url.
	 * 
	 * @param postInput
	 * @param requestUrl
	 * @return JSONObject
	 */
	public String postHttpUrlConnection(String postInput, String requestUrl) {
		String jsonString = null; 
		// Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(requestUrl);
  
        // Making HTTP Request
        try {
        	 httpPost.setEntity(new StringEntity(postInput));
        	 httpPost.setHeader("Accept", "application/json");
             httpPost.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(httpPost);
            jsonString = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
 
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonString;
  }
	public String postHttpUrlConnection(String postInput, String requestUrl,String token) {
		String jsonString = null; 
		// Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(requestUrl);
  
        // Making HTTP Request
        try {
        	 httpPost.setEntity(new StringEntity(postInput));
        	 httpPost.setHeader("Accept", "application/json");
             httpPost.setHeader("Content-type", "application/json");
             httpPost.setHeader("Authorization", "Token "+token );
      	   
            HttpResponse response = httpClient.execute(httpPost);
            jsonString =EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
 
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return jsonString;
  }
	public String patchHttpUrlConnection(String postInput, String requestUrl,String token) {
		String jsonString = null; 
		// Creating HTTP client
		
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        OrderItHttpPatch httpPost = new OrderItHttpPatch(requestUrl);
       
        // Making HTTP Request
        try {
        	 httpPost.setEntity(new StringEntity(postInput));
        	 httpPost.setHeader("Accept", "application/json");
             httpPost.setHeader("Content-type", "application/json");
             httpPost.setHeader("Authorization", "Token "+token );
      	   
            HttpResponse response = httpClient.execute(httpPost);
            jsonString =EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
 
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return jsonString;
  }
	/**
	 * Method returns String from input stream.
	 * 
	 * @param is
	 * @return jsonString
	 */
	public String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
	public class OrderItHttpPatch extends HttpEntityEnclosingRequestBase {

	    public final static String METHOD_NAME = "PATCH";

	    public OrderItHttpPatch() {
	        super();
	    }

	    public OrderItHttpPatch(final URI uri) {
	        super();
	        setURI(uri);
	    }

	    public OrderItHttpPatch(final String uri) {
	        super();
	        setURI(URI.create(uri));
	    }

	    @Override
	    public String getMethod() {
	        return METHOD_NAME;
	    }

	}
	public static boolean isNetworkAvailable(Activity argActivity) {
		if (argActivity == null) {
			return false;
		}

		ConnectivityManager connectivityManager;
		NetworkInfo activeNetworkInfo = null;
		try {
			connectivityManager = (ConnectivityManager) argActivity
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return activeNetworkInfo != null;
	}
}

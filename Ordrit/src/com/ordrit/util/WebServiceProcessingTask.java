package com.ordrit.util;

import com.ordrit.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public abstract class WebServiceProcessingTask extends AsyncTask<Void, Void, Void>{

	protected String jSONString=null;
	protected ServerConnection connection;
	protected String TAG="WebServiceProcessingTask";
	private ProgressDialog progressDialog;
	protected Activity mActivity;
	
	
	public WebServiceProcessingTask(Activity mActivity) {
		super();
		this.mActivity=mActivity;
	}

	@Override
	protected void onPreExecute() {
		preExecuteTask();
		// Check Internet access
				if (!ServerConnection.isNetworkAvailable(mActivity)) {
					Toast.makeText(
							mActivity,
							mActivity.getString(
									R.string.internet_connection_failed), Toast.LENGTH_LONG)
							.show();
				    cancel(true);
					return;
				}
			progressDialog=new ProgressDialog(mActivity);
		if (progressDialog!=null) {
			progressDialog.setMessage("Loading...");
			progressDialog.show();
			progressDialog.setCancelable(false);
			
		}
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		postExecuteTask();
		if (progressDialog!=null) {
			progressDialog.dismiss();
		}
		super.onPostExecute(result);
	}

	@Override
	protected Void doInBackground(Void... params) {
	    connection = new ServerConnection();
		backgroundTask();
	//	Log.e(TAG, ""+jSONObject);
		return null;
	}
public abstract void preExecuteTask();
public abstract void postExecuteTask();
public abstract void backgroundTask();
}

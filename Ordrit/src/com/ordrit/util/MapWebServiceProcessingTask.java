package com.ordrit.util;

import com.ordrit.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public abstract class MapWebServiceProcessingTask extends AsyncTask<Void, Void, Void>{

	protected String jSONString=null;
	protected ServerConnection connection;
	protected String TAG="WebServiceProcessingTask";
	private Dialog progressDialog;
	protected Activity mActivity;
	
	
	public MapWebServiceProcessingTask(Activity mActivity) {
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
			progressDialog=new Dialog(mActivity);
		if (progressDialog!=null) {
		
			progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					        Window window = progressDialog.getWindow();
					window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					//window.clearFlags(LayoutParams.FLAG_DIM_BEHIND);
					WindowManager.LayoutParams wlp = window.getAttributes();
					     wlp.gravity = Gravity.CENTER;
					     window.setAttributes(wlp);
			progressDialog
					.setContentView(R.layout.custom_prograssbar);
			ImageView imageView=(ImageView)progressDialog.findViewById(R.id.imageView1);
			imageView.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.rotate));
			
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

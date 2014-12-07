package fi.aalto.mcc_group_27_android.service;

import android.os.AsyncTask;

public abstract class ContactServiceAsyncTask<T> extends AsyncTask<Void, Void, T> {
	
	private ContactServiceCallbackListener<T> listener;
	
	public ContactServiceAsyncTask() {
		
	}

	public ContactServiceAsyncTask(ContactServiceCallbackListener<T> listener) {
		this.listener = listener;
	}
	
    @Override
    protected void onPostExecute(T result) {
    	if(listener != null) {
            listener.callback(result);    		
    	}
    }

}

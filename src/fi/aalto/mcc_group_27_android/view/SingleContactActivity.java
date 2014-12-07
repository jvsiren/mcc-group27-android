package fi.aalto.mcc_group_27_android.view;

import fi.aalto.mcc_group_27_android.R;
import fi.aalto.mcc_group_27_android.model.Contact;
import fi.aalto.mcc_group_27_android.service.ContactService;
import fi.aalto.mcc_group_27_android.service.ContactServiceCallbackListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public abstract class SingleContactActivity extends Activity {
	
	protected ContactService contactService = ContactService.getInstance();
	protected Contact contact;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getViewId());
		initData();	    
	}
		
	protected void initData() {
	    String contactId = getIntent().getStringExtra(ContactListActivity.SELECTED_CONTACT_ID);	    
	    contactService.findById(contactId, new ContactServiceCallbackListener<Contact>() {			
			@Override
			public void callback(Contact contact) {
				SingleContactActivity.this.contact = contact;
				initView();
			}
		});
	}
	
	protected void initView() {
		setTextFieldValue(R.id.contact_name, contact.getName());
		setTextFieldValue(R.id.contact_email, contact.getEmail());
		setTextFieldValue(R.id.contact_phoneNumber, contact.getPhoneNumber());
	}

	private void setTextFieldValue(int id, String value) {
		View view = findViewById(id);
		if(view instanceof TextView) {
			((TextView) view).setText(value);
		} else if (view instanceof EditText) {
			((EditText) view).setText(value);
		} else {
			throw new RuntimeException("Unknown field type: " + view.getClass());
		}		
	}
	
	protected abstract int getViewId();

}

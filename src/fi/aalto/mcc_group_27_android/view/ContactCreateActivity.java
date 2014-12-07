package fi.aalto.mcc_group_27_android.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import fi.aalto.mcc_group_27_android.R;
import fi.aalto.mcc_group_27_android.model.Contact;
import fi.aalto.mcc_group_27_android.model.ContactId;
import fi.aalto.mcc_group_27_android.service.ContactService;
import fi.aalto.mcc_group_27_android.service.ContactServiceCallbackListener;


public class ContactCreateActivity extends Activity {

	private ContactService contactService = ContactService.getInstance();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_form); 
	}
	
	public void submit(View view) {
		Contact contact = createContact();
		contactService.createContact(contact, new ContactServiceCallbackListener<ContactId>() {			
			@Override
			public void callback(ContactId contactId) {
				navigateToContactView(contactId.getId());
			}
		});
		
	}

	public void cancel(View view) {
		navigateToListView();
	}
	
	private Contact createContact() {
		String name = getFieldValue(R.id.contact_name);
		String email = getFieldValue(R.id.contact_email);
		String phoneNumber = getFieldValue(R.id.contact_phoneNumber);
		return new Contact(name, email, phoneNumber);
	}
	
	private String getFieldValue(int fieldId) {
		EditText view = (EditText) findViewById(fieldId);
		return view.getText().toString();
	}

	private void navigateToContactView(String contactId) {
		Intent intent = new Intent(this, ContactViewActivity.class);
		intent.putExtra(ContactListActivity.SELECTED_CONTACT_ID, contactId);
		startActivity(intent);
	}
	
	private void navigateToListView() {
		Intent intent = new Intent(this, ContactListActivity.class);
		startActivity(intent);
	}
}
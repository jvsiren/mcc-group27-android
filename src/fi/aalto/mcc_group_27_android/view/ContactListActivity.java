package fi.aalto.mcc_group_27_android.view;

import java.util.List;

import fi.aalto.mcc_group_27_android.R;
import fi.aalto.mcc_group_27_android.model.Contact;
import fi.aalto.mcc_group_27_android.service.ContactService;
import fi.aalto.mcc_group_27_android.service.ContactServiceCallbackListener;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactListActivity extends ListActivity {

	public static final String SELECTED_CONTACT_ID = "fi.aalto.mcc_group_27_android.selectedContactId";

	private ArrayAdapter<Contact> listAdapter;
	private ContactService contactService = ContactService.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list);
		initListContent();
	}

	public void initListContent() {		
		contactService.findAll(new ContactServiceCallbackListener<List<Contact>>() {			
			@Override
			public void callback(List<Contact> contacts) {
				listAdapter = new ArrayAdapter<Contact>(ContactListActivity.this, R.layout.contact_list_item, contacts);
				getListView().setAdapter(listAdapter);
			}
		});		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Contact clickedContact = listAdapter.getItem(position);
		openSingleContactView(clickedContact);
	}

	public void openSingleContactView(Contact contact) {
		Intent intent = new Intent(this, ContactViewActivity.class);
		intent.putExtra(SELECTED_CONTACT_ID, contact.get_id());
		startActivity(intent);
	}
	
	public void newContact(View view) {
		Intent intent = new Intent(this, ContactCreateActivity.class);
		startActivity(intent);
	}
}
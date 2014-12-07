package fi.aalto.mcc_group_27_android.view;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import fi.aalto.mcc_group_27_android.R;

public class ContactEditActivity extends SingleContactActivity {

	@Override
	protected int getViewId() {
		return R.layout.contact_form;
	}

	public void submit(View view) {
		updateContact();
		contactService.editContact(contact);
		navigateToContactView();
	}

	private void updateContact() {
		String name = getFieldValue(R.id.contact_name);
		String email = getFieldValue(R.id.contact_email);
		String phoneNumber = getFieldValue(R.id.contact_phoneNumber);

		contact.setName(name);
		contact.setEmail(email);
		contact.setPhoneNumber(phoneNumber);
	}

	private String getFieldValue(int fieldId) {
		EditText view = (EditText) findViewById(fieldId);
		return view.getText().toString();
	}

	public void cancel(View view) {
		navigateToContactView();
	}

	private void navigateToContactView() {
		Intent intent = new Intent(this, ContactViewActivity.class);
		intent.putExtra(ContactListActivity.SELECTED_CONTACT_ID,
				contact.get_id());
		startActivity(intent);
	}

}
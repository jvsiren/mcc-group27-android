package fi.aalto.mcc_group_27_android.view;

import fi.aalto.mcc_group_27_android.R;
import android.content.Intent;
import android.view.View;

public class ContactViewActivity extends SingleContactActivity {

	
	public void openEditView(View view) {
		Intent intent = new Intent(this, ContactEditActivity.class);
		intent.putExtra(ContactListActivity.SELECTED_CONTACT_ID, contact.get_id());
		startActivity(intent);
	}
	
	public void deleteContact(View view) {
		contactService.deleteContact(contact);
		navigateToListView();
	}
	
	private void navigateToListView() {
		Intent intent = new Intent(this, ContactListActivity.class);
		startActivity(intent);
	}

	@Override
	protected int getViewId() {
		return R.layout.contact_view;
	}
	
}
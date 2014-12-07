package fi.aalto.mcc_group_27_android.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import fi.aalto.mcc_group_27_android.model.Contact;
import fi.aalto.mcc_group_27_android.model.ContactId;

public class ContactService {

	private static ContactService instance;

	private static final String CONTACTS_URL = "http://mccgroup27.ddns.net:8080/api/contacts/";

	private RestTemplate restTemplate;

	public ContactService() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public void findAll(ContactServiceCallbackListener<List<Contact>> listener) {		
		ContactServiceAsyncTask<List<Contact>> findAllTask = new ContactServiceAsyncTask<List<Contact>>(listener) {
			@Override
			protected List<Contact> doInBackground(Void... params) {
				Contact[] contacts = restTemplate.getForObject(CONTACTS_URL, Contact[].class);
				List<Contact> contactList = new ArrayList<Contact>(Arrays.asList(contacts));
				Collections.sort(contactList);
				return contactList;
			}
		};
		findAllTask.execute();
	}

	public void findById(final String id, ContactServiceCallbackListener<Contact> listener) {
		ContactServiceAsyncTask<Contact> findByIdTask = new ContactServiceAsyncTask<Contact>(listener) {
			@Override
			protected Contact doInBackground(Void... params) {
				return restTemplate.getForObject(CONTACTS_URL + id, Contact.class);
			}
		};
		findByIdTask.execute();
	}

	public void deleteContact(final Contact contact) {
		ContactServiceAsyncTask<Void> deleteContactTask = new ContactServiceAsyncTask<Void>() {			
			@Override
			protected Void doInBackground(Void... params) {
				restTemplate.delete(CONTACTS_URL + contact.get_id());
				return null;
			}
		};
		deleteContactTask.execute();		
	}

	public void createContact(final Contact contact, ContactServiceCallbackListener<ContactId> listener) {
		ContactServiceAsyncTask<ContactId> createContactTask = new ContactServiceAsyncTask<ContactId>(listener) {			
			@Override
			protected ContactId doInBackground(Void... params) {
				return restTemplate.postForEntity(CONTACTS_URL, contact, ContactId.class).getBody();
			}
		};
		createContactTask.execute();
	}

	public void editContact(final Contact contact) {
		ContactServiceAsyncTask<Void> editContactTask = new ContactServiceAsyncTask<Void>() {			
			@Override
			protected Void doInBackground(Void... params) {
				restTemplate.postForEntity(CONTACTS_URL + contact.get_id(), contact, Object.class);
				return null;
			}
		};		
		editContactTask.execute();		
	}

	public static ContactService getInstance() {
		if (instance == null) {
			instance = new ContactService();
		}
		return instance;
	}

}

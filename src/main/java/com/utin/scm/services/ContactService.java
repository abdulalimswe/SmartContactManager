package com.utin.scm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utin.scm.entities.Contact;

public interface ContactService extends JpaRepository<Contact, String>{
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact>getAll();
    Contact getById(String id);
    Contact delete(String id);

    //search contact
    List<Contact> search(String name, String email, String phoneNumber);

    List<Contact> getByUserId(String userId);


}

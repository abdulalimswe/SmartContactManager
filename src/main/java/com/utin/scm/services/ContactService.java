package com.utin.scm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.utin.scm.entities.Contact;
import com.utin.scm.entities.User;

public interface ContactService extends JpaRepository<Contact, String>{
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact>getAll();
    Contact getById(String id);
    Contact delete(String id);

   // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,User user);

    List<Contact> getByUserId(String userId);
    Page<Contact>getByUser(User user, int page, int size, String sortfield, String sortDirection);


}

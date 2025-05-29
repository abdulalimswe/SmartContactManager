package com.utin.scm.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utin.scm.entities.Contact;
import com.utin.scm.entities.User;

public interface ContactRepo extends JpaRepository<Contact,String> {
    
    Page<Contact>findByUser(User user,Pageable pageable);
    //all contacts
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

    Contact save(String contactId);
    

}

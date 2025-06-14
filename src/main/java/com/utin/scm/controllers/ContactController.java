package com.utin.scm.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import com.utin.scm.entities.Contact;
import com.utin.scm.entities.User;
import com.utin.scm.forms.ContactForm;
import com.utin.scm.forms.ContactSearchForm;
import com.utin.scm.helpers.AppConstants;
import com.utin.scm.helpers.Helper;
import com.utin.scm.helpers.Message;
import com.utin.scm.helpers.MessageType;
import com.utin.scm.services.ContactService;
import com.utin.scm.services.ImageService;
import com.utin.scm.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);
    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    //add contact page: Handler
    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        // contactForm.setName("Alim");
        // contactForm.setAddress("address");
        // contactForm.setEmail("test@gmail.com");
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session){

        if(result.hasErrors()){

            //form Errors check
            // result.getAllErrors().forEach(error -> logger.info(error.toString()));

            session.setAttribute("message", Message.builder()
                .content("Please correct the following errors")
                .type(MessageType.red)
                .build());
            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        // logger.info("file information : {}", ( contactForm.getContactImage()).getContactImage().getOriginalFilename());

        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(),filename);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFevorite(contactForm.isFevorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        
        //* Set User */
        contact.setUser(user);

        contact.setLinkedinLink(contactForm.getLinkedinLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);
        contactService.save(contact); 
        System.out.println(contactForm); 

        session.setAttribute("message",
            Message.builder()
                .content("You have seccessfully added a new contact")
                .type(MessageType.green)
                .build());
       
        
        return "redirect:/user/contacts/add";
    }

    //view Contact
    @RequestMapping
    public String viewContact(      
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
        @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model, Authentication authentication) {

        //load to all user contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user,page,size,sortBy,direction);
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize",AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contacts";
    }

    @RequestMapping("/search")
    public String searchHandler(
            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model,
            Authentication authentication){

        // logger.info("field {} keyword {}", contactSearchForm.getField(), contactSearchForm.getValue());
        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));

        Page<Contact> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction, user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction, user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy, direction, user);
        }

        logger.info("pageContact {}", pageContact);

        model.addAttribute("contactSearchForm", contactSearchForm);
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/search";
    }

    // detete contact
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") String contactId, HttpSession session) {
        contactService.delete(contactId);
        logger.info("contactId {} deleted", contactId);
        session.setAttribute("message", Message.builder()
                        .content("Contact is Deleted successfully !! ")
                        .type(MessageType.green)
                        .build()
        );

        return "redirect:/user/contacts";
    }

    // update contact form view
    @GetMapping("/view/{contactId}")
    public String updateContactFormView(
            @PathVariable("contactId") String contactId,
            Model model) {

        var contact = contactService.getById(contactId);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkedinLink(contact.getLinkedinLink());
        contactForm.setPicture(contact.getPicture());
        ;
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);

        return "user/update_contact_view";
    }

    @RequestMapping(value = "/update/{contactId}", method = RequestMethod.POST)
    public String updateContact(@PathVariable("contactId") String contactId,
            @Valid @ModelAttribute ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {

        // update the contact
        if (bindingResult.hasErrors()) {
            return "user/update_contact_view";
        }

        var contact = contactService.getById(contactId);
        contact.setId(contactId);
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedinLink(contactForm.getLinkedinLink());

        // image processiong
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String fileName = UUID.randomUUID().toString();
            String imageUrl = imageService.uploadImage(contactForm.getContactImage(), fileName);
            contact.setCloudinaryImagePublicId(fileName);
            contact.setPicture(imageUrl);
            contactForm.setPicture(imageUrl);

        } else {
            logger.info("file is empty");
        }

        var updateCon = contactService.update(contact);
        logger.info("updated contact {}", updateCon);

        model.addAttribute("message", Message.builder().content("Contact Updated !!").type(MessageType.green).build());

        return "redirect:/user/contacts/view/" + contactId;
    }
    
}

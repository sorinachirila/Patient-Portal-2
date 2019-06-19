package com.sda.patientportal.service;

import com.sda.patientportal.model.Messages;
import com.sda.patientportal.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    //create
    public Messages createMessage(Messages message) {
        return messagesRepository.save(message);
    }

    //get
    public Messages get(Long id) {
        Optional<Messages> message = messagesRepository.findById(id);
        return message.orElse(null);
    }

    //getAll
    public List<Messages> getAllMessages() {
        return messagesRepository.findAll();
    }
    //----MODIFICARI--------------------------------------------------------------------
    //getAllByPatientId
    public List<Messages> getMessagesByPatientId() {
        return messagesRepository.findAllByPatientId();
    }
    //------------------------------------------------------------------------


    //update
    public Messages updateMessage(Messages message) {
        return messagesRepository.save(message);
    }
}

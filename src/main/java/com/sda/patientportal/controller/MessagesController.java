package com.sda.patientportal.controller;

import com.sda.patientportal.model.Messages;
import com.sda.patientportal.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessagesController {

    private MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    public MessagesService getMessagesService() {
        return messagesService;
    }

    public void setMessagesService(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

   /* @GetMapping(value = "/{id}")
    public Messages get(@PathVariable Long id) {
        return messagesService.get(id);
    }*/
   //----MODIFICARI--------------------------------------------------------------------
    //getAllByPatientId
   @GetMapping
    public List<Messages> getMessagesByPatientId() {
        return messagesService.getMessagesByPatientId();
    }
    //------------------------------------------------------------------------
    @PostMapping
    public Messages createMessage(@RequestBody Messages messages) {
        messages.setId(null);
        return messagesService.createMessage(messages);
    }

    @PutMapping(value = "/{id}")
    public Messages updateMessage(@PathVariable Long id, @RequestBody Messages message) {
        if (id != message.getId()) {
            throw new RuntimeException("Ids not match");
        }
        if (messagesService.get(message.getId()) == null) {
            throw new RuntimeException("Message does not exist");
        }
        return messagesService.updateMessage(message);
    }


}

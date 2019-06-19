package com.sda.patientportal.controller;

import com.sda.patientportal.model.Messages;
import com.sda.patientportal.model.Patient;
import com.sda.patientportal.service.MessagesService;
import com.sda.patientportal.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {


    private PatientService patientService;
    //----MODIFICARI--------------------------------------------------------------------
    private MessagesService messagesService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient p) {
        p.setId(null);
        return patientService.createPatient(p);
    }

    //----MODIFICARI--------------------------------------------------------------------
    @GetMapping(value = "/{id}/messages")
    public List<Messages> getMessagesById(@PathVariable Long id) {
        if (patientService.get(id) != null) {
            return messagesService.getMessagesByPatientId();
        }
        return null;
    }
    //------------------------------------------------------------------------

    @GetMapping
    public List<Patient> getAllPatients(@RequestParam(name = "lastName", required = false) String lastName) {
        if (lastName != null) {
            return patientService.getAllPatientsByLastName(lastName);
        } else {
            return patientService.getAllPatients();
        }
    }

    @GetMapping(value = "/{id}")
    public Patient get(@PathVariable Long id) {
        return patientService.get(id);
    }


    @PutMapping(value = "/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        if (id != patient.getId()) {
            throw new RuntimeException("Ids is not match");
        }
        if (patientService.get(patient.getId()) == null) {
            throw new RuntimeException("Patient does not exist");
        }
        return patientService.updatePatient(patient);
    }

    @DeleteMapping(value = "/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}

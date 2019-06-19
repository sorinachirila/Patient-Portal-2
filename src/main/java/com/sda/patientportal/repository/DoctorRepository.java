package com.sda.patientportal.repository;

import com.sda.patientportal.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findById(Long id);

    List<Doctor> findAll();

    List<Doctor> findAllByLastName(String lastName);

    void deleteById(Long id);
}

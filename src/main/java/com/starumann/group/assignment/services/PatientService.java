package com.starumann.group.assignment.services;

import com.starumann.group.assignment.entities.Patient;
import com.starumann.group.assignment.exceptions.PatientNotFoundException;
import com.starumann.group.assignment.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Cacheable("patients")
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id);
        if (existingPatient == null) {
            throw new PatientNotFoundException("Patient not found");
        }

        // Update other fields if needed
        existingPatient.setName(updatedPatient.getName());
        existingPatient.setAge(updatedPatient.getAge());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        Patient existingPatient = patientRepository.findById(id);
        if (existingPatient == null) {
            throw new PatientNotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}

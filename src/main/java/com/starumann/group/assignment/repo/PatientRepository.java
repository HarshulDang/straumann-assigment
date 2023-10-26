package com.starumann.group.assignment.repo;

import com.starumann.group.assignment.entities.Patient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PatientRepository {
    private Map<Long, Patient> patientData = new HashMap<>();
    private Long idCounter = 1L;

    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(idCounter++);
        }
        patientData.put(patient.getId(), patient);
        return patient;
    }

    public Patient findById(Long id) {
        return patientData.get(id);
    }

    public Map<Long, Patient> findAll() {
        return patientData;
    }

    public void deleteById(Long id) {
        patientData.remove(id);
    }
}

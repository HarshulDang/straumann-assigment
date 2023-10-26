package com.starumann.group.assignment;

import com.starumann.group.assignment.entities.Patient;
import com.starumann.group.assignment.repo.PatientRepository;
import com.starumann.group.assignment.services.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void testGetPatientById_Exists() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        Mockito.when(patientRepository.findById(patientId));

        Patient result = patientService.getPatientById(patientId);

        assertNotNull(result);
        assertEquals(patientId, result.getId());
    }

    @Test
    public void testGetPatientById_NotFound() {
        Long patientId = 2L;
        Mockito.when(patientRepository.findById(patientId));

        Patient result = patientService.getPatientById(patientId);

        assertNull(result);
    }
}

package com.starumann.group.assignment;

import com.starumann.group.assignment.controllers.PatientController;
import com.starumann.group.assignment.entities.Patient;
import com.starumann.group.assignment.services.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.reflect.Array.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @Test
    public void testGetPatient_Success() throws Exception {
        // Arrange
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);
        Mockito.when(patientService.getPatientById(patientId)).thenReturn(patient);

        // Act & Assert
        mockMvc.perform(get("/patients/{id}", patientId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPatient_NotFound() throws Exception {
        // Arrange
        Long patientId = 2L;
        Mockito.when(patientService.getPatientById(patientId)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/patients/{id}", patientId))
                .andExpect(status().isNotFound());
    }
}

package com.practice.hospitalmanagementsystem.Service;

import com.practice.hospitalmanagementsystem.Entity.Patient;
import com.practice.hospitalmanagementsystem.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // 🔹 Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // 🔹 Get patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + id));
    }

    // 🔹 Create a new patient
    public Patient createPatient(Patient patient) {
        // Check for duplicates
        if (patientRepository.existsByContactNumber(patient.getContactNumber())) {
            throw new IllegalArgumentException("Contact number already exists");
        }

        if (patient.getEmail() != null && patientRepository.existsByEmail(patient.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return patientRepository.save(patient);
    }

    // 🔹 Update existing patient
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + id));

        existing.setFullName(updatedPatient.getFullName());
        existing.setGender(updatedPatient.getGender());
        existing.setDateOfBirth(updatedPatient.getDateOfBirth());
        existing.setContactNumber(updatedPatient.getContactNumber());
        existing.setEmail(updatedPatient.getEmail());
        existing.setAddress(updatedPatient.getAddress());
        existing.setMedicalHistory(updatedPatient.getMedicalHistory());

        return patientRepository.save(existing);
    }

    // 🔹 Delete patient
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
    }
}

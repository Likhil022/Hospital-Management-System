package com.practice.hospitalmanagementsystem.Repository;

import com.practice.hospitalmanagementsystem.Entity.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Example custom queries:
    Patient findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByContactNumber(@NotBlank(message = "Contact number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits") String contactNumber);
}
package com.evolution.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evolution.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}

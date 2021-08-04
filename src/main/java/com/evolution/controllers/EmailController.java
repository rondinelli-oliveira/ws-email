package com.evolution.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evolution.dtos.EmailDTO;
import com.evolution.models.EmailModel;
import com.evolution.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/sending-email")
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO EmailDTO) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(EmailDTO, emailModel);
		emailService.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}

	@GetMapping("/emails")
	public ResponseEntity<Page<EmailModel>> getAllEmails(
			@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<EmailModel> emailModelPage = emailService.findAll(pageable);
		if (emailModelPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(emailModelPage, HttpStatus.OK);
		}
	}
}
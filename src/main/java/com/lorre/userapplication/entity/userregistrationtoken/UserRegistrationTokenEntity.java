package com.lorre.userapplication.entity.userregistrationtoken;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_registration_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationTokenEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID id;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "is_expired")
	private boolean isExpired;

	@Column(name = "user_id")
	private Long userId;
}

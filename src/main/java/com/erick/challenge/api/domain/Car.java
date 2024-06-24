package com.erick.challenge.api.domain;

import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "first_name", nullable = false)
	private int year;

	@Column(name = "license_plate", nullable = false)
	private String licensePlate;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "color", nullable = false)
	private String color;

	@ManyToOne
	@JoinColumn(name = "id_user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

}
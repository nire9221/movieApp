package com.erin.movie.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(value="user")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // pk생성규칙 - key값은 자동으로 증가
	@Column(name = "USERID")
	@NonNull
	private Long userId;
}

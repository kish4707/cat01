package com.ezen.demo.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="emp4")
public class Emp {

	@Id
	@SequenceGenerator(sequenceName = "EMP4_EMPNO_SEQ", allocationSize = 1, name = "EMP4_EMPNO_GEN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP4_EMPNO_GEN")
	private int empno;
	
	@Column(name="ename")
	private String name;
	
	private int deptno;
	private int sal;
	private java.sql.Date hiredate;
}
package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "studentName")
	private String studentName;

	@Column(name = "dob")
	private String dob;

	@Column(name = "totalMarks")
	private float totalMarks;

	public Student() {
	}

	public Student(long id, String studentName, String dob, float totalMarks) {
		this.id = id;
		this.studentName = studentName;
		this.dob = dob;
		this.totalMarks = totalMarks;
	}

	public long getId() {
		return id;
	}

	public Student setId(long id) {
		this.id = id;
		return this;
	}

	public String getStudentName() {
		return studentName;
	}

	public Student setStudentName(String studentName) {
		this.studentName = studentName;
		return this;
	}

	public String getDob() {
		return dob;
	}

	public Student setDob(String dob) {
		this.dob = dob;
		return this;
	}

	public float getTotalMarks() {
		return totalMarks;
	}

	public Student setTotalMarks(float totalMarks) {
		this.totalMarks = totalMarks;
		return this;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", dob=" + dob + ", totalMarks=" + totalMarks
				+ "]";
	}

}

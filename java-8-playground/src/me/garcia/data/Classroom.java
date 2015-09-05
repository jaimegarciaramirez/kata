package me.garcia.data;

import java.util.Collection;
import java.util.LinkedList;

public class Classroom {

	private String className;
	private Collection<Student> students = new LinkedList<>();
	
	public Classroom(String name) {
		className = name;
	}
	
	public Classroom addStudent(Student student) {
		students.add(student);
		return this;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "Classroom [className=" + className + ", getStudents()=" + getStudents() + "]";
	}
}

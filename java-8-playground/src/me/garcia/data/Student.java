package me.garcia.data;

public class Student {

	private String name;
	private Integer age;
	private int grade;
	
	public Student(String name, Integer age, int grade) {
		super();
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", grade=" + grade + "]";
	}
}

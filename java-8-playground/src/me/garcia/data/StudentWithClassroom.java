package me.garcia.data;

public class StudentWithClassroom {
	private String name;
	private Integer age;
	private int grade;
	private String className;
	
	public StudentWithClassroom(String name, Integer age, int grade, String className) {
		super();
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.className = className;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "StudentWithClassroom [name=" + name + ", age=" + age + ", grade=" + grade + ", className=" + className
				+ "]";
	}

}

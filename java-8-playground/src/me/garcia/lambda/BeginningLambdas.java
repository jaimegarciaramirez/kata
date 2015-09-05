package me.garcia.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import me.garcia.data.Classroom;
import me.garcia.data.Student;
import me.garcia.data.StudentWithClassroom;

public class BeginningLambdas {

	public static void main(String[] args) {
		Predicate<Student> studentsSevenOrOlder = (student) -> student.getAge() >= 7;
		
		printPersonsWithPredicate(Arrays.asList(new Student[]{
			new Student("Jaime", 8, 3),
			new Student("Misael", 3, 0)	
		}), studentsSevenOrOlder);
		
		System.out.println("Now traversing the list");
		Classroom classroom = new Classroom("Science")
				.addStudent(new Student("John", 12, 5))
				.addStudent(new Student("Mike", 11, 5)); 
		Classroom classroom2 = new Classroom("Math")
				.addStudent(new Student("John", 12, 5))
				.addStudent(new Student("Mike", 11, 5)); 
		traverse(classroom.getStudents(), 
			studentsSevenOrOlder,
			student -> studentWithClassroom(student),
			studentWithClassroom -> {
				studentWithClassroom.setClassName(classroom.getClassName());
				print(studentWithClassroom);
			});

		System.out.println("\n\nNow with a cool awesomeness");
		
		Stream<StudentWithClassroom> students = classroom.getStudents()
			.stream()
			.map(student -> studentWithClassroom(student))
			.sorted((student1, student2) -> student1.getAge().compareTo(student2.getAge()))
			.peek(student -> {
				student.setClassName(classroom.getClassName());
			});
		
		students.forEach(student -> print(student));
				
		System.out.println("\n\nThe old way");
		List<StudentWithClassroom> mappedStudents = new LinkedList<>();
		for (Student student : classroom2.getStudents()) {
			mappedStudents.add(new StudentWithClassroom(
					student.getName(), student.getAge(), student.getGrade(), classroom2.getClassName()));
		}
		Collections.sort(mappedStudents, new Comparator<StudentWithClassroom>() {
			@Override
			public int compare(StudentWithClassroom o1, StudentWithClassroom o2) {
				return o1.getAge().compareTo(o2.getAge());
			}
		});
		for (StudentWithClassroom student : mappedStudents) {
			System.out.println(student);
		}
		
		
		int ages = classroom.getStudents()
				.stream()
				.mapToInt(student -> student.getAge())
				.sum();
		System.out.println("\n\nLet's add up people's ages: " + ages);
	}
	
	private static <T extends Object> void print(T object) {
		System.out.println(object);
	}
	
	private static void printPersonsWithPredicate(List<Student> students, Predicate<Student> predicate) {
		for (Student student : students) {
			if (predicate.test(student)) {
				System.out.println(student.toString());	
			}
		}
	}
	
	private static StudentWithClassroom studentWithClassroom(Student student) {
		return new StudentWithClassroom(student.getName(), student.getAge(), student.getGrade(), student.getName());
	}
	
	private static <T extends Object, R extends Object> void traverse(Iterable<T> list, Predicate<T> predicate, 
			Function<T, R> mapper, Consumer<R> consumer) {
		for (T item : list) {
			if (predicate.test(item)) {
				consumer.accept(mapper.apply(item));
			}
		}
	}
}

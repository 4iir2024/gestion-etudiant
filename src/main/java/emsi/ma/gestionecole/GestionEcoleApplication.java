package emsi.ma.gestionecole;

import emsi.ma.gestionecole.entites.Student;
import emsi.ma.gestionecole.repositores.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GestionEcoleApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionEcoleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		System.out.println("---------------------- Insertion ---------------------------------------------------------------------");
		studentRepository.save(new Student(null,"A1","Amine",new Date(),true,null));
		studentRepository.save(new Student(null,"A2","Ilyas",new Date(),false,null));
		studentRepository.save(new Student(null,"A3","Saad",new Date(),true,null));
		studentRepository.save(new Student(null,"A4","Aziz",new Date(),false,null));
		studentRepository.save(new Student(null,"A5","Kamal",new Date(),true,null));
		System.out.println("----------------------Inserted Rows ------------------------------------------------------------------");
		System.out.println("Count: "+ studentRepository.count());

		System.out.println("---------------------- Display Rows --------------------------------------------------------------------");
		List <Student> students = studentRepository.findAll();
		students.forEach(student -> {
			System.out.println(student.toString());
		});

		System.out.println("---------------------- Get Element By ID --------------------------------------------------------------------");
		Student student = studentRepository.findById(4).orElse(null);
		System.out.println(student.toString());

		System.out.println("---------------------- Update An Element --------------------------------------------------------------------");
		student.setRegistrationNumber("S3");
		studentRepository.save(student);

		System.out.println("---------------------- Delete An Element --------------------------------------------------------------------");
		studentRepository.delete(student);
		System.out.println("Count: "+ studentRepository.count());

		studentRepository.deleteById(5);
		System.out.println("Count: "+studentRepository.count());


		System.out.println("-------------------- Select Active Students -------------------------------------------------------------------");
		List<Student> activeStudents = studentRepository.findByStillActive(true);
		activeStudents.forEach(s->{
			System.out.println(s.toString());
		});

	}
}

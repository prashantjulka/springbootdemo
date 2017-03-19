package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);

	}
	@Bean
	CommandLineRunner runner(ReservationRepository reservationRepository){
		return args -> {
			Arrays.asList("Prashant, Raman, Ravish, ALex, Supertrump".split(",")).forEach(name->reservationRepository.save(new
					Reservation(name)));

			reservationRepository.findAll().forEach( System.out::println);

			reservationRepository.findByReservationName("Alex");
			reservationRepository.findByReservationName("Ale22");
		};
	}
}
@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{
	//select * from reservation where reservation_name=:rn
	//	@Query(---)
	Collection<Reservation> findByReservationName(String rn);
}



@Entity
class Reservation{
	@Id
	@GeneratedValue
	private Long id;

	private String reservationName;

	Reservation(){}
	Reservation(String reservationName){
		this.reservationName=reservationName;
	}
	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}


}







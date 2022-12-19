package sideProject.moneyTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sideProject.moneyTracker.Utils.ArtUtils;

@SpringBootApplication
public class MoneyTrackerApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoneyTrackerApplication.class, args);
		System.out.println(ArtUtils.art);
	}

}

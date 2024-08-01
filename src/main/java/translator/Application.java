package translator;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("YANDEX_TOKEN", Objects.requireNonNull(dotenv.get("YANDEX_TOKEN")));
		System.setProperty("YANDEX_FOLDER_ID", Objects.requireNonNull(dotenv.get("YANDEX_FOLDER_ID")));

		SpringApplication.run(Application.class, args);
	}
}


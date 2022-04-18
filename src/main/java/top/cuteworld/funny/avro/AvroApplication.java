package top.cuteworld.funny.avro;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.cuteworld.funny.avro.caller.AvroDataGenerator;
import top.cuteworld.funny.avro.caller.UserRepository;
import top.cuteworld.funny.avro.model.User;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class AvroApplication implements ApplicationRunner {

    private final UserRepository userRepository;

    private final AvroDataGenerator avroDataGenerator;

    public static void main(String[] args) {
        SpringApplication.run(AvroApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<User> users = avroDataGenerator.avroSchemaGeneratedModel();

        log.info("Save avro objects to disk file");
        userRepository.save(users);

        log.info("Reading avro objects from disk file");
        List<User> read = userRepository.read();

        log.info("The size is {}", read.size());
        log.info("The user is {}", read.get(0));

    }
}

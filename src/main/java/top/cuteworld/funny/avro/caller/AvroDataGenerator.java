package top.cuteworld.funny.avro.caller;

import org.springframework.stereotype.Component;
import top.cuteworld.funny.avro.model.User;

import java.util.List;

@Component
public class AvroDataGenerator {

    public List<User> avroSchemaGeneratedModel() {
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
// Leave favorite color null

// Alternate constructor
        User user2 = new User("Ben", 7, "red");

// Construct via builder
        User user3 = User.newBuilder().setName("Charlie").setFavoriteColor("blue").setFavoriteNumber(null).build();

        return List.of(user1, user2, user3);
    }

}

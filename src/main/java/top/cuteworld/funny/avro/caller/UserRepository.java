package top.cuteworld.funny.avro.caller;

import top.cuteworld.funny.avro.model.User;

import java.io.IOException;
import java.util.List;

public interface UserRepository {

    void save(User user) throws IOException;

    void save(List<User> users) throws IOException;

    List<User> read() throws IOException;
}

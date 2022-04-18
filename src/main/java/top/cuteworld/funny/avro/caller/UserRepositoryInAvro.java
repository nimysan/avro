package top.cuteworld.funny.avro.caller;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.stereotype.Repository;
import top.cuteworld.funny.avro.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryInAvro implements UserRepository {


    public static final String fileInDisk = "/tmp/users.avro";

    @Override
    public void save(User user) throws IOException {
        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user.getSchema(), new File(fileInDisk));
        dataFileWriter.append(user);
//        dataFileWriter.append(user2);
//        dataFileWriter.append(user3);
        dataFileWriter.close();
    }

    @Override
    public void save(List<User> users) throws IOException {
        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(users.get(0).getSchema(), new File(fileInDisk));
        dataFileWriter.append(users.get(0));
        dataFileWriter.append(users.get(1));
        dataFileWriter.append(users.get(2));
        dataFileWriter.close();
    }


    public List<User> read() throws IOException {
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(fileInDisk), userDatumReader);
        User user = null;
        List<User> lists = new ArrayList<>();
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.
            user = dataFileReader.next(user);
            lists.add(user);
            System.out.println(user);
        }
        return lists;
    }

}

package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {

    public static void save(Path pathFile, Car car) throws IOException {
        Files.write(pathFile, car.serialize().getBytes());
    }
    public static Car extract(Path pathFile) throws IOException {
        return Car.unserialize(Files.readString(pathFile));
    }
}
// END

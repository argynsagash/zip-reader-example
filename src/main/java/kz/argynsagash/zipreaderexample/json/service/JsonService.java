package kz.argynsagash.zipreaderexample.json.service;

import kz.argynsagash.zipreaderexample.json.model.Person;
import org.springframework.web.multipart.MultipartFile;

public interface JsonService {
    String parseJson(MultipartFile file);
    void convertToJson(Person person);
}

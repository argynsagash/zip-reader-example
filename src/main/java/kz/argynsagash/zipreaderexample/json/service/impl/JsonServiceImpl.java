package kz.argynsagash.zipreaderexample.json.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.argynsagash.zipreaderexample.json.model.Person;
import kz.argynsagash.zipreaderexample.json.service.JsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JsonServiceImpl implements JsonService {

    @Override
    public String parseJson(MultipartFile file) {
        String personInfo = "";
        try {

            Person person = new ObjectMapper().readValue(file.getInputStream(), Person.class);
            personInfo = person.toString();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return personInfo;
    }


    public void convertToJson(Person person) {
        String path = "src/main/resources/" + person.getName() + ".json";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonLion = objectMapper.writeValueAsString(person);
            objectMapper.writeValue(new File(path),person);
            System.out.println(jsonLion);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
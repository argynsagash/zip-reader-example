package kz.argynsagash.zipreaderexample.json.controller;

import kz.argynsagash.zipreaderexample.json.model.Person;
import kz.argynsagash.zipreaderexample.json.service.JsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/json")
public class JsonController {
    private final JsonService jsonService;

    @PostMapping(value = "/from-json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE )
    public String convertFromJson( @RequestParam("file") MultipartFile file) {
        return jsonService.parseJson(file);
    }
    @PostMapping(value = "/to-json")
    public String convertToJson(@RequestBody Person person) {
        jsonService.convertToJson(person);
        return "DONE";
    }



}

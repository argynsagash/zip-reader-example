package kz.argynsagash.zipreaderexample.zip.controller;

import kz.argynsagash.zipreaderexample.zip.service.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/zip")
public class ZipController {

    private final ZipService zipService;

    @PostMapping(value = "/zip-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE )
    public String zipFile( @RequestParam("file") MultipartFile file) {
        zipService.zipFile(file);
        return "DONE";
    }

    @PostMapping(value = "/zip-multiple-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String zipMultipleFiles(@RequestParam("file") List<MultipartFile> files) {
        zipService.zipMultipleFiles(files);
        return "DONE";
    }
}

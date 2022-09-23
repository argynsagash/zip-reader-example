package kz.argynsagash.zipreaderexample.zip.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ZipService {
    void zipFile(MultipartFile file);
    void zipMultipleFiles(List<MultipartFile> srcFiles);
    void zipDirectory(String sourceFile);
}

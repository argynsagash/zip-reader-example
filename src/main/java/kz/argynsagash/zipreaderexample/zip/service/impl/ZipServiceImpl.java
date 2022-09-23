package kz.argynsagash.zipreaderexample.zip.service.impl;

import kz.argynsagash.zipreaderexample.zip.service.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ZipServiceImpl implements ZipService {

    @Override
    public void zipFile(MultipartFile file) {
        String path = "src/main/resources/";
        try (InputStream inputStream = file.getInputStream();
             FileOutputStream fos = new FileOutputStream(path + file.getOriginalFilename() + ".zip");
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            ZipEntry zipEntry = new ZipEntry(file.getOriginalFilename());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                zipOut.write(bytes, 0, length);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void zipMultipleFiles(List<MultipartFile> multipleFiles) {
        String path = "src/main/resources/";
        try {
            FileOutputStream fos = new FileOutputStream(path + "multiCompressed.zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (MultipartFile srcFile : multipleFiles) {
                InputStream in = srcFile.getInputStream();
                ZipEntry zipEntry = new ZipEntry(srcFile.getOriginalFilename());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = in.read(bytes)) != -1) {
                    zipOut.write(bytes, 0, length);
                }
                in.close();
            }
            zipOut.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void zipDirectory(String sourceFile) {
        try {
            FileOutputStream fos = new FileOutputStream("dirCompressed.zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}

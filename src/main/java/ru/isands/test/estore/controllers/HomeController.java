package ru.isands.test.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {



    @GetMapping
    public String home() {
        return "home";
    }


    /*
    @RequestMapping(
    value = "/upload",
     method = RequestMethod.POST,
     produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
      )
public ResponseEntity<Boolean> saveFile(@RequestPart(value = "file") final MultipartFile file) {
    Boolean state = false;
    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file.getBytes()))) {
        for (ZipEntry entry; (entry = zipStream.getNextEntry()) != null;) {
            state = handleDataList(zipStream.readAllBytes(), entry.getName());
            zis.closeEntry();
            zis.close();
            if (!state)
              break;
        }
    } catch (Exception e) {
      log.error(e);
    }
    return new ResponseEntity<Boolean>(state, HttpStatus.OK);
}

private boolean handleDataList(byte[] data, String fileName) {
   // обработка записи данных из файлов
}
*/
}

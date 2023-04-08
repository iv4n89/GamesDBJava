package com.orejita.games.Controllers.UploadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Developer;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Games.Publisher;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.Interfaces.IConsoleService;
import com.orejita.games.Services.Interfaces.IDeveloperService;
import com.orejita.games.Services.Interfaces.IGameService;
import com.orejita.games.Services.Interfaces.IIconService;
import com.orejita.games.Services.Interfaces.IManufacturerService;
import com.orejita.games.Services.Interfaces.IPublisherService;
import com.orejita.games.Services.UploadFile.UploadFileService;
import com.orejita.games.Util.UploadFileResponse;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/file")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private IConsoleService consoleService;

    @Autowired
    private IGameService gameService;

    @Autowired
    private IManufacturerService manufacturerService;

    @Autowired
    private IDeveloperService developerService;

    @Autowired
    private IPublisherService publisherService;

    @Autowired
    private IIconService iconService;

    private final String DOWNLOAD_URI_PART = "/file/download/";
    private final String IMAGE_URI_PART = "/file/image/";

    @PostMapping("/upload")
    @ResponseBody
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = uploadFileService.storeFile(file, null);

        String FileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path(DOWNLOAD_URI_PART)
                                    .path(fileName)
                                    .toUriString();

        return new UploadFileResponse(fileName, FileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/multiple")
    @ResponseBody
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .toList();
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName, HttpServletRequest request) {
        Resource resource = uploadFileService.loadFileAsResource(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable("filename") String filename, HttpServletRequest request) {
        Resource resource = uploadFileService.loadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

    @PostMapping("/console/{id}/logo")
    @ResponseBody
    public UploadFileResponse uploadConsoleLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {

        String fileName = uploadFileService.storeFile(file, "console-logo");

        String FileDownloadUri = this.IMAGE_URI_PART + fileName;

        Console console = new Console();
        console.setLogo(FileDownloadUri);
                                
        consoleService.updateConsole(console, id);

        return new UploadFileResponse(fileName, FileDownloadUri, file.getContentType(), file.getSize());
        
    }

    @PostMapping("/console/{id}/images")
    @ResponseBody
    public List<UploadFileResponse> uploadConsoleImages(@PathVariable("id") int id, @RequestParam("files") MultipartFile[] files) {
        Console console = new Console();
        List<UploadFileResponse> uploadedFiles = new ArrayList<>();

        for (MultipartFile file: files) {
            String fileName = uploadFileService.storeFile(file, "console-image");
            String fileDownloadUri = this.IMAGE_URI_PART + fileName;
            console.getImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        consoleService.updateConsole(console, id);

        return uploadedFiles;
    }

    @DeleteMapping("/console/{id}/images/{image:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsoleImage(@PathVariable("id") int id, @PathVariable("image") String image) {
        consoleService.deleteConsoleImage(id, image);
        uploadFileService.deleteFile(image);
    }

    @PostMapping("/console/{id}/box-images")
    @ResponseBody
    public List<UploadFileResponse> uploadConsoleBoxImages(@PathVariable("id") int id, @RequestParam("files") MultipartFile[] files) {
        Console console = new Console();
        List<UploadFileResponse> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = uploadFileService.storeFile(file, "console-box-image");
            String fileDownloadUri = this.IMAGE_URI_PART + fileName;
            console.getBoxImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        consoleService.updateConsole(console, id);
        return uploadedFiles;
    }

    @DeleteMapping("/console/{id}/box-images/{image:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsoleBoxImage(@PathVariable("id") int id, @PathVariable("image") String image) {
        consoleService.deleteConsoleBoxImage(id, image);
        uploadFileService.deleteFile(image);
    }

    @PostMapping("/console/{id}/icon")
    @ResponseBody
    public UploadFileResponse uploadConsoleIcon(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Icon icon = new Icon();
        String fileName = uploadFileService.storeFile(file, "console-icon");
        String fileDownloadUri = this.IMAGE_URI_PART + fileName;
        icon.setUrl(fileDownloadUri);
        iconService.createIconByConsoleId(id, icon);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/game/{id}/logo")
    @ResponseBody
    public UploadFileResponse uploadGameLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        String fileName = uploadFileService.storeFile(file, "game-logo");

        String FileDownloadUri = this.IMAGE_URI_PART + fileName;

        Game game = new Game();
        game.setLogo(FileDownloadUri);
                                
        gameService.updateGame(id, game);

        return new UploadFileResponse(fileName, FileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/game/{id}/images")
    @ResponseBody
    public List<UploadFileResponse> uploadGameImages(@PathVariable("id") int id, @RequestParam("files") MultipartFile[] files) {
        Game game = new Game();
        List<UploadFileResponse> uploadedFiles = new ArrayList<>();

        for (MultipartFile file: files) {
            String fileName = uploadFileService.storeFile(file, "console-image");
            String fileDownloadUri = this.IMAGE_URI_PART + fileName;
            game.getImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        gameService.updateGame(id, game);

        return uploadedFiles;
    }

    @DeleteMapping("/game/{id}/images/{image:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameImage(@PathVariable("id") int id, @PathVariable("image") String image) {
        gameService.deleteGameImage(id, image);
        uploadFileService.deleteFile(image);
    }

    @PostMapping("/game/{id}/box-images")
    @ResponseBody
    public List<UploadFileResponse> uploadGameBoxImages(@PathVariable("id") int id, @RequestParam("files") MultipartFile[] files) {
        Game game = new Game();
        List<UploadFileResponse> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = uploadFileService.storeFile(file, "console-box-image");
            String fileDownloadUri = this.IMAGE_URI_PART + fileName;
            game.getBoxImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        gameService.updateGame(id, game);
        return uploadedFiles;
    }

    @DeleteMapping("/game/{id}/box-images/{image:.+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameBoxImage(@PathVariable("id") int id, @PathVariable("image") String image) {
        gameService.deleteGameBoxImage(id, image);
        uploadFileService.deleteFile(image);
    }

    @PostMapping("/game/{id}/icon")
    @ResponseBody
    public UploadFileResponse uploadGameIcon(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Icon icon = new Icon();
        String fileName = uploadFileService.storeFile(file, "game-icon");
        String fileDownloadUri = this.IMAGE_URI_PART + fileName;
        icon.setUrl(fileDownloadUri);
        iconService.createIconByConsoleId(id, icon);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/manufacturer/{id}/logo")
    @ResponseBody
    public UploadFileResponse uploadManufacturerLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Manufacturer manufacturer = new Manufacturer();
        String fileName = uploadFileService.storeFile(file, "manufacturer-logo");
        String fileDownloadUri = this.IMAGE_URI_PART + fileName;
        manufacturer.setLogo(fileDownloadUri);
        manufacturerService.updateManufacturer(id, manufacturer);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/manufacturer/{id}/icon")
    @ResponseBody
    public UploadFileResponse uploadManufacturerIcon(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Icon icon = new Icon();
        String fileName = uploadFileService.storeFile(file, "manufacturer-icon");
        String fileDownloadUri = this.IMAGE_URI_PART + fileName;
        icon.setUrl(fileDownloadUri);
        iconService.createIconByManufacturerId(id, icon);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/developer/{id}/logo")
    @ResponseBody
    public UploadFileResponse uploadDeveloperLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Developer developer = new Developer();
        String fileName = uploadFileService.storeFile(file, "developer-logo");
        String fileDownloadUri = this.IMAGE_URI_PART + fileName;
        developer.setLogo(fileDownloadUri);
        developerService.updateDeveloper(id, developer);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/publisher/{id}/logo")
    @ResponseBody
    public UploadFileResponse uploadPublisherLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        Publisher publisher = new Publisher();
        String fileName = uploadFileService.storeFile(file, "publisher-logo");
        String fileDownloadUri = this.IMAGE_URI_PART + fileName;
        publisher.setLogo(fileDownloadUri);
        publisherService.updatePublisher(id, publisher);
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }
    
}

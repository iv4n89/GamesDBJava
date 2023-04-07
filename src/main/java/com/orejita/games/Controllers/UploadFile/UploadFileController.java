package com.orejita.games.Controllers.UploadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.Interfaces.IConsoleService;
import com.orejita.games.Services.Interfaces.IGameService;
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

    @PostMapping("/upload")
    @ResponseBody
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = uploadFileService.storeFile(file, null);

        String FileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/file/download/")
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

        String FileDownloadUri = "/file/image/" + fileName;

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
            String fileDownloadUri = "/file/image/" + fileName;
            console.getImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        consoleService.updateConsole(console, id);

        return uploadedFiles;
    }

    @PostMapping("/console/{id}/box-images")
    @ResponseBody
    public List<UploadFileResponse> uploadConsoleBoxImages(@PathVariable("id") int id, @RequestParam("files") MultipartFile[] files) {
        Console console = new Console();
        List<UploadFileResponse> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = uploadFileService.storeFile(file, "console-box-image");
            String fileDownloadUri = "/file/image/" + fileName;
            console.getBoxImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        consoleService.updateConsole(console, id);
        return uploadedFiles;
    }

    @PostMapping("/game/{id}/logo")
    @ResponseBody
    public UploadFileResponse uploadGameLogo(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) {
        String fileName = uploadFileService.storeFile(file, "game-logo");

        String FileDownloadUri = "/file/image/" + fileName;

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
            String fileDownloadUri = "/file/image/" + fileName;
            game.getImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        gameService.updateGame(id, game);

        return uploadedFiles;
    }

    @PostMapping("/game/{id}/box-images")
    @ResponseBody
    public List<UploadFileResponse> uploadGameBoxImages(@PathVariable("id") int id, @RequestParam("files") MultipartFile[] files) {
        Game game = new Game();
        List<UploadFileResponse> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = uploadFileService.storeFile(file, "console-box-image");
            String fileDownloadUri = "/file/image/" + fileName;
            game.getBoxImages().add(fileDownloadUri);
            uploadedFiles.add(new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
        }

        gameService.updateGame(id, game);
        return uploadedFiles;
    }
    
}

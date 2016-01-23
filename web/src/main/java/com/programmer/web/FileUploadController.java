package com.programmer.web;

import com.programmer.api.FileUploadResponse;
import com.programmer.entity.FileEntity;
import com.programmer.entity.Programmer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.programmer.services.FileUploadService;
import com.programmer.services.programmer.ProgrammerService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

@Controller
@RequestMapping("files")
public class FileUploadController {

    final static Logger logger = Logger.getLogger(FileUploadController.class);

    @Autowired
    private ProgrammerService programmerService;

    @Autowired
    private FileUploadService fileUploadService;

    /***************************************************
     * URL: /rest/web/upload
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @return FileUploadResponse as json format
     ****************************************************/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadResponse upload(MultipartHttpServletRequest request) {

        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        Programmer programmer = programmerService.getLoggedProgrammer();

        Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf = null;

        while(itr.hasNext()){
            mpf = request.getFile(itr.next());
            try {
                String newFilePath = "resource/files/" + mpf.getOriginalFilename();
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(newFilePath));

                FileEntity fileEntity = new FileEntity();
                fileEntity.setProgrammer(programmer);
                fileEntity.setFilePath(newFilePath);

                fileUploadService.create(fileEntity);
                fileUploadResponse.setProgrammerId(programmer.getId());
                fileUploadResponse.addFilePath(newFilePath);
                logger.debug("File " + mpf.getName() + " was uploaded");
            } catch (IOException e) {
                logger.debug("Uploading error");
                e.printStackTrace();
            }
        }
        return fileUploadResponse;
    }
}
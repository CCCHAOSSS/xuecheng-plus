package com.xuecheng.media.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.model.dto.UploadFileResultDto;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description 媒资文件管理接口
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */
 @Api(value = "媒资文件管理接口",tags = "媒资文件管理接口")
 @RestController
public class MediaFilesController {

     @Autowired
     MediaFileService mediaFileService;

     @ApiOperation("媒资列表查询接口")
     @PostMapping("/files")
     public PageResult<MediaFiles> list(PageParams pageParams, @RequestBody QueryMediaParamsDto queryMediaParamsDto){
        Long companyId = 1232141425L;
        return mediaFileService.queryMediaFiles(companyId,pageParams,queryMediaParamsDto);
     }


     @ApiOperation("上传图片")
     @RequestMapping(value = "/upload/coursefile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public UploadFileResultDto upload(@RequestParam("filedata") MultipartFile filedata) throws IOException {
         //RequestParam中的值是和前端约定好的名字，表示上传的图片的名字(filedata)
         UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
         uploadFileParamsDto.setFilename(filedata.getOriginalFilename());   //      原始文件名称
         uploadFileParamsDto.setFileSize(filedata.getSize());
         uploadFileParamsDto.setFileType("001001"); //写死，这个是上传图片，图片对应001001


         // 创建一个临时文件,方便操作上传的文件
         File tempFile = File.createTempFile("minio", "temp");
         filedata.transferTo(tempFile);

         //通过临时文件来上传所需的参数
         String localFilePath = tempFile.getAbsolutePath();

         //只要执行到这就是已经接收到文件了
         Long companyId = 1232141425L;
         UploadFileResultDto uploadFileResultDto = mediaFileService.uploadFile(companyId, uploadFileParamsDto, localFilePath);
         return uploadFileResultDto;

     }
}

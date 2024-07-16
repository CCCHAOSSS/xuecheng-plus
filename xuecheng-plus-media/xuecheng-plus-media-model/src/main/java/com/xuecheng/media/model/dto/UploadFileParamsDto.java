package com.xuecheng.media.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author limei
 * @data 2024/7/15
 * @description service层用的更加通用的保存文件的类
 */

@Data
@ToString
public class UploadFileParamsDto {
    /**
     * 文件名称
     */
    private String filename;


    /**
     * 文件类型（文档，音频，视频）
     */
    private String fileType;
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 标签
     */
    private String tags;

    /**
     * 上传人
     */
    private String username;

    /**
     * 备注
     */
    private String remark;

}

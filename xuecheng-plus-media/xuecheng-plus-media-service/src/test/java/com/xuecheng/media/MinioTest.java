package com.xuecheng.media;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.junit.jupiter.api.Test;

/**
 * @author limei
 * @data 2024/7/15
 * @description minio测试类
 */
public class MinioTest {
        static MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.101.65:9000")
                .credentials("minioadmin", "minioadmin")
                .build();

    //上传文件
    @Test
    public  void upload() {
        try {
            UploadObjectArgs testbucket = UploadObjectArgs.builder()
                    .bucket("testbucket")
//                    .object("test001.mp4")
                    .object("1.jpg")//上传后的对象名
                    .filename("D:\\xczx\\upload\\1.jpg")    //指定本地上传路径
//                    .contentType("video/mp4")//默认根据扩展名确定文件内容类型，也可以指定
                    .build();
            minioClient.uploadObject(testbucket);
            System.out.println("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }

    }

}

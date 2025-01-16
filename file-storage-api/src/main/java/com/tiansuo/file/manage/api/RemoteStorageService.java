package com.tiansuo.file.manage.api;

import com.tiansuo.file.manage.factory.StorageFallbackFactory;
import com.tiansuo.file.manage.model.dto.BusinessBindFileDTO;
import com.tiansuo.file.manage.model.vo.FileUploadResultVo;
import com.tiansuo.file.manage.response.ResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "remoteStorageService", value = "file-manage-cloud", fallbackFactory = StorageFallbackFactory.class)
public interface RemoteStorageService {

    /**
     * 绑定业务数据和文件数据
     */
    @PostMapping("/bind/business")
    ResultModel<Boolean> bindBusinessAndFile(@RequestBody BusinessBindFileDTO businessBindFileDTO);

    /**
     * 根据businessKey查询绑定的文件列表
     *
     * @param businessKey 业务唯一标识
     * @return 绑定的文件列表
     */
    @GetMapping("/query/file")
    ResultModel<List<FileUploadResultVo>> getFileByBusinessKey(@RequestParam(value = "businessKey") String businessKey);

    /**
     * 根据businessKey删除文件
     *
     * @param businessKey 业务唯一标识
     */
    @GetMapping("/delete/file/businessKey")
    ResultModel<Boolean> deleteFileByBusinessKey(@RequestParam(value = "businessKey") String businessKey);

    /**
     * 根据fileKey删除文件
     *
     * @param fileKey 文件唯一标识
     */
    @GetMapping("/delete/file/fileKey")
    ResultModel<Boolean> deleteFileByFileKey(@RequestParam(value = "fileKey") String fileKey);
}
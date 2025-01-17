package com.tiansuo.file.storage.api.factory;

import com.tiansuo.file.storage.api.model.dto.BusinessBindFileDTO;
import com.tiansuo.file.storage.api.model.vo.FileUploadResultVo;
import com.tiansuo.file.storage.api.service.RemoteStorageService;
import com.tiansuo.file.storage.api.response.ResultModel;
import com.tiansuo.file.storage.api.response.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级
 */
@Component
@Slf4j
public class StorageFallbackFactory implements FallbackFactory<RemoteStorageService> {

    @Override
    public RemoteStorageService create(Throwable cause) {
        log.error("文件存储服务远程调用失败,{}",cause.getMessage());
        cause.printStackTrace();
        return new RemoteStorageService() {
            @Override
            public ResultModel<Boolean> bindBusinessAndFile(BusinessBindFileDTO businessBindFileDTO) {

                return ResultModel.fail(ReturnCodeEnum.RC500.getCode(),"业务绑定文件出现异常!");
            }

            @Override
            public ResultModel<List<FileUploadResultVo>> getFileByBusinessKey(String businessKey) {
                return ResultModel.fail(ReturnCodeEnum.RC500.getCode(),"获取文件出现异常!");
            }

            @Override
            public ResultModel<Boolean> deleteFileByBusinessKey(String businessKey) {
                return ResultModel.fail(ReturnCodeEnum.RC500.getCode(),"根据业务key删除文件出现异常!");
            }

            @Override
            public ResultModel<Boolean> deleteFileByFileKey(String fileKey) {
                return ResultModel.fail(ReturnCodeEnum.RC500.getCode(),"根据文件key删除文件出现异常!");
            }
        };
    }
}

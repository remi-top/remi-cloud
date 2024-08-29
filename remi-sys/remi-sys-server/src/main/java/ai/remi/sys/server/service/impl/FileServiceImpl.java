package ai.remi.sys.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.sys.domain.entity.File;
import ai.remi.sys.infra.mapper.FileMapper;
import ai.remi.sys.server.service.FileService;
import org.springframework.stereotype.Service;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 文件管理(File)服务实现层
 */
@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

}


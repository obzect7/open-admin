package com.cxp.openadmin.dao;

import com.cxp.openadmin.domain.dto.backed.CommonFile;
import com.cxp.openadmin.domain.entity.CmCodeGrp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommonFileMapper extends Mapper<CmCodeGrp> {

    List<CommonFile> selectFileList(@Param("fileMap") CommonFile info);

    int  saveFile(@Param("fileMap") CommonFile info);

    int  updateFilGrpSeq(@Param("fileMap") CommonFile info);

    int  deleteFileSeq(@Param("fileMap") CommonFile info);

    int  deleteFileGrpSeq(@Param("fileMap") CommonFile info);

}
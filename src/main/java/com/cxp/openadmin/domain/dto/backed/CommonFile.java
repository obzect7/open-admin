package com.cxp.openadmin.domain.dto.backed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.File;
@Setter
@Getter
@ToString
public class CommonFile {
    @Id
    @Column(name = "FILE_SEQ", length = 30, nullable = false)
    private String file_seq;

	@Column(name = "FILE_GRP_SEQ", length = 30)
	private String file_grp_seq;

	@Column(name = "FILE_NO", length = 60)
	private String file_no;

	@Column(name = "FILE_NM", length = 128)
	private String file_nm;

	@Column(name = "FILE_TYPE", length = 8)
	private String file_type;

	@Column(name = "FILE_SIZE", precision = 53, scale = 0)
	private Long file_size;

	@Column(name = "FILE_PATH", length = 256)
	private String file_path;

	@Column(name = "FILE_DESC", length = 1024)
	private String file_desc;

	@Column(name = "SORT_ORDER",
			precision = 53, scale = 0)
	private int sort_order;

	@Column(name = "ORGIN_FILE_NM", length = 1024)
	private String orgin_file_nm;

	@Column(name = "USE_YN", length = 1)
	private String use_yn;

	@Column(name = "FILE_CONT", columnDefinition = "image")
	@Lob @Basic(fetch = FetchType.LAZY)
	private byte[] file_cont;

	@Column(name = "THUMBNAIL_FILE_PATH", length = 510)
	private String thumbnail_file_path;

	@Column(name = "THUMBNAIL_FILE_NM", length = 1024)
	private String thumbnail_file_nm;

	@Column(name = "SAVE_TYPE", length = 1)
	private String save_type;

	@Column(name = "THUMBNAIL_FILE_CONT", columnDefinition = "image")
	@Lob @Basic(fetch = FetchType.LAZY)
	private byte[] thumbnail_file_cont;

	@Column(name = "THUMBNAIL_SAVE_TYPE", length = 1)
	private String thumbnail_save_type;

	@Column(name = "THUMBNAIL_FILE_SIZE", precision = 53, scale = 0)
	private Double thumbnail_file_size;

    @JsonIgnore
    @Transient
    private String _preview;

    @JsonIgnore
    @Transient
    private String _thumbnail;

    @JsonIgnore
    @Transient
    private String _download;

    // CKEditor
    private int uploaded = 1;
    private String fileName;
    private String url;

    ///
    private File file;

//    private MultipartFile multipartFile;

    // 첨부구분용
    private String type;

    ///
    private boolean exitsFile;    // 파일 존재여부.(true/false)


    @JsonProperty("preview")
    public String preview() {
        if (StringUtils.isEmpty(_preview)) {
            return "/api/v1/comm/files/preview?fileSeq=" + getId();
        }
        return _preview;
    }

    @JsonProperty("thumbnail")
    public String thumbnail() {
        if (StringUtils.isEmpty(_thumbnail)) {
            return "/api/v1/comm/files/thumbnail?fileSeq=" + getId();
        }
        return _thumbnail;
    }

    @JsonProperty("download")
    public String download() {
        if (StringUtils.isEmpty(_download)) {
            return "/api/v1/comm/files/download?file_seq=" + getId();
        }
        return _download;
    }

    @Transient
    public String getThumbnailFileName() {
        return FilenameUtils.getBaseName(getFile_nm()) + "-thumbnail" + "." + FilenameUtils.getExtension(getFile_nm());
    }

    public String getId() {
        return file_seq;
    }
}

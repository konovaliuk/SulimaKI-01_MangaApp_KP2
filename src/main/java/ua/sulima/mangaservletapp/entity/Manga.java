package ua.sulima.mangaservletapp.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data @Builder
public class Manga {

    private Integer id;

    private String mangaName;

    private Integer authorId;

    private Integer artistId;

    private Short releaseYear;

    private Long translatorId;

    private String alternativeMangaName;

    private Timestamp addDatetime;

    private Timestamp updateDatetime;

    private Timestamp approvalDatetime;

    private String previewImagePath;

    private Boolean isApproved;

}

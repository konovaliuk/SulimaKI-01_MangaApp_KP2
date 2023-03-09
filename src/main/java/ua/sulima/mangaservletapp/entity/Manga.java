package ua.sulima.mangaservletapp.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Manga {

    private Long id;

    private String mangaName;

    private Integer authorId;

    private Integer artistId;

    private Short releaseYear;

    private Integer translatorId;

    private String alternativeMangaName;

    private Timestamp addDatetime;

    private Timestamp updateDatetime;

    private Timestamp approvalDatetime;

    private String previewImagePath;

    private Boolean isApproved;

}

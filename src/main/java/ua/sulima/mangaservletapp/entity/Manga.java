package ua.sulima.mangaservletapp.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Manga {

    private long id;

    private String mangaName;

    private int authorId;

    private int artistId;

    private short releaseYear;

    private int translatorId;

    private String alternativeMangaName;

    private Timestamp addDatetime;

    private Timestamp updateDatetime;

    private Timestamp approvalDatetime;

    private String previewImagePath;

    private boolean isApproved;

}

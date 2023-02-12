package io.github.wong1988.media;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.content.FileProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/*
2   * Copyright (C) 2007 The Android Open Source Project
3   *
4   * Licensed under the Apache License, Version 2.0 (the "License");
5   * you may not use this file except in compliance with the License.
6   * You may obtain a copy of the License at
7   *
8   *      http://www.apache.org/licenses/LICENSE-2.0
9   *
10  * Unless required by applicable law or agreed to in writing, software
11  * distributed under the License is distributed on an "AS IS" BASIS,
12  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
13  * See the License for the specific language governing permissions and
14  * limitations under the License.
15  *
16  *      android 9.0 package android.media.MediaFile 复刻修改
17  *
18  * 音频 1-50 区间
19  * 视频 51-100 区间
20  * 图片 101-150 区间
21  * 音视频列表文件（非脱机播放） 151-200 区间
22  * Drm（加密文件） 201-250 区间
23  * Excel 251-300 区间
24  * Word 301-350 区间
25  * PowerPoint 351-400 区间
26  * PDF 401-450 区间
27  * 压缩包 451-500 区间
28  * 其它 10000-
*/
public class MediaCenter {

    // 音频类型【数字声音格式】
    static final int FILE_TYPE_MP3 = 1;
    static final int FILE_TYPE_M4A = 2;
    static final int FILE_TYPE_WAV = 3;
    static final int FILE_TYPE_AMR = 4;
    static final int FILE_TYPE_AWB = 5;
    static final int FILE_TYPE_WMA = 6;
    static final int FILE_TYPE_OGG = 7;
    static final int FILE_TYPE_AAC = 8;
    static final int FILE_TYPE_MKA = 9;
    static final int FILE_TYPE_FLAC = 10;
    private static final int FIRST_AUDIO_FILE_TYPE = FILE_TYPE_MP3;
    private static final int LAST_AUDIO_FILE_TYPE = FILE_TYPE_FLAC;

    // MIDI文件类型【音乐演奏指令序列】
    static final int FILE_TYPE_MID = 21;
    static final int FILE_TYPE_SMF = 22;
    static final int FILE_TYPE_IMY = 23;
    private static final int FIRST_MIDI_FILE_TYPE = FILE_TYPE_MID;
    private static final int LAST_MIDI_FILE_TYPE = FILE_TYPE_IMY;
    // 音频扩展名数组
    public static final String[] AUDIO_EXTENSIONS;

    // 视频格式
    static final int FILE_TYPE_MP4 = 51;
    static final int FILE_TYPE_M4V = 52;
    static final int FILE_TYPE_3GPP = 53;
    static final int FILE_TYPE_3GPP2 = 54;
    static final int FILE_TYPE_WMV = 55;
    static final int FILE_TYPE_ASF = 56;
    static final int FILE_TYPE_MKV = 57;
    static final int FILE_TYPE_MP2TS = 58;
    static final int FILE_TYPE_AVI = 59;
    static final int FILE_TYPE_WEBM = 60;
    static final int FILE_TYPE_MP2PS = 61;
    static final int FILE_TYPE_QT = 62;
    private static final int FIRST_VIDEO_FILE_TYPE = FILE_TYPE_MP4;
    private static final int LAST_VIDEO_FILE_TYPE = FILE_TYPE_QT;
    // 视频扩展名数组
    public static final String[] VIDEO_EXTENSIONS;

    // 图片格式
    static final int FILE_TYPE_JPEG = 101;
    static final int FILE_TYPE_GIF = 102;
    static final int FILE_TYPE_PNG = 103;
    static final int FILE_TYPE_BMP = 104;
    static final int FILE_TYPE_WBMP = 105;
    static final int FILE_TYPE_WEBP = 106;
    static final int FILE_TYPE_HEIF = 107;
    private static final int FIRST_IMAGE_FILE_TYPE = FILE_TYPE_JPEG;
    private static final int LAST_IMAGE_FILE_TYPE = FILE_TYPE_HEIF;

    // Raw图片格式
    static final int FILE_TYPE_DNG = 120;
    static final int FILE_TYPE_CR2 = 121;
    static final int FILE_TYPE_NEF = 122;
    static final int FILE_TYPE_NRW = 123;
    static final int FILE_TYPE_ARW = 124;
    static final int FILE_TYPE_RW2 = 125;
    static final int FILE_TYPE_ORF = 126;
    static final int FILE_TYPE_RAF = 127;
    static final int FILE_TYPE_PEF = 128;
    static final int FILE_TYPE_SRW = 129;
    private static final int FIRST_RAW_IMAGE_FILE_TYPE = FILE_TYPE_DNG;
    private static final int LAST_RAW_IMAGE_FILE_TYPE = FILE_TYPE_SRW;
    // 图片扩展名数组
    public static final String[] IMAGE_EXTENSIONS;

    // 播放列表文件
    static final int FILE_TYPE_M3U = 151;
    static final int FILE_TYPE_PLS = 152;
    static final int FILE_TYPE_WPL = 153;
    static final int FILE_TYPE_HTTPLIVE = 154;
    private static final int FIRST_PLAYLIST_FILE_TYPE = FILE_TYPE_M3U;
    private static final int LAST_PLAYLIST_FILE_TYPE = FILE_TYPE_HTTPLIVE;

    // Drm文件
    static final int FILE_TYPE_FL = 201;
    private static final int FIRST_DRM_FILE_TYPE = FILE_TYPE_FL;
    private static final int LAST_DRM_FILE_TYPE = FILE_TYPE_FL;

    // Excel文件
    static final int FILE_TYPE_MS_EXCEL = 251;
    static final int FILE_TYPE_MS_XLAM_EXCEL = 252;
    static final int FILE_TYPE_MS_XLSB_EXCEL = 253;
    static final int FILE_TYPE_MS_XLSM_EXCEL = 254;
    static final int FILE_TYPE_MS_XLTM_EXCEL = 255;
    static final int FILE_TYPE_MS_XLSX_EXCEL = 256;
    static final int FILE_TYPE_MS_XLTX_EXCEL = 257;
    private static final int FIRST_EXCEL_FILE_TYPE = FILE_TYPE_MS_EXCEL;
    private static final int LAST_EXCEL_FILE_TYPE = FILE_TYPE_MS_XLTX_EXCEL;

    // Word文件
    static final int FILE_TYPE_MS_WORD = 301;
    static final int FILE_TYPE_MS_DOCM_WORD = 302;
    static final int FILE_TYPE_MS_DOCX_WORD = 303;
    static final int FILE_TYPE_MS_DOTM_WORD = 304;
    static final int FILE_TYPE_MS_DOTX_WORD = 305;
    private static final int FIRST_WORD_FILE_TYPE = FILE_TYPE_MS_WORD;
    private static final int LAST_WORD_FILE_TYPE = FILE_TYPE_MS_DOTX_WORD;

    // PowerPoint文件
    static final int FILE_TYPE_MS_POWERPOINT = 351;
    static final int FILE_TYPE_MS_POTM_POWERPOINT = 352;
    static final int FILE_TYPE_MS_POTX_POWERPOINT = 353;
    static final int FILE_TYPE_MS_PPAM_POWERPOINT = 354;
    static final int FILE_TYPE_MS_PPSM_POWERPOINT = 355;
    static final int FILE_TYPE_MS_PPSX_POWERPOINT = 356;
    static final int FILE_TYPE_MS_PPTM_POWERPOINT = 357;
    static final int FILE_TYPE_MS_PPTX_POWERPOINT = 358;
    private static final int FIRST_PPT_FILE_TYPE = FILE_TYPE_MS_POWERPOINT;
    private static final int LAST_PPT_FILE_TYPE = FILE_TYPE_MS_PPTX_POWERPOINT;

    // PDF文件
    static final int FILE_TYPE_PDF = 401;

    // 压缩包
    static final int FILE_TYPE_ZIP = 451;
    static final int FILE_TYPE_RAR = 452;
    static final int FILE_TYPE_7Z = 453;
    static final int FILE_TYPE_CAB = 454;
    static final int FILE_TYPE_ACE = 455;
    static final int FILE_TYPE_CFS = 456;
    static final int FILE_TYPE_DGC = 457;
    static final int FILE_TYPE_LHA = 458;
    static final int FILE_TYPE_LZH = 459;
    static final int FILE_TYPE_TAR = 460;
    static final int FILE_TYPE_GZ = 461;
    static final int FILE_TYPE_BZ = 462;
    static final int FILE_TYPE_BZ2 = 463;
    static final int FILE_TYPE_UVZ = 464;
    private static final int FIRST_ZIP_FILE_TYPE = FILE_TYPE_ZIP;
    private static final int LAST_ZIP_FILE_TYPE = FILE_TYPE_UVZ;

    // 其它文件
    static final int FILE_TYPE_TEXT = 10000;
    static final int FILE_TYPE_HTML = 10001;
    static final int FILE_TYPE_XML = 10002;
    static final int FILE_TYPE_APP = 10003;

    // 安装包扩展名数组
    public static final String[] INSTALL_EXTENSIONS;

    public enum FileClassify {
        UNKNOWN,
        MEDIA_AUDIO,
        MEDIA_AUDIO_MIDI,
        MEDIA_VIDEO,
        MEDIA_IMAGE,
        MEDIA_IMAGE_RAW,
        MEDIA_PLAY_LIST,
        DRM,
        DOCUMENT_EXCEL,
        DOCUMENT_WORD,
        DOCUMENT_POWER_POINT,
        DOCUMENT_PDF,
        TXT,
        HTML,
        XML,
        APK,
        COMPRESS
    }

    public static class MediaFileType {
        public final String extension;
        public final int fileType;
        public final String mimeType;
        public final FileClassify classify;

        MediaFileType(String extension, int fileType, String mimeType, FileClassify classify) {
            this.extension = extension;
            this.fileType = fileType;
            this.mimeType = mimeType;
            this.classify = classify;
        }
    }

    // KEY:MP3 VALUE:{1,audio/mpeg"}
    private static final HashMap<String, MediaFileType> sFileTypeMap
            = new HashMap<String, MediaFileType>();
    // KEY:audio/mpeg VALUE:1
    private static final HashMap<String, Integer> sMimeTypeMap
            = new HashMap<String, Integer>();

    static void addFileType(String extension, int fileType, String mimeType, FileClassify classify) {
        sFileTypeMap.put(extension, new MediaFileType(extension, fileType, mimeType, classify));
        sMimeTypeMap.put(mimeType, fileType);
    }

    static {

        // Audio
        addFileType("MP3", FILE_TYPE_MP3, "audio/mpeg", FileClassify.MEDIA_AUDIO);
        addFileType("MPGA", FILE_TYPE_MP3, "audio/mpeg", FileClassify.MEDIA_AUDIO);
        addFileType("M4A", FILE_TYPE_M4A, "audio/mp4", FileClassify.MEDIA_AUDIO);
        addFileType("WAV", FILE_TYPE_WAV, "audio/x-wav", FileClassify.MEDIA_AUDIO);
        addFileType("AMR", FILE_TYPE_AMR, "audio/amr", FileClassify.MEDIA_AUDIO);
        addFileType("AWB", FILE_TYPE_AWB, "audio/amr-wb", FileClassify.MEDIA_AUDIO);
        addFileType("WMA", FILE_TYPE_WMA, "audio/x-ms-wma", FileClassify.MEDIA_AUDIO);
        addFileType("OGG", FILE_TYPE_OGG, "audio/ogg", FileClassify.MEDIA_AUDIO);
        addFileType("OGG", FILE_TYPE_OGG, "application/ogg", FileClassify.MEDIA_AUDIO);
        addFileType("OGA", FILE_TYPE_OGG, "application/ogg", FileClassify.MEDIA_AUDIO);
        addFileType("AAC", FILE_TYPE_AAC, "audio/aac", FileClassify.MEDIA_AUDIO);
        addFileType("AAC", FILE_TYPE_AAC, "audio/aac-adts", FileClassify.MEDIA_AUDIO);
        addFileType("MKA", FILE_TYPE_MKA, "audio/x-matroska", FileClassify.MEDIA_AUDIO);
        addFileType("FLAC", FILE_TYPE_FLAC, "audio/flac", FileClassify.MEDIA_AUDIO);

        // MIDI
        addFileType("MID", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("MIDI", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("XMF", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("RTTTL", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("RTX", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("OTA", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("MXMF", FILE_TYPE_MID, "audio/midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("SMF", FILE_TYPE_SMF, "audio/sp-midi", FileClassify.MEDIA_AUDIO_MIDI);
        addFileType("IMY", FILE_TYPE_IMY, "audio/imelody", FileClassify.MEDIA_AUDIO_MIDI);
        AUDIO_EXTENSIONS = new String[]{".MP3", ".MPGA", ".M4A", ".WAV", ".AMR", ".AWB", ".WMA", ".OGG", ".OGA", ".AAC", ".MKA", ".FLAC", ".MID", ".MIDI", ".XMF", ".RTTTL", ".RTX", ".OTA", ".MXMF", ".SMF", ".IMY"};

        // Video
        addFileType("MPEG", FILE_TYPE_MP4, "video/mpeg", FileClassify.MEDIA_VIDEO);
        addFileType("MPG", FILE_TYPE_MP4, "video/mpeg", FileClassify.MEDIA_VIDEO);
        addFileType("MP4", FILE_TYPE_MP4, "video/mp4", FileClassify.MEDIA_VIDEO);
        addFileType("M4V", FILE_TYPE_M4V, "video/mp4", FileClassify.MEDIA_VIDEO);
        addFileType("3GP", FILE_TYPE_3GPP, "video/3gpp", FileClassify.MEDIA_VIDEO);
        addFileType("3GPP", FILE_TYPE_3GPP, "video/3gpp", FileClassify.MEDIA_VIDEO);
        addFileType("3G2", FILE_TYPE_3GPP2, "video/3gpp2", FileClassify.MEDIA_VIDEO);
        addFileType("3GPP2", FILE_TYPE_3GPP2, "video/3gpp2", FileClassify.MEDIA_VIDEO);
        addFileType("WMV", FILE_TYPE_WMV, "video/x-ms-wmv", FileClassify.MEDIA_VIDEO);
        addFileType("ASF", FILE_TYPE_ASF, "video/x-ms-asf", FileClassify.MEDIA_VIDEO);
        addFileType("MKV", FILE_TYPE_MKV, "video/x-matroska", FileClassify.MEDIA_VIDEO);
        addFileType("TS", FILE_TYPE_MP2TS, "video/mp2ts", FileClassify.MEDIA_VIDEO);
        addFileType("AVI", FILE_TYPE_AVI, "video/avi", FileClassify.MEDIA_VIDEO);
        addFileType("WEBM", FILE_TYPE_WEBM, "video/webm", FileClassify.MEDIA_VIDEO);
        addFileType("MPG", FILE_TYPE_MP2PS, "video/mp2p", FileClassify.MEDIA_VIDEO);
        addFileType("MPEG", FILE_TYPE_MP2PS, "video/mp2p", FileClassify.MEDIA_VIDEO);
        addFileType("MOV", FILE_TYPE_QT, "video/quicktime", FileClassify.MEDIA_VIDEO);
        VIDEO_EXTENSIONS = new String[]{".MPEG", ".MPG", ".MP4", ".M4V", ".3GP", ".3GPP", ".3G2", ".3GPP2", ".WMV", ".ASF", ".MKV", ".TS", ".AVI", ".WEBM", ".MOV"};

        // Image
        addFileType("JPG", FILE_TYPE_JPEG, "image/jpeg", FileClassify.MEDIA_IMAGE);
        addFileType("JPEG", FILE_TYPE_JPEG, "image/jpeg", FileClassify.MEDIA_IMAGE);
        addFileType("GIF", FILE_TYPE_GIF, "image/gif", FileClassify.MEDIA_IMAGE);
        addFileType("PNG", FILE_TYPE_PNG, "image/png", FileClassify.MEDIA_IMAGE);
        addFileType("BMP", FILE_TYPE_BMP, "image/x-ms-bmp", FileClassify.MEDIA_IMAGE);
        addFileType("WBMP", FILE_TYPE_WBMP, "image/vnd.wap.wbmp", FileClassify.MEDIA_IMAGE);
        addFileType("WEBP", FILE_TYPE_WEBP, "image/webp", FileClassify.MEDIA_IMAGE);
        addFileType("HEIC", FILE_TYPE_HEIF, "image/heif", FileClassify.MEDIA_IMAGE);
        addFileType("HEIF", FILE_TYPE_HEIF, "image/heif", FileClassify.MEDIA_IMAGE);

        // Raw Image
        addFileType("DNG", FILE_TYPE_DNG, "image/x-adobe-dng", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("CR2", FILE_TYPE_CR2, "image/x-canon-cr2", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("NEF", FILE_TYPE_NEF, "image/x-nikon-nef", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("NRW", FILE_TYPE_NRW, "image/x-nikon-nrw", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("ARW", FILE_TYPE_ARW, "image/x-sony-arw", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("RW2", FILE_TYPE_RW2, "image/x-panasonic-rw2", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("ORF", FILE_TYPE_ORF, "image/x-olympus-orf", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("RAF", FILE_TYPE_RAF, "image/x-fuji-raf", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("PEF", FILE_TYPE_PEF, "image/x-pentax-pef", FileClassify.MEDIA_IMAGE_RAW);
        addFileType("SRW", FILE_TYPE_SRW, "image/x-samsung-srw", FileClassify.MEDIA_IMAGE_RAW);
        IMAGE_EXTENSIONS = new String[]{".JPG", ".JPEG", ".GIF", ".PNG", ".BMP", ".WBMP", ".WEBP", ".HEIC", ".HEIF", ".DNG", ".CR2", ".NEF", ".NRW", ".ARW", ".RW2", ".ORF", ".RAF", ".PEF", ".SRW"};

        // 播放列表文件
        addFileType("M3U", FILE_TYPE_M3U, "audio/x-mpegurl", FileClassify.MEDIA_PLAY_LIST);
        addFileType("M3U", FILE_TYPE_M3U, "application/x-mpegurl", FileClassify.MEDIA_PLAY_LIST);
        addFileType("PLS", FILE_TYPE_PLS, "audio/x-scpls", FileClassify.MEDIA_PLAY_LIST);
        addFileType("WPL", FILE_TYPE_WPL, "application/vnd.ms-wpl", FileClassify.MEDIA_PLAY_LIST);
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "application/vnd.apple.mpegurl", FileClassify.MEDIA_PLAY_LIST);
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "audio/mpegurl", FileClassify.MEDIA_PLAY_LIST);
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "audio/x-mpegurl", FileClassify.MEDIA_PLAY_LIST);

        // DRM文件
        addFileType("FL", FILE_TYPE_FL, "application/x-android-drm-fl", FileClassify.DRM);

        // Excel文件
        addFileType("XLA", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLC", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLM", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLS", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLT", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLW", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLAM", FILE_TYPE_MS_XLAM_EXCEL, "application/vnd.ms-excel.addin.macroenabled.12", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLSB", FILE_TYPE_MS_XLSB_EXCEL, "application/vnd.ms-excel.sheet.binary.macroenabled.12", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLSM", FILE_TYPE_MS_XLSM_EXCEL, "application/vnd.ms-excel.sheet.macroenabled.12", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLTM", FILE_TYPE_MS_XLTM_EXCEL, "application/vnd.ms-excel.template.macroenabled.12", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLSX", FILE_TYPE_MS_XLSX_EXCEL, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", FileClassify.DOCUMENT_EXCEL);
        addFileType("XLTX", FILE_TYPE_MS_XLTX_EXCEL, "application/vnd.openxmlformats-officedocument.spreadsheetml.template", FileClassify.DOCUMENT_EXCEL);

        // Word文件
        addFileType("DOC", FILE_TYPE_MS_WORD, "application/msword", FileClassify.DOCUMENT_WORD);
        addFileType("DOT", FILE_TYPE_MS_WORD, "application/msword", FileClassify.DOCUMENT_WORD);
        addFileType("DOCM", FILE_TYPE_MS_DOCM_WORD, "application/vnd.ms-word.document.macroenabled.12", FileClassify.DOCUMENT_WORD);
        addFileType("DOCX", FILE_TYPE_MS_DOCX_WORD, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", FileClassify.DOCUMENT_WORD);
        addFileType("DOTM", FILE_TYPE_MS_DOTM_WORD, "application/vnd.ms-word.template.macroenabled.12", FileClassify.DOCUMENT_WORD);
        addFileType("DOTX", FILE_TYPE_MS_DOTX_WORD, "application/vnd.openxmlformats-officedocument.wordprocessingml.template", FileClassify.DOCUMENT_WORD);

        // PowerPoint文件
        addFileType("PPT", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("PPS", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("POT", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("POTM", FILE_TYPE_MS_POTM_POWERPOINT, "application/vnd.ms-powerpoint.template.macroenabled.12", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("POTX", FILE_TYPE_MS_POTX_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.template", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("PPAM", FILE_TYPE_MS_PPAM_POWERPOINT, "application/vnd.ms-powerpoint.addin.macroenabled.12", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("PPSM", FILE_TYPE_MS_PPSM_POWERPOINT, "application/vnd.ms-powerpoint.slideshow.macroenabled.12", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("PPSX", FILE_TYPE_MS_PPSX_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.slideshow", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("PPTM", FILE_TYPE_MS_PPTM_POWERPOINT, "application/vnd.ms-powerpoint.presentation.macroenabled.12", FileClassify.DOCUMENT_POWER_POINT);
        addFileType("PPTX", FILE_TYPE_MS_PPTX_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.presentation", FileClassify.DOCUMENT_POWER_POINT);

        // PDF文件
        addFileType("PDF", FILE_TYPE_PDF, "application/pdf", FileClassify.DOCUMENT_PDF);

        // 压缩包文件
        addFileType("ZIP", FILE_TYPE_ZIP, "application/zip", FileClassify.COMPRESS);
        addFileType("RAR", FILE_TYPE_RAR, "application/x-rar-compressed", FileClassify.COMPRESS);
        addFileType("7Z", FILE_TYPE_7Z, "application/x-7z-compressed", FileClassify.COMPRESS);
        addFileType("CAB", FILE_TYPE_CAB, "application/vnd.ms-cab-compressed", FileClassify.COMPRESS);
        addFileType("ACE", FILE_TYPE_ACE, "application/x-ace-compressed", FileClassify.COMPRESS);
        addFileType("CFS", FILE_TYPE_CFS, "application/x-cfs-compressed", FileClassify.COMPRESS);
        addFileType("DGC", FILE_TYPE_DGC, "aapplication/x-dgc-compressed", FileClassify.COMPRESS);
        addFileType("LHA", FILE_TYPE_LHA, "application/x-lzh-compressed", FileClassify.COMPRESS);
        addFileType("LZH", FILE_TYPE_LZH, "application/x-lzh-compressed", FileClassify.COMPRESS);
        addFileType("TAR", FILE_TYPE_TAR, "application/x-tar", FileClassify.COMPRESS);
        addFileType("GZ", FILE_TYPE_GZ, "application/x-gzip", FileClassify.COMPRESS);
        addFileType("BZ", FILE_TYPE_BZ, "application/x-bzip", FileClassify.COMPRESS);
        addFileType("BZ2", FILE_TYPE_BZ2, "application/x-bzip2", FileClassify.COMPRESS);
        addFileType("UVZ", FILE_TYPE_UVZ, "application/vnd.dece.zip", FileClassify.COMPRESS);

        // 其它文件
        addFileType("TXT", FILE_TYPE_TEXT, "text/plain", FileClassify.TXT);
        addFileType("HTM", FILE_TYPE_HTML, "text/html", FileClassify.HTML);
        addFileType("HTML", FILE_TYPE_HTML, "text/html", FileClassify.HTML);
        addFileType("XML", FILE_TYPE_XML, "text/plain", FileClassify.XML);
        addFileType("APK", FILE_TYPE_APP, "application/vnd.android.package-archive", FileClassify.APK);
        INSTALL_EXTENSIONS = new String[]{".APK"};

    }

    /**
     * 是否音频格式
     */
    public static boolean isAudioFileType(int fileType) {
        return ((fileType >= FIRST_AUDIO_FILE_TYPE &&
                fileType <= LAST_AUDIO_FILE_TYPE) ||
                (fileType >= FIRST_MIDI_FILE_TYPE &&
                        fileType <= LAST_MIDI_FILE_TYPE));
    }

    public static boolean isAudioFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.MEDIA_AUDIO == mediaFileType.classify || FileClassify.MEDIA_AUDIO_MIDI == mediaFileType.classify;
    }

    /**
     * 是否是视频格式
     */
    public static boolean isVideoFileType(int fileType) {
        return fileType >= FIRST_VIDEO_FILE_TYPE &&
                fileType <= LAST_VIDEO_FILE_TYPE;
    }

    public static boolean isVideoFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.MEDIA_VIDEO == mediaFileType.classify;
    }

    /**
     * 是否是图片格式
     */
    public static boolean isImageFileType(int fileType) {
        return (fileType >= FIRST_IMAGE_FILE_TYPE &&
                fileType <= LAST_IMAGE_FILE_TYPE)
                || (fileType >= FIRST_RAW_IMAGE_FILE_TYPE &&
                fileType <= LAST_RAW_IMAGE_FILE_TYPE);
    }

    public static boolean isImageFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.MEDIA_IMAGE == mediaFileType.classify || FileClassify.MEDIA_IMAGE_RAW == mediaFileType.classify;
    }

    /**
     * 是否播放列表格式
     */
    public static boolean isPlayListFileType(int fileType) {
        return (fileType >= FIRST_PLAYLIST_FILE_TYPE &&
                fileType <= LAST_PLAYLIST_FILE_TYPE);
    }

    public static boolean isPlayListFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.MEDIA_PLAY_LIST == mediaFileType.classify;
    }

    /**
     * 是否媒体格式
     */
    public static boolean isMediaFileType(int fileType) {
        return isAudioFileType(fileType) || isVideoFileType(fileType)
                || isImageFileType(fileType) || isPlayListFileType(fileType);
    }

    public static boolean isMediaFileType(@NonNull MediaFileType mediaFileType) {
        return isAudioFileType(mediaFileType) || isVideoFileType(mediaFileType)
                || isImageFileType(mediaFileType) || isPlayListFileType(mediaFileType);
    }

    /**
     * 是否drm格式
     */
    public static boolean isDrmFileType(int fileType) {
        return (fileType >= FIRST_DRM_FILE_TYPE &&
                fileType <= LAST_DRM_FILE_TYPE);
    }

    public static boolean isDrmFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.DRM == mediaFileType.classify;
    }

    /**
     * 是否是Excel格式
     */
    public static boolean isExcelFileType(int fileType) {
        return (fileType >= FIRST_EXCEL_FILE_TYPE &&
                fileType <= LAST_EXCEL_FILE_TYPE);
    }

    public static boolean isExcelFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.DOCUMENT_EXCEL == mediaFileType.classify;
    }

    /**
     * 是否是Word格式
     */
    public static boolean isWordFileType(int fileType) {
        return (fileType >= FIRST_WORD_FILE_TYPE &&
                fileType <= LAST_WORD_FILE_TYPE);
    }

    public static boolean isWordFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.DOCUMENT_WORD == mediaFileType.classify;
    }

    /**
     * 是否是PowerPoint格式
     */
    public static boolean isPowerPointFileType(int fileType) {
        return (fileType >= FIRST_PPT_FILE_TYPE &&
                fileType <= LAST_PPT_FILE_TYPE);
    }

    public static boolean isPowerPointFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.DOCUMENT_POWER_POINT == mediaFileType.classify;
    }

    /**
     * 是否是PDF格式
     */
    public static boolean isPdfFileType(int fileType) {
        return fileType == FILE_TYPE_PDF;
    }

    public static boolean isPdfFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.DOCUMENT_PDF == mediaFileType.classify;
    }

    /**
     * 是否是文档格式
     */
    public static boolean isDocumentFileType(int fileType) {
        return isExcelFileType(fileType) ||
                isWordFileType(fileType) ||
                isPowerPointFileType(fileType) ||
                isPdfFileType(fileType);
    }

    public static boolean isDocumentFileType(@NonNull MediaFileType mediaFileType) {
        return isExcelFileType(mediaFileType) ||
                isWordFileType(mediaFileType) ||
                isPowerPointFileType(mediaFileType) ||
                isPdfFileType(mediaFileType);
    }

    /**
     * 是否是压缩格式
     */
    public static boolean isCompressFileType(int fileType) {
        return (fileType >= FIRST_ZIP_FILE_TYPE &&
                fileType <= LAST_ZIP_FILE_TYPE);
    }

    public static boolean isCompressFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.COMPRESS == mediaFileType.classify;
    }

    /**
     * 是否是TXT格式
     */
    public static boolean isTxtFileType(int fileType) {
        return fileType == FILE_TYPE_TEXT;
    }

    public static boolean isTxtFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.TXT == mediaFileType.classify;
    }

    /**
     * 是否是Html格式
     */
    public static boolean isHtmlFileType(int fileType) {
        return fileType == FILE_TYPE_HTML;
    }

    public static boolean isHtmlFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.HTML == mediaFileType.classify;
    }

    /**
     * 是否是Xml格式
     */
    public static boolean isXmlFileType(int fileType) {
        return fileType == FILE_TYPE_XML;
    }

    public static boolean isXmlFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.XML == mediaFileType.classify;
    }

    /**
     * 是否是安装包格式
     */
    public static boolean isApkFileType(int fileType) {
        return fileType == FILE_TYPE_APP;
    }

    public static boolean isApkFileType(@NonNull MediaFileType mediaFileType) {
        return FileClassify.APK == mediaFileType.classify;
    }

    /**
     * 获取文件的mimeType
     */
    public static MediaFileType getMediaFileType(String path) {
        int lastDot = path.lastIndexOf('.');
        if (lastDot < 0)
            return new MediaFileType("", 0, "*/*", FileClassify.UNKNOWN);
        String extension = path.substring(lastDot + 1).toUpperCase(Locale.ROOT);
        MediaFileType mediaFileType = sFileTypeMap.get(extension);
        if (mediaFileType == null)
            mediaFileType = new MediaFileType(extension, 0, "*/*", FileClassify.UNKNOWN);
        return mediaFileType;
    }

    public static MediaFileType getMediaFileType(File file) {
        return getMediaFileType(file.getAbsolutePath());
    }

    /**
     * 获取文件的mimeType
     */
    public static String getMimeType(String path) {
        MediaFileType mediaFileType = getMediaFileType(path);
        return mediaFileType.mimeType;
    }

    public static String getMimeType(File file) {
        MediaFileType mediaFileType = getMediaFileType(file);
        return mediaFileType.mimeType;
    }

    /**
     * 获取文件的mimeType的Code值
     */
    public static int getMimeTypeCode(String mimeType) {
        Integer value = sMimeTypeMap.get(mimeType);
        return (value == null ? 0 : value.intValue());
    }

    /**
     * 注意：需要读取权限
     * 使用第三方App打开文件
     */
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE})
    public static void openFile(Context context, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            Toast.makeText(context, "文件未找到", Toast.LENGTH_SHORT).show();
            return;
        }

        MediaFileType fileType = getMediaFileType(filePath);
        String mimeType = fileType.mimeType;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(FileProvider.getUriForFile(context, context.getApplicationInfo().packageName + ".media.provider", new File(filePath)), mimeType);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(filePath)), mimeType);
        }

        context.startActivity(intent);

    }

}

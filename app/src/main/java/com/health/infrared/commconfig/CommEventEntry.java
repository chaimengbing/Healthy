package com.health.infrared.commconfig;

/**
 * Created by 123 on 2017/12/4.
 */

public class CommEventEntry {

    public final static String HOME_NAME = "HOME_NAME";
    public final static String IMAGE_POSITION = "IMAGE_POSITION";
    public final static String MAIN_TYPE = "MAIN_TYPE";

    /**
     * 首页
     */
    public final static int TYPE_QUARANTINE = 100;
    public final static int TYPE_INFAREDPIC = 101;

    /**
     * 检疫查验
     */
    public final static int QUARANTINE_REGISTRATION = 0;
    public final static int QUARANTINE_INQUIRE = 1;
    public final static int QUARANTINE_MEDIACAL_EXAMINA = 2;
    public final static int QUARANTINE_CASE_HANDLE = 3;
    public final static int QUARANTINE_COLLECT_SAMPLE = 4;
    public final static int QUARANTINE_SUPERVISE = 5;

    /**
     * 红外图片
     */
    //快速登记
    public final static int INFAREDPIC_RAPID_REGISTRATION = 6;
    //漏警处置
    public final static int INFAREDPIC_DISPOSITION = 7;
    //警情浏览
    public final static int INFAREDPIC_ALERT_BROWSE = 8;
    //追踪登记
    public final static int INFAREDPIC_REGISTRATION = 9;


}

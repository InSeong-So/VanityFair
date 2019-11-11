package web.biz.vanityFair.controller.fragment;

import lombok.Getter;

@Getter
public enum CallMenu
{
    // MAIN_PATH + HEADER
    HOME("home"),
    PRODUCTS("products"),
    DEALS("deals"),
    STORES("stores"),
    ARTICLES("articles"),
    CONTACT("contact"),
    QNA("qna"),
    LOGIN("login"),
    REGISTRATION("registration"),
    MYPROFILE("myprofile"),
    
    // MENU_LEFT
    LA("01"),
    LB("02"),
    LC("03"),
    LD("04"),
    LE("05"),
    LF("06"),
    LG("07"),
    LH("08"),
    
    // MAIN_CONTENT
    MA("AA"),
    MB("BA"),
    MC("CA"),
    MD("DA"),
    ME("EA"),
    MF("FA"),
    MG("GA"),
    MH("HA")
    ;
    
    private String title;

    private CallMenu(String title)
    {
        this.title = title;
    }
}

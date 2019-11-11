package web.biz.vanityFair.controller.fragment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public enum CallFragmentType
{
    MAIN_PATH("Main", Arrays.asList(CallMenu.HOME, CallMenu.PRODUCTS, CallMenu.DEALS, CallMenu.STORES, CallMenu.ARTICLES, CallMenu.CONTACT, CallMenu.QNA, CallMenu.MYPROFILE, CallMenu.LOGIN, CallMenu.REGISTRATION)),
    MENU_LEFT("Left", Arrays.asList(CallMenu.LA, CallMenu.LB,CallMenu.LC,CallMenu.LD, CallMenu.LE,CallMenu.LF,CallMenu.LG,CallMenu.LH)),
    MAIN_CONTENT("Content", Arrays.asList(CallMenu.MA, CallMenu.MB, CallMenu.MC, CallMenu.MD, CallMenu.ME, CallMenu.MF, CallMenu.MG, CallMenu.MH)),
    EMPTY("None", Collections.emptyList());
    
    private String type;
    
    private List<CallMenu> typeList;
    
    CallFragmentType(String type, List<CallMenu> typeList)
    {
        this.type = type;
        this.typeList = typeList;
    }
    
    public static CallFragmentType findByMenu(String code)
    {
        return Arrays.stream(CallFragmentType.values()).filter(callFragmentType -> callFragmentType.hasFragmentType(code)).findAny().orElse(EMPTY);
    }
    
    public boolean hasFragmentType(String code)
    {
        return typeList.stream().anyMatch(fragmentType -> fragmentType.equals(code));
    }
}

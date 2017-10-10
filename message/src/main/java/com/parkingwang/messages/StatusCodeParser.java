package com.parkingwang.messages;

import android.content.res.Resources;

/**
 * @author 陈永佳 (chenyongjia@parkingwang, yoojiachen@gmail.com)
 */
public interface StatusCodeParser {

    MessageResult onParse(Resources resources, int code, String text);

}

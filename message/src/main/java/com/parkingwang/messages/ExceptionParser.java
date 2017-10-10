package com.parkingwang.messages;

import android.content.res.Resources;

/**
 * @author 陈永佳 (chenyongjia@parkingwang, yoojiachen@gmail.com)
 */
public interface ExceptionParser {

    MessageResult onParse(Resources resources, Throwable error);

}

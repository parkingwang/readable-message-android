package com.parkingwang.messages.impl;

import android.content.res.Resources;

import com.parkingwang.messages.MessageResult;
import com.parkingwang.messages.R;
import com.parkingwang.messages.StatusCodeParser;

import static com.parkingwang.messages.MessageResult.accept;
import static com.parkingwang.messages.MessageResult.reject;

/**
 * @author 陈永佳 (chenyongjia@parkingwang, yoojiachen@gmail.com)
 */
public class DefaultStatusCodeParser implements StatusCodeParser {

    @Override
    public MessageResult onParse(Resources resources, int statusCode) {
        switch (statusCode) {
            case 400:
                return accept(resources.getString(R.string.msg_sc_400));
            case 401:
                return accept(resources.getString(R.string.msg_sc_401));
            case 403:
                return accept(resources.getString(R.string.msg_sc_403));
            case 404:
                return accept(resources.getString(R.string.msg_sc_404));
            case 405:
                return accept(resources.getString(R.string.msg_sc_405));
            case 500:
                return accept(resources.getString(R.string.msg_sc_500));
            case 503:
                return accept(resources.getString(R.string.msg_sc_503));
            case 504:
                return accept(resources.getString(R.string.msg_sc_504));
            default:
                return reject();
        }
    }

}

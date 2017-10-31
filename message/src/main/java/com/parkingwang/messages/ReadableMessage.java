package com.parkingwang.messages;

import android.content.Context;
import android.content.res.Resources;

import com.parkingwang.messages.impl.DefaultNetworkParser;
import com.parkingwang.messages.impl.DefaultStatusCodeParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈永佳 (chenyongjia@parkingwang, yoojiachen@gmail.com)
 */
public class ReadableMessage {

    private final Context mContext;
    private final List<ExceptionParser> mExceptionParsers = new ArrayList<>();
    private final List<StatusCodeParser> mStatusCodeParsers = new ArrayList<>();

    private String mDefaultMessage = "未知错误";

    private ReadableMessage(Context context) {
        mContext = context;
    }

    public static ReadableMessage create(Context context) {
        return new ReadableMessage(context);
    }

    public ReadableMessage usingDefaults() {
        register(new DefaultStatusCodeParser());
        register(new DefaultNetworkParser());
        return this;
    }

    ////////

    public ReadableMessage register(StatusCodeParser parser) {
        mStatusCodeParsers.add(parser);
        return this;
    }

    public ReadableMessage register(ExceptionParser parser) {
        mExceptionParsers.add(parser);
        return this;
    }

    ////////

    /**
     * 设置默认消息资源ID，当解析器无法找到消息时，返回默认消息。
     *
     * @param message 消息资源ID
     * @return ReadableMessage
     */
    public ReadableMessage setDefaultMessage(int message) {
        return setDefaultMessage(mContext.getString(message));
    }

    /**
     * 设置默认消息，当解析器无法找到消息时，返回默认消息。
     *
     * @param message 消息
     * @return ReadableMessage
     */
    public ReadableMessage setDefaultMessage(String message) {
        mDefaultMessage = message;
        return this;
    }

    ////////

    /**
     * 解析Throwable对应的出错消息
     *
     * @param error Throwable出错对象
     * @return 提示消息
     */
    public String messageOfThrowable(Throwable error) {
        final Resources resources = mContext.getResources();
        for (ExceptionParser parser : mExceptionParsers) {
            final MessageResult rs = parser.onParse(resources, error);
            if (rs.passed) {
                return rs.message;
            }
        }
        return mDefaultMessage;
    }

    /**
     * 解析Http Status Code对应的出错消息
     *
     * @param statusCode StatusCode
     * @return 提示消息
     */
    public String messageOfHttpCode(int statusCode) {
        final Resources resources = mContext.getResources();
        for (StatusCodeParser parser : mStatusCodeParsers) {
            final MessageResult rs = parser.onParse(resources, statusCode);
            if (rs.passed) {
                return rs.message;
            }
        }
        return mDefaultMessage;
    }

}

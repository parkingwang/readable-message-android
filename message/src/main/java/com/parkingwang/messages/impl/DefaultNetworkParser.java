package com.parkingwang.messages.impl;

import android.content.res.Resources;

import com.parkingwang.messages.ExceptionParser;
import com.parkingwang.messages.MessageResult;
import com.parkingwang.messages.R;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 * @author 陈永佳 (chenyongjia@parkingwang, yoojiachen@gmail.com)
 */
public class DefaultNetworkParser implements ExceptionParser {

    @Override
    public MessageResult onParse(Resources resources, Throwable error) {
        return MessageResult.accept(parse(resources, error));
    }

    private static String parse(Resources res, Throwable throwable) {
        String msg;
        Throwable cause;
        if (throwable.getCause() != null) {
            cause = throwable.getCause();
            msg = cause.getMessage() != null ? cause.getMessage() : throwable.getMessage();
        } else {
            cause = throwable;
            msg = throwable.getMessage();
        }

        if (msg != null) {
            msg = msg.toUpperCase(Locale.US);
            if (msg.contains("ECONNREFUSED")) { /*Connection refused*/
                return res.getString(R.string.msg_cnn_refuse);
            } else if (msg.contains("ENETUNREACH")
                    || msg.contains("EHOSTUNREACH")
                    || msg.contains("ECONNRESET")
                    || msg.contains("EAI_NODATA")) { /*Network is unreachable*/
                return res.getString(R.string.msg_cnn_unreachable);
            } else if (msg.contains("ETIMEDOUT")
                    || msg.contains("TIMEOUT")) {
                return res.getString(R.string.msg_cnn_timeout);
            } else if (msg.contains("CANCELED")
                    || msg.contains("SOCKET CLOSED")) {
                return res.getString(R.string.msg_cnn_close);
            }
        }

        if (cause instanceof UnknownHostException
                || cause instanceof NoRouteToHostException) {
            return res.getString(R.string.msg_cnn_no_route);
        } else if (cause instanceof SocketTimeoutException) {
            return res.getString(R.string.msg_cnn_timeout);
        } else if ((cause instanceof EOFException) || (cause instanceof ConnectException)) {
            return res.getString(R.string.msg_cnn_close);
        } else {
            return String.format("未知错误(%s,%s)", throwable.getLocalizedMessage(), cause.getClass().getSimpleName());
        }
    }
}

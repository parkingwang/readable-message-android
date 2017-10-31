# ReadableMessage

将Error和Http Status Code状态码解析成对用户友好的提示消息。

> 为公司Android提供统一的出错消息解析实现

## 依赖

> compile 'com.parkingwang:message:1.0.0-ALPHA'

```java

String message = ReadableMessage.create(context)
        .useDefaults()                                          // 使用默认配置；
        .setDefaultMessage("当解析器无法解析错误时，使用此默认消息")
        .messageOfStatusCode(500);

```


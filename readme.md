## 手写RPC框架基础版

## 架构

- Netty

- SpringBoot

- JDK动态代理

### 概要

客户端和服务端基本的Netty服务。
通信协议：
将消息分为消息头和消息体，消息头中包含表示消息总长度（或者消息体长度）的字段。

- 第一、二个字节是魔法数，比如我定义为0X35。

- 第三个字节代表序列化类型。

- 第四个字节是请求类型，如0代表请求1代表响应。

- 第五~十二字节代表消息UUID  第十三~十六字节表示消息长度，即此十六个个字节后面此长度的内容是消息content。

客户端通过JDK的动力代理发送请求（数据序列化）调用服务端，服务端收到请求进行反序列化，根据请求对应的参数通过反射调用真正bean的方法。再进行序列化返回给客户端，客户端收到响应，进行反序列化得到Result。其中因为Netty的异步，这里使用netty concurrent包下的Promise存储返回结果。设计比较基础简陋，之后会增加rpc框架对应的功能，例如：注册中心、服务注册、服务发现、负载均衡等。

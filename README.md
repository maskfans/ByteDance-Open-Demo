# ByteDance-Open-Demo
- 该demo为[ByteDanceOpen SDK](https://github.com/yydzxz/ByteDanceOpen)用法示例

### 申请账号
- 根据[字节跳动开放平台文档](https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#)指引，去[字节跳动小程序管理后台](https://microapp.bytedance.com)注册一个账号。
- [字节跳动小程序管理后台](https://microapp.bytedance.com)账号自带一个[字节跳动第三方平台](https://open.microapp.bytedance.com)账号（登录小程序管理后台后，进入第三方平台直接就是登录状态），创建一个第三平台后，将第三方平台的相关数据填入
`application-dev.yml`
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-122557%402x.png)

### 启动redis
- `access_token`等数据都是保存在redis中，所以需要一个redis服务

- 为了方便使用，项目中提供了一个默认的`redis.conf`, 只修改了两个配置:

  ```bash
  # 把protected-mode改为了no
  protected-mode no
  # 注释了bind
  # bind 127.0.0.1 ::1
  ```
- 本项目使用的redis客户端是[redisson](https://github.com/redisson/redisson)，可以直接使用。如果想修改redis连接相关配置，可以在`application-dev.yml`指定redis的配置文件。

- 如果想要使用jedis，可以自己实现一个`IByteDanceRedisOps`
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200715-144937%402x.png)


### 启动项目
- 启动项目后需要等待字节跳动服务器将ticket推送过来后（一般10分钟以内），才能进行后续的授权等api调用。如果一直没有推送，请确认推送地址是否配置正确
![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200714-130942%402x.png)

### 接口测试
- 请确定ticket已经推送过来了。如果ticket推送过来，日志中会打印"MsgTypeTicketHandler 开始处理消息: xxxx"
  #### 内网穿透
  - 如果没有公网地址，那么需要使用内网穿透工具。比如[ngrok](https://ngrok.com/)
  - 如果使用ngrok，下载好后，在命令行执行`ngrok http 8080`就能得到一个映射到8080端口（项目默认启动端口）的外网地址。
  #### 网页授权
  - 先去[字节跳动小程序管理后台](https://microapp.bytedance.com/app/applist)创建一个小程序
  - 然后去[字节跳动第三方平台](https://open.microapp.bytedance.com/tplist)将刚才创建的小程序的appid添加到【授权测试小程序列表】，以便测试
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200717-210508%402x.png)
  
  - 浏览器中输入授权地址: [https://你的公网地址或者ngrok生成的公网地址/bytedance/auth/goto_auth_url_show]()
  #### 模版管理
  - [获取第三方应用的草稿](http://127.0.0.1:8080/bytedance/template/draft/list)
  - [获取第三方应用的所有模版](http://127.0.0.1:8080/bytedance/template/list)

### 其他注意事项
  - 把本机外网ip配置到白名单
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/QQ20200717-210903%402x.png)
  - 目前字节跳动的[字节跳动开放平台文档](https://bytedance.feishu.cn/docs/doccnYmtnRy6APhKiTfYgW#)还在不断更新，我也会根据他的更新不断新增接口。
  - 如果有接口没有及时更新，可以给我提issue或者PR，着急的话也可以通过sdk暴露的接口自己实现。
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/1407E96CAA9184803B3BF7D53A80649E.jpg)
  - 字节跳动授权流程中的小bug。从gif中可以看到，第一次授权跳转到字节跳动页面时，显示 **授权信息异常** 。必须要管理员先登录小程序管理后台，授权流程才能正确进行。
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/gifs/auth_bug.gif)
  ![image](https://github.com/yydzxz/ByteDance-Open-Demo/blob/master/images/1991595100618_.pic_hd.jpg)
  

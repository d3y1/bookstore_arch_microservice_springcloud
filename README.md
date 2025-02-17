# Bookstore后端：以Spring Cloud微服务实现

## 需求场景

单体架构痛点：体积大，难维护，可以分而治之（微服务）。
单体系统发展到一定程度，拆分成为“用户”、“商品”、“交易”三个能够独立运行的子系统，它们将在一系列非功能性技术模块（认证、授权等）和基础设施（配置中心、服务发现等）的支撑下互相协作，以统一的API网关对外提供与原来单体系统功能一致的服务。

## 运行程序

以下几种途径，可以运行程序，浏览最终的效果：

- 通过Makefile脚本运行：

  ```bash
  # 进入工程根目录
  $ cd bookstore_arch_microservice_springcloud
  
  # 容器方式运行
  $ make start
  ```

  服务地址：[http://localhost:8080](http://localhost:8080)，系统预置了一个用户（`user:tao，pw:123456`）

- 通过Docker容器方式运行：在工程中提供了专门的配置，以便使用docker-compose来运行：

  ```bash
  # 进入工程根目录
  $ cd bookstore_arch_microservice_springcloud
  
  # 启动服务
  $ docker-compose up
  ```

- Maven编译、运行，开发调试使用：
  
  ```bash
  # 使用Maven编译出JAR包后，可使用以下命令直接在本地构建镜像运行
  $ docker-compose -f docker-compose.dev.yml up
  ```
  
- 在IDE环境中运行：

  - 以IntelliJ IDEA为例，Git克隆本项目后，在File -> Open菜单选择本项目所在的目录，或者pom.xml文件，以Maven方式导入工程。
  - 待Maven自动安装依赖后，即可在IDE或者Maven面板中编译全部子模块的程序。
  - 本工程下面八个模块，其中除bookstore-microservices-library-infrastructure外，其余均是SpringBoot工程，将这七个工程的Application类加入到IDEA的Run Dashboard面板中。
  - 在Run Dashboard中先启动“bookstore-microservices-platform-configuration”微服务，然后可一次性启动其余六个子模块的微服务。

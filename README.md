本课程主要是使用 Spring技术栈 + dubbo 开发一个类似当当的图书电商后台的实战教程。

视频学习请戳：http://www.roncoo.com/course/view/70121aaefac043d993b6075ba1a30344

课程特点：

1.课程的技术体系足够系统、全面以及细致：课程中涉及的主要技术包括：
Spring IO (依赖版本管理),
Spring Boot（自动化配置，零XML）,
Spring MVC (RESTful API开发) ,
Spring Security, Spring Security OAuth（RESTful API安全）,
Spring Framework（基础框架,服务层开发）,
Srping Data JPA（数据持久层开发，零SQL）,
Dubbo（服务治理）.
所有的技术全部使用现在的最新版本。

2.全程案例实战驱动讲解和动手演练，每个知识点都会通过实际的代码样例来演示其原理和特性，以模拟真实的案例来驱动讲解各种技术点，帮助同学们在业务背景中理解和掌握复杂的技术。

3.测试驱动开发，整个课程全部采用测试驱动开发的方式，先编写自动化测试用例，然后编写业务代码，在掌握开发技术的基础上，还可以学习到如何针对持久层、业务层和WEB服务层进行单元测试。

4.课程包含很多全网独家的、深入细致的技术讲解以及解密。例如：Spring Data Jpa映射策略，继承策略，抓取策略的控制。让你全面掌握这个强大的ORM框架的每个细节。Spring MVC开发RESTful API时各种映射的处理，异常的处理，异步请求的处理，服务的伪造和文档的自动生成等。Spring Security的详细说明，各种实际的认证和授权需求的处理方法，如何扩展框架的功能，如何与第三方登录或单点登录集成等等。Spring Boot与Dubbo的整合，处理服务的日志、事务、消息、定时任务，多线程等问题。

5.课程中详细演示了一个应用从单块架构到垂直应用架构再到分布式服务架构的演进过程。讲解了如何在前后端分离的架构下设计RESTful API。最终的系统对外提供REST风格的http服务，内部各个垂直应用通过dubbo共享无状态的Java服务。整个系统在Web层和服务层都可以无缝的横向扩展。

课程学完以后能够达到的效果：

1.可以独立搭建分布式RESTful API开发框架

2.可以使用Spring Boot和Spring IO简化Spring项目的配置，提高开发速度。

3.可以使用Spring Data JPA操作关系型数据库，完全面向对象，零SQL的快速开发数据库持久层服务，掌握如何控制自动生成的SQL，确保整个数据库持久层的性能。

4.可以使用Spring MVC快速开发RESTful的API，并处理WEB层常见的问题。掌握异常的处理，文件上传下载，服务的伪造和文档的自动生成，使用异步方式处理Http请求以提高中间件吞吐量等技术。

5.可以使用Spring Security保护RESTful API的安全，通过JavaConfig的方式快速实现各种常见的认证授权需求。掌握‘记住我’，Session管理，第三方(QQ，微信，微博等)登录，单点登录等常见需求的实现方式。

6.理解分布式服务架构，使用Dubbo搭建分布式服务框架，并与Spring Boot和Spring framework结合，处理服务的日志、事务、消息、缓存、定时任务，多线程等问题。

课程主要章节如下:

01.课程介绍，架构说明，案例说明，前置知识

02.环境设置（JDK8, STS, Maven, String IO, String Boot）

03.Spring Data JPA简介

04.对象映射-基本属性映射

05.对象映射-全局命名策略

06.对象映射-双向一对多关系映射

07.对象映射-多对多和一对一关系映射

08.对象映射-继承关系映射

09.Repository-基本增删改查

10.Repository-分页和排序

11.Repository-静态查询

12.Repository-动态查询

13.Repository-自定义Repository实现

14.高级话题-持久化上下文

15.高级话题-抓取策略

16.高级话题-继承策略1

17.高级话题-继承策略2

18.高级话题-乐观锁

19.高级话题-Hibernate Valodator

20.数据库开发小结

21.RestAPI开发-概述

22.RestAPI开发-查询图书请求处理

23.RestAPI开发-参数映射,分页和排序

24.RestAPI开发-正则表达式和JsonView

25.RestAPI开发-新建图书请求处理

26.RestAPI开发-修改和删除,Cookie和Header

27.RestAPI开发-异常和静态资源处理

28.RestAPI开发-拦截器和过滤器

29.RestAPI开发-文件上传下载处理

30.RestAPI开发-异步处理Http请求

31.RestAPI开发-使用Swagger生成文档

32.RestAPI开发-使用WireMock伪造服务

33.RestAPI安全-Spring Security概述和基本配置

34.RestAPI安全-Spring Security内置过滤器链介绍

35.RestAPI安全-实现Http Basic认证

36.RestAPI安全-实现表单认证

37.RestAPI安全-实现'记住我'功能

38.RestAPI安全-在Spring Security中管理Session

39.RestAPI安全-了解授权决策

40.RestAPI安全-使用配置控制URL权限

41.RestAPI安全-使用注解控制方法权限

42.RestAPI安全-自定义授权策略

43.RestAPI安全-常见攻击方式及防护

44.RestAPI安全-OAuth协议介绍

45.RestAPI安全-使用Spring Security OAuth实现第三方登录

46.RestAPI安全-整合CAS实现SSO

47.分布式服务开发-服务层架构概述

48.分布式服务开发-重构代码结构，引入dubbo

49.分布式服务开发-实现增删改查服务1

50.分布式服务开发-实现增删改查服务2

51.分布式服务开发-使用logback记录日志

52.分布式服务开发-使用AOP创建全局日志切片

53.分布式服务开发-事务控制

54.分布式服务开发-缓存处理

55.分布式服务开发-定时任务处理

56.分布式服务开发-配置信息处理

57.分布式服务开发-使用多线程开发提升系统吞吐量1

58.分布式服务开发-使用多线程开发提升系统吞吐量2

59.持续集成概述

60.使用jenkins实现持续集成

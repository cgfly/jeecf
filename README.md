
## 徽章  
[![license Status](https://img.shields.io/badge/License-Apache%202.0-blue.svg?branch=v1.5)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/jianym/jeecf.svg?branch=v1.5)](https://travis-ci.org/jianym/jeecf)  
## 介绍  
Jee Cloud Factory 是基于模版化的代码生成平台，致力于解决企业业务开发效率与代码规范的平衡问题，通过使用通用的抽象模版即保证了业务开发效率，又保证了代码的规范,解决了多人开发风格差异化，可读性差等痛点问题，易于项目的维护与升级。

## 快速开始  
1.下载项目  
2.将 src/main/resources/document/sql/jeecf.sql导入数据库，修改配置文件信息，数据库与redis连接信息  
3.启动Application，默认端口8801，用户名：admin,密码：123456  
4.例子模版文件在 src/main/resources/document/example文件夹下  
## v1.5 新功能与优化  
1.插件化服务，基于插件实现自定义代码生成辅助功能与编排，达到功能差异化和扩展化。  
2.去除异构语言的构建支持，提供基础的操作，可以使用插件进行功能扩展和语言差异化实现。  
3.加入Mysql引擎，支持线上表创建和删除（持续完善更加强大的功能。   
4.优化数据源相关操作，真实数据源可以做为同步表结构和查询数据时使用，假数据源只做为数据源标识使用。  
5.加入虚表配置和插件管理功能，参考wiki文档。  
6.代码的一些优化。  
## 功能说明  
wiki:https://github.com/jianym/jeecf/wiki  
## 总体架构
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture.png)
## MVC架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_mvc.png)
## 代码生成架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_gen.png)
## OSGI插件架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_osgi.png)


## 徽章  
![license Status](https://img.shields.io/badge/License-Apache%202.0-blue.svg?branch=v1.5) 
[![Build Status](https://travis-ci.org/jianym/jeecf.svg?branch=v1.5)](https://travis-ci.org/jianym/jeecf)  
## 介绍  
Jee Cloud Factory 是基于模版化的代码生成平台，致力于解决企业业务开发效率与代码规范的平衡问题，通过使用通用的抽象模版即保证了业务开发效率，又保证了代码的规范,解决了多人开发风格差异化，可读性差等痛点问题，易于项目的维护与升级。

## 快速开始  
1.下载项目  
2.将 src/main/resources/document/sql/jeecf.sql导入数据库，修改配置文件信息，数据库与redis连接信息  
3.启动Application，默认端口8801，用户名：admin,密码：123456  
4.例子模版文件在 src/main/resources/document/example文件夹下  
## 功能说明  
wiki:https://github.com/jianym/jeecf/wiki  
## MVC架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_mvc.png)
## 代码生成架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_gen.png)


## 徽章  
<<<<<<< HEAD
[![license Status](https://img.shields.io/badge/License-Apache%202.0-blue.svg?branch=v2.0)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/cgfly/jeecf.svg?branch=v2.0)](https://travis-ci.org/cgfly/jeecf)  
=======
[![license Status](https://img.shields.io/badge/License-Apache%202.0-blue.svg?branch=v1.5)](http://www.apache.org/licenses/LICENSE-2.0.txt)
[![Build Status](https://travis-ci.org/cgfly/jeecf.svg?branch=master)](https://travis-ci.org/cgfly/jeecf)  
>>>>>>> branch 'master' of https://github.com/jianym/jeecf.git
## 介绍  
Jee Cloud Factory 是基于模版化的代码生成平台，致力于解决企业业务开发效率与代码规范的平衡问题，通过使用通用的抽象模版即保证了业务开发效率，又保证了代码的规范,解决了多人开发风格差异化，可读性差等痛点问题，易于项目的维护与升级。

## 快速开始  
1.下载项目  
2.将 dist/sql/jeecf.sql导入数据库，修改配置文件信息，数据库与redis连接信息  
3.启动Application，默认端口8801，用户名：admin,密码：123456  
4.例子模版文件在 dist/example-tmpl文件夹下  
5.插件模版文件在 dist/example-plugin文件夹下  
6.基于docker-compose启动，jeecf-parent/docker-compose 文件夹下
7.命令行客户端项目地址:https://github.com/cgfly/jeecf-cli
## 功能说明  
wiki:https://github.com/cgfly/jeecf/wiki 
## v2.0 新功能与优化  
1.基于like和正则的模块匹配策略
2.加入全量模版
3.提供客户端服务支持
4.优化代码若干
## 总体架构
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture.png)
## MVC架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_mvc.png)
## 代码生成架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_gen.png)
## OSGI插件架构  
![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture_osgi.png)

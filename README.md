# jeecf

## 介绍

Jee Cloud Factory 是基于模版化的代码生成平台，致力于解决企业业务开发效率与代码规范的平衡问题，通过使用通用的抽象模版即保证了业务开发效率，又保证了代码的规范,解决了多人开发风格不同一，可读性差等痛点问题。

## 基础架构

![Image text](https://github.com/jianym/jeecf/blob/master/jeecf-module-manager/src/main/resources/static/images/jeecf_architecture.png)

## 快速开始

1.下载项目

2.将 src/main/resources/document/jeecf.sql导入数据库，修改配置文件信息，数据库与redis连接信息

3.启动Application，默认端口8801，用户名：admin,密码：123456

## 功能说明

### 一.基础配置

1.菜单，通过配置菜单添加需要新要的新的页面,基于权限判断用户是否拥有该菜单

2.命名空间，命名空间决定数据的隔离，通过命名空间可以对数据进行分组，基于权限判断用户是否拥有该命名空间

2.数据库连接，通过配置数据库连接方法，读取数据库表信息，基于权限判断用户是否拥有该数据库连接

### 二.权限控制

1.一个用户有多个角色，一个角色有多个权限

2.规定以base结尾的权限，拥有edit，view结尾的两种权限

3.规定edit,view结尾为原子权限

4.如果选中原子权限，则相对应的base权限不起作用

### 三.代码生成

1.数据字典，通过配置数据字典来生成枚举类，不同命名空间的数据字典数据不互通

### 四.模版生成

1.数表配置，通过数据库连接扫描物理表信息，转化成业务表信息，供模版生成内置参数使用，不同命名空间数据不互通

2.模版参数，通过配置模版参数，在模版生成时手动输入参数，不同命名空间数据不互通

3.模版生成，上传模版文件，选择模版参数和业务表,生成相应代码文件

### 五.业务表内置参数

1.nowDate,当前时间

2.table,业务表实体

3.table.name,业务表名称

4.table.comment,业务表注释

5.table.parentTableFk,父表外键名称

6.table.parent,父表实体

7.table.capitalizeClassName,首字母大写类名

8.table.uncapitalizeClassName,首字母小写类名

9.table.queryColumns,获取查询字段

10.table.insertColumns,获取插入字段

11.table.updateColumns,获取编辑字段

12.table.listColumns,获取列表字段

13.table.intervalColumns,获取区间字段

14.table.closeIntervalColumns,获取闭区间字段

15.table.openIntervalColumns,获取开区间字段

16.table.childList,获取字表

17.table.genTableColumns,获取表字段实体列表，下面以tableColumn表示字段实体

18.tableColumn.name,字段名称

19.tableColumn.isNull,是否为空，0为空，1非空

20.tableColumn.sort,字段循序

21.tableColumn.isKey,是否是主键

22.tableColumn.comment,字段注释

23.tableColumn.jdbcType,获取jdbc类型

24.tableColumn.type,获取类型

25.tableColumn.formType,获取form表单类型

26.tableColumn.field,获取属性

27.tableColumn.queryType,获取搜索类型

28.tableColumn.isInsert,是否插入字段

29.tableColumn.isEdit,是否编辑字段

30.tableColumn.isList,是否列表字段

31.tableColumn.isQuery,是否查询字段

32.tableColumn.simpleType,获取简写类型

33.tableColumn.simpleJdbcType,获取简写jdbc类型

34.tableColumn.simpleField,获取简写属性

35.tableColumn.dataLength,获取jdbc属性最大长度

#### java语言 simpleType与jdbcType 对应关系

String --> varchar,text

Integer --> integer,int,tinyint

Long --> bigint

Date --> date,datetime,timestamp

BigDecimal --> decimal

#### go语言 jdbcType 与 simpleType 对应关系

varchar,text --> string

bigint --> int64

integer,int --> int32

tinyint --> int8

double,decimal --> float64

date,datetime,timestamp --> Time

### 六.模版文件
1.上传文件以.zip压缩

2.文件夹下有config.json文件，其中path参数描述模版文件相对路径，可配置多个文件路径，模版文件为xml格式。outBaseDir参数，生成文件输出的基础包

3.模版文件（xml）中，name参数是描述模版文件名字，filePath参数是文件输出包与outBaseDir参数连用，即整个输出包路径，fileName参数是输出文件的名字，content具体的代码生成内容模版

4.内容模版基于freemarket规则编写

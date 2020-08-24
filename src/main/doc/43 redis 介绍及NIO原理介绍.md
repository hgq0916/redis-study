文件里面的数据：查询

grep awk  、java IO

磁盘：

1.寻址：ms

带宽：G/M

内存：

​	1.寻址：ns

​	2.带宽：

秒>毫秒>微秒>纳秒

磁盘寻址比内存慢10万倍

I/O buffer:成本问题

磁盘有磁道、扇区，一扇区 512 Byte

对于索引来说：扇区越小，索引的成本越大

4k对齐，操作系统无论你读多少数据，都是以4k为单位读取

随着文件变大，读取文件速度变慢

数据库：

data page :4k  使用了4k的扇区存储数据

关系型数据库建表：必须给出schema(类型，字节宽度)

存储：以行为单位存储

内存：B+tree

![image-20200824214254814](43 redis 介绍及NIO原理介绍.assets/image-20200824214254814.png)

减少磁盘IO，以提高查询效率

**数据库表很大，则检索效率变低：**

​	如果表有索引，增删改(维护索引)变慢，

查询速度：

​	1.1个或少量查询，where可以命中索引，速度依然很快

​	2.并发大的时候会受磁盘带宽影响速度

SAP公司：HANA内存级别的关系型数据库

数据在磁盘和内存体积不一样：磁盘没有指针的概念，内存可以多个指针指向同一对象

缓存：memcached,redis

2个基础设施：1.冯洛伊曼体系的硬件，硬件的限制2.以太网，Tcp/ip的网络，网络不稳定

架构师：技术选型，技术对比

数据库排名：https://db-engines.com/en/ranking

redis官网：redis-io

redis:并发15M ops/sec

用于数据库、缓存、消息中间件

![image-20200824221207073](43 redis 介绍及NIO原理介绍.assets/image-20200824221207073.png)

memcached:value没有数据类型，可以使用json存储复杂数据

![image-20200824221409959](43 redis 介绍及NIO原理介绍.assets/image-20200824221409959.png)

value类型的意义：

![image-20200824221925849](43 redis 介绍及NIO原理介绍.assets/image-20200824221925849.png)

**计算向数据移动**

客户端访问memcached，取走全量的json，解析，取出需要的数据,计算发生在客户端

客户端访问redis,直接取走需要的数据，计算发生在redis server

磁盘的扇区大小：4k，实际情况是取决于上层应用的需要，比如视频录像的数据量很大，这样可以把扇区加大，减少寻址的次数，提高访问效率

环境：centos 6.x,redis 5.x

```shell
yum install wget
wget redis下载地址
```

readme.md:安装redis

MakeFile:  会进入src目录/Makefile 

编译:把源码编译成可执行程序

安装：文件copy

MakeFile:  

PREFIX:指定安装路径

PREFIX?=/usr/local表示如果没有指定安装路径，则以/usr/local为安装路径

make
报错:![image-20200824224627629](43 redis 介绍及NIO原理介绍.assets/image-20200824224627629.png)

需要安装gcc（linux的c语言编译器）

安装后，执行make，

报错：![image-20200824224827789](43 redis 介绍及NIO原理介绍.assets/image-20200824224827789.png)

原因：上次执行make失败了，需要清理下

执行make distclean

再次执行make,成功

在src下，启动：redis-server

安装：

![image-20200824225208476](43 redis 介绍及NIO原理介绍.assets/image-20200824225208476.png)

安装为系统的服务：

vi /etc/profile

![image-20200824225759427](43 redis 介绍及NIO原理介绍.assets/image-20200824225759427.png)

刷新配置：source /etc/profile

![image-20200824225844127](43 redis 介绍及NIO原理介绍.assets/image-20200824225844127.png)

通过端口号区分不同服务，配置目录，日志目录，数据目录

![image-20200824230119370](43 redis 介绍及NIO原理介绍.assets/image-20200824230119370.png)

copied：把刚才指定的配置从临时目录拷贝到安装目录，

安装服务，把系统脚本安装到了etc的init.d目录下，

added to chkconfig:设置开启启动

added to runlevels 345:指定运行级别为345级别

启动redis

![image-20200824230306163](43 redis 介绍及NIO原理介绍.assets/image-20200824230306163.png)

cd /etc/init.d 目录，

vi  redis_6379

![image-20200824230922834](43 redis 介绍及NIO原理介绍.assets/image-20200824230922834.png)

启动redis服务：

service redis_6379 start

![image-20200824231054017](43 redis 介绍及NIO原理介绍.assets/image-20200824231054017.png)

再创建一个redis实例：

/utils目录下，重复上述步骤

![image-20200824231354914](43 redis 介绍及NIO原理介绍.assets/image-20200824231354914.png)

![image-20200824232028271](43 redis 介绍及NIO原理介绍.assets/image-20200824232028271.png)

![image-20200824232100588](43 redis 介绍及NIO原理介绍.assets/image-20200824232100588.png)

redis：单进程，单线程，单实例

并发很多的请求，如何变得很快的呢？

redis：使用了epoll系统调用

![image-20200824232615644](43 redis 介绍及NIO原理介绍.assets/image-20200824232615644.png)

BIO:

![image-20200824232839979](43 redis 介绍及NIO原理介绍.assets/image-20200824232839979.png)

yum install man（帮助命令） man-pages(所有帮助页)  

man man-pages

![image-20200824233525960](43 redis 介绍及NIO原理介绍.assets/image-20200824233525960.png)


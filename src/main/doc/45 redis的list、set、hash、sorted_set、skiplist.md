##### list

链表：双向链表

![image-20200830170220366](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830170220366.png)

key：对象中存储了head、tail；

help @list

lpush :从链表的左边开始放

rpush:从链表的右边开始放

lpop :从左边弹出一个元素

lrange: 查看指定范围的数据

![image-20200830171102617](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830171102617.png)

![image-20200830171213786](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830171213786.png)

![image-20200830171327502](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830171327502.png)

lindex:根据索引取出元素

![image-20200830171443234](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830171443234.png)

![image-20200830171533254](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830171533254.png)

LREM  key  count  value:移除元素



![image-20200830171747246](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830171747246.png)

LINSERTl   key after oldvalue value:插入数据

![image-20200830172013152](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830172013152.png)

![image-20200830172059958](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830172059958.png)

![image-20200830172213086](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830172213086.png)

BLPOP:阻塞弹出元素，实现阻塞队列

开启三个客户端，

第一个和第二个客户端，输入
![image-20200830172947651](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830172947651.png)

客户端1和客户端2都被阻塞住，等待数据返回，

客户端3 push一个数据，

![image-20200830173048328](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173048328.png)

客户端1此时获取到数据：

![image-20200830173157155](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173157155.png)

客户端2继续阻塞，

然后客户端3继续push一个数据：

![image-20200830173241549](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173241549.png)

客户端2获取到数据：

![image-20200830173305486](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173305486.png)

LTRIM:截取list集合的指定范围元素，其他元素删除

![image-20200830173629549](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173629549.png)

![image-20200830173641677](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173641677.png)

![image-20200830173719603](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173719603.png)

![image-20200830173845366](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830173845366.png)

##### hash

![image-20200830174314643](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830174314643.png)

![image-20200830174648494](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830174648494.png)

![image-20200830175005741](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830175005741.png)

##### SET

help @set

SMEMBERS key:获取所有的value，会影响性能

![image-20200830175743402](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830175743402.png)

![image-20200830175901146](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830175901146.png)

sinter：取多个key的交集

![image-20200830180248945](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830180248945.png)

sunion：取并集

![image-20200830180409580](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830180409580.png)

sdiff:取差集

![image-20200830180549131](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830180549131.png)

srandmember:取多个随机值

![image-20200830181616832](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830181616832.png)

![image-20200830181832931](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830181832931.png)

spop key :随机弹出一个值

![image-20200830222138037](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830222138037.png)

##### sorted set

![image-20200831084957123](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200831084957123.png)

物理内存：按照左小右大的顺序，不会随着命令发生变化

![image-20200830223823012](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830223823012.png)

![image-20200830224305520](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830224305520.png)

![image-20200830224552212](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830224552212.png)

![image-20200830225109887](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830225109887.png)

场景：歌曲排行榜

点击量：开始为0，当用户访问时，更新点击量的分数

![image-20200830230014000](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830230014000.png)

![image-20200830230057330](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200830230057330.png)

**sorted set 底层原理**

skip list:跳跃表：类平衡树

当数据量很大，增删改查的平均值相对最优

![image-20200831084440322](45 redis的list、set、hash、sorted_set、skiplist.assets/image-20200831084440322.png)


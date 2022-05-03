### 正常

1. 10

   ![image-20220329104750642](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291047709.png)

2. 100

   ![image-20220329104708094](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291047135.png)

---

### 启动负载均衡

1. 10

   ![image-20220329105237868](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291052905.png)

2. 100

   ![image-20220329105324576](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291053754.png)

---

### 使用单个redis+负载均衡

![image-20220329150833101](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291508163.png)

![image-20220329150849369](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291508412.png)

由于保存了session，可以连续做add和delete操作

![image-20220329150942431](https://gitee.com/cosie/markdown-pic/raw/master/img/202203291509482.png)

用redis做了cache

---

### 使用redis集群+负载均衡

![image-20220331000835736](https://gitee.com/cosie/markdown-pic/raw/master/img/202203310008835.png)

![image-20220331001112568](https://gitee.com/cosie/markdown-pic/raw/master/img/202203310011612.png)

启动了3个master 3个slave


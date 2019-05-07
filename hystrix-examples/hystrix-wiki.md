How it Works

Contents

1. [Flow Chart]()
2. Sequence Diagram
3. Circuit Breaker
4. Isolation
5. Threads & Thread Pools
6. Request Collapsing
7. Request Caching


<a name="Flow Chart">Flow Chart</a>

The following diagram shows what happens when you make a request to a service dependency by means of Hystrix:

####参考资料

1. [github上的wiki](https://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.strategy)
2. [hystrix系列](https://www.cnblogs.com/cowboys/p/7661267.html)
3. [例子](https://github.com/Netflix/Hystrix/blob/master/hystrix-examples/src/main/java/com/netflix/hystrix/examples/basic/CommandCollapserGetValueForKey.java)   
4. [hystrix使用与分析](https://hot66hot.iteye.com/blog/2155036)
5. https://www.jianshu.com/p/b9af028efebb
6. https://blog.csdn.net/dengqiang123456/article/details/75935122
7. https://blog.csdn.net/liuchuanhong1/article/details/73718794
8. https://www.jianshu.com/p/df1525d58c20
9. [服务降级](https://blog.csdn.net/ityouknow/article/details/81230412)
10. https://www.cnblogs.com/jinjiyese153/p/8669702.html
11. https://blog.csdn.net/Crystalqy/article/details/79083857
12. https://www.cnblogs.com/haoxinyue/p/8260974.html
13. [Hystrix工作原理（官方文档翻译）](https://segmentfault.com/a/1190000012439580)

How it Works 它是如何工作的

Contents    目录

1. [Flow Chart]() 流程图
2. Sequence Diagram 
3. Circuit Breaker
4. Isolation
5. Threads & Thread Pools
6. Request Collapsing
7. Request Caching

<a name="Flow Chart">Flow Chart</a>

The following diagram shows what happens when you make a request to a service dependency by means of Hystrix:




The following sections will explain this flow in greater detail:

1. Construct a HystrixCommand or HystrixObservableCommand Object 
        构建一个HystrixCommand或者HystrixObservableCommand对象。
2. Execute the Command
        执行命令
3. Is the Response Cached?
4. Is the Circuit Open?
5. Is the Thread Pool/Queue/Semaphore Full?
6. HystrixObservableCommand.construct() or HystrixCommand.run()
7. Calculate Circuit Health
8. Get the Fallback
9. Return the Successful Response

####参考资料

1. [github上的wiki](https://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.strategy)
2. [hystrix系列](https://www.cnblogs.com/cowboys/p/7661267.html)
3. [例子](https://github.com/Netflix/Hystrix/blob/master/hystrix-examples/src/main/java/com/netflix/hystrix/examples/basic/CommandCollapserGetValueForKey.java)   
4. [hystrix使用与分析](https://hot66hot.iteye.com/blog/2155036)
5. https://www.jianshu.com/p/b9af028efebb
6. [断路器-Hystrix 的隔离策略](https://blog.csdn.net/dengqiang123456/article/details/75935122)
7. [Hystrix系列-5-Hystrix的资源隔离策略](https://blog.csdn.net/liuchuanhong1/article/details/73718794)
8. [Hystrix线程隔离技术解析-线程池](https://www.jianshu.com/p/df1525d58c20)
9. [服务降级](https://blog.csdn.net/ityouknow/article/details/81230412)
10. https://www.cnblogs.com/jinjiyese153/p/8669702.html
11. https://blog.csdn.net/Crystalqy/article/details/79083857
12. https://www.cnblogs.com/haoxinyue/p/8260974.html
13. [Hystrix工作原理（官方文档翻译）](https://segmentfault.com/a/1190000012439580)
14. [hystrix-javanica](https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica)

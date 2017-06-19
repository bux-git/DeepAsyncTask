# Android异步消息处理机制   
####  1.概述
Handler 、 Looper 、Message 这三者都是Android异步消息处理线程相关的概念 。   
异步消息处理线程：启用异步消息处理后，内部会开始一个无限循环，每循环一次，都会从起内部的消息队列中  
取出一个消息，然后回调给相应的处理消息的函数，若消息队列为空则线程会阻塞等待  
其中  
1. **Handler 负责创建和消息的发送和处理者**    
2. **Looper与当前线程绑定，保证一个线程只会有一个Looper实例，同时一个Looper实例也只有一个MessageQueue
负责创建一个消息队列MessageQueue,然后进入一个无限循环，不断从MessageQueue中取出消息**    
2. **Message承载消息内容的类**  

#### 2.各项说明 
1.**Handler是什么**   

	Hanlder是Android给我们提供用来异步消息处理，更新UI的一套机制，也是一套消息处理机制，  
	我们可以使用它发送消息，也可以通过他处理消息  
3.**为什么要用Handler**  

    Android在设计的时候，就封装了一套消息创建、传递、处理机制，如果不遵循这样的机制 
    就没办法在子线程中更新UI信息，就会抛出异常信息 
2.**Handler怎么用**    
	Handler中分发消息的一些方法   
	post(Runnable)

	postAtTime(Runnable，long)

	postDelayed(Runnable long)

	sendEmptyMessage(int)

	sendMessage(Message)

	sendMessageAtTime(Message，long)

	sendMessageDelayed(Message，long)

	以上post类方法允许你排列一个Runnable对象到主线程队列中
	
	handler.obtain()获取系统空的Message如果系统没有则创建一个Message返回
	
	
4.**android为什么要设计只能通过Handler机制更新UI呢？**  

	最根本的目的就是解决多线程并发问题
	
5.**Handler的原理是什么**  

    一.Handler封装了消息的发送   
    Looper  
	1.内部包含一个消息队列也就是MessageQueue,所有的Handler发送的消息都走向这个消息队列    
	2.Looper.Looper方法，就是一个死循环，不断的从MessageQueue取消息，如有消息就处理   
	消息，没有消息就阻塞消息    
	
    二.MessageQueue,就是一个消息队列，可以添加消息，并处理消息   
    
    三.Handler内部会跟Looper进行关联，也就是说在Handler的内部可以找到Looper,找到了   
    Looper也就找到了MessageQueue,在Handler中发送消息，其实就是向MessageQueue队列   
    中添加消息   

6.**使用Handler时候遇到的问题**  

7.**如果实现一个与线程相关的Handler**   

	Handler在哪个线程中创建就会使用哪个线程中的Looper 
	如子线程中创建之前需：先调用Looper.loop   
	
8.**HandlerThread又是什么**     

    异步任务，主线程向子线程中发送消息     

9.**如何在主线程给子线程发消息 **
    
    主线程通过HandlerThread发送消息  
    子线程总通过使用主线程中创建的Handler向主线程发送消息   
#### 总结
    Handler负责发送消息，Looper负责接收Hanlder发送的消息，并直接把消息回传给Handler自己  
    MessageQueue就是一个存储消息的容器 


[Handler详解](http://www.imooc.com/learn/267)      
[ Android 异步消息处理机制](http://blog.csdn.net/lmj623565791/article/details/38377229)      
[handler机制原理分析](http://www.cnblogs.com/tuyong1012341/p/5840519.html)    
[AsyncTask基础](http://www.imooc.com/learn/377)
	
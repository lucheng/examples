package com.lucheng;

public class Singleton {

    // 私有构造函数
    private Singleton() {}

    public static Singleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    private enum SingletonEnum {

        INSTANCE;

        private Singleton singleton;

        // JVM 保证这个方法只调用一次
        SingletonEnum() {
            singleton = new Singleton();
        }

        public Singleton getInstance() {
            return singleton;
        }
    }
}
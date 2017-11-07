/**
 * Copyright 2009-2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.mapping;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.TransactionFactory;

/**
 * @author Clinton Begin
 *         整个类被final修饰，这意味着这个类是个最终类，就如String类一样，
 *         是一个不可被更改的类，即其实例是唯一的不能被轻易修改。
 *         环境
 *         决定加载哪种环境(开发环境/生产环境)
 */
public final class Environment {
    //环境id
    private final String id;
    //事务工厂
    private final TransactionFactory transactionFactory;
    //数据源
    private final DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter 'id' must not be null");
        }
        if (transactionFactory == null) {
            throw new IllegalArgumentException("Parameter 'transactionFactory' must not be null");
        }
        this.id = id;
        if (dataSource == null) {
            throw new IllegalArgumentException("Parameter 'dataSource' must not be null");
        }
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    //一个静态内部类Builder
    //建造模式
    //用法应该是new Environment.Builder(id).transactionFactory(xx).dataSource(xx).build();
    
    /*为什么要使用构建者模式呢？那让我们来回忆一下构建者模式的内容。
      构建者模式一般用于构建复杂对象时，将复杂对象分割成许多小对象进行分别构建，然后整合在一起形成一个大对象，
      这样做能很好的规范对象构建的细节过程，这里也是一样的目的，虽然说Environment类的字段较少，
      但在MyBatis中大量使用构建者模式的基础上，在此处使用构建者模式也无可厚非，而且通过内部类的方式构建，
      这个Environment对象的创建会在内部类构建方法build()被显式调用时才会在内存中创建，实现了懒加载。
      这又有点单例模式的意思在内，虽然Mybatis中可创建多个Environment环境，但是在正式运行时，只会存在一个环境，
      确实是使用内部类实现了懒加载的单例模式。
      这个实例的创建最显然的使用位置就是在XMLConfigBuilder构建器中解析构建Configuration类时在
      解析了Configuration.xml配置文件中environment标签的内容之后，这个位置用于将读自于配置文件的配置信息
      配置到了Environment对象中。（这句话有点拗口，不要着急，这个过程在以后介绍XMLConfigBuilder类时会有介绍的）
      其实这个类很简单，但它内部包含的TracsactionFactory和DataSource这两个内容又是两大块内容，这一部分容后介绍。
      自己理解的还很浅显，希望随着不断的解读源码，能够提升自己的认识，到时候再来这里进行进一步改进。*/
    public static class Builder {
        private String id;
        private TransactionFactory transactionFactory;
        private DataSource dataSource;

        public Builder(String id) {
            this.id = id;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public String id() {
            return this.id;
        }

        public Environment build() {
            return new Environment(this.id, this.transactionFactory, this.dataSource);
        }

    }

    public String getId() {
        return this.id;
    }

    public TransactionFactory getTransactionFactory() {
        return this.transactionFactory;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

}

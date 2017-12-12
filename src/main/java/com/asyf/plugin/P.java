package com.asyf.plugin;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class P implements Interceptor {
    //Mybatis支持对Executor、StatementHandler、PameterHandler和ResultSetHandler 接口
    // 进行拦截，也就是说会对这4种对象进行代理(会打印出来4个plugin)。

    // 此方法在 Plugin 类的 invoke 方法执行
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        System.err.println(boundSql.getSql().trim());
        System.err.println("intercept");
        return invocation.proceed();
    }

    public Object plugin(Object obj) {
        System.err.println("plugin");
        return Plugin.wrap(obj, this);
    }

    public void setProperties(Properties properties) {
        Object obj = properties.get("someProperty");
        System.err.println("someProperty---" + obj);
        System.err.println("setProperties");
    }

}

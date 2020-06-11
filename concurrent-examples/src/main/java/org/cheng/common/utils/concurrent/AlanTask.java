package org.cheng.common.utils.concurrent;

/**
 * Created by whf on 8/27/16.
 */
@FunctionalInterface
public interface AlanTask<T_RETURN> {
    T_RETURN execute();
}

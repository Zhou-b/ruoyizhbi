
package com.yan2b.core.base;

import java.util.List;

/**
 * @program: zbeladmin
 * @description: 通用MapStruct实体类转换
 * @author: ZhouBing
 * @create: 2021-08-25 14:44
 **/
public interface BaseMapper<V, E> {

    /**
     * VO转Entity
     *
     * @param vo /
     * @return /
     */
    E toEntity(V vo);

    /**
     * Entity转VO
     *
     * @param entity /
     * @return /
     */
    V toVo(E entity);

    /**
     * VO集合转Entity集合
     *
     * @param voList /
     * @return /
     */
    List<E> toEntity(List<V> voList);

    /**
     * Entity集合转VO集合
     *
     * @param entityList /
     * @return /
     */
    List<V> toVo(List<E> entityList);
}

package com.cxp.openadmin.util;

import java.util.ArrayList;
import java.util.List;

public class TreeParser {
    /**
     * * 트리 데이터 구문 분석
     *
     * @param topId
     * @param entityList
     * @return
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(Integer topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        // 최상위 요소 컬렉션 가져오기
        Integer parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId == parentId) {
                resultList.add(entity);
            }
        }
        // 각 최상위 요소에 대한 하위 데이터 컬렉션 가져오기
        for (E entity : resultList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    /**
     * * 하위 데이터 세트 가져오기
     *
     * @param id
     * @param entityList
     * @return
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(Integer id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        Integer parentId;
        // 부분집합의 직계 자식
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id == parentId) {
                childList.add(entity);
            }
        }
        // 하위 집합의 간접 자식 개체
        for (E entity : childList) {
            entity.setChildren(getSubList(entity.getId(), entityList));
        }
        // 재귀 종료 조건
        if (childList.size() == 0) {
            return childList;
        }
        return childList;
    }
}

package com.cxp.openadmin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisOperator {

	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	// Key（키），간단한 키-값 연산

	/**
	 * 구현: TTL 키(초)는 주어진 키에 대한 남은 수명(TTL, 수명)을 반환합니다.
	 * 
	 * @param key
	 * @return
	 */
	public long ttl(String key) {
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * 구현: 만료 만료 시간을 초 단위로 설정합니다.
	 * 
	 * @param key
	 * @return
	 */
	public void expire(String key, long timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 구현: INCR 키, 키를 한 번 증가
	 * 
	 * @param key
	 * @return
	 */
	public long incr(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 구현: KEYS 패턴, 주어진 패턴 패턴과 일치하는 모든 키 찾기
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 구현 : DEL 키, 키 삭제
	 * 
	 * @param key
	 */
	public void del(String key) {
		redisTemplate.delete(key);
	}

	// 문자열(문자열)

	/**
	 * 구현: SET key value, key-value 설정(문자열 value 값을 key에 연결)
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 구현 : SET key value EX seconds, key-value 및 시간 초과 설정(초)
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            (초)
	 */
	public void set(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 구현 명령: GET key, key와 관련된 문자열 값을 반환합니다.
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}

	// Hash(해시 테이블)

	/**
	 * 구현: HSET key field value, 해시 테이블 key의 필드 field 값을 value로 설정
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * 구현 : HGET key field, 해시 테이블 key에서 지정된 필드 field의 값을 반환합니다.
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * 구현: HDEL key field [field ...], 해시 테이블 key에서 하나 이상의 지정된 필드 삭제, 존재하지 않는 필드는 무시됩니다.
	 * 
	 * @param key
	 * @param fields
	 */
	public void hdel(String key, Object... fields) {
		redisTemplate.opsForHash().delete(key, fields);
	}

	/**
	 * 구현: HGETALL key, 해시 테이블 key의 모든 필드와 값을 반환합니다.
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	// List（列表）

	/**
	 * 구현: LPUSH key value, 목록 key의 헤더에 value 값 삽입
	 * 
	 * @param key
	 * @param value
	 * @return LPUSH 명령을 실행한 후 목록의 길이입니다.
	 */
	public long lpush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * LPOP key, 목록 key의 헤드 요소를 제거하고 반환합니다.
	 * 
	 * @param key
	 * @return 목록 key의 헤드 요소입니다.
	 */
	public String lpop(String key) {
		return (String)redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 구현 : RPUSH key value, 목록 key의 끝(맨 오른쪽)에 value 값을 삽입합니다.
	 * 
	 * @param key
	 * @param value
	 * @return LPUSH 명령을 실행한 후 목록의 길이입니다.
	 */
	public long rpush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

}
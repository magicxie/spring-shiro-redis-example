/*
 * Copyright 2015 dolphin.magic-edu.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.eggcanfly.spring.shiro.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author magic
 *
 */
@Component
public class RedisSessionDao extends AbstractSessionDAO{

	private static final Logger logger = Logger.getLogger(RedisSessionDao.class);
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	public static final String SESSION_KEY = "session-redis:";
	
	protected String getSessionId(Session session){
		return (String) session.getId();
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		
		return deserializeSession(sessionId);
	}

	protected Session deserializeSession(Serializable sessionId) {
		
		BoundHashOperations<String, String, Object> hashOperations = redisTemplate.boundHashOps(SESSION_KEY + sessionId);
		
		SimpleSession session = new SimpleSession();
		
		try {
			
			
			session.setHost((String) hashOperations.get("host"));
			session.setId(sessionId);
			session.setLastAccessTime(hashOperations.get("lastAccessTime") == null ? new Date(System.currentTimeMillis()) : (Date) hashOperations.get("lastAccessTime"));
			session.setStartTimestamp((Date) hashOperations.get("startTimestamp"));
			session.setStopTimestamp((Date) hashOperations.get("stopTimestamp"));
			session.setTimeout((long) hashOperations.get("timeout"));
			Set<String> hashKeys = hashOperations.keys();
			for (Iterator<String> iterator = hashKeys.iterator(); iterator.hasNext();) {
				String hashKey = iterator.next();
				session.setAttribute(hashKey, hashOperations.get(hashKey));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
		}
		
		logger.debug("read session " + sessionId + ", session is " + session);
		
		return session.isValid() ? session : null;
	}


	@Override
	public void update(Session session) throws UnknownSessionException {
		
		String sessionId = getSessionId(session);
		
		seriablizeSession(session, sessionId);
		
		logger.debug("update session " + sessionId);
		
	}

	protected void seriablizeSession(Session session, Serializable sessionId) {
		
		BoundHashOperations<String, String, Object> hashOperations = redisTemplate.boundHashOps(SESSION_KEY + sessionId );
		
		Collection<Object> attributeKeys = session.getAttributeKeys();
		
		for (Iterator<Object> iterator = attributeKeys.iterator(); iterator.hasNext();) {
			Object key = iterator.next();
			hashOperations.put((String) key, session.getAttribute(key));
		}
		
		hashOperations.put("host", session.getHost());
		hashOperations.put("lastAccessTime", session.getLastAccessTime());
		hashOperations.put("startTimestamp", session.getStartTimestamp());
		hashOperations.put("stopTimestamp", ((SimpleSession) session).getStopTimestamp());
		hashOperations.put("timeout", session.getTimeout());
	}


	@Override
	public void delete(Session session) {
		
		String sessionId = getSessionId(session);
		redisTemplate.delete(SESSION_KEY + sessionId);
		
		logger.debug("delete session " + sessionId);
		
	}


	@Override
	public Collection<Session> getActiveSessions() {

		Set<String> keys = redisTemplate.keys(SESSION_KEY + "*");
		
		Collection<Session> sessions = new HashSet<Session>();
		
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String sessionId = iterator.next();
			sessions.add(doReadSession(sessionId));
		}
		
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {

		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		
		seriablizeSession(session, sessionId);
		
		logger.debug("create session " + sessionId);
		
		return sessionId;
	}
	
}
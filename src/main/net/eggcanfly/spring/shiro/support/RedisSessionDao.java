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

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author magic
 *
 */
@Component
public class RedisSessionDao extends CachingSessionDAO{

	@Resource
	private RedisTemplate<String, String> template;
	
	protected String getSessionKey(Session session){
		return (String) session.getId();
	}
	
	@Override
	protected void doUpdate(Session session) {
		
		BoundHashOperations<String, Object, Object>  boudhashOps = template.boundHashOps(getSessionKey(session));
		
		if(session instanceof Serializable){
			
			Serializable serializableSession = (Serializable) session;
			
			boudhashOps.put("session", serializableSession);
			
		}else{
			for(Object key : session.getAttributeKeys()){
				boudhashOps.put(key, session.getAttribute(key));
			}
		}
		
		
	}

	@Override
	protected void doDelete(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Serializable doCreate(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		return null;
	}
	
}
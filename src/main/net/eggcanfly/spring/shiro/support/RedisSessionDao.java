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

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.stereotype.Component;

/**
 * @author magic
 *
 */
@Component
public class RedisSessionDao implements SessionDAO {

	
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#create(org.apache.shiro.session.Session)
	 */
	@Override
	public Serializable create(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#readSession(java.io.Serializable)
	 */
	@Override
	public Session readSession(Serializable sessionId)
			throws UnknownSessionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.session.Session)
	 */
	@Override
	public void update(Session session) throws UnknownSessionException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.session.Session)
	 */
	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions()
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		return null;
	}

}

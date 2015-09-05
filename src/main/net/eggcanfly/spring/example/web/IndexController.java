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
package net.eggcanfly.spring.example.web;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author magic
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Resource
	private RedisTemplate<String, String> template;
	
	@Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;
	
	@ResponseBody
	@RequestMapping("/")
	public String index(){
		return listOps.leftPush("hello", "world").toString();
	}
}

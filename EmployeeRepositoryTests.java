/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.employeedata;

import com.example.employeedata.bean.Employee;
import com.example.employeedata.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employees;

	@Test
	public void testAddEmployee() {
		Employee employee = new Employee(100,"Vasudev", "reddi","vasu@gmail.com","7868767676","2023-11-12",1199999);
		entityManager.persist(employee);

	}

	@Test
	public void testFindAll() {

	}
}

/*
 * Copyright (C) 2018 the original author or authors.
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

package com.alibaba.cloud.sidecar.consul;

import com.alibaba.cloud.sidecar.SidecarDiscoveryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;

/**
 * @author www.itmuch.com
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SidecarConsulDiscoveryClient implements SidecarDiscoveryClient {
    private final ConsulDiscoveryProperties properties;
    private final ConsulServiceRegistry serviceRegistry;
    private final ConsulAutoRegistration registration;

    @Override
    public void registerInstance(String applicationName, String ip, Integer port) {

        if (!this.properties.isRegister()) {
            log.debug("Registration disabled.");
            return;
        }

        serviceRegistry.register(registration);

    }

    @Override
    public void deregisterInstance(String applicationName, String ip, Integer port) {
        if (!this.properties.isRegister() || !this.properties.isDeregister()) {
            return;
        }
        serviceRegistry.deregister(registration);
    }
}

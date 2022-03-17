package br.com.course.api;

import io.micronaut.context.annotation.Factory;
import io.micronaut.http.client.LoadBalancer;
import io.micronaut.http.client.loadbalance.DiscoveryClientLoadBalancerFactory;
import jakarta.inject.Singleton;

import java.util.*;

@Factory
public class GatewayLoadBalancersFactory {
    @Singleton
    public Map<String, LoadBalancer> serviceLoadBalancers(GatewayProperties gatewayProperties,
                                                          DiscoveryClientLoadBalancerFactory factory){
        Set<String> services = gatewayProperties.getServices();
        Map<String, LoadBalancer> loadBalancers = new HashMap<>();
        services.forEach(serviceName -> loadBalancers.put(serviceName, factory.create(serviceName)));
        return Collections.unmodifiableMap(loadBalancers);
    }
}

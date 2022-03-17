package br.com.course.api;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.Set;

//getting all services that are specified in the application.yml
@ConfigurationProperties("gateway")
public class GatewayProperties {
    private Set<String> services;

    public Set<String> getServices(){
        return services;
    }

    public void setServices(Set<String> services){
        this.services = services;
    }
}

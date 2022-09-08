package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix = "sfg.brewery", ignoreInvalidFields = true)
@Component
public class CustomerClient {
    public final String BEER_PATH_V1 = "/api/v1/beer/";

    private String apihost;

    private final RestTemplate restTemplate;

    private String getBaseUrl() {
        return apihost + BEER_PATH_V1;
    }

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return this.restTemplate.getForObject(getBaseUrl() + uuid, CustomerDto.class);
    }

    public URI createCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(getBaseUrl(), customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(getBaseUrl() + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(getBaseUrl() + uuid);
    }
}

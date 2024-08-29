package ai.remi.boot.api.client;

import ai.remi.boot.api.factory.FeignClientFactory;
import ai.remi.comm.feign.aspect.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "remi-demo-web", configuration = FeignAutoConfiguration.class, fallbackFactory = FeignClientFactory.class)
public interface FeignClientApi {

    @GetMapping("/books/findByBooksId")
    String findById(@RequestParam("booksId") int booksId);
}

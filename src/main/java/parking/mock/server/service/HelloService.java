package parking.mock.server.service;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;
import parking.mock.server.mapper.ProductMapper;
import parking.mock.server.model.Product;
import parking.mock.server.model.Shop;
import parking.mock.server.model.Trade;
import parking.mock.server.remote.GithubClient;
import parking.mock.server.remote.GoogleClient;
import parking.mock.server.response.Repo;

import java.util.List;
import java.util.Optional;

@Service
public class HelloService {

    private final GithubClient githubClient;
    private final GoogleClient googleClient;


    public HelloService(GithubClient githubClient, GoogleClient googleClient) {
        this.githubClient = githubClient;
        this.googleClient = googleClient;
    }

    public void combinationRequest() {
        Observable<List<Repo>> s = Observable.just("s")
                .map(it -> githubClient.repos(it));

        Observable<List<Repo>> b = Observable.just("b")
                .map(it -> googleClient.repos(it));

        Observable.zip(s, b, (rs, rb) -> rs.containsAll(rb))
                .subscribe(x -> {

                });
    }


    public static void main(String[] args) {

        ProductMapper productMapper = new ProductMapper();
        Trade trade = Trade.builder().product("PRD1").build();
        Shop build = Shop.builder().price(11D).build();

        Product product = productMapper.map(trade, build);
        System.out.println(product);
    }

}

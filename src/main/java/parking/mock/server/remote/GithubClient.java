package parking.mock.server.remote;

import feign.Param;
import feign.RequestLine;
import feign.Response;
import parking.mock.server.response.Repo;

import java.util.List;

public interface GithubClient {
    @RequestLine("GET /users/{username}/repos")
    List<Repo> repos(@Param("username") String owner);
}

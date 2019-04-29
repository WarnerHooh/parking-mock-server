package parking.mock.server.controller;

import feign.Response;
import feign.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parking.mock.server.remote.GithubClient;
import parking.mock.server.remote.GoogleClient;
import parking.mock.server.response.Repo;

import java.io.IOException;
import java.util.List;

@RestController
public class RemoteController {

    private GithubClient githubClient;
    private GoogleClient googleClient;

    public RemoteController(GithubClient githubClient, GoogleClient googleClient) {
        this.githubClient = githubClient;
        this.googleClient = googleClient;
    }

    @GetMapping("/hello")
    public ResponseEntity fetFromGit() {
        try {
            List<Repo> repos = githubClient.repos("warnerhooh");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/google")
    public ResponseEntity fetFromGoogle() throws IOException {
        try {
            List<Repo> repos = googleClient.repos("warnerhooh");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("hello");
    }
}

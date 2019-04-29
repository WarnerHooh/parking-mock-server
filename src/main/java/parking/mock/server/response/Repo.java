package parking.mock.server.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Repo {
    private Long id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
}

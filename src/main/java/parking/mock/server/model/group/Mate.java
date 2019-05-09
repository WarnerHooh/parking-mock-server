package parking.mock.server.model.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mate {

    private String name;
    private String gender;

    private String apName;
    private String apGender;

    private String bpName;
    private String bpGender;

    private String cpName;
    private String cpGender;

    private int age;
}

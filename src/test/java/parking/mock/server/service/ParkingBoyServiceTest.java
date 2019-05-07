package parking.mock.server.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ParkingBoyServiceTest {
    @Autowired
    private ParkingBoyService parkingBoyService;

    @Test
    @Disabled
    public void should_save_parking_boy() {
        parkingBoyService.saveParkingBoy("Bob3");
    }
}

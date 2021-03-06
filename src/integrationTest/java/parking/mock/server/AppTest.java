package parking.mock.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import parking.mock.server.model.ParkingBoy;
import parking.mock.server.service.ParkingBoyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "integration")
class AppTest {
    @Autowired
    ParkingBoyService parkingBoyService;


    @Test
    void should_save_parking_boy() {
        parkingBoyService.saveParkingBoy("Warner");
        Optional<ParkingBoy> optional = parkingBoyService.getParkingBoy(1L);

        assertNotNull(optional.get());
        assertEquals("Warner", optional.get().getName());
    }
}

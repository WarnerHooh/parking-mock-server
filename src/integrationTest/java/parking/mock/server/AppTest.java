package parking.mock.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import parking.mock.server.model.ParkingBoy;
import parking.mock.server.service.ParkingBoyService;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "integration")
public class AppTest {
    @Autowired
    ParkingBoyService parkingBoyService;


    @Test public void should_save_parking_boy() {
        parkingBoyService.saveParkingBoy("Warner");
        Optional<ParkingBoy> optional = parkingBoyService.getParkingBoy(1L);

        assertNotNull(optional.get());
        assertEquals("Warner", optional.get().getName());
    }
}

package parking.mock.server.service;

import org.springframework.stereotype.Service;
import parking.mock.server.model.ParkingBoy;
import parking.mock.server.repository.ParkingBoyRepository;

@Service
public class ParkingBoyService {
    private final ParkingBoyRepository parkingBoyRepository;

    public ParkingBoyService(ParkingBoyRepository parkingBoyRepository) {
        this.parkingBoyRepository = parkingBoyRepository;
    }

    public void saveParkingBoy(final String name) {
        ParkingBoy parkingBoy = ParkingBoy.builder()
                .name(name)
                .build();
        parkingBoyRepository.save(parkingBoy);
    }
}

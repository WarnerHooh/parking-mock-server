package parking.mock.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parking.mock.server.model.ParkingBoy;

@Repository
public interface ParkingBoyRepository extends JpaRepository<ParkingBoy, Long> {
}

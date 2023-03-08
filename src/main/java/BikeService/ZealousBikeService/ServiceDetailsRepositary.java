package BikeService.ZealousBikeService;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDetailsRepositary extends JpaRepository<ServiceDetails, Integer>
{
	public List<ServiceDetails> findAllByBikedetails1(BikeDetails bike);
}

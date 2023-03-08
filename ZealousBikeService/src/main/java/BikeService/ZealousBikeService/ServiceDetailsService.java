package BikeService.ZealousBikeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDetailsService 
{
	@Autowired
	ServiceDetailsRepositary srepo;

	public ServiceDetails newservice(ServiceDetails service)
	{
		return srepo.save(service);
	}
	public List<ServiceDetails> Exactcusidwithservicedetails(BikeDetails bike)
	{
		return srepo.findAllByBikedetails1(bike);
	}
	public Optional<ServiceDetails> Exactoneservice(int jobcard)
	{
		return srepo.findById(jobcard);
	}
}

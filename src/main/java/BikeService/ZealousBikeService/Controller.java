package BikeService.ZealousBikeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybikeproject")
@CrossOrigin(origins = "http://localhost:3000/")

public class Controller 
{
	@Autowired
	BikeDetailsService service;
	@Autowired
	ServiceDetailsService sservice;
	//http://locolhost:8080/createbikedetails
	@PostMapping("/createbikedetails")
	public String newbikedetails(@RequestBody BikeDetails bike)
	{
		return service.create(bike).getCusName()+"has been added successfully";
	}
	@PutMapping("/updatebikedetails")
	public String updatebike(@RequestBody BikeDetails bike)
	{
		BikeDetails temp = service.create(bike);
		return temp.getCusName()+"has been updated successfully";
	}
	@GetMapping("/exactbikenumber/{bikeno}")
	public Optional<BikeDetails> findbikeno(@PathVariable("bikeno") String bikeno)
	{
		return service.exactbikeno(bikeno);
	}
	@GetMapping("/listallbikedetails")
	public List<BikeDetails> listallbikedetails()
	{
		return service.MakeFetchAll();
	}
	@GetMapping("/listonebikedetail/{id}")
	public Optional<BikeDetails> readonebikedetail(@PathVariable("id")int id)
	{
		return service.makefetchone(id);
	}
	@DeleteMapping("/deletebybikedetails/{id}")
	public String deletebikedetails(@PathVariable("id") int id)
	{
		return service.deletebyid(id);
	}
	@PostMapping("/createnewservice")
	public String newservicedetails(@RequestBody ServiceDetails serv)
	{
		BikeDetails temp=service.gettingexactone(serv.getBikedetails1().getCusId());

		if(serv.getBikeTypeofservice()=="free")
		{
			int total=serv.getBikeNewproductcost()+(serv.getBikeNewproductcost()*18/100);
			serv.setBikeFinalamount(total);
		}
		else
		{
			int total=serv.getBikeNewproductcost()+serv.getBikeLabourcharge();//2500+900=3400
			total+=total*18/100;//3400+=(3400*18/100)
			serv.setBikeFinalamount(total);
		}
		temp.getMyservicedetails().add(serv);
		serv.setBikedetails1(temp);
		sservice.newservice(serv);
		return serv.getBikeJobcardno()+"has been service details is added";
	}
	@PutMapping("/updateservicedetails")
	public String updateservice(@RequestBody ServiceDetails serv)
	{
		ServiceDetails temp=sservice.newservice(serv);
		return temp.getBikeJobcardno()+"has been updated successfully";
	}

	@GetMapping("/exactcusidwithservicedetails/{cusid}")
	public List<ServiceDetails> gettingparticularall(@PathVariable("cusid")int cusid)
	{
		BikeDetails temp=service.gettingexactone(cusid);
		return sservice.Exactcusidwithservicedetails(temp);
	}

	@GetMapping("/exactoneservice/{jobcardno}")
	public Optional<ServiceDetails> findoneservice(@PathVariable("jobcardno")int jobcardno)
	{
		return sservice.Exactoneservice(jobcardno);
	}
}

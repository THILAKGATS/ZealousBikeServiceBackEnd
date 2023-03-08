package BikeService.ZealousBikeService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ZealousBikeServiceApplicationTests
{
	@MockBean
	BikeDetailsRepository jpa;
	@Autowired
	BikeDetailsService service;
	public void testcase1()
	{
		Date date=new Date(2022,10,10);
		BikeDetails bike1=new BikeDetails(1,"TN37CB7498", "THILAK",9003869045L,"thilakg399@gmail.com", date);
		
		when(jpa.findAll()).thenReturn(Stream.of(bike1).collect(Collectors.toList()));
		assertNotNull(service.MakeFetchAll());
		assertSame(date, service.MakeFetchAll().get(0).getCusDateofpurchase());
	}


	@Test
	void contextLoads() {
	}

}

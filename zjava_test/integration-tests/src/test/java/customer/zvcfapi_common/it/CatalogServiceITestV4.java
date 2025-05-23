package customer.zvcfapi_common.it;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import customer.zvcfapi_common.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CatalogServiceITestV4 {

	// @Autowired
	// private MockMvc mockMvc;

	// @Test
	// public void testReadBooks() throws Exception {
	// 	mockMvc.perform(get("/odata/v4/CatalogService/Books")
	// 					.header("Authorization", basic("authenticated", "")))
	// 			.andExpect(status().isOk())
	// 			.andExpect(jsonPath("$.value[0].title").value(containsString("Wuthering Heights")))
	// 			.andExpect(jsonPath("$.value[0].stock").value(100))
	// 			.andExpect(jsonPath("$.value[1].title").value(containsString("Jane Eyre (discounted)")))
	// 			.andExpect(jsonPath("$.value[1].stock").value(500));
	// }

	// private String basic(String user, String password) {
	// 	return "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
	// }

}

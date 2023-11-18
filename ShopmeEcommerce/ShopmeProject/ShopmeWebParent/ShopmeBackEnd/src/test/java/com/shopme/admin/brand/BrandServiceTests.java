package com.shopme.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shopme.common.entity.Brand;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BrandServiceTests {

	@MockBean
	private BrandRepository repo;

	@InjectMocks
	private BrandService service;

	@Test
	public void testCheckUniqueInNewModeReturnDuplicateName() {
		Integer id = null;
		String name = "Acer";

		Brand brand = new Brand(name);

		Mockito.when(repo.findByName(name)).thenReturn(brand);

		String result = service.checkUnique(id, name);

		assertThat(result).isEqualTo("DuplicateName");
	}

	@Test
	public void testCheckUniqueInNewModeReturnOK() {
		Integer id = null;
		String name = "Computers";

		Brand brand = new Brand(name);

		Mockito.when(repo.findByName(name)).thenReturn(null);

		String result = service.checkUnique(id, name);

		assertThat(result).isEqualTo("OK");
	}

	@Test
	public void testCheckUniqueInEditModeReturnDuplicateName() {
		Integer id = 1;
		String name = "Acer";

		Brand brand = new Brand(name);

		Mockito.when(repo.findByName(name)).thenReturn(brand);

		String result = service.checkUnique(id, name);

		assertThat(result).isEqualTo("DuplicateName");
	}

	@Test
	public void testCheckUniqueInEditModeReturnOK() {
		Integer id = 1;
		String name = "Acer";
		Brand brand = new Brand(name);

		Mockito.when(repo.findByName(name)).thenReturn(null);

		String result = service.checkUnique(id, name);

		assertThat(result).isEqualTo("OK");
	}
}

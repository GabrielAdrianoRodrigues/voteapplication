package br.com.gabrieladriano.voteapplication.repositoriesTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.gabrieladriano.voteapplication.domain.models.Associated;
import br.com.gabrieladriano.voteapplication.repositories.AssociatedRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Criei um banco Mock para testar operações no mesmo
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AssociatedRepositoryTest {
    
    //defino um container que contem um banco em do postgres 
    @Container
    //criacao do banco mock 
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:14")
      .withDatabaseName("springboot")
      .withPassword("springboot")
      .withUsername("springboot");
   
    @DynamicPropertySource
    //set popridades dynamicas
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
      propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
      propertyRegistry.add("spring.datasource.password", database::getPassword);
      propertyRegistry.add("spring.datasource.username", database::getUsername);
    }
   
    @Autowired
    private AssociatedRepository associatedRepository;
   
    @Test
    //teste basico de retorno mais para fim didatico
    void shouldReturnAssociatedWithName() {
   
      associatedRepository.save(createAssociated("Gabriel", "0", true));
   
      associatedRepository.save(createAssociated("Gabriel", "0", false));
   
      associatedRepository.save(createAssociated("Rafael", "0", true));
    
      List<Associated> associated = associatedRepository.findAllPaginetedAndFiltered(0, 10 , "%Gabriel%", "%", null);
   
      assertEquals(2, associated.size());
    }
   
    private Associated createAssociated(String name, String cpf, Boolean able) {
      Associated associated = new Associated();
      associated.setName(name);
      associated.setCpf(cpf);
      associated.setAble(able);
      return associated;
    }
  }

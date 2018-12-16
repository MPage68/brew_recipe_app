package com.pagecomp.brewapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("local")
public class GrainRepositoryTest {

  @Autowired
  GrainRepository grainRepository;

  @Autowired
  TestEntityManager testEntityManager;

  @Test
  public void save_addsGrainIntoDatabase_whenExecuted(){
    Grain grain = grainRepository.save(new Grain().builder()
            .id(1)
            .name("awesome")
            .lovibond(2).build());
    assertThat(grain).isNotNull();
    assertThat(grain.getName()).isEqualTo("awesome");
    assertThat(grain.getLovibond()).isEqualTo(2);
  }

  @Test
  public void deleteAll_deletesAllGrains_whenExecuted(){
    testEntityManager.persist(new Grain().builder().name("Cara Red")
            .lovibond(13).build());
    testEntityManager.persist(new Grain().builder().name("2 Row Pale")
            .lovibond(10).build());
    testEntityManager.persist(new Grain().builder().name("Flaked Oat")
            .lovibond(9).build());
    grainRepository.deleteAll();
    assertThat(grainRepository.findAll()).isEmpty();
  }

  @Test
  public void findAll_findsAllGrains_whenExecuted(){
    Grain grain1 = testEntityManager.persist(new Grain().builder().name("Cara Red")
            .lovibond(13).build());
    Grain grain2 = testEntityManager.persist(new Grain().builder().name("2 Row Pale")
            .lovibond(10).build());
    Grain grain3 = testEntityManager.persist(new Grain().builder().name("Flaked Oat")
            .lovibond(9).build());
    Iterable<Grain> grains = grainRepository.findAll();
    assertThat(grains).hasSize(3).contains(grain1, grain2, grain3);
  }

  @Test
  public void findById_findsExactGrainById_whenExecuted(){
    testEntityManager.persist(new Grain().builder().name("Cara Red")
            .lovibond(13).build());
    Grain grain2 = testEntityManager.persist(new Grain().builder().name("2 Row Pale")
            .lovibond(10).build());
    Grain foundGrain = grainRepository.findById(grain2.getId()).get();
    assertThat(foundGrain).isEqualTo(grain2);
  }

  @Test
  public void deleteById_deletesExactGrainById_whenExecuted(){
    Grain grain1 = testEntityManager.persist(new Grain().builder().name("Cara Red")
            .lovibond(13).build());
    Grain grain2 = testEntityManager.persist(new Grain().builder().name("2 Row Pale")
            .lovibond(10).build());
    grainRepository.deleteById(grain2.getId());
    Iterable<Grain> grains = grainRepository.findAll();
    assertThat(grains).hasSize(1).contains(grain1);
  }

}
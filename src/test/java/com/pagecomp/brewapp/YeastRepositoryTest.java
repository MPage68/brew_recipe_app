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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
public class YeastRepositoryTest {

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  YeastRepository yeastRepository;

  @Test
  public void save_persistsYeast_whenExecuted() {
    Yeast yeast = yeastRepository.save(new Yeast().builder()
            .name("1056")
            .style("Ale")
            .attenuation("high")
            .floculation("med").build());
    assertThat(yeast).isNotNull();
    assertThat(yeast.getName()).isEqualTo("1056");
    assertThat(yeast.getStyle()).isEqualTo("Ale");
    assertThat(yeast.getAttenuation()).isEqualTo("high");
    assertThat(yeast.getFloculation()).isEqualTo("med");
  }

  @Test
  public void deleteAll_deleteAllYeast_whenExecuted(){
    testEntityManager.persist(new Yeast().builder()
            .name("1056")
            .style("Ale")
            .attenuation("high")
            .floculation("med").build());
    testEntityManager.persist(new Yeast().builder()
            .name("ESB")
            .style("Ale")
            .attenuation("med")
            .floculation("low").build());
    testEntityManager.persist(new Yeast().builder()
            .name("SSY")
            .style("Cali")
            .attenuation("high")
            .floculation("high").build());
    yeastRepository.deleteAll();
    assertThat(yeastRepository.findAll()).isEmpty();
  }

  @Test
  public void findAll_findsAllYeasts_whenExecuted(){
    Yeast yeast1 = testEntityManager.persist(new Yeast().builder()
            .name("1056")
            .style("Ale")
            .attenuation("high")
            .floculation("med").build());
    Yeast yeast2 = testEntityManager.persist(new Yeast().builder()
            .name("ESB")
            .style("Ale")
            .attenuation("med")
            .floculation("low").build());
    Yeast yeast3 = testEntityManager.persist(new Yeast().builder()
            .name("SSY")
            .style("Cali")
            .attenuation("high")
            .floculation("high").build());
    Iterable<Yeast> yeasts = yeastRepository.findAll();
    assertThat(yeasts).hasSize(3).contains(yeast1, yeast2, yeast3);
  }

  @Test
  public void findById_findsExactYeastById_whenExecuted(){
    testEntityManager.persist(new Yeast().builder()
            .name("1056")
            .style("Ale")
            .attenuation("high")
            .floculation("med").build());
    Yeast yeast2 = testEntityManager.persist(new Yeast().builder()
            .name("ESB")
            .style("Ale")
            .attenuation("med")
            .floculation("low").build());
    Yeast foundYeast = yeastRepository.findById(yeast2.getId()).get();
    assertThat(foundYeast).isEqualTo(yeast2);
  }

  @Test
  public void deleteById_deletesExactHopById_whenExecuted(){
    Yeast yeast1 = testEntityManager.persist(new Yeast().builder()
            .name("1056")
            .style("Ale")
            .attenuation("high")
            .floculation("med").build());
    Yeast yeast2 = testEntityManager.persist(new Yeast().builder()
            .name("ESB")
            .style("Ale")
            .attenuation("med")
            .floculation("low").build());
    yeastRepository.deleteById(yeast2.getId());
    Iterable<Yeast> yeasts = yeastRepository.findAll();
    assertThat(yeasts).hasSize(1).contains(yeast1);
  }
}
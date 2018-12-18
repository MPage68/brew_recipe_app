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
public class HopRepositoryTest {

  @Autowired
  HopRepository hopRepository;

  @Autowired
  TestEntityManager testEntityManager;

  @Test
  public void save_persistsHopEntity_whenExecuted() {
    Hop hop = hopRepository.save(new Hop().builder()
            .alphaAcid("34")
            .flavor("yummy")
            .name("bob").build());
    assertThat(hop).isNotNull();
    assertThat(hop.getAlphaAcid()).isEqualTo("34");
    assertThat(hop.getFlavor()).isEqualTo("yummy");
    assertThat(hop.getName()).isEqualTo("bob");
  }

  @Test
  public void deleteAll_deletesAllHops_whenExecuted(){
    testEntityManager.persist(new Hop().builder()
            .name("bob")
            .alphaAcid("8")
            .flavor("piney").build());
    testEntityManager.persist(new Hop().builder()
            .name("Citra")
            .flavor("Citrus")
            .alphaAcid("11").build());
    testEntityManager.persist(new Hop().builder()
            .name("Simcoe")
            .flavor("bitter")
            .alphaAcid("2").build());
    hopRepository.deleteAll();
    assertThat(hopRepository.findAll()).isEmpty();
  }

  @Test
  public void findAll_findsAllHops_whenExecuted(){
    Hop hop1 = testEntityManager.persist(new Hop().builder()
            .name("bob")
            .alphaAcid("8")
            .flavor("piney").build());
    Hop hop2 = testEntityManager.persist(new Hop().builder()
            .name("Citra")
            .flavor("Citrus")
            .alphaAcid("11").build());
    Hop hop3 = testEntityManager.persist(new Hop().builder()
            .name("Simcoe")
            .flavor("bitter")
            .alphaAcid("2").build());
    Iterable<Hop> hops = hopRepository.findAll();
    assertThat(hops).hasSize(3).contains(hop1, hop2, hop3);
  }

  @Test
  public void findById_findsExactHopById_whenExecuted(){
    testEntityManager.persist(new Hop().builder()
            .name("Simcoe")
            .flavor("bitter")
            .alphaAcid("2").build());
    Hop hop2 = testEntityManager.persist(new Hop().builder()
            .name("Citra")
            .flavor("Citrus")
            .alphaAcid("11").build());
    Hop foundHop = hopRepository.findById(hop2.getId()).get();
    assertThat(foundHop).isEqualTo(hop2);
  }

  @Test
  public void deleteById_deletesExactHopById_whenExecuted(){
    Hop hop1 = testEntityManager.persist(new Hop().builder()
            .name("bob")
            .alphaAcid("8")
            .flavor("piney").build());
    Hop hop2 = testEntityManager.persist(new Hop().builder()
            .name("Citra")
            .flavor("Citrus")
            .alphaAcid("11").build());
    hopRepository.deleteById(hop2.getId());
    Iterable<Hop> hops = hopRepository.findAll();
    assertThat(hops).hasSize(1).contains(hop1);
  }
}
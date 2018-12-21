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
public class RecipeRepositoryTest {

  @Autowired
  TestEntityManager testEntityManager;

  @Autowired
  RecipeRepository recipeRepository;

  @Autowired
  HopRepository hopRepository;

  @Test
  public void save_persistsRecipeWithNameOnly_whenExecuted(){
    Recipe recipe = recipeRepository.save(new Recipe().builder()
            .name("RhineHeist").build());
    assertThat(recipe).isNotNull();
    assertThat(recipe.getName()).isEqualTo("RhineHeist");
  }

  @Test
  public void save_persistsRecipeWithNameAndOneHopOnly_whenExecuted(){
    Hop hop = new Hop().builder().build();
    testEntityManager.persist(hop);
    Recipe recipe = recipeRepository.save(new Recipe().builder()
            .name("RhineHeist").hop1(hop).build());
    assertThat(recipe).isNotNull();
    assertThat(recipe.getName()).isEqualTo("RhineHeist");
  }
}
package com.aixigo.energyimpact.rules;

import java.util.Collections;
import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonarsource.api.sonarlint.SonarLintSide;

@SonarLintSide
public class RulesCheckRegistrar implements CheckRegistrar
{

   @Override
   public void register( RegistrarContext registrarContext )
   {
      registrarContext.registerClassesForRepository(
         JavaEnergyImpactRulesDefinition.REPOSITORY_KEY,
         Collections.singletonList( AvoidJavaCollectionFrameworkRule.class ),
         Collections.singletonList( AvoidJavaCollectionFrameworkRule.class )
      );
   }
}

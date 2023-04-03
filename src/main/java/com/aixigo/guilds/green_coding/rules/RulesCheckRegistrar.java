package com.aixigo.guilds.green_coding.rules;

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
         RulesDefinition.REPOSITORY_KEY,
         Collections.singletonList( AvoidArrayListRule.class ),
         Collections.singletonList( AvoidArrayListRule.class )
      );
   }
}

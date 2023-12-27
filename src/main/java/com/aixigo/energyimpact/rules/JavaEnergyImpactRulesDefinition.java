package com.aixigo.energyimpact.rules;

import java.util.Collections;
import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

public class JavaEnergyImpactRulesDefinition implements RulesDefinition
{
   public static final String REPOSITORY_KEY = "java-energyimpact";

   private final SonarRuntime runtime;

   public JavaEnergyImpactRulesDefinition( SonarRuntime runtime )
   {
      this.runtime = runtime;
   }

   @Override
   public void define( Context context )
   {
      NewRepository repository = context.createRepository( REPOSITORY_KEY, "java" )
         .setName( "Java Energy Impact" );

      RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(
         "com/aixigo/energyimpact/rules",
         runtime
      );

      ruleMetadataLoader.addRulesByAnnotatedClass(
         repository,
         Collections.singletonList( AvoidJavaCollectionFrameworkRule.class )
      );

      repository.done();
   }
}

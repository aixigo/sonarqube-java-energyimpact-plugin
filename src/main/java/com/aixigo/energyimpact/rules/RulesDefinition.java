package com.aixigo.energyimpact.rules;

import java.util.Collections;
import org.sonar.api.SonarRuntime;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

public class RulesDefinition implements org.sonar.api.server.rule.RulesDefinition
{
   public static final String REPOSITORY_KEY = "java-energyimpact";

   private final SonarRuntime runtime;

   public RulesDefinition( SonarRuntime runtime )
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
         Collections.singletonList( AvoidArrayListRule.class )
      );

      repository.done();
   }
}

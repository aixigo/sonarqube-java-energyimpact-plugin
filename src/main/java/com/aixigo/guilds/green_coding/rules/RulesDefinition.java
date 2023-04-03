package com.aixigo.guilds.green_coding.rules;

import java.util.Collections;
import org.sonar.api.SonarRuntime;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

public class RulesDefinition implements org.sonar.api.server.rule.RulesDefinition
{
   public static final String REPOSITORY_KEY = "green-coding-guild-java";

   private final SonarRuntime runtime;

   public RulesDefinition( SonarRuntime runtime )
   {
      this.runtime = runtime;
   }

   @Override
   public void define( Context context )
   {
      NewRepository repository = context.createRepository( REPOSITORY_KEY, "java" )
         .setName( "Green Coding Guild Repository" );

      RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(
         "com/aixigo/guilds/green_coding/rules",
         runtime
      );

      ruleMetadataLoader.addRulesByAnnotatedClass(
         repository,
         Collections.singletonList( AvoidArrayListRule.class )
      );

      repository.done();
   }
}

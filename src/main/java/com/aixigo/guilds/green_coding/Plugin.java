package com.aixigo.guilds.green_coding;

import com.aixigo.guilds.green_coding.rules.RulesCheckRegistrar;
import com.aixigo.guilds.green_coding.rules.RulesDefinition;

public class Plugin implements org.sonar.api.Plugin
{

   @Override
   public void define( Context context )
   {
      context.addExtension( RulesDefinition.class );
      context.addExtension( RulesCheckRegistrar.class );
   }
}

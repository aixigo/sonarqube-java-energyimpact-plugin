package com.aixigo.energyimpact;

import com.aixigo.energyimpact.rules.RulesCheckRegistrar;
import com.aixigo.energyimpact.rules.RulesDefinition;

public class Plugin implements org.sonar.api.Plugin
{

   @Override
   public void define( Context context )
   {
      context.addExtension( RulesDefinition.class );
      context.addExtension( RulesCheckRegistrar.class );
   }
}

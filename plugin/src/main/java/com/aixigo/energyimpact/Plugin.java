package com.aixigo.energyimpact;

import com.aixigo.energyimpact.rules.RulesCheckRegistrar;
import com.aixigo.energyimpact.rules.JavaEnergyImpactRulesDefinition;

public class Plugin implements org.sonar.api.Plugin
{

   @Override
   public void define( Context context )
   {
      context.addExtension( JavaEnergyImpactRulesDefinition.class );
      context.addExtension( RulesCheckRegistrar.class );
   }
}

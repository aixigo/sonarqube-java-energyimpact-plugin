//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2000-2023
//    by aixigo AG, Aachen, Germany.
//
//  All rights reserved. This material is confidential and proprietary to AIXIGO AG and no part of this
//  material should be reproduced, published in any form by any means, electronic or mechanical including
//  photocopy or any information storage or retrieval system nor should the material be disclosed to third
//  parties without the express written authorization of AIXIGO AG.
//
//  aixigo AG
//  http://www.aixigo.de
//  Aachen, Germany
//

package com.aixigo.guilds.green_coding.rules;

import java.util.Collections;
import java.util.List;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;

@Rule( key = "AvoidArrayList" )
public class AvoidArrayListRule extends IssuableSubscriptionVisitor
{

   @Override
   public List< Tree.Kind > nodesToVisit()
   {
      return Collections.singletonList( Tree.Kind.NEW_CLASS );
   }

   @Override
   public void visitNode( Tree tree )
   {
      if( ( (NewClassTree) tree ).symbolType().isSubtypeOf( "java.util.ArrayList" ) ) {
         reportIssue( tree, "Avoid ArrayList in favor of energy-efficient List implementation" );
      }
   }

}

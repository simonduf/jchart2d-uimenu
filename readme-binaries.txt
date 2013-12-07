/*
 *
 * This file is part of jchart2d-uimenu -
 * the UI extension to the Open Source real time charting library jchart2d.
 *
 * Copyright (c) 2013 Achim Westermann
 */

Quick intro

Use this library for integration of charts into your Java application enriched 
by context menus and file menus. 
To just view a chart open a shell (under windows cmd, penguins
will know), cd to the directory you downloaded this file to 
and type
 
Linux: "java  -classpath ext/jchart2d-3.3.0.jar:jchart2d-uimenu-1.0.0.jar info.monitorenter.gui.chart.uidemos.Showcase". 
Windows: "java  -classpath ext/jchart2d-3.3.0.jar;jchart2d-uimenu-1.0.0.jar info.monitorenter.gui.chart.uidemos.Showcase". 

You may also want to look at other demos. The may be launched by typing 
"java -cp <jarfilelist> <fullyqualifiedclassname>". 
E.g.: "java -cp jchart2d-3.3.0.jar:jchart2d-uimenu-1.0.0.jar  info.monitorenter.gui.chart.uidemos.CoordinateViewChart".

This project was split from jchart2d as it's scope on gui and extended functionality added 3rd party dependencies to jchart2d. 

See the license files in the /ext folder to know which licenses you drag in. 

You can use this library for additional gui support for jchart2d under lgpl if you just incorporate the proper 
jchart2d-<version>.jar. Then the menu items  for "Save eps" and "Save pdf" will be grayed out showing a tooltip about the missing 
libraries. 

Save EPS support: To have the "Save eps" support you have to incorporate xmlgraphics-commons-<version>.jar. Then that menu will not 
be grayed out. Like: 

java  -classpath ext/jchart2d-3.3.0.jar:jchart2d-uimenu-1.0.0.jar:ext/xmlgraphics-commons-1.5.jar info.monitorenter.gui.chart.uidemos.Showcase

Save PDF support: Requires a bunch of jar files. Like: 

java  -classpath ext/jchart2d-3.3.0.jar:jchart2d-uimenu-1.0.0.jar:ext/xmlgraphics-commons-1.5.jar:ext/avalon-framework-4.2.0.jar:ext/commons-logging-1.0.4.jar:ext/commons-io-1.3.1.jar:ext/batik-all-1.7.jar:ext/fop-1.1.jar info.monitorenter.gui.chart.uidemos.Showcase

History of changes for the jchart2d-uimenu project.

Changes are chronologically ordered from top (most recent) 
to bottom (least recent).

Legend:
! New Feature
* Bug fixed, (unreleased) points out that this bug was never released. 
- General comment
o API change that requires refactoring. 
  Note that refactoring of protected code intended for library 
  internal details will not be listed.

jchart2d-uimenu-1.0.0 - <month>, <day>, <year>
  
- Migrated all GUI - code that was above the core of jchart2d from https://sourceforge.net/p/jchart2d/code/ci/master/tree/ to 
  subproject https://sourceforge.net/p/jchart2d/jchart2d-uimenu/code/ci/master/tree/. 
! On the fly added support for saving PDF document (apache fop and dependencies were introduced). 

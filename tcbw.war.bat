if not "%ANT_HOME%" == "" goto gotBase
rem defined JAVA_HOME
set JAVA_HOME=..\..\jdk1.6
set ANT_HOME=..\..\ant
set PATH=%PATH%;%JAVA_HOME%\bin;%ANT_HOME%\bin
:gotBase

set ANT_OPTS=-Xmx1024m
cmd /k ant tcbw.war
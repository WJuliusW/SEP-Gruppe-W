@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Client startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and CLIENT_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Client-1.0-SNAPSHOT.jar;%APP_HOME%\lib\converter-jackson-2.9.0.jar;%APP_HOME%\lib\jackson-databind-2.10.1.jar;%APP_HOME%\lib\jackson-annotations-2.11.4.jar;%APP_HOME%\lib\jfoenix-9.0.10.jar;%APP_HOME%\lib\lombok-1.18.20.jar;%APP_HOME%\lib\view-11.8.3.jar;%APP_HOME%\lib\ical4j-3.0.10.jar;%APP_HOME%\lib\commons-codec-1.15.jar;%APP_HOME%\lib\commons-io-2.8.0.jar;%APP_HOME%\lib\converter-gson-2.5.0.jar;%APP_HOME%\lib\adapter-rxjava-2.9.0.jar;%APP_HOME%\lib\retrofit-2.9.0.jar;%APP_HOME%\lib\controlsfx-11.1.0.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\org.eclipse.persistence.moxy-2.7.3.jar;%APP_HOME%\lib\javafx-fxml-16-win.jar;%APP_HOME%\lib\javafx-controls-16-win.jar;%APP_HOME%\lib\javafx-controls-16.jar;%APP_HOME%\lib\javafx-graphics-16-win.jar;%APP_HOME%\lib\javafx-graphics-16-linux.jar;%APP_HOME%\lib\javafx-graphics-16-mac.jar;%APP_HOME%\lib\javafx-graphics-16.jar;%APP_HOME%\lib\javafx-base-16-win.jar;%APP_HOME%\lib\javafx-base-16.jar;%APP_HOME%\lib\gson-2.8.2.jar;%APP_HOME%\lib\okhttp-3.14.9.jar;%APP_HOME%\lib\rxjava-1.3.8.jar;%APP_HOME%\lib\ikonli-javafx-11.3.4.jar;%APP_HOME%\lib\ikonli-fontawesome-pack-11.3.4.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\org.eclipse.persistence.core-2.7.3.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\javax.json-api-1.1.2.jar;%APP_HOME%\lib\jackson-core-2.10.1.jar;%APP_HOME%\lib\ikonli-core-11.3.4.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\commons-lang3-3.8.1.jar;%APP_HOME%\lib\commons-collections4-4.1.jar;%APP_HOME%\lib\javax.mail-1.6.2.jar;%APP_HOME%\lib\org.eclipse.persistence.asm-2.7.3.jar;%APP_HOME%\lib\okio-1.17.2.jar;%APP_HOME%\lib\activation-1.1.jar


@rem Execute Client
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CLIENT_OPTS%  -classpath "%CLASSPATH%" de.unidue.SEP.eteach.client.MainApp %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CLIENT_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CLIENT_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

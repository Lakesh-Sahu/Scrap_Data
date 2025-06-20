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
@rem SPDX-License-Identifier: Apache-2.0
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Scrape Data startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and SCRAPE_DATA_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Scrape Data.jar;%APP_HOME%\lib\selenium-java-4.21.0.jar;%APP_HOME%\lib\jackson-annotations-2.14.2.jar;%APP_HOME%\lib\jackson-core-2.14.2.jar;%APP_HOME%\lib\jackson-databind-2.14.2.jar;%APP_HOME%\lib\log4j-core-2.24.3.jar;%APP_HOME%\lib\log4j-api-2.24.3.jar;%APP_HOME%\lib\selenium-chrome-driver-4.21.0.jar;%APP_HOME%\lib\selenium-devtools-v123-4.21.0.jar;%APP_HOME%\lib\selenium-devtools-v124-4.21.0.jar;%APP_HOME%\lib\selenium-devtools-v125-4.21.0.jar;%APP_HOME%\lib\selenium-firefox-driver-4.21.0.jar;%APP_HOME%\lib\selenium-devtools-v85-4.21.0.jar;%APP_HOME%\lib\selenium-edge-driver-4.21.0.jar;%APP_HOME%\lib\selenium-ie-driver-4.21.0.jar;%APP_HOME%\lib\selenium-safari-driver-4.21.0.jar;%APP_HOME%\lib\selenium-support-4.21.0.jar;%APP_HOME%\lib\selenium-chromium-driver-4.21.0.jar;%APP_HOME%\lib\selenium-remote-driver-4.21.0.jar;%APP_HOME%\lib\selenium-manager-4.21.0.jar;%APP_HOME%\lib\selenium-http-4.21.0.jar;%APP_HOME%\lib\selenium-json-4.21.0.jar;%APP_HOME%\lib\selenium-os-4.21.0.jar;%APP_HOME%\lib\selenium-api-4.21.0.jar;%APP_HOME%\lib\auto-service-annotations-1.1.1.jar;%APP_HOME%\lib\guava-33.2.0-jre.jar;%APP_HOME%\lib\opentelemetry-semconv-1.25.0-alpha.jar;%APP_HOME%\lib\opentelemetry-exporter-logging-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-extension-autoconfigure-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-extension-autoconfigure-spi-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-trace-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-metrics-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-logs-1.38.0.jar;%APP_HOME%\lib\opentelemetry-sdk-common-1.38.0.jar;%APP_HOME%\lib\opentelemetry-api-incubator-1.38.0-alpha.jar;%APP_HOME%\lib\opentelemetry-api-1.38.0.jar;%APP_HOME%\lib\opentelemetry-context-1.38.0.jar;%APP_HOME%\lib\byte-buddy-1.14.15.jar;%APP_HOME%\lib\failsafe-3.3.2.jar;%APP_HOME%\lib\failureaccess-1.0.2.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-3.42.0.jar;%APP_HOME%\lib\error_prone_annotations-2.26.1.jar;%APP_HOME%\lib\commons-exec-1.4.0.jar


@rem Execute Scrape Data
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SCRAPE_DATA_OPTS%  -classpath "%CLASSPATH%" scrap.App %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable SCRAPE_DATA_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%SCRAPE_DATA_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega

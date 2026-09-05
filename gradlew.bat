@echo off
where gradle >nul 2>nul
if %ERRORLEVEL% EQU 0 (
  gradle %*
  exit /b %ERRORLEVEL%
)
echo Gradle Wrapper JAR is not bundled; use an online builder with Gradle/Android SDK preinstalled.
exit /b 1

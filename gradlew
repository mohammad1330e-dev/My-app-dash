#!/bin/sh
set -e
APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
if [ -x "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" ]; then
  exec java -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
fi
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo "Gradle Wrapper JAR is not bundled; use an online builder with Gradle/Android SDK preinstalled." >&2
exit 1

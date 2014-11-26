call config.bat
if "%RELEASE%"==0 (
	%PLATFORM_TOOLS%\adb install -r app/bin/%APP_NAME%-debug.apk
) else (
	%PLATFORM_TOOLS%\adb install -r app/bin/%APP_NAME%-release.apk
)

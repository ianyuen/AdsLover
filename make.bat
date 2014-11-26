call config.bat
cd app
if "%RELEASE%"==0 (
	call make_apk.bat debug
) else (
	call make_apk.bat release
	%JAVA_HOME%\bin\jarsigner -verify bin\%APP_NAME%-release.apk
)
cd ..
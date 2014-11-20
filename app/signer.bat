%JAVA_HOME%\bin\jarsigner -keystore iansoft.keystore -storepass P@ssw0rd %APP_NAME%-release-unsigned.apk ianyuen
%ANDROID_HOME%\build-tools\21.0.2\zipalign -v 4 %APP_NAME%-release-unsigned.apk %APP_NAME%-release.apk
%JAVA_HOME%\bin\jarsigner -verify %APP_NAME%-release.apk
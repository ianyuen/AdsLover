call config.bat
cd app
call make_apk.bat release
call signer.bat
cd ..
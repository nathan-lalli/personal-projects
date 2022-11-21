@echo off
:: This is a batch file that will add a given image as the locksreen and the desktop
:: it will set the registry keys that are required to do so and move the image to the
:: temp folder on the machine at C:\Temp

:: This file assumes that the image you want to be the lockscreen is in the same location
:: that you are running this file from and the image is called "image.jpg"

:: Copy the given image to the machine
if not exist image.jpg exit 7
copy image.jpg C:\Temp
if not exist C:\temp\image.jpg exit 7

:: Add registry keys to set the lockscreen image
reg add "HKLM\Software\Policies\Microsoft\Windows\Personalization" /v LockScreenImage /t REG_SZ /d "C:\temp\image.jpg" /f
reg add "HKLM\Software\Policies\Microsoft\Windows\Personalization" /v LockScreenOverlaysDisabled /t REG_DWORD /d 1 /f

:: Add registry key to prevent the user from changing the image
reg add "HKLM\Software\Policies\Microsoft\Windows\Personalization" /v NoChangingLockScreen /t REG_DWORD /d 1 /f

:: Add registry key to set the desktop wallpaper for all users and prevent them from changing it
reg add "HKCU\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System" /v Wallpaper /t REG_SZ /d "C:\temp\image.jpg /f
reg add "HKCU\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System" /v WallpaperStyle /t REG_SZ /d "4" /f

echo "All values have been added, a restart is required for this to work properly."

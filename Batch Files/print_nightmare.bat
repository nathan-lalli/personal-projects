:: Running this will allow a user to add a printer to their device after it has been affected by the 
:: Print Nightmare bug in Windows 10 and 11

:: adds the key to allow any user to do any driver installations
reg add "HKLM\Software\Policies\Microsoft\Windows\DriverInstall\Restrictions" /v AllowUserDeviceClasses /t REG_DWORD /d 1 /f

:: adds the keys to disabled the point and print restrictions
reg add "HKLM\Software\Policies\Microsoft\Windows NT\Printers\PointAndPrint" /v InForest /t REG_DWORD /d 0 /f
reg add "HKLM\Software\Policies\Microsoft\Windows NT\Printers\PointAndPrint" /v Restricted /t REG_DWORD /d 0 /f
reg add "HKLM\Software\Policies\Microsoft\Windows NT\Printers\PointAndPrint" /v TrustedServers /t REG_DWORD /d 0 /f

:: adds the key to not restrict driver installation to only administrators
reg add "HKLM\Software\Policies\Microsoft\Windows NT\Printers\PointAndPrint" /v RestrictDriverInstallationToAdministrators /t REG_DWORD /d 0 /f
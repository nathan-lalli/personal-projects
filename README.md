# Personal Projects

Personal Projects/Assignments that I have done.

You are welcome to use this code in anyway that you would like and if you have questions feel free to comment!

I would suggest that you make sure that you know what is going on before you decide to run any programs that are written here.

---

# Programs In This Repo

Secure Notepad

Cryptogram Solver

Print Nightmare

## Languages

#### Python

###### secure_notepad.py

    Description:

This program opens up a notepad like application that you can enter any text that you want into it and then choose to encrypt that note. When saved, the program asks for a password and then will encrypt the file with that password (if you do not want to encrypt a note, leave the password blank). The program then allows you to open the file and asks for the password and then decrypts the file using that password (if the password is entered wrong, it will still decrypt the file using that as the key and it will just be unreadable). The password is not stored on the system in anyway. When encrypting the file, it will be saved with the extension .txt.enc to show that it is encrypted but a plaintext file will just be .txt.

    Instructions:

To use the program, just run the python file "secure_notpad.py." `python secure_notepad.py` You will then have the application appear on screen and be able to type in the textbox. In the menu bar you can then save, save as, and open files which will prompt you for password to encrypt and decrypt the

###### cryptogram_solver.py

    Description:

This program will take a cryptogram ciphertext from a user and then decrypt it to get the plaintext value of the entered ciphertext. The program strips all of the punctuation from the message and checks to make sure that the plaintext message has only english words in it before returning the plaintext message to the user. The algorithm is not perfect, so sometimes it may return the wrong message but it will return the closet thing it can find. The algorithm also will work better the longer that the message because it can get a better frequency count of the letters.

    Instructions:

To use this the program, make sure that the EN.json file is in the same location as the python file and run the python file "cryptogram_solver.py." `python cryptogram_solver.py` The interface will then prompt you for the ciphertext. Once entered the program will decrypt the entered message and return the plaintext to the interface. Sometimes the screen will not output anything for sometime because the program is working on the ciphertext, just be patient and it will return the plaintext message.

#### Windows Batch

###### print_nightmare.bat

    Description:

This batch file runs a script that fixes the print nightmare bug in Windows 10 and 11 that doesn't allow users to add printers to their devices (including admins). The program adds 5 registry keys to the machine that allow any user to add printers to their device. It does not change rules for installation of any devices other than printers.

    Instructions:

To run the batch file, right click on the downloaded file and run it as an administrator. The file will run and then prompt that the keys were either successfully added or that they failed. After that the printer should be able to be added.

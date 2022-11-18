# By default the program will save the file in the root directory
# this will encrypt a file without saving the password
# and then let you decrypt it when you enter the password
#
# To run this you will need to run the following
# pip install tk
# pip install pycryptodome


import os
import sys
import hashlib
import tkinter as tk
from tkinter import *
from tkinter import filedialog
from tkinter import simpledialog
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes
from Crypto.Util.Padding import pad, unpad
from base64 import b64encode, b64decode

root = tk.Tk()
root.title('Secure Notepad')

# Set variable for open file name
global open_status_name
open_status_name = False

global selected
selected = False


def encrypt(message, key):
    iv = get_random_bytes(AES.block_size)
    cipher = AES.new(key, AES.MODE_CBC, iv)
    return b64encode(iv + cipher.encrypt(pad(message.encode('utf-8'), AES.block_size)))


def decrypt(ciphertext, key):
    iv = b64decode(ciphertext)
    cipher = AES.new(key, AES.MODE_CBC, iv[:AES.block_size])
    return unpad(cipher.decrypt(
        iv[AES.block_size:]), AES.block_size)


def decrypt_file(file_name, key):
    with open(file_name, 'r') as fo:
        ciphertext = fo.read()
    return decrypt(ciphertext, key)


def new_file():
    # Delete previous text
    my_text.delete("1.0", END)

    global open_status_name
    open_status_name = False


def open_file():
    # Delete previous text
    my_text.delete("1.0", END)

    # Grab Filename
    text_file = filedialog.askopenfilename(initialdir="C:\", title="Open File", filetypes=(
        ("Text Files", "*.txt"), ("Encrypted Files", "*.enc"), ("Python Files", "*.py"), ("All Files", "*.*")))

    # Check to see if there is a file name
    if text_file:
        # Make filename global so we can access it later
        global open_status_name
        open_status_name = text_file

    if (check_tag(text_file)):
        key = tk.simpledialog.askstring(
            "Password", "File is Encrpyted\nPlease enter the password: ")
        hashedkey = hashlib.sha256(key.encode('utf-8')).digest()
        dec = decrypt_file(text_file, hashedkey).decode('utf-8')
        with open(text_file[:-4], 'w') as fo:
            fo.write(dec)
        # Open the file
        text_file = open(text_file[:-4], 'r')
        stuff = text_file.read()
        # Add file to textbox
        my_text.insert(END, stuff)
        # Close the opened file
        text_file.close()
    else:
        # Open the file
        text_file = open(text_file, 'r')
        stuff = text_file.read()
        # Add file to textbox
        my_text.insert(END, stuff)
        # Close the opened file
        text_file.close()


def save_as_file():
    key = tk.simpledialog.askstring(
        "Password", "Create a Password: \n (If you do not want to encrypt, leave blank")
    text_file = filedialog.asksaveasfilename(defaultextension=".txt", initialdir="C:\", title="Save File", filetypes=(
        ("Text Files", "*.txt"), ("All Files", "*.*")))
    if key == '':
        if text_file:
            # Save the file
            text_file = open(text_file, 'w')
            text_file.write(my_text.get(1.0, END))
    else:
        if text_file:
            # Save the file
            text_file = open(text_file + ".enc", 'w')
            hashedkey = hashlib.sha256(key.encode('utf-8')).digest()
            enc = encrypt(my_text.get(1.0, END), hashedkey).decode('utf-8')
            text_file.write(enc)


def save_file():
    global open_status_name
    if check_tag(open_status_name):
        if open_status_name:
            key = tk.simpledialog.askstring(
                "Password", "Create a Password: \n (If you do not want to encrypt, leave blank")
            # Save the file
            text_file = open(text_file + ".enc", 'w')
            hashedkey = hashlib.sha256(key.encode('utf-8')).digest()
            enc = encrypt(my_text.get(1.0, END), hashedkey).decode('utf-8')
            text_file.write(enc)
        else:
            save_as_file()
    else:
        if open_status_name:
            # Save the file
            text_file = open(open_status_name, 'w')
            text_file.write(my_text.get(1.0, END))
            # Close the file
            text_file.close()
        else:
            save_as_file()


def cut_text(e):
    global selected
    # Check to see if keyboard shortcut used
    if e:
        selected = root.clipboard_get()
    else:
        if my_text.selection_get():
            # Grab selected text from text box
            selected = my_text.selection_get()
            # Delete Selected Text from text box
            my_text.delete("sel.first", "sel.last")
            # Clear the clipboard then append
            root.clipboard_clear()
            root.clipboard_append(selected)


def copy_text(e):
    global selected
    # check to see if we used keyboard shortcuts
    if e:
        selected = root.clipboard_get()

    if my_text.selection_get():
        # Grab selected text from text box
        selected = my_text.selection_get()
        # Clear the clipboard then append
        root.clipboard_clear()
        root.clipboard_append(selected)


def paste_text(e):
    global selected
    # Check to see if keyboard shortcut used
    if e:
        selected = root.clipboard_get()
    else:
        if selected:
            position = my_text.index(INSERT)
            my_text.insert(position, selected)


# Check the tag of the file
def check_tag(file):
    # Define Current tags
    tag = str(file)[-4:]
    # If statment to see if tag has been set
    if '.enc' == tag:
        return True
    else:
        return False


def select_all(e):
    # Add sel tag to select all text
    my_text.tag_add('sel', '1.0', 'end')


def clear_all():
    my_text.delete(1.0, END)


# Create a toolbar frame
toolbar_frame = Frame(root)
toolbar_frame.pack(fill=X)

# Create Main Frame
my_frame = Frame(root)
my_frame.pack(pady=5)

# Create Text Box
my_text = Text(my_frame, width=97, height=25, font=("Helvetica", 16), selectbackground="red",
               selectforeground="black", undo=True, wrap="none")
my_text.pack()

# Create Menu
my_menu = Menu(root)
root.config(menu=my_menu)

# Add File Menu
file_menu = Menu(my_menu, tearoff=False)
my_menu.add_cascade(label="File", menu=file_menu)
file_menu.add_command(label="New", command=new_file)
file_menu.add_command(label="Open", command=open_file)
file_menu.add_command(label="Save", command=save_file)
file_menu.add_command(label="Save As...", command=save_as_file)
file_menu.add_separator()
file_menu.add_separator()
file_menu.add_command(label="Exit", command=root.quit)

# Edit Bindings
root.bind('<Control-Key-x>', cut_text)
root.bind('<Control-Key-c>', copy_text)
root.bind('<Control-Key-v>', paste_text)
# Select Binding
root.bind('<Control-A>', select_all)
root.bind('<Control-a>', select_all)

root.mainloop()

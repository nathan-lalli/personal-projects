import binascii
import hashlib
import sys

def dcc2_hash(username: str, password: str) -> str:
    """
    Computes the DCC2 (Domain Cached Credentials v2) hash for a given username and password.
    DCC2 uses PBKDF2-HMAC-SHA1 with 10240 iterations.
    """
    # DCC1: MD4(UTF-16LE(password))
    pw_utf16 = password.encode('utf-16le')
    dcc1 = hashlib.new('md4', pw_utf16).digest()

    # DCC2: PBKDF2-HMAC-SHA1(DCC1, UTF-16LE(username.lower()), 10240, 16)
    username_utf16 = username.lower().encode('utf-16le')
    dcc2 = hashlib.pbkdf2_hmac('sha1', dcc1, username_utf16, 10240, 16)

    return binascii.hexlify(dcc2).decode()

def extract_nt_hash_from_dcc2(dcc2_hash_hex: str, username: str, password_list: list) -> str:
    """
    Attempts to recover the NT hash (DCC1) from a given DCC2 hash, username, and a list of possible passwords.
    Returns the NT hash in hex if found, else None.
    Note: This is a brute-force approach since PBKDF2 is a one-way function.
    """
    username_utf16 = username.lower().encode('utf-16le')
    target_dcc2 = binascii.unhexlify(dcc2_hash_hex)

    for password in password_list:
        pw_utf16 = password.encode('utf-16le')
        dcc1 = hashlib.new('md4', pw_utf16).digest()
        dcc2 = hashlib.pbkdf2_hmac('sha1', dcc1, username_utf16, 10240, 16)
        if dcc2 == target_dcc2:
            return binascii.hexlify(dcc1).decode()
    return None

if __name__ == "__main__":

    print("Choose an option:")
    print("1. Generate DCC2 hash")
    print("2. Extract NT hash from DCC2 hash (brute-force)")

    choice = input("Enter 1 or 2: ").strip()

    if choice == "1":
        username = input("Enter username: ")
        password = input("Enter password: ")
        print("DCC2 hash:", dcc2_hash(username, password))
    elif choice == "2":
        dcc2_hash_hex = input("Enter DCC2 hash (hex): ")
        username = input("Enter username: ")
        passwords = input("Enter possible passwords (comma-separated): ").split(",")
        passwords = [p.strip() for p in passwords]
        nt_hash = extract_nt_hash_from_dcc2(dcc2_hash_hex, username, passwords)
        if nt_hash:
            print("Recovered NT hash:", nt_hash)
        else:
            print("NT hash not found in provided password list.")
    else:
        print("Invalid option.")
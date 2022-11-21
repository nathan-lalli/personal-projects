TITLE "Base Conversion"
;************************************************************************************************************************************
;*	Title:	Base Conversion																											*
;*	Author:	Nathan Lalli																											*
;*	Date:	9-30-2020																												*
;*	Purpose: This program converts the numbers given into other bases																*
;************************************************************************************************************************************

		.MODEL	small
		STACK	256
		
;************************************************************************************************************************************
;*	Equates Section																													*
;************************************************************************************************************************************

EOS			EQU		0														;End of string
numLen		EQU		6														;Length of number the user can give
nameLen		EQU		25														;Length of the user's name

;************************************************************************************************************************************
;*	Data Section																													*
;************************************************************************************************************************************
		.DATA
		
exCode		db		0														;DOS error code

prompt1		db		"Please enter your name: ",EOS							;Prompt user for their name
prompt2		db		", enter a positive number between 0-65,535",EOS		;Prompt user for number
prompt3		db		": ",EOS												;Colon for after putting name in to prompt2
prompt4		db		", would you like to enter another number? (Y/N) ",EOS	;Prompt the user to enter another number
prompt5		db		" in binary is:      ",EOS								;Print user's number in binary
prompt6		db		" in hexadecimal is: ",EOS								;Print user's number in hexadecimal
prompt7		db		", thank you for using my program!",EOS					;Ending message
prompt8		db		"Hello ",EOS											;Greet the user
prompt9		db		"The number ",EOS										;Echo the user's number
greeting	db		"Welcome to the best number converter ever! :)",EOS		;Greeting for the user
userName	db		nameLen dup (?)											;The user's name
userNum		dw		numLen	dup (?)											;The user's number
convertNum	dw		0														;The binary version of user's number
strBinNum	dw		16	dup (?)												;Holds the string binary number
strHexNum	dw		4	dup	(?)												;Holds the string hexadecimal number
strUserAns	db		2	dup (?)												;Holds user's answer to continue

;************************************************************************************************************************************
;*	Code Section																													*
;************************************************************************************************************************************
		.Code
		
;************************************************************************************************************************************
;*	Exxternal procedures from STRINGS.OBJ & STRIO.OBJ																				*
;************************************************************************************************************************************

		EXTRN	StrLength:proc, StrRead:proc
		EXTRN	StrWrite:proc, NewLine:proc
		
;************************************************************************************************************************************
;* External procedures from BINASC.OBJ																								*
;************************************************************************************************************************************

		EXTRN	BinToAscHex:proc, SBinToAscDec:proc, BinToAscDec:proc
		EXTRN	BinToAscBin:proc, AscToBin:proc
		
;************************************************************************************************************************************
;*	Main entry point of the program																									*
;************************************************************************************************************************************
Start:
		mov		ax,	@data													;Initialize DS to address
		mov		ds,	ax														;of data segment
		mov		es,	ax														;Make es = ds
		
;************************************************************************************************************************************
;*	Greet the new user																												*
;************************************************************************************************************************************

GreetUser:

		call	newLine														;Add a spcae to make it look better
		
		mov		di, OFFSET greeting											;Move the index to the right location
		call	strWrite													;Print the greeting message to the screen	
		
;************************************************************************************************************************************
;*	Get the user's name																												*
;************************************************************************************************************************************

GetName:

		call	newLine														;Add a space to make it look better

		mov 	di,	OFFSET prompt1											;Move the index to the right location
		call	strWrite													;Print out asking for the user's name
		
		mov		di, OFFSET userName											;Move the index to the right location
		mov		cx,	nameLen													;Move the right amount of space into the buffer
		call	strRead														;Read the user's name
		
;************************************************************************************************************************************
;*	Get the user's number																											*
;************************************************************************************************************************************

Number:
		
		call	newLine														;Add a space to make it look better
		call	newLine														;Add a space to make it look better
		
		mov		di, OFFSET prompt8											;Move the index to the right location
		call	strWrite													;Print Hello to the screen
		
		mov		di,	OFFSET userName											;Move the index to the right location
		call	strWrite													;Print out user's name
		
		mov		di,	OFFSET prompt2											;Move the index to the right location
		call	strWrite													;Print out asking for user's number
		
		mov		di,	OFFSET prompt3											;Move index to the right location
		call	strWrite													;Print out a colon after the user's name
		
		mov		di,	OFFSET userNum											;Move the index to the right location
		mov		cx,	numLen													;Move the right amount of space into the buffer
		call	strRead														;Read the user's name
		
;************************************************************************************************************************************
;*	Convert the user's number																										*
;************************************************************************************************************************************

Conversion:										
		
		mov		di, OFFSET userNum											;Move the index to the right location
		call	AscToBin													;Convert the number to ascii binary
		mov		convertNum, ax												;Move the number into binNum

		mov		di, OFFSET strBinNum										;Move the index to the right location
		mov		cx,	16														;Make room in the buffer
		mov		ax,	convertNum												;Move the number into ax
		call	BinToAscBin													;Convert the number into binary
		
		mov		di, OFFSET strHexNum										;Move the index to the right location
		mov		cx, 4														;Make room in the buffer
		mov		ax, convertNum												;Move the number into ax
		call	BinToAscHex													;Convert the number in Hex

;************************************************************************************************************************************
;*	Print out the user's converted numbers																							*
;************************************************************************************************************************************

Printing:

		call	newLine														;Add a space to make it look better
		
		mov		di, OFFSET prompt9											;Move the index to the right location
		call	strWrite													;Print the string to the screen
		
		mov		di, OFFSET userNum											;Move the index to the right location
		call	strWrite													;Print the user's number to the screen
		
		mov		di, OFFSET prompt5											;Move the index to the right location
		call	strWrite													;Print the statement for binary number to screen
		
		mov		di,	OFFSET strBinNum										;Move the index to the right location
		call	strWrite													;Print out the binary number to the screen
		
		call	newLine														;Add a space to make it look better
		
		mov		di, OFFSET prompt9											;Move the index to the right location
		call	strWrite													;Print the string to the screen
		
		mov		di, OFFSET userNum											;Move the index to the right location
		call	strWrite													;Print the user's number to the screen
		
		mov		di, OFFSET prompt6											;Move the index to the right location
		call	strWrite													;Print the statement for hex number to screen
		
		mov		di, OFFSET strHexNum										;Move the index to the right location
		call	strWrite													;Print out the hexadecimal number to screen
		
;************************************************************************************************************************************
;* Ask if they want to continue																										*
;************************************************************************************************************************************

Continue:

		call	newLine														;Add a space to make it look better
		call	newLine														;Add a space to make it look better

		
		mov		di, OFFSET userName											;Move the index to the right location
		call	strWrite													;Print the user's name to the screen
		
		mov		di, OFFSET prompt4											;Move the index to the right location
		call	strWrite													;Print the continue question to screen
		
		mov		di, OFFSET strUserAns										;Move the index to the right location
		mov		cx,	2														;Create room in buffer
		call 	strRead														;Read user's answer
		
		and		strUserAns, 11011111b										;Set the case bit to off to make it uppercase
		
		mov		al, strUserAns												;Move user answer into al to compare for jump
		
		mov		bl, 'Y'														;Move character 'Y' into bx to compare with answer
		cmp		al, bl														;Compare the value in al and bl
		
		jne		Check														;Jump to Done if the values are not equal		
		jmp		Number														;Jump to Number if al and bl are equal
		
;************************************************************************************************************************************
;*	Check the answer the user gives																									*
;************************************************************************************************************************************

Check:

		mov		bl, 'N'														;Move the character 'N' into bl to compare
		cmp		al, bl														;Compare the value in al and bl
		
		jne		Continue													;If character is not an 'N' jump to Continue
		jmp		Done														;If character is an 'N' jump to Done
		
;************************************************************************************************************************************
;*	End of program																													*
;************************************************************************************************************************************

Done:
		
		call	newLine														;Add a space to make it look better
		call	newLine														;Add a space to make it look better
		
		mov		di, OFFSET userName											;Move the index to the right location
		call	strWrite													;Print the user's name to the screen
		
		mov		di, OFFSET prompt7											;Move index to right location
		call	strWrite													;Print goodbye message to screen
		
;************************************************************************************************************************************
;* Program termination code																											*
;************************************************************************************************************************************
		mov 	ah, 04Ch													;DOS function: Exit program
		mov 	al, exCode													;Return exit code value
		int 	21h															;Call DOS. Terminate program
		
		END 	Start														;End of program / Entry point
;************************************************************************************************************************************

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		





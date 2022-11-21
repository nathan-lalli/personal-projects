TITLE "Letter Frequency"
;************************************************************************************************************************************
;*	Title:	Letter Frequency																										*
;*	Author:	Nathan Lalli																											*
;*	Date:	10-16-2020																												*
;*	Purpose: This program outputs frequency of letters in a sentence																*
;************************************************************************************************************************************

		.MODEL	small
		STACK	256
		
;************************************************************************************************************************************
;*	Equates Section																													*
;************************************************************************************************************************************

EOS			EQU		0														;End of string

;************************************************************************************************************************************
;*	Data Section																													*
;************************************************************************************************************************************
		.DATA
		
exCode		db		0														;DOS error code

inString	db		255 DUP (?)												;Holds the user's sentence
ftable		db		26 DUP (?)												;Holds the frequency of characters
buffer		db		100 DUP (?)												;Buffer for printing
rowNum		dw		0														;Holds the row number
strCont		db		2 DUP (?)												;Hold user continue answer

conStr		db		"Do you wish to process another string? (Y/N)",EOS		;Ask user if they want to do it again
exitStr		db		"Thank you for using my program, goodbye!",EOS			;Say thank you and goodbye to user
welcomeStr	db		"Welcome to the letter frequency program!",EOS			;Welcome message
enterStr	db		"Please enter a sentence: ",EOS							;Ask user for a senctence to analyze

printSpace	db		"         ",EOS											;Print spaces for the table
printChar	db		"Character",EOS											;Print "Character" for table
printDash	db		"---------",EOS											;Print dashes for table
printFrq	db		"Frequency",EOS											;Print "Frequency" for table

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
;*	Get the string from user																										*
;************************************************************************************************************************************
GetString:

		call 	newLine														;Add a space for looks
		
		mov		di, OFFSET welcomeStr										;Move the index to the right location
		call	strWrite													;Greet the user
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET enterStr											;Move the index to the right location
		call	strWrite													;Tell user to enter a string
		
		mov		di, OFFSET inString											;Move the index to the right location
		mov		cx, 255														;Make room in the buffer
		call	strRead														;Read in the user's string

		call	newLine														;Add a space for looks

;************************************************************************************************************************************
;*	Fill table with values																											*
;************************************************************************************************************************************
PutValueInTable:

		mov		di, OFFSET ftable											;Move the index to the right location
		mov		cx, 26														;Make space in the buffer
		mov		al, 0														;Move 0 into al to 0 the table 
		call	FillTableWithValue											;Fill the table with zeros

;************************************************************************************************************************************		
;	Count the characters in string																									*
;************************************************************************************************************************************
CountCharacters:

		mov		si, 0														;Move 0 in si to start the table

	GetNextChar:

		mov		al, inString[si]											;Move the next charcter into al
		cmp		al, EOS														;Compare character to EOS
		je		DoneCalc													;If the character being looked at is EOS, jump to done
		and		al, 11011111b												;For the character to uppercase
		sub		al, 'A'														;Subtract an A from al
		cmp		al, 25														;Compare the character to 25
		ja		LoopEnd														;Go to loopend if the character is above 25
		mov		ah, 0														;Add a 00 to ax
		mov		di, ax														;Move the obtained value into di
		inc		ftable[di]													;Increment the value in the table
		
	LoopEnd:

		inc		si															;Increment si for the loop to continue
		jmp		GetNextChar													;Jump to run the loop again
		
		
;************************************************************************************************************************************
;*	Print out table and ask if they want to go again																				*
;************************************************************************************************************************************
DoneCalc:
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET inString											;Move the index to the right location
		call	strWrite													;Print out the user's string
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET printChar										;Move the index to the right location
		call	strWrite													;Print "Character"
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print out spaces
		mov		di, OFFSET printFrq											;Move the index to the right location
		call	strWrite													;Print "Frequency"
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print out spaces
		mov		di, OFFSET printChar										;Move the index to the right location
		call	strWrite													;Print "Character"
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print out spaces
		mov		di, OFFSET printFrq											;Move the index to the right location
		call	strWrite													;Print "Frequency"
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET printDash										;Move the index to the right location
		call	strWrite													;Print out the dashes
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print out spaces
		mov		di, OFFSET printDash										;Move the index to the right location
		call	strWrite													;Print out the dashes
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print out spaces
		mov		di, OFFSET printDash										;Move the index to the right location
		call	strWrite													;Print out the dashes
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print out spaces
		mov		di, OFFSET printDash										;Move the index to the right location
		call	strWrite													;Print out the dashes	
		
		call	newLine														;Add a space for looks
		
		call	PrintTable													;Call subroutine to print	
		
;************************************************************************************************************************************
;*	Ask to Continue																													*
;************************************************************************************************************************************

Continue:

		call	newLine														;Add space to make it look better

		mov		di, OFFSET conStr											;Move the index to the right location
		call	strWrite													;Print question to the screen
		
		mov		di, OFFSET strCont											;Move the index to the right location
		mov		cx, 2														;Make room in the buffer
		call	strRead														;Get the user answer
		
		and		strCont, 11011111b											;Make the answer uppercase
		
		mov		al, strCont													;Move answer into al compare
		
		mov		bl, 'Y'														;Move 'Y' to bl for compare
		cmp		al, bl														;Compare the value in al and bl
		
		jne		Check														;Jump to Done if the values are not equal		
		jmp		GetString													;Jump to Number if al and bl are equal
		
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

		call	newLine														;Add a space for looks
		
		mov		di, OFFSET exitStr											;Move the index to the right location
		call	strWrite													;Write out the goodbye message
		
;************************************************************************************************************************************
;* Program termination code																											*
;************************************************************************************************************************************
		mov 	ah, 04Ch													;DOS function: Exit program
		mov 	al, exCode													;Return exit code value
		int 	21h															;Call DOS. Terminate program	
		
;************************************************************************************************************************************
;*	Subroutines																														*
;************************************************************************************************************************************

FillTableWithValue:

	FTloop:
		mov		[di], al													;move 0 into di
		inc		di															;increment di
		loop	FTloop														;loop through
		ret

PrintTable:

		mov		rowNum, 0													;Make row number 0
		
	ptloop:
		
		mov		ax, rowNum													;Make ax 0
		call	printLetterFreq												;Print the row
		mov		ax, rowNum
		add		ax, 13														;Add 13 to get to next row
		call	printLetterFreq												;Print the row
		call	newLine														;Add space for looks
		inc 	rowNum														;Increment the row number
		cmp		rowNum, 13													;Compare to see if at the end
		jb		ptloop														;Go back to top if not at end
		
		ret																	;Return to program
		
	
PrintLetterFreq:
		
		mov		si, ax														;Move the correct location into si
		add		al, 'A'														;Add a capital A to al
		mov		buffer, al													;Move the value into the buffer
		mov		buffer+1, EOS												;Move an EOS into the buffer
		mov		di, OFFSET buffer											;Move the index to the right location
		call	strWrite													;Print out the next letter
		
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print the spaces
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print the spaces
		
		
		mov		al, ftable[si]												;Move the value in ftable into al
		mov		ah, 0														;Move 0 into ah because we are using ax
		mov		di, OFFSET buffer											;Move the index to the right location
		mov		cx, 1														;Make room in the buffer
		call	BinToAscDec													;Make the numbers printable
		call	strWrite													;Print the number
		
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print the spaces
		mov		di, OFFSET printSpace										;Move the index to the right location
		call	strWrite													;Print the spaces
		
		ret																	;Return to program
		
		END 	Start														;End of program / Entry point
;************************************************************************************************************************************
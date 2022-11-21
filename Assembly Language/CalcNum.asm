TITLE "Calculator"
;****************************************************************************************************************
;*	Title:   Calculator																							*
;*	Author:  Nathan Lalli																						*
;*	Date:    10-9-2020																							*
;*	Purpose: Does simple calculator functions with two numbers													*
;****************************************************************************************************************

		.MODEL small
		STACK  256
		
;****************************************************************************************************************
;*	Equates Section                                                 							                *
;****************************************************************************************************************

EOS				EQU		0    										;End of string
numLen			EQU		7											;Max len of the number

;****************************************************************************************************************
;*	Data Section	    								      													*
;****************************************************************************************************************
		.DATA

exCode	 db		0													;DOS error code

numPrompt1		db		"Enter 1st number: ",EOS					;Ask user for a number
numPrompt2		db		"Enter 2nd number: ",EOS					;Ask the user for another number
opPrompt		db		"Enter wanted operation (+, -, C,): ",EOS 	;Ask user for the operation to use

strNum1			dw		numLen DUP (?)								;User's first number
strNum2			dw		numLen DUP (?)								;User's second number
opWanted		db		2 DUP (?)									;Hold the user operation

askCont			db		"Do another calculation? (Y or N): ",EOS	;Ask user if they want to do it again
strCont			db		2 DUP (?)									;Hold user continue answer

num1			dw		0											;User's first number in value form
num2			dw		0											;User's second number in value form
numAnswer		dw		0											;User's answer in value form
strAnswer		dw		numLen DUP (?)								;User's string answer

strError		db		"Your numbers are too big, try again",EOS	;Error message

ansPrint		db		"The result is: ",EOS						;Print user answer

strEqual		db		"The numbers are equal",EOS					;Numbers are equal print
oneLarger		db		"The first number is larger",EOS			;Number 1 is larger print
twoLarger		db		"The second number is larger",EOS			;Number 2 is larger print

exitPrompt		db		"Thank you for using my program!",EOS		;Ending message

;****************************************************************************************************************
;*	Code Section																								*
;****************************************************************************************************************
		.CODE

;****************************************************************************************************************
;*	External procedures from STRINGS.OBJ & STRIO.OBJ 															*
;****************************************************************************************************************

		EXTRN	StrLength:proc, StrRead:proc
		EXTRN	StrWrite:proc, NewLine:proc
		
;****************************************************************************************************************
;*	External procedures from BINASC.OBJ																			*
;****************************************************************************************************************

		EXTRN BinToAscHex:proc, SBinToAscDec:proc, BinToAscDec:proc
		EXTRN BinTOAscBin:proc, AscToBin:proc
		
;****************************************************************************************************************
;* Main entry point of program 																					*
;****************************************************************************************************************

Start:
		mov		ax, @data											;Initalize DS to address
		mov 	ds, ax												;of data segment
		mov		es, ax												;Make es = ds
		
;****************************************************************************************************************
;*	Get the user's numbers and wanted operation																	*
;****************************************************************************************************************

GetNum:

		call	newLine												;Add space to make it look better
		
		mov		di, OFFSET numPrompt1								;Move the index to the right location
		call	strWrite											;Print 1st user prompt to screen
		
		mov		di, OFFSET strNum1									;Move the index to the right location
		mov		cx, numLen											;Make room in buffer
		call	strRead												;Get the user's first number
		
		call	newLine												;Add space to make it look better
		
		mov		di, OFFSET numPrompt2								;Move the index to the right location
		call	strWrite											;Print the 2nd user prompt to screen
		
		mov		di, OFFSET strNum2									;Move the index to the right location
		mov		cx, numLen											;Make room in the buffer
		call	strRead												;Get the user's 2nd number
		
		call 	newLine												;Add space to make it look better
		
;****************************************************************************************************************
;*	Convert the string values to digits																			*
;****************************************************************************************************************
	
Convert:
		
		mov		di, OFFSET strNum1									;Move the index to the right location
		call	AscToBin											;Convert the string to a number
		mov		num1, ax											;Move the user's first number into num1

		
		mov		di, OFFSET strNum2									;Move the index to the right location
		call	AscToBin											;Convert the string to a number
		mov		num2, ax											;Move the user's second number into num2
		
;****************************************************************************************************************
;*	Get an operation to perform																					*
;****************************************************************************************************************

GetOperation:

		mov		di, OFFSET opPrompt									;Move the index to the right location
		call	strWrite											;Print the prompt to the screen
		
		mov 	di, OFFSET opWanted									;Move the index to the right location
		mov		cx, 2												;Make room in the buffer
		call	strRead												;Read the user's answer
		
		call 	newLine												;Add a space to make it look better

;****************************************************************************************************************
;*	Check Operation																								*
;****************************************************************************************************************

CheckOperation:

		mov		di, OFFSET opWanted									;Move the index to the right location
		
		cmp		opWanted, '+'										;Compare the values
		je		Addition											;If equal go to addition
		
		cmp		opWanted, '-'										;Compare the values
		je		Subtraction											;If equal go to subtraction
		
		and		opWanted, 11011111b									;Make the user value uppercase
		cmp		opWanted, 'C'										;Compare the values
		je		Comparing											;If equal go to comparing
		
		jmp		GetOperation										;If never equal jmp back to GetOperation
		
;****************************************************************************************************************
;*	Do the selected Operation																					*
;****************************************************************************************************************

Addition:
	
		mov		ax, num1											;Move the first number into ax
		add		ax, num2											;Add the second number to answer
		jo		ErrorMessage										;If overflow, jump to error message
		mov		numAnswer, ax										;Move the answer into numAnswer
		jmp		GiveAnswer											;If good answer, jump to answer
		
Subtraction:

		mov		ax, num1											;Move the first number into ax
		sub		ax, num2											;Subtract the second number from answer
		jo		ErrorMessage										;If overflow, jump to error message
		mov		numAnswer, ax										;Move the answer into numAnswer
		jmp		GiveAnswer											;If good answer, jump to answer

Comparing:

		mov		ax, num1											;Move number 1 into ax
		sub		ax, num2											;Subtract number 2 from 1
		
		jo		ErrorMessage										;If overflow, jump to error message
		je		AreEqual											;Numbers are equal jump
		jg		Num1Larger											;1st number larger jump
		jl		Num2Larger											;2nd number larger jump
		
;****************************************************************************************************************
;*	Error Message																								*
;****************************************************************************************************************

ErrorMessage:

		mov		di, OFFSET strError									;Move the index to the right location
		call	strWrite											;Print out the error message to screen
		
		jmp		GetNum												;Get new numbers
		
;****************************************************************************************************************
;*	Numbers are equal																							*
;****************************************************************************************************************

AreEqual:

		call	newLine												;Add space to make it look better
		
		mov		di, OFFSET strEqual									;Move the index to the right location
		call	strWrite											;Print they are equal
		
		jmp		Continue											;Jump to continue

;****************************************************************************************************************
;*	First number is larger																						*
;****************************************************************************************************************

Num1Larger:

		call	newLine												;Add space to make it look better

		mov		di, OFFSET oneLarger								;Move the index to the right location
		call	strWrite											;Print number one is larger
		
		jmp		Continue											;Jump to continue

;****************************************************************************************************************
;*	Second number is larger																						*
;****************************************************************************************************************

Num2Larger:

		call	newLine												;Add space to make it look better

		mov		di, OFFSET twoLarger								;Move the index to the right location
		call	strWrite											;Print number two is larger
		
		jmp		Continue											;Jump to continue

;****************************************************************************************************************
;*	Output the answer																							*
;****************************************************************************************************************

GiveAnswer:

		call	newLine												;Add space to make it look better

		mov		di, OFFSET strAnswer								;Move the index to the right location
		mov		ax, numAnswer										;Move the answer into ax
		mov		cx, 1												;Make room in the buffer
		call	SBinToAscDec										;Convert the user's answer

		mov		di, OFFSET ansPrint									;Move the index to the right location
		call	strWrite											;Print the ansPrint
		
		mov		di, OFFSET strAnswer								;Move the index to the right location
		call	strWrite											;Print the answer to the screen
		
		jmp		Continue											;Jump to continue
		
;****************************************************************************************************************
;*	Ask to Continue																								*
;****************************************************************************************************************

Continue:

		call	newLine												;Add space to make it look better

		mov		di, OFFSET askCont									;Move the index to the right location
		call	strWrite											;Print question to the screen
		
		mov		di, OFFSET strCont									;Move the index to the right location
		mov		cx, 2												;Make room in the buffer
		call	strRead												;Get the user answer
		
		and		strCont, 11011111b									;Make the answer uppercase
		
		mov		al, strCont											;Move answer into al compare
		
		mov		bl, 'Y'												;Move 'Y' to bl for compare
		cmp		al, bl												;Compare the value in al and bl
		
		jne		Check												;Jump to Done if the values are not equal		
		jmp		GetNum												;Jump to Number if al and bl are equal
		
		
;****************************************************************************************************************
;*	Check the answer the user gives																				*
;****************************************************************************************************************

Check:

		mov		bl, 'N'												;Move the character 'N' into bl to compare
		cmp		al, bl												;Compare the value in al and bl
		
		jne		Continue											;If character is not an 'N' jump to Continue
		jmp		Done												;If character is an 'N' jump to Done
	
;****************************************************************************************************************
;*	End of program																								*
;****************************************************************************************************************

Done: 
		call 	NewLine
		call 	NewLine
		
		mov		di, OFFSET exitPrompt								;Move the index to the right location
		call	strWrite											;Print goodbye message to the screen
		
;****************************************************************************************************************
;*	Program termination code																					*
;****************************************************************************************************************
		mov 	ah, 04Ch											;DOS function: Exit program
		mov 	al, exCode											;Return exit code value
		int 	21h													;Call DOS. Terminate program
		
		END 	Start												;End of program / Entry point
;****************************************************************************************************************
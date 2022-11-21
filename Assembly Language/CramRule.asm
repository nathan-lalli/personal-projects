
TITLE "Cramer's Rule"
;************************************************************************************************************************************
;*	Title:	Prime Sieve																												*
;*	Author:	Nathan Lalli																											*
;*	Date:	11-11-2020																												*
;*	Purpose: This program solve's an equation using Cramer's rule																	*
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

variable	dw		0
Dxresult	dw		0
Dyresult	dw		0
Dresult		dw		0														;Result of D
Xresult		dw		0														;Result of X
Yresult		dw		0														;Result of Y
numA		dw		0														;Holds variable A
numB		dw		0														;Holds variable B
numC		dw		0														;Holds variable C
numD		dw		0														;Holds variable D
numE		dw		0														;Holds variable E
numF		dw		0														;Holds variable F
tracker		db		0														;A tracker
d1			dw		0														;Holds number for calculations
d2			dw		0														;Holds number for calculations
d3			dw		0														;Holds number for calculations
d4			dw		0														;Holds number for calculations

buffer		db		42 DUP (?)												;Buffer
inBuffer	db		42 DUP (?)												;Input buffer
strCont		db		2 DUP (?)												;Hold user continue answer
matrix		dw		4 DUP (?)												;Matrix for use during solving

welMessage	db		"The Fantastic Systems of equation solver!",EOS			;Welcome message
entMessage	db		"Please enter the coefficient values: ",EOS				;Enter coefficient message
entA		db		"A = ",EOS												;Show what letter to enter
entB		db		"B = ",EOS												;Show what letter to enter
entC		db		"C = ",EOS												;Show what letter to enter
entD		db		"D = ",EOS												;Show what letter to enter
entE		db		"E = ",EOS												;Show what letter to enter
entF		db		"F = ",EOS												;Show what letter to enter
solutionX	db		"X = ",EOS												;Show solution to X
solutionY	db		"Y = ",EOS												;Show solution to Y
noSolution	db		"There is no single solution. ",EOS						;Message to show no single solution
conStr		db		"Would you like to do another equation? (Y/N) ",EOS		;Continue message
goodbye		db		"Goodbye, user.",EOS									;Goodbye message

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
;*	Welcome the user																												*
;************************************************************************************************************************************

WelcomeUser:

		call	newLine														;Add space for looks

		mov		di, OFFSET welMessage										;Move the index to the right location
		call	strWrite													;Print out the welcome message

;************************************************************************************************************************************
;*	Get the coefficients																											*
;************************************************************************************************************************************

GetCoefficient:

		call	newLine														;Add a space for looks
		
		mov		di, OFFSET entMessage										;Move the index to the right location
		call	strWrite													;Print the enter message to the screen
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET entA												;Move the index to the right location
		call	strWrite													;Print the message to the screen
		
		mov		di, OFFSET inBuffer											;Move the index to the right location
		mov		cx, 4
		call	strRead														;Read in the user's number
		
		call	AscToBin													;Convert the string to a number
		mov		numA, ax													;Move the user's coefficient into A
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET entB												;Move the index to the right location
		call	strWrite													;Print the message to the screen

		mov		di, OFFSET inBuffer											;Move the index to the right location
		mov		cx, 4
		call	strRead														;Read in the user's number
		
		call	AscToBin													;Convert the string to a number
		mov		numB, ax													;Move the user's coefficient into B
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET entC												;Move the index to the right location
		call	strWrite													;Print the message to the screen
		
		mov		di, OFFSET inBuffer											;Move the index to the right location
		mov		cx, 4
		call	strRead														;Read in the user's number
		
		call	AscToBin													;Convert the string to a number
		mov		numC, ax													;Move the user's coefficient into C
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET entD												;Move the index to the right location
		call	strWrite													;Print the message to the screen
		
		mov		di, OFFSET inBuffer											;Move the index to the right location
		mov		cx, 4
		call	strRead														;Read in the user's number
		
		call	AscToBin													;Convert the string to a number
		mov		numD, ax													;Move the user's coefficient into D		
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET entE												;Move the index to the right location
		call	strWrite													;Print the message to the screen

		mov		di, OFFSET inBuffer											;Move the index to the right location
		mov		cx, 4
		call	strRead														;Read in the user's number
		
		call	AscToBin													;Convert the string to a number
		mov		numE, ax													;Move the user's coefficient into E
		
		call	newLine														;Add a space for looks

		mov		di, OFFSET entF												;Move the index to the right location
		call	strWrite													;Print the message to the screen
		
		mov		di, OFFSET inBuffer											;Move the index to the right location
		mov		cx, 4
		call	strRead														;Read in the user's number
		
		call	AscToBin													;Convert the string to a number
		mov		numF, ax													;Move the user's coefficient into F
		
		call	newLine														;Add a space for looks
		
;************************************************************************************************************************************
;*	Get the D equation																												*
;************************************************************************************************************************************

PutIntoMatrixD:

		mov		si, 0
		mov		ax, 0

		mov		ax, numA
		mov		matrix, ax
		mov		ax, numB
		;inc		si
		mov		2[matrix], ax
		mov		ax, numD
		;inc		si
		mov		4[matrix], ax
		mov		ax, numE
		;inc		si
		mov		6[matrix], ax
		
		mov		si, OFFSET matrix
		
		call	Determinant
		
		mov		ax, variable
		mov		Dresult, ax
		
		;mov		di, OFFSET buffer
		;mov		cx, 10
		;call	SBinToAscDec
		
		cmp		Dresult, 0 
		jne		PutIntoMatrixDx
		jmp		NoSolutionForTheEquation
		
;************************************************************************************************************************************
;*	Get the Dx equation																												*
;************************************************************************************************************************************		

PutIntoMatrixDx:

		mov		si, 0

		mov		ax, numC
		mov		matrix, ax
		mov		ax, numB
		;inc		si
		mov		2[matrix], ax
		mov		ax, numF
		;inc		si
		mov		4[matrix], ax
		mov		ax, numE
		;inc		si
		mov		6[matrix], ax
		
		mov		si, OFFSET matrix
		
		call	Determinant
		
		mov		ax, variable
		mov		Dxresult, ax
		
;************************************************************************************************************************************
;*	Get the Dy equation																												*
;************************************************************************************************************************************

PutIntoMatrixDy:
	
		mov		si, 0
	
		mov		ax, numA
		mov		matrix, ax
		mov		ax, numC
		;inc		si
		mov		2[matrix], ax
		mov		ax, numD
		;inc		si
		mov		4[matrix], ax
		mov		ax, numF
		;inc		si
		mov		6[matrix], ax
		
		mov		si, OFFSET matrix
		
		call	Determinant
		
		mov		ax, variable
		mov		Dyresult, ax

;************************************************************************************************************************************
;*	Calculate the answer to the equations																							*
;************************************************************************************************************************************

Calculation:

		mov		dx, 0														;Clear dx
		
		mov		ax, Dxresult												;Move the result of Dx into ax
		cwd																	;Convert to double
		mov		bx, Dresult													;Move the result of D into bx
		cwd																	;Convert to double
		idiv	bx															;Divide Dx by D
		mov		Xresult, ax													;Move the result into Xresult
		
		mov		dx, 0														;Clear dx
		
		mov		ax, Dyresult												;Move the result of Dy into ax
		cwd																	;Convert to double
		mov		bx, Dresult													;Move the result of D into bx
		cwd																	;Convert to double
		idiv	bx
		mov		Yresult, ax													;Move the result into Yresult
		
;************************************************************************************************************************************
;*	Display the result of the equation																								*
;************************************************************************************************************************************

DisplayAnswer:

		call	newLine														;Add a space for looks

		mov		di, OFFSET solutionX										;Move the index to the right location
		call	strWrite													;Print the X= to screen
		
		mov		ax, Xresult													;Move the X result into ax
		mov		di, OFFSET buffer											;Move the index to the right location
		mov		cx, 1														;Make room in the buffer
		call	SBinToAscDec													;Convert to a string
		call	strWrite													;Print the answer
		
		call newLine														;Add a space for looks
		
		mov		di, OFFSET solutionY										;Move the index to the right location
		call	strWrite													;Print the Y= to screen
		
		mov		ax, Yresult													;Move the X result into ax
		mov		di, OFFSET buffer											;Move the index to the right location
		mov		cx, 1														;Make room in the buffer
		call	SBinToAscDec													;Convert to a string
		call	strWrite													;Print the answer

		call	newLine														;Add a space for looks
		
		jmp		Continue													;Go to continue section

;************************************************************************************************************************************
;*	If there is no solution, go here																								*
;************************************************************************************************************************************

NoSolutionForTheEquation:

		mov		di, OFFSET noSolution										;Move the index to the right location
		call	strWrite													;Print the no solution message

		jmp		Continue													;Ask if they want to do another one

;************************************************************************************************************************************
;*	Ask to Continue																													*
;************************************************************************************************************************************

Continue:
		
		call	newLine														;Add a space for looks

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
		jmp		GetCoefficient												;Jump to Number if al and bl are equal
		
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
		
		mov		di, OFFSET goodbye											;Move the index to the right location
		call	strWrite													;Print out the goodbye message
		
;************************************************************************************************************************************
;* Program termination code																											*
;************************************************************************************************************************************
		
		mov 	ah, 04Ch													;DOS function: Exit program
		mov 	al, exCode													;Return exit code value
		int 	21h															;Call DOS. Terminate program
		
;************************************************************************************************************************************
;*	Subroutines																														*
;************************************************************************************************************************************

Determinant:
		
		mov		ax, 0														;Clear ax
		mov		bx, 0														;Clear bx
		
		mov		bx, [si]
		mov		d1, bx														;Move the coefficient into variable
		;inc		si
		mov		bx, 4[si]
		mov		d2,	bx														;Move the coefficient into variable
		;inc		si
		mov		bx, 2[si]
		mov		d3,	bx														;Move the coefficient into variable
		;inc		si
		mov		bx, 6[si]
		mov		d4,	bx														;Move the coefficient into variable
		
		mov		ax, d1														;Move variable into ax
		imul	d4															;Multiply the number with the correct coefficient
		mov		variable, ax												;Move the result into variable
		
		mov		ax, d2														;Move the variable into ax
		imul	d3															;Multiply the number with the correct coefficient
		sub		variable, ax												;Subtract the result from the frist result
		
		ret
		
		END 	Start														;End of program / Entry point
;************************************************************************************************************************************
TITLE "Prime Sieve"
;************************************************************************************************************************************
;*	Title:	Prime Sieve																												*
;*	Author:	Nathan Lalli																											*
;*	Date:	10-26-2020																												*
;*	Purpose: This program creates a prime sieve to see all prime numbers															*
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
stopValue	dw		0														;Stopping value from user
track		dw		0														;Tracks the location that you are at in the table
trackLine	dw		0														;Tracks what line you are
prevValue	dw		0														;The previous value
trackPair	dw		0														;Tracks the prime pairs
trackPrime	dw		0														;Tracks the primes

buffer		db		42 DUP (?)												;Buffer for converting
strCont		db		2 DUP (?)												;Hold user continue answer
valToPrint	db		6 DUP (?)												;Hold the value that will be printed
stopValStr	db		12 DUP (?)												;String stopping value form user
sieveTable	db		10000 DUP (?)											;Table for the sieve

welcome		db		"Welcome to the prime sieve",EOS						;Welcome message
goodbye		db		"Goodbye, user.",EOS									;Goodbye message
entPrompt	db		"Enter the stopping value (2-10000): ",EOS				;Prompt user to enter stopping value
prompt2		db		"Here are all of the prime numbers from 2 to ",EOS		;Prompt about the numbers from 2 to the user's value
colon		db		": ",EOS												;Able to print out a colon
space		db		" ",EOS													;Able to print out a space
conStr		db		"Do you wish to do another number? (Y/N)",EOS			;Ask user if they want to do it again
asterisk	db		"*",EOS													;Able to print an asterisk
printPair	db		"Prime Pairs: ",EOS										;Print how many prime pairs there were
printPrime	db		"Primes: ",EOS											;Print how many primes there were
strError	db		"Number needs to be between 2-10000. Try again.",EOS	;Error message

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
;*	Fill the table																													*
;************************************************************************************************************************************
		
FillSieve:
		
		mov		di, OFFSET sieveTable										;Move the index to the right location
		mov		cx, 10000													;Make room in the buffer
		mov		al, 0000													;Move the right number into al
		mov		trackPair, 0												;Make the prime pair tracker 0
		mov		trackPrime, 0												;Make the prime tracker 0
		
		call	FillTableWithValue											;Call subroutine
		
		mov		sieveTable, 1												;Move 1 into the first and second spot in the table
		mov		sieveTable+1, 1												;because these numbers are Prime
		
;************************************************************************************************************************************
;*	Get user's value to stop the table at																							*
;************************************************************************************************************************************

GetUserValue:
		
		call	newLine														;Add space for looks
		
		mov		di, OFFSET entPrompt										;Move the index to the right location
		call	strWrite													;Write the question for the user to the screen
		
		mov		di, OFFSET stopValStr										;Move the index to the right location
		mov		cx, 6														;Make room in the buffer
		call	strRead														;Read in the user's stop value
		
		call	AscToBin													;Make the user's string an actual number
		mov		stopValue, ax												;Move the user's number stop value into stopValue
		
		cmp		stopValue, 10000											;Compare the user's entered value to 10000
		ja		BadSize														;If the value is too big jump to BadSize
		
		cmp		stopValue, 2												;Compare the user's entered value to 2
		jb		BadSize														;If the value is too small jump to BadSize
		jmp		DisplayUserVal												;Otherwise continue on
		
;************************************************************************************************************************************
;*	Check Number size																												*
;************************************************************************************************************************************

BadSize:

		call	newLine														;Add a space for looks
		
		mov		di, OFFSET strError											;Move the index to the right location
		call	strWrite													;Write the Error message to screen
		
		jmp		FillSieve													;Jump back to the top of the program
		
;************************************************************************************************************************************
;*	Display the user's value																										*
;************************************************************************************************************************************

DisplayUserVal:

		call	newLine														;Add space for looks
		
		mov		di, OFFSET prompt2											;Move the index to the right location
		call	strWrite													;Write the prompt
		
		mov		di, OFFSET stopValStr										;Move the index to the right location
		call	strWrite													;Print out the user's stopping value
		
		mov		di, OFFSET colon											;Move the index to the right location
		call	strWrite													;Print a colon after the user's number
		
		call	newLine														;Add space for looks
		
;************************************************************************************************************************************
;*	Goes through the sieve and converts the table to the correct values																*
;************************************************************************************************************************************		
		
PrimeTime:
	
		mov		trackLine, 0												;Set the line tracker to 0
		mov		track, 0													;Set the tracker to 0
		mov		prevValue, 5												;Move 5 into the previous value
		mov		si, track													;Start at the beginning of the table
		
	LoopThroughTable:
		
		cmp		si, stopValue												;Compare si to the user's given stop value
		jbe		BreakPoint													;If si is be or equal to the user value, keep going
		jmp		Continue													;If si is above, jump to ask if they want to go again	

BreakPoint:

		cmp		sieveTable[si], 0											;Compare the next value to a zero
		je		PairCheck													;If it is a zero print that number
	
		inc		si															;Increment si to keep going through the table
		jmp		LoopThroughTable											;Jump to the top to check the next value
		
	PairCheck:
		
		mov		ax,	si														;Move si into ax
		sub		ax, prevValue												;Subtract the previous value from ax 
		
		cmp		ax, 2														;Compare ax to 2
		jbe		PrintAsterisk												;If below or equal jump to PrintAsterisk
		jne		LineNumber2													;If not equal jump to LineNumber2
		
	PrintAsterisk:
		
		inc		trackPair													;Increment the pair tracker
		mov		di, OFFSET asterisk											;Move the index to the right location
		call	strWrite													;Print an asterisk
		
	LineNumber:

		cmp		trackLine, 15												;Compare the line tracker to 15
		jb		PrintTable													;If below jump to PrintTable
		mov		trackLine, 0												;If not below set the line tracker to 0
		
		call	newLine														;Add a space for looks
	
	PrintTable:
		
		inc		trackPrime													;Increment the prime tracker
		inc		trackLine													;Increment the line tracker
		mov		prevValue, si												;Move si into prevValue for next pair calculation
		mov		track, si													;Move si into tracker
		mov		ax, si														;Move si into ax for converting
		
		mov		di, OFFSET valToPrint										;Move the index to the right location
		mov		cx, 4														;Make room in the buffer
		call	BinToAscDec													;Convert to a string

		mov		di, 0														;Set di to 0
		
CompareZeros:
		
		cmp		valToPrint[di], "0"											;Compare character in leadZeros to 0
		je		ChangeZeros													;If equal jump to ChangeZeros
		jmp		DontChangeZeros												;If not equal jump tp DontChangeZeros
		
ChangeZeros:
		
		mov		valToPrint[di], " "											;Change character in leadZeros to a space
		inc		di															;Increment the pointer
		jmp		CompareZeros												;Jump bcak to compare again
		
DontChangeZeros:
		
		mov		di, OFFSET valToPrint										;Move the index to the right location
		call	StrWrite													;Print out the number
		
		mov		si, track													;Restore si
		
	Make1or0:
	
		mov		sieveTable[si], 1											;Make the number in the table a 1 since its prime
		add		si, track													;Add the values to get the next number
		
		cmp		si, stopValue												;Compare the index to the stopping value
		jbe		Make1or0													;If it is smaller, loop back to Make1or0
		
		mov		si, track													;Restore si
		jmp		LoopThroughTable											;Continue loop through table

	LineNumber2:

		cmp		trackLine, 15												;Compare the line tracker to 15
		jb		PrintTable2													;If below jump to PrintTable
		mov		trackLine, 0												;If not below set the line tracker to 0
		
		call	newLine														;Add a space for looks
	
	PrintTable2:
		
		inc		trackPrime													;Increment the prime tracker
		inc		trackLine													;Increment the line tracker
		mov		prevValue, si												;Move si into prevValue for next pair calculation
		mov		track, si													;Move si into tracker
		mov		ax, si														;Move si into ax for converting
		
		mov		di, OFFSET space											;Move the index to the right location
		call	StrWrite													;Print out a space
		
		mov		di, OFFSET valToPrint										;Move the index to the right location
		mov		cx, 4														;Make room in the buffer
		call	BinToAscDec													;Convert to a string
		
		mov		di, 0														;Set di to 0
		
CompareZeros2:

		cmp		valToPrint[di], "0"											;Compare character in leadZeros to 0
		je		ChangeZeros2												;If equal jump to ChangeZeros
		jmp		DontChangeZeros2											;If not equal jump tp DontChangeZeros
		
ChangeZeros2:
		
		mov		valToPrint[di], " "											;Change character in leadZeros to a space
		inc		di															;Increment the pointer
		jmp		CompareZeros2												;Jump back to compare again
		
DontChangeZeros2:
		
		mov		di, OFFSET valToPrint										;Move the index to the right location
		call	StrWrite													;Print out the number
		
		mov		si, track													;Restore si

	Make1or02:
	
		mov		sieveTable[si], 1											;Make the number in the table a 1 since its prime
		add		si, track													;Add the values to get the next number
		
		cmp		si, stopValue												;Compare the index to the stopping value
		jbe		Make1or02													;If it is smaller, loop back to Make1or0
		
		mov		si, track													;Restore si
		jmp		LoopThroughTable											;Continue loop through table

;************************************************************************************************************************************
;*	Ask to Continue																													*
;************************************************************************************************************************************

Continue:
		
		call	newLine														;Add a space for looks
		
		mov		di, OFFSET printPrime										;Move the index to the right location
		call	strWrite													;Print the prompt to the screen
		
		mov		ax, trackPrime												;Move the tracker into ax
		mov		di, OFFSET buffer											;Move the index to the right location
		mov		cx, 1														;Make room in the buffer
		call	BinToAscDec													;Convert the number to a string
		call	strWrite													;Print the number to the screen
		
		call	newLine														;Add space for looks
		
		mov		di, OFFSET printPair										;Move the index to the right location
		call	strWrite													;Print the prompt to the screen
		
		mov		ax, trackPair												;Move the tracker into ax
		mov		di, OFFSET buffer											;Move the index to the right location
		mov		cx, 1														;Make room in the buffer
		call	BinToAscDec													;Convert the number to a string
		call	strWrite													;Print the number to the screen

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
		jmp		FillSieve													;Jump to Number if al and bl are equal
		
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

FillTableWithValue:

	FTloop:
		mov		[di], al													;move 0 into di
		inc		di															;increment di
		loop	FTloop														;loop through
		ret
		
		END 	Start														;End of program / Entry point
;************************************************************************************************************************************
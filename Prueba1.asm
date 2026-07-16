.model small
.stack
.data
    MENSAJE DB 'HOLA MAESTRA'

.code

    mov ax,@data
    mov ds,ax
    mov es,ax

    MOV SI,0
    MOV CX,12

    MOV DX,2040H

LABEL01:
    MOV AL,MENSAJE[SI]
    OUT DX,AL
    INC SI
    INC DX
    LOOP LABEL01

    MOV AX,4C00H
    INT 21H

end
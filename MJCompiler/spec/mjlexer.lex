package rs.ac.bg.etf.pp1;
import java_cup.runtime.Symbol;

%%

%{
	
	private Symbol new_symbol(int type){
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	private Symbol new_symbol(int type, Object value){
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}


%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"	{ return new_symbol(sym.PROG, yytext());}
"break"		{ return new_symbol(sym.BREAK, yytext());}
"class"		{ return new_symbol(sym.CLASS, yytext());}
"else"		{ return new_symbol(sym.ELSE, yytext());}
"const"     { return new_symbol(sym.CONST, yytext());}
"if"		{ return new_symbol(sym.IF, yytext());}
"while"		{ return new_symbol(sym.WHILE, yytext());}
"new"		{ return new_symbol(sym.NEW, yytext());}
"print"		{ return new_symbol(sym.PRINT, yytext());}
"read"		{ return new_symbol(sym.READ, yytext());}
"return"		{ return new_symbol(sym.RETURN, yytext());}
"void"		{ return new_symbol(sym.VOID, yytext());}
"extends"		{ return new_symbol(sym.EXTENDS, yytext());}
"continue"		{ return new_symbol(sym.CONTINUE, yytext());}
"map"		{ return new_symbol(sym.MAP, yytext());}

"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" {yybegin(YYINITIAL);}

[0-9]+ 							{ return new_symbol(sym.NUMBER, Integer.valueOf(yytext()));}
"true"					{ return new_symbol(sym.BOOL, true);}
"false"					{ return new_symbol(sym.BOOL, false);}
'.'		 	{ return new_symbol(sym.CHAR, yytext().charAt(1)); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{ return new_symbol(sym.IDENT, yytext());}

"+"			{ return new_symbol(sym.PLUS, yytext());}
"-"			{ return new_symbol(sym.MINUS, yytext());}
"*"			{ return new_symbol(sym.ASTERISK, yytext());}
"/"			{ return new_symbol(sym.SLASH, yytext());}
"%"			{ return new_symbol(sym.PERCENT, yytext());}
"=="		{ return new_symbol(sym.IS_EQUAL, yytext());}
"!="		{ return new_symbol(sym.NOT_EQUAL, yytext());}
">"			{ return new_symbol(sym.GREATER, yytext());}
">="		{ return new_symbol(sym.GREATER_EQUAL, yytext());}
"<"			{ return new_symbol(sym.LESS, yytext());}
"<="		{ return new_symbol(sym.LESS_EQUAL, yytext());}
"&&"		{ return new_symbol(sym.AND, yytext());}
"||"		{ return new_symbol(sym.OR, yytext());}
"="			{ return new_symbol(sym.EQUAL, yytext());}
"++"		{ return new_symbol(sym.INCREMENT, yytext());}
"--"		{ return new_symbol(sym.DECREMENT, yytext());}
";"			{ return new_symbol(sym.SEMICOLON, yytext());}
":"			{ return new_symbol(sym.COLON, yytext());}
","			{ return new_symbol(sym.COMMA, yytext());}
"."			{ return new_symbol(sym.DOT, yytext());} 
"("			{ return new_symbol(sym.LPAREN, yytext());}
")"			{ return new_symbol(sym.RPAREN, yytext());}
"["			{ return new_symbol(sym.LSQUARE, yytext());}
"]"			{ return new_symbol(sym.RSQUARE, yytext());}
"{"			{ return new_symbol(sym.LCURLY, yytext());}
"}"			{ return new_symbol(sym.RCURLY, yytext());}
"=>"		{ return new_symbol(sym.ASSIGN, yytext());}





. { System.err.println("Leksicka greska ("+ yytext() + ")");}






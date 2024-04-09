grammar ExprWithIf;

prog:   statlista EOF             #vege
    ;

statlista: stat(stat)*
    ;

stat:   expr NEWLINE            #kiiras
    |   ID '=' expr NEWLINE     #ertekadas
    |   'IF' logexpr 'THEN' stat  #ifthen
    |   'WHILE' logexpr 'DO' NEWLINE? statlista 'END' NEWLINE #whiledo
    ;

expr:  <assoc=right> expr jel='^' expr  #hatvanyozas
    |   expr jel=('*'|'/') expr         #szorzasosztas
    |   expr jel=('+'|'-') expr         #osszeadaskivonas
    |   expr jel='MOD' expr             #modulo
    |   INT                             #szam
    |   ID                              #azonosto
    |   '(' expr ')'                    #zaroljelek
    ;

logexpr:  '(' expr jel='>' expr ')'   #nagyobbmint
    |     '(' expr jel='<' expr ')'   #kisebbmint
    |     '(' expr jel='==' expr ')'  #egyenlo
    |     INT                     #logikaiszam
    ;


ID  :   [a-zA-Z]+ ;      // match identifiers <label id="code.tour.expr.3"/>
INT :   [0-9]+ ;         // match integers
NEWLINE:'\r'? '\n';     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace

MUL : '*' ; // assigns token name to '*' used above in grammar
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
EXP : '^' ;
MOD : 'MOD' ;
GT : '>' ;
LT : '<' ;
EQ : '==' ;
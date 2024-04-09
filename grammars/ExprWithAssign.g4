grammar ExprWithAssign;
/** The start rule; begin parsing here. */
prog:   stat+ EOF              #vege
    ;

stat:   expr NEWLINE            #kiiras
    |   ID '=' expr NEWLINE     #ertekadas
    |   NEWLINE                 #uressor
    ;

expr:  <assoc=right> expr jel='^' expr  #hatvanyozas
    |   expr jel=('*'|'/') expr         #szorzasosztas
    |   expr jel=('+'|'-') expr         #osszeadaskivonas
    |   INT                         #szam
    |   ID                          #azonosto
    |   '(' expr ')'                #zaroljelek
    ;

ID  :   [a-zA-Z]+ ;      // match identifiers <label id="code.tour.expr.3"/>
INT :   [0-9]+ ;         // match integers
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace

MUL : '*' ; // assigns token name to '*' used above in grammar
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
EXP : '^' ;

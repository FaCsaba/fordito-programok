grammar LogExpr;
/** The start rule; begin parsing here. */
prog:   stat+ EOF ;

stat:   expr NEWLINE # print
    |   ID '=' expr NEWLINE # assign
    |   NEWLINE # newline
    ;

expr:  NEG expr # neg
    |   <assoc=right> expr AND expr # and
    |   <assoc=right> expr OR expr # or
    |   <assoc=right> expr IMP expr # imp
    |   ID # id
    |   BOOL # bool
    |   '(' expr ')' # paren
    ;

ID  :   [A-Z]+ ;      // match identifiers <label id="code.tour.expr.3"/>
BOOL:   TRU | FLS ;         // match integers
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace

TRU: '1';
FLS: '0';
NEG: 'nem';
AND: 'es';
OR: 'vagy';
IMP: '->';
package com.csabapro; /***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/


import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.ParseTree;

import com.csabapro.gen.ExprLexer;
import com.csabapro.gen.ExprParser;

public class Calc {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("t.expr");

        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        System.out.println(tokens.getNumberOfOnChannelTokens());
        List<Token> alltokens = tokens.getTokens();
        alltokens.stream().forEach(System.out::println);

        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.s();
        
        System.out.println(tree.toStringTree(parser));
        
        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(new CsucsSorolo(), tree);
    }
}
